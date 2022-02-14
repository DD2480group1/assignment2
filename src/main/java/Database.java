import java.sql.*;

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
				"commitDate TIMESTAMP," +
				"description txt" +
				")";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

	private static String jdbcUrl(String path) {
		return "jdbc:sqlite:db/" + path + ".db";
	}


	private static PreparedStatement getInsertPrepStmt(Connection conn, tableEntry entry) throws SQLException {
		PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO History (commitId, branch, commitDate, description) VALUES(?, ?, ?, ?)");
		prepStmt.setString(1, entry.commitId);
		prepStmt.setString(2, entry.branch);
		prepStmt.setString(3, entry.timeStamp);
		prepStmt.setString(4, entry.description);
		return prepStmt;
	}

	static boolean addCommit(String repo, tableEntry entry) {
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
}
