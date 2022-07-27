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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Welcome;


public class Update {
	JFrame f;
	JLabel lbl;
	JLabel lblAddBranch;
	JButton btnback;
	JLabel lblfullname;
	JLabel lblfullname1;
	JLabel lblname1;
	JTextField tffullname;
	JTextField tfname;
	JButton btnupdate;
	int id;
	
	JLabel img;
	
	static Update o = new Update();
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
	void back() {
		Updatebranch.main(null);
		f.dispose();
	}
	
	void retrieve() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps = conn.prepareStatement("Select name,sname,id from branch where name=?");
			ps.setString(1, Updatebranch.branchname);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String tname = rs.getString(1);
				String tsname = rs.getString(2);
				id = rs.getInt(3);
				
				tffullname.setText(tname);
				tfname.setText(tsname);
				
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void update() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps = conn.prepareStatement("Update branch set name=?,sname=? where id = ?");
			ps.setString(1, tffullname.getText());
			ps.setString(2, tfname.getText());
			ps.setInt(3, id);
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Branch Updated Successfully");
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Updatebranch.main(null);
		f.dispose();
		
		
	}
	void body() {
		lbl = new JLabel("Update");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lblAddBranch = new JLabel("Update");
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
		
		
		
		JLabel lblupdate = new JLabel("Update");
		lblupdate.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblupdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblupdate.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblupdate);
		
		btnupdate = new JButton("");
		btnupdate.setIcon(new ImageIcon("img\\button.png"));
		btnupdate.setBackground(null);
		btnupdate.setOpaque(false);
		btnupdate.setBorder(null);
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.update();
			}
		});
		btnupdate.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnupdate);
		
		o.retrieve();
		
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
