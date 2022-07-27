package teachers;

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

import homepage.Homepage;
import homepage.Welcome;

public class Teachers {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnHome;
	JButton btnViewteacherslist;
	JButton btnAddteacher;
	JButton btnRemoveteacher;
	JButton btnUpdateteacher;
	JLabel img;
	static Teachers o=new Teachers();
	
	
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

void viewteacher(){
	Viewteacher.main(null);
	f.dispose();
}

void addteacher() {

		Addteacher.main(null);
		f.dispose();
}

void updateteacher() {

	int temp=0;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		
		
		Statement s=conn.createStatement();
		ResultSet rs=s.executeQuery("Select distinct branch from teacher");
		
		
		while(rs.next()) {
			temp++;
		}
		conn.close();
	}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
if(temp==0) {
JOptionPane.showMessageDialog(null, "No teachers found");
}
else {
Updateteacher.main(null);
f.dispose();
}
	
}

void removeteacher() {
	int temp=0;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select distinct branch from teacher");
			
			
			while(rs.next()) {
				temp++;
			}
			conn.close();
		}
			catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	if(temp==0) {
	JOptionPane.showMessageDialog(null, "No teachers found");
	}
	else {
	Removeteacher.main(null);
	f.dispose();
	}
}

void body() {
	lbl = new JLabel("Faculty Section");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("Faculty Section");
	label.setVerticalAlignment(SwingConstants.BOTTOM);
	label.setForeground(new Color(0, 0, 0));
	label.setBackground(new Color(240, 240, 240));
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
	label.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(label);

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

	JLabel lblViewteacherslist = new JLabel("View Faculty");
	lblViewteacherslist.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblViewteacherslist.setHorizontalAlignment(SwingConstants.CENTER);
	lblViewteacherslist.setBounds(610, 500, 130, 100);
	f.getContentPane().add(lblViewteacherslist);
	
	
	btnViewteacherslist = new JButton("");
	btnViewteacherslist.setIcon(new ImageIcon("img\\button.png"));
	btnViewteacherslist.setBackground(null);
	btnViewteacherslist.setOpaque(false);
	btnViewteacherslist.setBorder(null);
	btnViewteacherslist.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.viewteacher();
		}
	});
	btnViewteacherslist.setBounds(610, 500, 130, 100);
	f.getContentPane().add(btnViewteacherslist);

	JLabel lblAddTeacher = new JLabel("Add Faculty");
	lblAddTeacher.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblAddTeacher.setHorizontalAlignment(SwingConstants.CENTER);
	lblAddTeacher.setBounds(320, 350, 130, 100);
	f.getContentPane().add(lblAddTeacher);
	
	btnAddteacher = new JButton("");
	btnAddteacher.setIcon(new ImageIcon("img\\button.png"));
	btnAddteacher.setBackground(null);
	btnAddteacher.setOpaque(false);
	btnAddteacher.setBorder(null);
	btnAddteacher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.addteacher();
		}
	});
	btnAddteacher.setBounds(320, 350, 130, 100);
	f.getContentPane().add(btnAddteacher);

	JLabel lblRemoveTeacher = new JLabel("Remove Faculty");
	lblRemoveTeacher.setHorizontalAlignment(SwingConstants.CENTER);
	lblRemoveTeacher.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblRemoveTeacher.setBounds(900, 350, 130, 100);
	f.getContentPane().add(lblRemoveTeacher);
	
	btnRemoveteacher = new JButton("");
	btnRemoveteacher.setIcon(new ImageIcon("img\\button.png"));
	btnRemoveteacher.setBackground(null);
	btnRemoveteacher.setOpaque(false);
	btnRemoveteacher.setBorder(null);
	btnRemoveteacher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		o.removeteacher();
		}
	});
	
	btnRemoveteacher.setBounds(900, 350, 130, 100);
	f.getContentPane().add(btnRemoveteacher);
	
	JLabel lblUpdateTeacher = new JLabel("Update Faculty");
	lblUpdateTeacher.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblUpdateTeacher.setHorizontalAlignment(SwingConstants.CENTER);
	lblUpdateTeacher.setBounds(610, 300, 130, 100);
	f.getContentPane().add(lblUpdateTeacher);
	
	btnUpdateteacher = new JButton("");
	btnUpdateteacher.setIcon(new ImageIcon("img\\button.png"));
	btnUpdateteacher.setBackground(null);
	btnUpdateteacher.setOpaque(false);
	btnUpdateteacher.setBorder(null);
	btnUpdateteacher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.updateteacher();
		}
	});	
	
	btnUpdateteacher.setBounds(610, 300, 130, 100);
	f.getContentPane().add(btnUpdateteacher);
	
	JLabel img1 = new JLabel("");
	img1.setIcon(new ImageIcon("img\\buttonbg.png"));
	img1.setBounds(65, 605, 130, 100);
	f.getContentPane().add(img1);
	
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
	img5.setBounds(612, 505, 130, 100);
	f.getContentPane().add(img5);
	
	img = new JLabel("");
	img.setIcon(new ImageIcon("img\\background.jpg"));
	img.setBounds(0, 0, 1354, 739);
	f.getContentPane().add(img);
}
}