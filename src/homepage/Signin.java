package homepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Welcome;

public class Signin {
	JFrame f;
	JTextField ausernametf;
	JPasswordField apwdtf;
	JLabel lblAdminLogin;
	JLabel ausername;
	JLabel apwd;
	JButton asubmitbtn;
	JButton aresetbtn;
	JLabel img;
	String username1;
	String pwd1;
	static Signin o=new Signin();

	public static void main(String[] args){
		o.frame();
		o.body();
	}
	
void frame() {
	f=new JFrame();
	f.setResizable(false);
	f.getContentPane().setBackground(new Color(245, 245, 245));
	f.setVisible(true);
	f.setEnabled(true);
	f.setBounds(400, 250, 500, 350);
	f.getContentPane().setLayout(null);
	f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			Welcome.f.setEnabled(true);
			e.getWindow().dispose();
			}
	});
}

void submit() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		String str="select * from account";
		Statement s=conn.createStatement();
		ResultSet rs=s.executeQuery(str);
		
		while(rs.next()) {
			username1=rs.getString(1);
			pwd1=rs.getString(2);
			}
		
		String username= ausernametf.getText();
		@SuppressWarnings("deprecation")
		String pswd= apwdtf.getText();
		
		
		if(username.equals(username1) && pswd.equals(pwd1)) {
			
			Statement s1=conn.createStatement();
			s1.executeUpdate("Delete from loginout");
			Statement s2=conn.createStatement();
			s2.executeUpdate("Insert into loginout values (true)");
			conn.close();
			JOptionPane.showMessageDialog(null, "Login Successfully");
			Homepage.main(null);
			f.dispose();
			Welcome.f.dispose();
		}
		else if(username.equals("")||pswd.equals("")) {
			JOptionPane.showMessageDialog(null, "Username or Password can't be empty");
		}
		else {
			JOptionPane.showMessageDialog(null, "invalid credentials");
		}
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

void reset() {
	ausernametf.setText(null);
	apwdtf.setText(null);
}
void body() {
	
	
	lblAdminLogin = new JLabel("Admin Login");
	lblAdminLogin.setForeground(new Color(205, 133, 63));
	lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdminLogin.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblAdminLogin.setBounds(10, 50, 474, 50);
	f.getContentPane().add(lblAdminLogin);
	
	JLabel lblAdminLogin1 = new JLabel("Admin Login");
	lblAdminLogin1.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdminLogin1.setForeground(Color.BLACK);
	lblAdminLogin1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblAdminLogin1.setBounds(13, 53, 474, 50);
	f.getContentPane().add(lblAdminLogin1);
	
	ausername = new JLabel("Username : ");
	ausername.setForeground(new Color(205, 133, 63));
	ausername.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	ausername.setBounds(100, 130, 100, 20);
	f.getContentPane().add(ausername);
	
	JLabel ausername1 = new JLabel("Username : ");
	ausername1.setForeground(Color.BLACK);
	ausername1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	ausername1.setBounds(103, 131, 100, 20);
	f.getContentPane().add(ausername1);
	
	ausernametf = new JTextField();
	ausernametf.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	ausernametf.setBounds(250, 130, 100, 20);
	f.getContentPane().add(ausernametf);
	ausernametf.setColumns(10);
	
	apwd = new JLabel("Password : ");
	apwd.setForeground(new Color(205, 133, 63));
	apwd.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	apwd.setBounds(100, 170, 100, 20);
	f.getContentPane().add(apwd);
	
	JLabel apwd1 = new JLabel("Password : ");
	apwd1.setForeground(Color.BLACK);
	apwd1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	apwd1.setBounds(103, 171, 100, 20);
	f.getContentPane().add(apwd1);
	
	apwdtf = new JPasswordField();
	apwdtf.setFont(new Font("Tahoma", Font.PLAIN, 10));
	apwdtf.setBounds(250, 170, 100, 20);
	f.getContentPane().add(apwdtf);
	
	asubmitbtn = new JButton("Submit");
	asubmitbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		o.submit();				
		}
	});
	asubmitbtn.setBounds(100, 250, 100, 20);
	f.getContentPane().add(asubmitbtn);
	
	aresetbtn = new JButton("Clear");
	aresetbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.reset();
		}
	});
	aresetbtn.setBounds(250, 250, 100, 20);
	f.getContentPane().add(aresetbtn);
	
	

	
	img = new JLabel("");
	img.setHorizontalAlignment(SwingConstants.CENTER);
	img.setIcon(new ImageIcon("img\\background2.jpeg"));
	img.setBounds(0, 0, 494, 321);
	f.getContentPane().add(img);
	}
}