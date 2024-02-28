package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.concurrent.Flow;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.BookController;
import connectDB.DBConnect;

public class BookUI extends JFrame {

	DefaultTableModel dtbBook;
	JTable tbBook;
	PreparedStatement ps;
	ResultSet rs;
	Connection conn;
	JButton btnSave, btnFind, btnDelete;
	BookController bookController;
	JTextField tfBookId, tfBookName, tfAuthor, tfCategory, tfPrice, tfStatus;

	/**
	 * @return the tfBookId
	 */
	public JTextField getTfBookId() {
		return tfBookId;
	}

	/**
	 * @param tfBookId the tfBookId to set
	 */
	public void setTfBookId(JTextField tfBookId) {
		this.tfBookId = tfBookId;
	}

	/**
	 * @return the tfBookName
	 */
	public JTextField getTfBookName() {
		return tfBookName;
	}

	/**
	 * @param tfBookName the tfBookName to set
	 */
	public void setTfBookName(JTextField tfBookName) {
		this.tfBookName = tfBookName;
	}

	/**
	 * @return the tfAuthor
	 */
	public JTextField getTfAuthor() {
		return tfAuthor;
	}

	/**
	 * @param tfAuthor the tfAuthor to set
	 */
	public void setTfAuthor(JTextField tfAuthor) {
		this.tfAuthor = tfAuthor;
	}

	/**
	 * @return the tfCategory
	 */
	public JTextField getTfCategory() {
		return tfCategory;
	}

	/**
	 * @param tfCategory the tfCategory to set
	 */
	public void setTfCategory(JTextField tfCategory) {
		this.tfCategory = tfCategory;
	}

	/**
	 * @return the tfPrice
	 */
	public JTextField getTfPrice() {
		return tfPrice;
	}

	/**
	 * @param tfPrice the tfPrice to set
	 */
	public void setTfPrice(JTextField tfPrice) {
		this.tfPrice = tfPrice;
	}

	/**
	 * @return the tfStatus
	 */
	public JTextField getTfStatus() {
		return tfStatus;
	}

	/**
	 * @param tfStatus the tfStatus to set
	 */
	public void setTfStatus(JTextField tfStatus) {
		this.tfStatus = tfStatus;
	}

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public BookUI(Connection conn) {
		super("Book Store");
		this.conn = conn;
		this.bookController = new BookController(this);

		addControl();
		addEvent();
		init();
		showBook();
	}

	private void addControl() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		dtbBook = new DefaultTableModel();
		dtbBook.addColumn("Book Id");
		dtbBook.addColumn("Book Name");
		dtbBook.addColumn("Author");
		dtbBook.addColumn("Price");
		dtbBook.addColumn("Category");
		dtbBook.addColumn("Status");
		JTable tblBook = new JTable(dtbBook);
		JScrollPane sc = new JScrollPane(tblBook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMain.add(sc, BorderLayout.CENTER);
		con.add(pnMain, BorderLayout.CENTER);
//---------------------------------------------------------------

		JPanel inputBook = new JPanel();
		inputBook.setLayout(new GridLayout(1, 2));
		con.add(inputBook, BorderLayout.SOUTH);

		JPanel inputBook2 = new JPanel();
		JPanel inputBook1 = new JPanel();
		inputBook1.setLayout(new GridLayout(6, 2));

		JLabel lbBookId = new JLabel("Book ID");
		tfBookId = new JTextField(20);
		inputBook1.add(lbBookId);
		inputBook1.add(tfBookId);

		JLabel lbBookName = new JLabel("Book Name");
		tfBookName = new JTextField(20);
		inputBook1.add(lbBookName);
		inputBook1.add(tfBookName);

		JLabel lbAuthor = new JLabel("Author");
		tfAuthor = new JTextField(20);
		inputBook1.add(lbAuthor);
		inputBook1.add(tfAuthor);

		JLabel lbPrice = new JLabel("Price");
		tfPrice = new JTextField(20);
		inputBook1.add(lbPrice);
		inputBook1.add(tfPrice);

		JLabel lbCategory = new JLabel("Category");
		tfCategory = new JTextField(20);
		inputBook1.add(lbCategory);
		inputBook1.add(tfCategory);

		JLabel lbStatus = new JLabel("Status");
		tfStatus = new JTextField(20);
		inputBook1.add(lbStatus);
		inputBook1.add(tfStatus);

		btnSave = new JButton("Save");
		btnFind = new JButton("Find");

		inputBook2.setLayout(new FlowLayout());
		inputBook2.add(btnSave);
		inputBook2.add(btnFind);

		inputBook.add(inputBook1);
		inputBook.add(inputBook2);

//		pnMain.add(inputBook1, BorderLayout.SOUTH);
//		con.add(inputBook,BorderLayout.SOUTH);
	}

	private void addEvent() {
		btnSave.addActionListener(bookController);
		btnSave.setActionCommand("Save");
		btnFind.addActionListener(bookController);
		btnFind.setActionCommand("Find");
	}

	private void init() {
		setTitle("Find book by ID");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(350, 100);
		setVisible(true);

	}

	public void showBook() {
		try {
			String sql = "Select * from book";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			dtbBook.setRowCount(0);
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String author = rs.getString("author");
				String price = rs.getString("price");
				String category = rs.getString("bookCategory");
				String status = rs.getString("status");
				Vector<Object> v = new Vector();
				v.add(bookId);
				v.add(bookName);
				v.add(author);
				v.add(price);
				v.add(category);
				v.add(status);
				dtbBook.addRow(v);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

}
