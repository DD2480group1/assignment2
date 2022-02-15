import javax.management.Query;
import java.net.ConnectException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.Condition;

public class Database {
	private static Connection dbConnectionInit(String repo) throws SQLException {
		Connection conn = DriverManager.getConnection(jdbcUrl(repo));
		tableInit(conn);
		return conn;
	}

	private static void tableInit(Connection conn) throws SQLException {
		String query = "CREATE table IF NOT EXISTS History (" +
				"key INTEGER NOT NULL PRIMARY KEY, " +
				"commitId text, " +
				"branch txt," +
				"timeStamp TIMESTAMP," +
				"description txt" +
				")";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

	private static String jdbcUrl(String path) {
		return "jdbc:sqlite:db/" + path + ".db";
	}


	private static PreparedStatement getInsertPrepStmt(Connection conn, tableEntry entry) throws SQLException {
		PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO History (commitId, branch, timeStamp, description) VALUES(?, ?, ?, ?)");
		prepStmt.setString(1, entry.commitId);
		prepStmt.setString(2, entry.branch);
		prepStmt.setTimestamp(3, entry.timeStamp);
		prepStmt.setString(4, entry.description);
		return prepStmt;
	}

	public static boolean addCommit(String repo, tableEntry entry) {
		try {
			Connection conn = dbConnectionInit(repo);
			PreparedStatement prepStmt = getInsertPrepStmt(conn, entry);
			prepStmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Optional<tableEntry> getRow(String repo, String commitId) {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl(repo));
			PreparedStatement query = conn.prepareStatement("SELECT * FROM History WHERE commitId = ?");
			query.setString(1, commitId);
			ResultSet rs = query.executeQuery();

			if (!rs.next()) {
				return Optional.empty();
			}

			return Optional.of(getTableEntry(rs));
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

	private static tableEntry getTableEntry(ResultSet rs) throws SQLException {

		String commitId = rs.getString("commitId");
		String branch = rs.getString("branch");
		Timestamp timeStamp = rs.getTimestamp("timeStamp");
		String description = rs.getString("description");

		return new tableEntry(commitId, branch, timeStamp, description);
	}

	public static List<tableEntry> getRows(String repo) {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl(repo));
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM History");
			ArrayList<tableEntry> rows = new ArrayList<>();
			while (rs.next()) {
				rows.add(getTableEntry(rs));
			}
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static List<tableEntry> getRows(String repo, QueryParams params) {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl(repo));
			PreparedStatement query = prepareQuery(conn, params);
			ResultSet rs = query.executeQuery();

			ArrayList<tableEntry> rows = new ArrayList<>();
			while (rs.next()) {
				rows.add(getTableEntry(rs));
			}
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private static PreparedStatement prepareQuery(Connection conn, QueryParams params) throws SQLException {
		String query = "SELECT * FROM History" + params.getQueryString();
		PreparedStatement prepStmt = conn.prepareStatement(query);
		int idx = 1;
		if (params.commitId.isPresent()) {
			prepStmt.setString(idx, params.commitId.get());
			idx++;
		}
		if (params.branch.isPresent()) {
			prepStmt.setString(idx, params.branch.get());
			idx++;
		}
		if (params.before.isPresent()) {
			prepStmt.setTimestamp(idx, params.before.get());
			idx++;
		}

		if (params.after.isPresent()) {
			prepStmt.setTimestamp(idx, params.after.get());
			idx++;
		}

		return prepStmt;
	}
}