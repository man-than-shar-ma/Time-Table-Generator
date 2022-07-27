package advanced;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;
import subjects.Addsubject;
import teachers.Addteacher;

public class Advanced {
	JLabel lbl;
	JLabel lblAdvancedFlters;
	JFrame f;
	JButton btnHome;
	JLabel lblSubjectsFilter;
	JButton btnSubjectsFilter;
	JLabel lblTeachersFilter;
	JButton btnTeachersFilter;
	JLabel img;
	static Advanced o=new Advanced();
	public static void main(String[] args) {
		o.frame();
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
	
	void home() {
		Homepage.main(null);
		f.dispose();
	}
	
	void subjectsfilter() {
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s= conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(*) from subject");
			while(rs.next()) {
				count=rs.getInt(1);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(count==0) {
			JOptionPane.showMessageDialog(null, "Add some subjects first");
			Addsubject.main(null);
			f.dispose();
		}
		else {
		Subjectsfilter.main(null);
		f.dispose();
		}
	}
	
	void teachersfilter() {
		int count=0;
		int count1=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s= conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(*) from subject");
			while(rs.next()) {
				count=rs.getInt(1);
			}
			ResultSet rs1=s.executeQuery("Select count(*) from teacher");
			while(rs1.next()) {
				count1=rs1.getInt(1);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(count==0) {
			JOptionPane.showMessageDialog(null, "Add some Subjects first");
			Addsubject.main(null);
			f.dispose();
		}
		else if(count1==0) {
			JOptionPane.showMessageDialog(null, "Add some Teachers first");
			Addteacher.main(null);
			f.dispose();
		}
		else {
		Teachersfilter.main(null);
		f.dispose();
		}
	}
	
	void body() {
		
		lbl = new JLabel("Advanced Filters");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lblAdvancedFlters = new JLabel("Advanced Filters");
		lblAdvancedFlters.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAdvancedFlters.setForeground(new Color(0, 0, 0));
		lblAdvancedFlters.setBackground(new Color(240, 240, 240));
		lblAdvancedFlters.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvancedFlters.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lblAdvancedFlters.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lblAdvancedFlters);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(70, 600, 130, 100);
		f.getContentPane().add(lblHome);
		
		btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon("img\\button.png"));
		btnHome.setBackground(null);
		btnHome.setOpaque(false);
		btnHome.setBorder(null);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.home();
			}
		});
		btnHome.setBounds(70, 600, 130, 100);
		f.getContentPane().add(btnHome);
		
		lblSubjectsFilter = new JLabel(" Subjects Filter");
		lblSubjectsFilter.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblSubjectsFilter.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectsFilter.setBounds(320, 350, 130, 100);
		f.getContentPane().add(lblSubjectsFilter);
		
		btnSubjectsFilter = new JButton("");
		btnSubjectsFilter.setIcon(new ImageIcon("img\\button.png"));
		btnSubjectsFilter.setBackground(null);
		btnSubjectsFilter.setOpaque(false);
		btnSubjectsFilter.setBorder(null);
		btnSubjectsFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.subjectsfilter();
			}
		});
		btnSubjectsFilter.setBounds(320, 350, 130, 100);
		f.getContentPane().add(btnSubjectsFilter);
		
		lblTeachersFilter = new JLabel("Faculty Filter");
		lblTeachersFilter.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblTeachersFilter.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeachersFilter.setBounds(900, 350, 130, 100);
		f.getContentPane().add(lblTeachersFilter);
		
		
		btnTeachersFilter = new JButton();
		btnTeachersFilter.setIcon(new ImageIcon("img\\button.png"));
		btnTeachersFilter.setBackground(null);
		btnTeachersFilter.setOpaque(false);
		btnTeachersFilter.setBorder(null);
		btnTeachersFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.teachersfilter();
			}
		});
		btnTeachersFilter.setBounds(900, 350, 130, 100);
		f.getContentPane().add(btnTeachersFilter);
		
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setIcon(new ImageIcon("img\\buttonbg.png"));
		img2.setBounds(317, 355, 130, 100);
		f.getContentPane().add(img2);
		
		JLabel img4 = new JLabel("");
		img4.setIcon(new ImageIcon("img\\buttonbg.png"));
		img4.setBounds(905, 355, 130, 100);
		f.getContentPane().add(img4);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
}
