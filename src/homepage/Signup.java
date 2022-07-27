package homepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Signup {
	static JFrame f;
	JTextField ausernametf;
	JPasswordField apwdtf;
	JLabel lblAdminLogin;
	JLabel ausername;
	JLabel apwd;
	JButton asubmitbtn;
	String username1;
	String pwd1;
	String cpwd1;
	JButton aresetbtn;
	JLabel img;
	static Signup o=new Signup();
	JPasswordField acpwdtf;
	JLabel acpwd;
	JLabel acwpd1;

	
	public static void main(String[] args){
		o.frame();
		o.body();
	}
	
	void frame() {
		f=new JFrame();
		f.setResizable(false);
		f.getContentPane().setBackground(new Color(245, 245, 245));
		f.setVisible(true);
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
	
	@SuppressWarnings("deprecation")
	void submit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			username1=ausernametf.getText();
			pwd1=apwdtf.getText();
			cpwd1=acpwdtf.getText();
			
			
			
			if(username1.equals("")||pwd1.equals("")) {
				JOptionPane.showMessageDialog(null, "Username or Password can't be empty");
			}
			else if(!cpwd1.equals(pwd1)){
				JOptionPane.showMessageDialog(null, "Password is not same");				
			}
			else {
				PreparedStatement ps=conn.prepareStatement("insert into account values(?,?)");
				ps.setString(1, username1);
				ps.setString(2, cpwd1);
				
				ps.executeUpdate();
				conn.close();
				JOptionPane.showMessageDialog(null, "Signup Successfully");
				Signin.main(null);
				f.dispose();
			}
		} 
		catch (ClassNotFoundException e1) {
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

	
	lblAdminLogin = new JLabel("Admin Signup");
	lblAdminLogin.setForeground(new Color(205, 133, 63));
	lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdminLogin.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblAdminLogin.setBounds(10, 50, 474, 50);
	f.getContentPane().add(lblAdminLogin);
	
	JLabel lblAdminLogin1 = new JLabel("Admin Signup");
	lblAdminLogin1.setHorizontalAlignment(SwingConstants.CENTER);
	lblAdminLogin1.setForeground(Color.BLACK);
	lblAdminLogin1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblAdminLogin1.setBounds(13, 53, 474, 50);
	f.getContentPane().add(lblAdminLogin1);
	
	ausername = new JLabel("Username : ");
	ausername.setForeground(new Color(205, 133, 63));
	ausername.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	ausername.setBounds(80, 130, 100, 20);
	f.getContentPane().add(ausername);
	
	JLabel ausername1 = new JLabel("Username : ");
	ausername1.setForeground(Color.BLACK);
	ausername1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	ausername1.setBounds(83, 131, 100, 20);
	f.getContentPane().add(ausername1);
	
	ausernametf = new JTextField();
	ausernametf.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	ausernametf.setBounds(250, 130, 100, 20);
	f.getContentPane().add(ausernametf);
	ausernametf.setColumns(10);
	
	apwd = new JLabel("Password : ");
	apwd.setForeground(new Color(205, 133, 63));
	apwd.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	apwd.setBounds(80, 160, 100, 20);
	f.getContentPane().add(apwd);
	
	JLabel apwd1 = new JLabel("Password : ");
	apwd1.setForeground(Color.BLACK);
	apwd1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	apwd1.setBounds(83, 161, 100, 20);
	f.getContentPane().add(apwd1);
	
	apwdtf = new JPasswordField();
	apwdtf.setFont(new Font("Tahoma", Font.PLAIN, 10));
	apwdtf.setBounds(250, 160, 100, 20);
	f.getContentPane().add(apwdtf);
	
	acpwdtf = new JPasswordField();
	acpwdtf.setFont(new Font("Tahoma", Font.PLAIN, 10));
	acpwdtf.setBounds(250, 190, 100, 20);
	f.getContentPane().add(acpwdtf);
	
	acpwd = new JLabel("Confirm Password : ");
	acpwd.setForeground(new Color(205, 133, 63));
	acpwd.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	acpwd.setBounds(80, 190, 160, 20);
	f.getContentPane().add(acpwd);
	
	acwpd1 = new JLabel("Confirm Password : ");
	acwpd1.setForeground(Color.BLACK);
	acwpd1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	acwpd1.setBounds(83, 191, 160, 20);
	f.getContentPane().add(acwpd1);
	
	
	
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