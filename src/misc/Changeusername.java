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

public class Changeusername {

	JFrame f;
	JTextField tfCurrentusername;
	JTextField tfNewusername;
	JLabel lbl;
	JLabel lblCurrentusername;
	JLabel lblNewusername;
	JButton asubmitbtn;
	String currentusername;
	String newusername;
	String username;
	String confirmusername;
	static Changeusername o=new Changeusername();
	JTextField tfConfirmusername;
	JLabel lblConfirmusername;
	JButton aresetbtn;
	JLabel img;
	private JLabel lbl1;
	private JLabel lblCurrentUsername1;
	private JLabel lblNewusername1;
	private JLabel lblConfirmusername1;

	
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
			
			currentusername=tfCurrentusername.getText();
			newusername=tfNewusername.getText();
			confirmusername=tfConfirmusername.getText();
			
			String str="select username from account";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(str);
			while(rs.next()) {
				username=rs.getString(1);
				}
			
			
			if(currentusername.equals("")||newusername.equals("")||confirmusername.equals("")) {
				JOptionPane.showMessageDialog(null, "Fields can't be empty");
			}
			else if(!currentusername.equals(username)){
				JOptionPane.showMessageDialog(null, "Current Username is incorrect");
			}
			else if(!newusername.equals(confirmusername)) {
				JOptionPane.showMessageDialog(null, "New Username is not same");	
			}
			else {
				PreparedStatement ps=conn.prepareStatement("update account set username=? where username=?");
				ps.setString(1, confirmusername);
				ps.setString(2, currentusername);
				
				ps.executeUpdate();
				conn.close();
				JOptionPane.showMessageDialog(null, "Username changed Successfully");
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
	tfCurrentusername.setText(null);
	tfNewusername.setText(null);
	tfConfirmusername.setText(null);
	
}
	
void body() {

	
	lbl = new JLabel("Change Username");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbl.setBounds(10, 30, 474, 50);
	f.getContentPane().add(lbl);
	
	lbl1 = new JLabel("Change Username");
	lbl1.setHorizontalAlignment(SwingConstants.CENTER);
	lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbl1.setBounds(13, 33, 474, 50);
	f.getContentPane().add(lbl1);
	
	lblCurrentusername = new JLabel("Current Username : ");
	lblCurrentusername.setForeground(new Color(205, 133, 63));
	lblCurrentusername.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblCurrentusername.setBounds(80, 110, 160, 20);
	f.getContentPane().add(lblCurrentusername);
	
	lblCurrentUsername1 = new JLabel("Current Username : ");
	lblCurrentUsername1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblCurrentUsername1.setBounds(83, 111, 160, 20);
	f.getContentPane().add(lblCurrentUsername1);
	
	tfCurrentusername = new JTextField();
	tfCurrentusername.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfCurrentusername.setBounds(250, 110, 100, 20);
	f.getContentPane().add(tfCurrentusername);
	tfCurrentusername.setColumns(10);
	
	lblNewusername = new JLabel("New Username : ");
	lblNewusername.setForeground(new Color(205, 133, 63));
	lblNewusername.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblNewusername.setBounds(80, 150, 160, 20);
	f.getContentPane().add(lblNewusername);
	
	lblNewusername1 = new JLabel("New Username : ");
	lblNewusername1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblNewusername1.setBounds(83, 151, 160, 20);
	f.getContentPane().add(lblNewusername1);
	
	tfNewusername = new JTextField();
	tfNewusername.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfNewusername.setBounds(250, 150, 100, 20);
	f.getContentPane().add(tfNewusername);
	
	tfConfirmusername = new JTextField();
	tfConfirmusername.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	tfConfirmusername.setBounds(250, 190, 100, 20);
	f.getContentPane().add(tfConfirmusername);
	
	lblConfirmusername = new JLabel("Confirm Username : ");
	lblConfirmusername.setForeground(new Color(205, 133, 63));
	lblConfirmusername.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblConfirmusername.setBounds(80, 190, 160, 20);
	f.getContentPane().add(lblConfirmusername);
	
	asubmitbtn = new JButton("Submit");
	asubmitbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			o.submit();
			
		}
	});
	
	lblConfirmusername1 = new JLabel("Confirm Username : ");
	lblConfirmusername1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 15));
	lblConfirmusername1.setBounds(83, 191, 160, 20);
	f.getContentPane().add(lblConfirmusername1);
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
