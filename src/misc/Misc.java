package misc;

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

public class Misc {

	static JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel lblSchema;
	JLabel img;
	JButton btnHome;
	JButton btnChangeusername;
	JButton btnSchema;
	JButton btnChangepassword;
	static Misc o=new Misc();
	private JLabel lblChangePassword;
	private JLabel lblChangeUsername;
	int sch=0;
	private JLabel lblChangePassword1;
	JButton btnClearDb;
	
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

void changeusername() {
	Changeusername.main(null);
	f.setEnabled(false);
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
		if(sch==0) {
			JOptionPane.showMessageDialog(null, "Create Schema First");
			Changeschema.main(null);
			f.dispose();
		}
		else {
			Schema.main(null);
			f.dispose();
		}
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

void changepasswowrd() {
	Changepassword.main(null);
	f.setEnabled(false);
}

void clearDb() {
	JOptionPane.showMessageDialog(null, "Warning : You are about to clear all the database, You can not undo this action");
	int result1 = JOptionPane.showConfirmDialog(null, "Do you Really want to clear all the data in the Database","Clear Database",JOptionPane.YES_NO_OPTION);
	if(result1==JOptionPane.YES_OPTION) {
		int result2 = JOptionPane.showConfirmDialog(null, "Are You Sure","Clear Database",JOptionPane.YES_NO_OPTION);
		if(result2==JOptionPane.YES_OPTION) {
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
				Statement s=conn.createStatement();
				s.execute("drop database ttg");
				conn.close();
				
				JOptionPane.showMessageDialog(null, "The Database is Cleared");
				JOptionPane.showMessageDialog(null, "The application will be closed...");
				System.exit(0);
		}
			catch (Exception e) {
				// TODO: handle exception
			}
	}
	}
}

void body() {
	lbl = new JLabel("Misc Section");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("Misc Section");
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

	btnChangeusername = new JButton("");
	btnChangeusername.setIcon(new ImageIcon("img\\button.png"));
	btnChangeusername.setBackground(null);
	btnChangeusername.setOpaque(false);
	btnChangeusername.setBorder(null);
	btnChangeusername.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		o.changeusername();	
		}
	});
	
	lblChangeUsername = new JLabel("Change");
	lblChangeUsername.setHorizontalAlignment(SwingConstants.CENTER);
	lblChangeUsername.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblChangeUsername.setBounds(320, 340, 130, 100);
	f.getContentPane().add(lblChangeUsername);
	
	JLabel lblChangeUsername1 = new JLabel("Username");
	lblChangeUsername1.setHorizontalAlignment(SwingConstants.CENTER);
	lblChangeUsername1.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblChangeUsername1.setBounds(320, 360, 130, 100);
	f.getContentPane().add(lblChangeUsername1);
	btnChangeusername.setBounds(320, 350, 130, 100);
	f.getContentPane().add(btnChangeusername);

	btnChangepassword = new JButton("");
	btnChangepassword.setIcon(new ImageIcon("img\\button.png"));
	btnChangepassword.setBackground(null);
	btnChangepassword.setOpaque(false);
	btnChangepassword.setBorder(null);
	btnChangepassword.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.changepasswowrd();
		}
	});
	
	
	lblSchema = new JLabel("Schema");
	lblSchema.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblSchema.setHorizontalAlignment(SwingConstants.CENTER);
	lblSchema.setBounds(610, 300, 130, 100);
	f.getContentPane().add(lblSchema);
	
	btnSchema = new JButton("");
	btnSchema.setIcon(new ImageIcon("img\\button.png"));
	btnSchema.setBackground(null);
	btnSchema.setOpaque(false);
	btnSchema.setBorder(null);
	btnSchema.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.schema();
		}
	});
	btnSchema.setBounds(610, 300, 130, 100);
	f.getContentPane().add(btnSchema);
	
	
	
	lblChangePassword = new JLabel("Change");
	lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
	lblChangePassword.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblChangePassword.setBounds(900, 340, 130, 100);
	f.getContentPane().add(lblChangePassword);
	
	lblChangePassword1 = new JLabel("Password");
	lblChangePassword1.setHorizontalAlignment(SwingConstants.CENTER);
	lblChangePassword1.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblChangePassword1.setBounds(900, 360, 130, 100);
	f.getContentPane().add(lblChangePassword1);
	btnChangepassword.setBounds(900, 350, 130, 100);
	f.getContentPane().add(btnChangepassword);
	
	
	JLabel lblClearDb = new JLabel("Clear Database");
	lblClearDb.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblClearDb.setHorizontalAlignment(SwingConstants.CENTER);
	lblClearDb.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(lblClearDb);
	
	btnClearDb = new JButton("");
	btnClearDb.setIcon(new ImageIcon("img\\button.png"));
	btnClearDb.setBackground(null);
	btnClearDb.setOpaque(false);
	btnClearDb.setBorder(null);
	btnClearDb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.clearDb();
		}
	});
	btnClearDb.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(btnClearDb);
	
	
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
	img5.setBounds(1137, 605, 130, 100);
	f.getContentPane().add(img5);
	
	img = new JLabel("");
	img.setIcon(new ImageIcon("img\\background.jpg"));
	img.setBounds(0, 0, 1354, 739);
	f.getContentPane().add(img);



}
}
