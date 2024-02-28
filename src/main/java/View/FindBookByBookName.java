package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.BookController;

public class FindBookByBookName extends JDialog {

	Connection conn;
	BookController bookController;
	DefaultTableModel dtbBook;
	JTable tbBook;
	Container con;
	JPanel pnMain, pnTop;
	JLabel lbFind;
	JTextField tfFind;
	JButton btnFind_1;
	PreparedStatement ps;
	ResultSet rs;

	public FindBookByBookName(BookUI bookUI) {
		this.conn = bookUI.getConn();
		this.bookController = new BookController(bookUI);
		addControl();
		addEvent();
		init();
	}

	public void addControl() {
		con = getContentPane();
		con.setLayout(new BorderLayout());
		pnMain = new JPanel();
		con.add(pnMain, BorderLayout.CENTER);
		dtbBook = new DefaultTableModel();
		dtbBook.addColumn("Book Id");
		dtbBook.addColumn("Book Name");
		dtbBook.addColumn("Author");
		dtbBook.addColumn("Price");
		dtbBook.addColumn("Category");
		dtbBook.addColumn("Status");
		tbBook = new JTable(dtbBook);
		JScrollPane sc = new JScrollPane(tbBook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMain.add(sc, BorderLayout.CENTER);
		con.add(pnMain, BorderLayout.CENTER);

		pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		lbFind = new JLabel("Nhập tên sách:");
		tfFind = new JTextField(18);
		btnFind_1 = new JButton("Tìm kiếm");

		pnTop.add(lbFind);
		pnTop.add(tfFind);
		pnTop.add(btnFind_1);

		con.add(pnTop, BorderLayout.NORTH);
	}

	public void addEvent() {
		btnFind_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xulitimkiem();
			}
		});

	}

	public void xulitimkiem() {
		try {
			String sql = "Select * from [book] where bookName like  ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + tfFind.getText() + '%');
			rs = ps.executeQuery();
			dtbBook.setRowCount(0);
			while (rs.next()) {
				Vector<Object> v = new Vector();
				v.add(rs.getString("bookId"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("price"));
				v.add(rs.getString("bookCategory"));
				v.add(rs.getString("status"));
				dtbBook.addRow(v);
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}

	private void init() {
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(800, 100);
		setVisible(true);
	}

	/**
	 * @return the btnFind
	 */
	public JButton getBtnFind() {
		return btnFind_1;
	}

	/**
	 * @param btnFind the btnFind to set
	 */
	public void setBtnFind(JButton btnFind) {
		this.btnFind_1 = btnFind;
	}

	/**
	 * @return the tfFind
	 */
	public JTextField getTfFind() {
		return tfFind;
	}

	/**
	 * @param tfFind the tfFind to set
	 */
	public void setTfFind(JTextField tfFind) {
		this.tfFind = tfFind;
	}

}
