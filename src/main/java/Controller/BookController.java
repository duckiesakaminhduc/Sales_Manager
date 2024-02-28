package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;

import View.BookUI;
import View.FindBookByBookName;
import connectDB.DBConnect;

public class BookController implements ActionListener {
	BookUI bookUI;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	FindBookByBookName f;

	public BookController(BookUI bookUI) {
		this.bookUI = bookUI;
		this.conn = bookUI.getConn();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			try {
				String sql = "insert into [book](bookId,bookName,author,price,bookCategory,status)" + "Values ('"
						+ bookUI.getTfBookId().getText() + "','" + bookUI.getTfBookName().getText() + "','"
						+ bookUI.getTfAuthor().getText() + "','" + bookUI.getTfPrice().getText() + "','"
						+ bookUI.getTfCategory().getText() + "','" + bookUI.getTfStatus().getText() + "')";
				ps = conn.prepareStatement(sql);

				rs = ps.executeQuery();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());

			}
		}

		if (e.getActionCommand() == "Find") {
			f = new FindBookByBookName(bookUI);
			
		}


	}

}
