package timetable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Welcome;

public class View {

	JFrame f;
	JLabel lbl;
	JLabel lbl1;
	JButton btnback;
	JButton btnnext;
	JLabel lblsem;
	JLabel lblsem1;
	JComboBox<String> cbsem;
	JComboBox<String> cbbranch;
	JLabel img;
	static int s1,s2,s3;
	static String singlebranch="";
	
	static View o=new View();
	public static void main(String[] args) {
		o.frame();
		o.retrievebranches();
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
		Timetable.main(null);
		f.dispose();
	}
	
void retrievebranches() {
		
		cbbranch.addItem("All");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select name from branch order by name");
			while(rs.next()) {
				cbbranch.addItem(rs.getString(1));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	void next() {
		singlebranch="";
		String cbs=cbsem.getSelectedItem().toString();
		if(cbs.contentEquals("1st sem, 3rd sem and 5th sem")) {
			s1=1;
			s2=3;
			s3=5;
		}
		else {
			s1=2;
			s2=4;
			s3=6;
		}
		int count=0;
		if(cbbranch.getSelectedItem().toString().contentEquals("All")) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			if(cbs.contentEquals("1st sem, 3rd sem and 5th sem")) {
				Statement s=conn.createStatement();
				ResultSet rs=s.executeQuery("select * from time1");
				
				while(rs.next()) {
					count++;
				}
			}
			else {
				Statement s=conn.createStatement();
				ResultSet rs=s.executeQuery("select * from time2");
				
				while(rs.next()) {
					count++;
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count==0) {
			JOptionPane.showMessageDialog(null, "Please save TimeTable first");
		}
		else {
		Viewcurrent.main(null);
		f.dispose();
		}
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
				int br=0; 
				
				PreparedStatement ps1=conn.prepareStatement("Select id from branch where name=?");
				ps1.setString(1, cbbranch.getSelectedItem().toString());
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next()) {
					br=rs1.getInt(1);
				}
				
				if(cbs.contentEquals("1st sem, 3rd sem and 5th sem")) {
					Statement s=conn.createStatement();
					ResultSet rs=s.executeQuery("select * from bt"+br+"1");
					
					while(rs.next()) {
						count++;
					}
				}
				else {
					Statement s=conn.createStatement();
					ResultSet rs=s.executeQuery("select * from bt"+br+"2");
					
					while(rs.next()) {
						count++;
					}
				}
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(count==0) {
				JOptionPane.showMessageDialog(null, "Please save TimeTable first");
			}
			else {
			JOptionPane.showMessageDialog(null, "Note : This is the independent time table tha you have been saved");
			singlebranch=cbbranch.getSelectedItem().toString();
			Viewcurrent.main(null);
			f.dispose();
			}
		}
	}
	
	void body() {
		lbl = new JLabel("Time Table");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lbl1 = new JLabel("Time Table");
		lbl1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl1.setForeground(new Color(0, 0, 0));
		lbl1.setBackground(new Color(240, 240, 240));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lbl1.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl1);
		
		
		
		lblsem = new JLabel("Select Semester : ");
		lblsem.setForeground(new Color(205, 133, 63));
		lblsem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem.setBounds(200, 300, 400, 40);
		f.getContentPane().add(lblsem);
		
		lblsem1 = new JLabel("Select Semester : ");
		lblsem1.setForeground(new Color(0, 0, 0));
		lblsem1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem1.setBounds(203, 303, 400, 40);
		f.getContentPane().add(lblsem1);
		
		
		JLabel lblbranch = new JLabel("Select Branch : ");
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch.setBounds(200, 400, 400, 40);
		f.getContentPane().add(lblbranch);
		
		JLabel lblSelectBranch = new JLabel("Select Branch : ");
		lblSelectBranch.setForeground(Color.BLACK);
		lblSelectBranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblSelectBranch.setBounds(203, 403, 400, 40);
		f.getContentPane().add(lblSelectBranch);
		
		cbsem = new JComboBox<String>();
		cbsem.setModel(new DefaultComboBoxModel<String>(new String[] {"1st sem, 3rd sem and 5th sem", "2nd sem, 4th sem and 6th sem"}));
		cbsem.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cbsem.setBounds(600, 300, 500, 40);
		f.getContentPane().add(cbsem);
		
		
		cbbranch = new JComboBox<String>();
		cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cbbranch.setBounds(600, 400, 500, 40);
		f.getContentPane().add(cbbranch);
		
		
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
		
		
		JLabel lblnext = new JLabel("Next");
		lblnext.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblnext.setHorizontalAlignment(SwingConstants.CENTER);
		lblnext.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblnext);		
		
		btnnext = new JButton("");
		btnnext.setIcon(new ImageIcon("img\\button.png"));
		btnnext.setBackground(null);
		btnnext.setOpaque(false);
		btnnext.setBorder(null);
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.next();
			}
		});
		btnnext.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnnext);
		
		
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		JLabel img5 = new JLabel("");
		img5.setIcon(new ImageIcon("img\\buttonbg.png"));
		img5.setBounds(1137, 605, 130, 100);
		f.getContentPane().add(img5);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
		
	}
}