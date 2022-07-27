package branches;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import homepage.Welcome;
import net.proteanit.sql.DbUtils;

public class Viewbranch {
	JFrame f;
	JButton btnback;
	JScrollPane scrollPane;
	JTable table;
	JLabel img;
	
	static Viewbranch o = new Viewbranch();
	
	public static void main(String[] args) {
		o.frame();
		o.table();
	}
	void frame() {
		f=new JFrame();
		f.setResizable(false);
		f.getContentPane().setBackground(new Color(245, 245, 245));
		f.setSize(1360, 768);
		f.getContentPane().setLayout(null);
		o.body();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void back() {
		Branches.main(null);
		f.dispose();
	}
	
	void table() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s = conn.createStatement();
			ResultSet rs=s.executeQuery("select id as Branch_Id,name as Branch_Name,sname as Acronym from Branch order by id");
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void body() {
		JLabel lblback = new JLabel("Back");
		lblback.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblback.setHorizontalAlignment(SwingConstants.CENTER);
		lblback.setBounds(70, 600, 130, 100);
		f.getContentPane().add(lblback);
		
		btnback = new JButton("");
		btnback.setIcon(new ImageIcon("img\\button.png"));
		btnback.setBackground(null);
		btnback.setOpaque(false);
		btnback.setBorder(null);
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.back();
			}
		});
		btnback.setBounds(70, 600, 130, 100);
		f.getContentPane().add(btnback);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 51, 1269, 545);
		f.getContentPane().add(scrollPane);
		table = new JTable();
		table.setRowHeight(table.getRowHeight()+20);
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),table.getRowHeight()));
		table.setEnabled(false);
		table.getTableHeader().setFont(new Font("Kristen ITC", Font.PLAIN, 10));
		table.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
		scrollPane.setViewportView(table);
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
}
