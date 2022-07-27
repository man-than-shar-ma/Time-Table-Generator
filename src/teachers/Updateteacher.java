package teachers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;


public class Updateteacher {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel lblbranch;
	JLabel lblbranch1;
	JLabel lblname;
	JLabel lblname1;
	JLabel img;
	JButton btnback;
	JButton btnremove;
	JComboBox<String> cbbranch;
	JComboBox<String> cbname;
	static int tid=0;
	String b;
	static Updateteacher o=new Updateteacher();
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
	
	void branch() {
		cbbranch.addItem("All");
		cbbranch.addItem("Common");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select name from branch");
			
			while(rs.next()) {
				String abc=rs.getString(1);
				cbbranch.addItem(abc);
			}
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void name() {
		
		int temp=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			b=cbbranch.getSelectedItem().toString();
			
			if(b=="All") {
				PreparedStatement ps=conn.prepareStatement("Select name from teacher where schema_id=?");
				ps.setInt(1, Homepage.sch);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					String abc=rs.getString(1);
					cbname.addItem(abc);
					temp++;
				}
			}
			else {
				PreparedStatement ps=conn.prepareStatement("Select name from teacher where branch=?&&schema_id=?");
				ps.setString(1, b);
				ps.setInt(2, Homepage.sch);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					String abc=rs.getString(1);
					cbname.addItem(abc);
					temp++;
				}
			}
			if(temp==0) {
				cbname.addItem("No Teachers Found");
			}
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	void back() {
		Teachers.main(null);
		f.dispose();
	}
	void next() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			
			String name=(String)cbname.getSelectedItem();
			
			PreparedStatement s=conn.prepareStatement("Select id from teacher where name=?&&schema_id=?");
			s.setString(1, name);
			s.setInt(2, Homepage.sch);
			
			ResultSet rs=s.executeQuery();
			
			
			while(rs.next())
			{
				tid=rs.getInt(1);
			}
				conn.close();
				Update.main(null);
				f.dispose();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	void body() {
		lbl = new JLabel("Update Faculty");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Update Faculty");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(label);
		
		
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
		
		lblbranch = new JLabel("Branch : ");
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch.setBounds(400, 350, 200, 50);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel("Branch : ");
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch1.setBounds(403, 353, 150, 50);
		f.getContentPane().add(lblbranch1);
		
		cbbranch = new JComboBox<String>();
		cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		o.branch();
		cbbranch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
					cbname.removeAllItems();
					o.name();
			}
		});
		
		cbbranch.setBounds(650, 350, 200, 50);
		f.getContentPane().add(cbbranch);
		
		lblname = new JLabel("Faculty : ");
		lblname.setForeground(new Color(205, 133, 63));
		lblname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname.setBounds(400, 500, 200, 50);
		f.getContentPane().add(lblname);
		
		lblname1 = new JLabel("Faculty : ");
		lblname1.setForeground(Color.BLACK);
		lblname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname1.setBounds(403, 503, 200, 50);
		f.getContentPane().add(lblname1);
		
		cbname = new JComboBox<String>();
		cbname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		o.name();
		cbname.setBounds(650, 500, 200, 50);
		f.getContentPane().add(cbname);
		
		
		
		JLabel lblupdate = new JLabel("Update");
		lblupdate.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblupdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblupdate.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblupdate);
		
		btnremove = new JButton("");
		btnremove.setIcon(new ImageIcon("img\\button.png"));
		btnremove.setBackground(null);
		btnremove.setOpaque(false);
		btnremove.setBorder(null);
		btnremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.next();
			}
		});
		btnremove.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnremove);
	
		
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
