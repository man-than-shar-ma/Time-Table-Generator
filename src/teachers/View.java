package teachers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;
import net.proteanit.sql.DbUtils;

public class View {

	static JFrame f;
	JButton btnback;
	JScrollPane scrollPane;
	static View o=new View();
	JTable table;
	JLabel img;

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
		Viewteacher.main(null);
		f.dispose();
	}

	void table() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String branch = Viewteacher.cbbranch.getSelectedItem().toString();
			if(branch=="All") {
				PreparedStatement ps=conn.prepareStatement("select id as Teacher_Id,name as Teacher_Name,schema_id as Schema_No,branch as Branch,year1 as 1st_Year,year2 as 2nd_Year,year3 as 3rd_Year,sem1 as Sem1,sem2 as Sem2,sem3 as Sem3,sem4 as Sem4,sem5 as Sem5,sem6 as Sem6 from teacher where schema_id = ? order by id");
				ps.setInt(1, Homepage.sch);
				ResultSet rs=ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			else {
			PreparedStatement ps=conn.prepareStatement("select id as Teacher_Id,name as Teacher_Name,schema_id as Schema_No,branch as Branch,year1 as 1st_Year,year2 as 2nd_Year,year3 as 3rd_Year,sem1 as Sem1,sem2 as Sem2,sem3 as Sem3,sem4 as Sem4,sem5 as Sem5,sem6 as Sem6 from teacher where schema_id = ?&&branch = ? order by id");
			ps.setInt(1, Homepage.sch);
			ps.setString(2, branch);
			ResultSet rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setMinWidth(350);
		table.getColumnModel().getColumn(1).setMaxWidth(350);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setMinWidth(250);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setMinWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(50);
		
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setMinWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(50);
		
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setMinWidth(50);
		table.getColumnModel().getColumn(6).setMaxWidth(50);
		
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setMinWidth(50);
		table.getColumnModel().getColumn(7).setMaxWidth(50);
		
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setMinWidth(50);
		table.getColumnModel().getColumn(8).setMaxWidth(50);
		
		table.getColumnModel().getColumn(9).setPreferredWidth(50);
		table.getColumnModel().getColumn(9).setMinWidth(50);
		table.getColumnModel().getColumn(9).setMaxWidth(50);
		
		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.getColumnModel().getColumn(10).setMinWidth(50);
		table.getColumnModel().getColumn(10).setMaxWidth(50);
		
		table.getColumnModel().getColumn(11).setPreferredWidth(50);
		table.getColumnModel().getColumn(11).setMinWidth(50);
		table.getColumnModel().getColumn(11).setMaxWidth(50);
		
		table.getColumnModel().getColumn(12).setPreferredWidth(50);
		table.getColumnModel().getColumn(12).setMinWidth(50);
		table.getColumnModel().getColumn(12).setMaxWidth(50);
		
		
		
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

