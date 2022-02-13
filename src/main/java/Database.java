import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection dbConnectionInit(String path) throws SQLException {
		Connection conn = DriverManager.getConnection(jdbcUrl(path));
		tableInit(conn);
		return conn;
	}

	private static void tableInit(Connection conn) throws SQLException {
		String query = "CREATE table IF NOT EXISTS History (" +
				"key INTEGER NOT NULL PRIMARY KEY, " +
				"commitId text, " +
				"description txt" +
				"branch txt" +
				"commitDate Date" +
				")";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

	private static String jdbcUrl(String path) {
		return "jdbc:sqlite:" + path;
	}
}
