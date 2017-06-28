package meetingmanager.util;

import java.sql.*;

public class DataBaseConnection {
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/meetingmgr?useUnicode=true&characterEncoding=utf8",
							"root", "123456");
			System.out.println("Connection Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DataBaseConnection.getConnection();
	}
}
