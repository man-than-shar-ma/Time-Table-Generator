package homepage;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import advanced.Advanced;
import misc.Changeschema;
import misc.Misc;
import branches.Addbranch;
import branches.Branches;
import subjects.Subjects;
import teachers.Teachers;
import timetable.Timetable;
import timings.Timings;


public class Homepage {
	static JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel lblBranch;
	JLabel lblAdminlogout;
	JLabel lblSubjects;
	JLabel lblTimings;
	JLabel lblTeachers;
	JLabel lblTimeTable;
	JLabel img6;
	JButton btnBranch;
	JButton btnAdminlogout;
	JButton btnSubjects;
	JButton btnTeachers;
	JButton btnTimings;
	JButton btnMisc; 
	JButton btnTimetable;
	JLabel img;
	public static int sch=0;
	static Homepage o=new Homepage();
	private JLabel img7;
	private JLabel img8;
	
	
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

void logout() {
	int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Log Out","Log Out",JOptionPane.YES_NO_OPTION);
	if(result==JOptionPane.YES_OPTION) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		Statement s=conn.createStatement();
		s.executeUpdate("Delete from loginout");
		Statement s1=conn.createStatement();
		s1.executeUpdate("Insert into loginout values (false)");
		
		conn.close();
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	JOptionPane.showMessageDialog(null, "Logged out successfully");
	Welcome.main(null);
	f.dispose();
	}
}

void branch() {
	Branches.main(null);
	f.dispose();
}

