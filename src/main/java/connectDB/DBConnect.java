package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static Connection conn;

	public static Connection getConn() {
 
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLKH";
			String username = "sa";
			String password = "minh123";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("ket noi ok");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}

		return conn;
	};
	
	public static void main(String[] args) {
		System.out.println("Ket noi thanh cong");;
	}
}
