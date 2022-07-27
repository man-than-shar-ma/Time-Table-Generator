package misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Welcome;

public class Changepassword {

	JFrame f;
	JTextField tfCurrentpassword;
	JTextField tfNewpassword;
	JLabel lbl;
	JLabel lblCurrentpassword;
	JLabel lblNewpassword;
	JButton asubmitbtn;
	String currentpassword;
	String newpassword;
	String password;
	String confirmpassword;
	static Changepassword o=new Changepassword();
	JTextField tfConfirmpassword;
	JLabel lblConfirmpassword;
	JButton aresetbtn;
	JLabel img;
	private JLabel lbl1;
	private JLabel lblCurrentpassword1;
	private JLabel lblNewPasswword1;
	private JLabel lblConfirmPassword1;

	
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
				Misc.f.setEnabled(true);
				e.getWindow().dispose();
				}
		});
	}
	
	void submit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			currentpassword=tfCurrentpassword.getText();
			newpassword=tfNewpassword.getText();
			confirmpassword=tfConfirmpassword.getText();
			
			String str="select password from account";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(str);
			while(rs.next()) {
				password=rs.getString(1);
				}
			
			if(currentpassword.equals("")||newpassword.equals("")||confirmpassword.equals("")) {
				JOptionPane.showMessageDialog(null, "Fields can't be empty");
			}
			else if(!currentpassword.equals(password)){
				JOptionPane.showMessageDialog(null, "Current Password is incorrect");
			}
			else if(!newpassword.equals(confirmpassword)) {
				JOptionPane.showMessageDialog(null, "Password is not same");	
			}
			else {
				PreparedStatement ps=conn.prepareStatement("update account set password=? where password=?");
				ps.setString(1, confirmpassword);
				ps.setString(2, currentpassword);
				
				ps.executeUpdate();
				
				conn.close();
				JOptionPane.showMessageDialog(null, "Password changed Successfully");
				Misc.f.setEnabled(true);
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
	tfCurrentpassword.setText(null);
	tfNewpassword.setText(null);
}
	
void body() {

	
	lbl = new JLabel("Change Password");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbl.setBounds(10, 30, 474, 50);
	f.getContentPane().add(lbl);
	
	lbl1 = new JLabel("Change Password");
	lbl1.setHorizontalAlignment(SwingConstants.CENTER);
	lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbl1.setBounds(13, 33, 474, 50);
	f.getContentPane().add(lbl1);
	
	lblCurrentpassword = new JLabel("Current Password : ");
	lblCurrentpassword.setForeground(new Color(205, 133, 63));
	lblCurrentpassword.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblCurrentpassword.setBounds(80, 110, 160, 20);
	f.getContentPane().add(lblCurrentpassword);
	
	lblCurrentpassword1 = new JLabel("Current Password : ");
	lblCurrentpassword1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblCurrentpassword1.setBounds(83, 111, 160, 20);
	f.getContentPane().add(lblCurrentpassword1);
	
	tfCurrentpassword = new JTextField();
	tfCurrentpassword.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfCurrentpassword.setBounds(250, 110, 100, 20);
	f.getContentPane().add(tfCurrentpassword);
	tfCurrentpassword.setColumns(10);
	
	lblNewpassword = new JLabel("New Password : ");
	lblNewpassword.setForeground(new Color(205, 133, 63));
	lblNewpassword.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblNewpassword.setBounds(80, 150, 160, 20);
	f.getContentPane().add(lblNewpassword);
	
	lblNewPasswword1 = new JLabel("New Password : ");
	lblNewPasswword1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblNewPasswword1.setBounds(83, 151, 160, 20);
	f.getContentPane().add(lblNewPasswword1);
	
	tfNewpassword = new JTextField();
	tfNewpassword.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfNewpassword.setBounds(250, 150, 100, 20);
	f.getContentPane().add(tfNewpassword);
	
	tfConfirmpassword = new JTextField();
	tfConfirmpassword.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfConfirmpassword.setBounds(250, 190, 100, 20);
	f.getContentPane().add(tfConfirmpassword);
	
	lblConfirmpassword = new JLabel("Confirm Password : ");
	lblConfirmpassword.setForeground(new Color(205, 133, 63));
	lblConfirmpassword.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblConfirmpassword.setBounds(80, 190, 160, 20);
	f.getContentPane().add(lblConfirmpassword);
	
	asubmitbtn = new JButton("Submit");
	asubmitbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			o.submit();
			
		}
	});
	
	lblConfirmPassword1 = new JLabel("Confirm Password : ");
	lblConfirmPassword1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblConfirmPassword1.setBounds(83, 191, 160, 20);
	f.getContentPane().add(lblConfirmPassword1);
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
