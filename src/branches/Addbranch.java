package branches;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Welcome;

public class Addbranch {

	JFrame f;
	JLabel lbl;
	JLabel lblAddBranch;
	JButton btnback;
	JButton btnsave;
	JLabel lblfullname;
	JTextField tffullname;
	JTextField tfname;
	JLabel img;

	int id;
	int maxid;
	int tempid;
	private JLabel lblfullname1;
	private JLabel lblname1;
	static Addbranch o = new Addbranch();
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
	void save() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String fname=tffullname.getText();
			String name=tfname.getText();
			
			String tempname1="",tempsname1="";
			String tempname,tempsname;
			String tempname2,tempsname2;
			
			tempname=fname.toLowerCase();
			tempsname=name.toLowerCase();
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("select name,sname from branch");
			while(rs.next()) {
				  tempname1=rs.getString(1);
				  tempsname1=rs.getString(2);
				  tempname2=tempname1.toLowerCase();
					tempsname2=tempsname1.toLowerCase();
				  
				  if(tempname.contentEquals(tempname2)||tempsname.contentEquals(tempsname2)) {
						JOptionPane.showMessageDialog(null, "Name already exist");
						return;
					}
			 }
			
			if(fname.contentEquals("")) {
				JOptionPane.showMessageDialog(null,"Branch Name can't be Empty");
			}
			else if(name.contentEquals("")){
				JOptionPane.showMessageDialog(null, "Acronym Can't be Empty");
			}
			else {
				Statement s1=conn.createStatement();
				ResultSet rs1=s1.executeQuery("Select id from branch");
				int count=0;
				while(rs1.next()) {
					count++;
				}
				if(count>10) {
					JOptionPane.showMessageDialog(null, "Max Limit Reached i.e. 10");
				}
				
				else {
				int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Add this Branch","Add Branch",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					id=id();
					
					
					
					PreparedStatement s2=conn.prepareStatement("insert into Branch values(?,?,?)");
					s2.setInt(1, id);
					s2.setString(2, fname);
					s2.setString(3, name);
					s2.executeUpdate();
					
					conn.close();
					JOptionPane.showMessageDialog(null, "Branch Added");
					
					Branches.main(null);
					f.dispose();
				}
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	int id() {
		int id=1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			int bid=1;
			int maxid=1;
			
			Statement s2=conn.createStatement();
			
			ResultSet rs2=s2.executeQuery("select max(id) from branch");
			while(rs2.next()) {
				  maxid=rs2.getInt(1);
			 }
			
			for(id=bid;id<=maxid+1;id++)
			{
			tempid=0;
			PreparedStatement s3=conn.prepareStatement("select id from branch where id=?");
			s3.setInt(1, id);
			ResultSet rs3=s3.executeQuery();
			while(rs3.next())
			{
				tempid=rs3.getInt(1);
			}
			if(tempid==0){
				break;
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
		return id;
		

	}
	
	void back() {
		Branches.main(null);
		f.dispose();
	}
	
	void body() {
		lbl = new JLabel("Add Branch");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lblAddBranch = new JLabel("Add Branch");
		lblAddBranch.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAddBranch.setForeground(new Color(0, 0, 0));
		lblAddBranch.setBackground(new Color(240, 240, 240));
		lblAddBranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lblAddBranch.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lblAddBranch);
		
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
		
		lblfullname = new JLabel("Branch Name : ");
		lblfullname.setForeground(new Color(205, 133, 63));
		lblfullname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblfullname.setBounds(200, 300, 400, 40);
		f.getContentPane().add(lblfullname);
		
		lblfullname1 = new JLabel("Branch Name : ");
		lblfullname1.setForeground(new Color(0, 0, 0));
		lblfullname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblfullname1.setBounds(203, 303, 400, 40);
		f.getContentPane().add(lblfullname1);
		
		JLabel lblname = new JLabel("Acronym :");
		lblname.setForeground(new Color(205, 133, 63));
		lblname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname.setBounds(200, 400, 400, 40);
		f.getContentPane().add(lblname);
		
		lblname1 = new JLabel("Acronym :");
		lblname1.setForeground(new Color(0, 0, 0));
		lblname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname1.setBounds(203, 403, 400, 40);
		f.getContentPane().add(lblname1);
		
		tffullname = new JTextField();
		tffullname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tffullname.setBounds(600, 300, 600, 40);
		f.getContentPane().add(tffullname);
		tffullname.setColumns(10);
		
		tfname = new JTextField();
		tfname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfname.setColumns(10);
		tfname.setBounds(600, 400, 600, 40);
		f.getContentPane().add(tfname);
		
		
		
		JLabel lblsave = new JLabel("Save");
		lblsave.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblsave.setHorizontalAlignment(SwingConstants.CENTER);
		lblsave.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblsave);
		
		btnsave = new JButton("");
		btnsave.setIcon(new ImageIcon("img\\button.png"));
		btnsave.setBackground(null);
		btnsave.setOpaque(false);
		btnsave.setBorder(null);
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.save();
			}
		});
		btnsave.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnsave);
		
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