void subjects() {
	if(Homepage.sch==0) {
		JOptionPane.showMessageDialog(null, "Create Schema First");
		Changeschema.main(null);
		f.dispose();
	}
	else {
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s= conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(*) from branch");
			while(rs.next()) {
				count=rs.getInt(1);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(count==0) {
			JOptionPane.showMessageDialog(null, "Create at least one branch First");
			Addbranch.main(null);
			f.dispose();
		}
		else {
		Subjects.main(null);
		f.dispose();
		}
	}
	
}

void teachers() {
	if(Homepage.sch==0) {
		JOptionPane.showMessageDialog(null, "Create Schema First");
		Changeschema.main(null);
		f.dispose();
	}
	else {
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s= conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(*) from branch");
			while(rs.next()) {
				count=rs.getInt(1);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(count==0) {
			JOptionPane.showMessageDialog(null, "Create at least one branch First");
			Addbranch.main(null);
			f.dispose();
		}
		else {
		
		Teachers.main(null);
		f.dispose();
		}
	}
}

void timings() {
	Timings.main(null);
	f.dispose();
}

void misc() {
	Misc.main(null);
	f.dispose();
}

void timetable() {
	Timetable.main(null);
	f.dispose();
}

void advanced() {
	Advanced.main(null);
	f.dispose();
}


void schema() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		Statement s=conn.createStatement();
		ResultSet rs=s.executeQuery("Select * from schematype");
		while(rs.next()) {
			sch=rs.getInt(1);
		}
		conn.close();
	}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

void body() {
	
	
	lbl = new JLabel("Time Table Generator");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("Time Table Generator");
	label.setVerticalAlignment(SwingConstants.BOTTOM);
	label.setForeground(new Color(0, 0, 0));
	label.setBackground(new Color(240, 240, 240));
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
	label.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(label);
	
	lblBranch = new JLabel("Branch");
	lblBranch.setHorizontalAlignment(SwingConstants.CENTER);
	lblBranch.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblBranch.setBounds(320, 500, 130, 100);
	f.getContentPane().add(lblBranch);
	
	lblAdminlogout = new JLabel("Adminlogout");
	lblAdminlogout.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblAdminlogout.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdminlogout.setBounds(70, 600, 130, 100);
	f.getContentPane().add(lblAdminlogout);
	
	lblSubjects = new JLabel("Subjects");
	lblSubjects.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblSubjects.setHorizontalAlignment(SwingConstants.CENTER);
	lblSubjects.setBounds(320, 350, 130, 100);
	f.getContentPane().add(lblSubjects);
	
	lblTeachers = new JLabel("Faculty");
	lblTeachers.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblTeachers.setHorizontalAlignment(SwingConstants.CENTER);
	lblTeachers.setBounds(900, 350, 130, 100);
	f.getContentPane().add(lblTeachers);
	
	lblTimings = new JLabel("Timings");
	lblTimings.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblTimings.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimings.setBounds(610, 300, 130, 100);
	f.getContentPane().add(lblTimings);
	
	JLabel lblMisc = new JLabel("Misc");
	lblMisc.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblMisc.setHorizontalAlignment(SwingConstants.CENTER);
	lblMisc.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(lblMisc);
	
	lblTimeTable = new JLabel("Time Table");
	lblTimeTable.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblTimeTable.setHorizontalAlignment(SwingConstants.CENTER);
	lblTimeTable.setBounds(610, 500, 130, 100);
	f.getContentPane().add(lblTimeTable);

	
	btnBranch = new JButton("");
	btnBranch.setIcon(new ImageIcon("img\\button.png"));
	btnBranch.setBackground(null);
	btnBranch.setOpaque(false);
	btnBranch.setBorder(null);
	btnBranch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.branch();
		}
	});
	
	JLabel lblAdvanced = new JLabel("Advanced");
	lblAdvanced.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdvanced.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblAdvanced.setBounds(900, 500, 130, 100);
	f.getContentPane().add(lblAdvanced);
	
	btnBranch.setBounds(320, 500, 130, 100);
	f.getContentPane().add(btnBranch);
	
	
	btnAdminlogout = new JButton("");
	btnAdminlogout.setIcon(new ImageIcon("img\\button.png"));
	btnAdminlogout.setBackground(null);
	btnAdminlogout.setOpaque(false);
	btnAdminlogout.setBorder(null);
	btnAdminlogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.logout();
		}
	});
	
	btnAdminlogout.setBounds(70, 600, 130, 100);
	f.getContentPane().add(btnAdminlogout);
	
	
	btnSubjects = new JButton("");
	btnSubjects.setIcon(new ImageIcon("img\\button.png"));
	btnSubjects.setBackground(null);
	btnSubjects.setOpaque(false);
	btnSubjects.setBorder(null);
	btnSubjects.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.subjects();
		}
	});
	btnSubjects.setBounds(320, 350, 130, 100);
	f.getContentPane().add(btnSubjects);
	
	
	btnTeachers = new JButton("");
	btnTeachers.setIcon(new ImageIcon("img\\button.png"));
	btnTeachers.setBackground(null);
	btnTeachers.setOpaque(false);
	btnTeachers.setBorder(null);
	btnTeachers.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.teachers();
		}
	});
	
	
	btnTeachers.setBounds(900, 350, 130, 100);
	f.getContentPane().add(btnTeachers);
	
	
	
	btnTimings = new JButton("");
	btnTimings.setIcon(new ImageIcon("img\\button.png"));
	btnTimings.setBackground(null);
	btnTimings.setOpaque(false);
	btnTimings.setBorder(null);
	btnTimings.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.timings();
		}
	});
	btnTimings.setBounds(610, 300, 130, 100);
	f.getContentPane().add(btnTimings);
	
	
	btnMisc = new JButton("");
	btnMisc.setIcon(new ImageIcon("img\\button.png"));
	btnMisc.setBackground(null);
	btnMisc.setOpaque(false);
	btnMisc.setBorder(null);
	btnMisc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.misc();
		}
	});
	btnMisc.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(btnMisc);
	
	
	btnTimetable = new JButton("");
	btnTimetable.setIcon(new ImageIcon("img\\button.png"));
	btnTimetable.setBackground(null);
	btnTimetable.setOpaque(false);
	btnTimetable.setBorder(null);
	btnTimetable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.timetable();
		}
	});
	
	btnTimetable.setBounds(610, 500, 130, 100);
	f.getContentPane().add(btnTimetable);
	
	JButton btnAdvanced = new JButton("");
	btnAdvanced.setIcon(new ImageIcon("img\\button.png"));
	btnAdvanced.setBackground(null);
	btnAdvanced.setOpaque(false);
	btnAdvanced.setBorder(null);
	btnAdvanced.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.advanced();
		}
	});
	
	btnAdvanced.setBounds(900, 500, 130, 100);
	f.getContentPane().add(btnAdvanced);
	
	JLabel img1 = new JLabel("");
	img1.setIcon(new ImageIcon("img\\buttonbg.png"));
	img1.setBounds(65, 605, 130, 100);
	f.getContentPane().add(img1);
	
	img7 = new JLabel("");
	img7.setIcon(new ImageIcon("img\\buttonbg.png"));
	img7.setBounds(317, 505, 130, 100);
	f.getContentPane().add(img7);
	
	JLabel img2 = new JLabel("");
	img2.setIcon(new ImageIcon("img\\buttonbg.png"));
	img2.setBounds(317, 355, 130, 100);
	f.getContentPane().add(img2);
	
	JLabel img3 = new JLabel("");
	img3.setIcon(new ImageIcon("img\\buttonbg.png"));
	img3.setBounds(612, 305, 130, 100);
	f.getContentPane().add(img3);
	
	JLabel img4 = new JLabel("");
	img4.setIcon(new ImageIcon("img\\buttonbg.png"));
	img4.setBounds(905, 355, 130, 100);
	f.getContentPane().add(img4);
	
	JLabel img5 = new JLabel("");
	img5.setIcon(new ImageIcon("img\\buttonbg.png"));
	img5.setBounds(1137, 605, 130, 100);
	f.getContentPane().add(img5);
	
	img6 = new JLabel("");
	img6.setIcon(new ImageIcon("img\\buttonbg.png"));
	img6.setBounds(612, 505, 130, 100);
	f.getContentPane().add(img6);
	
	img8 = new JLabel("");
	img8.setIcon(new ImageIcon("img\\buttonbg.png"));
	img8.setBounds(905, 505, 130, 100);
	f.getContentPane().add(img8);
	
	img = new JLabel("");
	img.setIcon(new ImageIcon("img\\background.jpg"));
	img.setBounds(0, 0, 1354, 739);
	f.getContentPane().add(img);
	
	o.schema();
}
}
