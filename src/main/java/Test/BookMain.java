package Test;

import java.sql.Connection;

import View.BookUI;
import connectDB.DBConnect;

public class BookMain {

	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		Connection conn = db.getConn();
		BookUI b = new BookUI(conn);
	}
}
