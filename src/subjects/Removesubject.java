package subjects;

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
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Removesubject {

	static JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnback;
	JButton btnremove;
	JLabel lblbranch;
	JLabel lblbranch1;
	JLabel lblsem;
	JLabel lblsem1;
	JLabel lblname;
	JLabel lblname1;
	JLabel img;
	JComboBox<String> cbbranch;
	JComboBox<String> cbsem;
	JComboBox<String> cbname;
	static int sid=0;
	static Removesubject o=new Removesubject();
	String b="",s="";
	int id=0;
	String none= "No Subjects Found";
	
	
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
	
	void semester() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps=conn.prepareStatement("Select distinct sem from subject where id like ? order by sem");
			ps.setString(1, Homepage.sch+"%");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				String abc=rs.getString(1);
				cbsem.addItem(abc);
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
		try {
			int s1;
			int temp=0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			b=cbbranch.getSelectedItem().toString();
			
			if(cbsem.getSelectedItem()==null) {
				s1=0;
			}
			else{
				s=cbsem.getSelectedItem().toString();
				s1=Integer.parseInt(s);
			}
			
			PreparedStatement ps=conn.prepareStatement("Select name from subject where branch=?&&sem=?&&id like ?");
			ps.setString(1, b);
			ps.setInt(2, s1);
			ps.setString(3, Homepage.sch+"%");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				String abc=rs.getString(1);
				cbname.addItem(abc);
				temp++;
			}
			if(temp==0) {
				cbname.addItem(none);
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
		Subjects.main(null);
		f.dispose();
	}
	
	void next() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String branch=(String)cbbranch.getSelectedItem();
			String semester=(String)cbsem.getSelectedItem();
			String name=(String)cbname.getSelectedItem();
			
			if(name == none) {
				JOptionPane.showMessageDialog(null, none);
			}
			else {
			int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Remove this Subject","Remove Subject",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {	
			
			PreparedStatement s=conn.prepareStatement("Delete from subject where branch=?&&sem=?&&name=?");
			s.setString(1, branch);
			s.setString(2, semester);
			s.setString(3, name);
			s.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Subject Deleted");
			conn.close();
			Subjects.main(null);
			f.dispose();
			}
			}

			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void body() {
		lbl = new JLabel("Remove Subject");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Remove Subject");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(label);;
	
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
		lblbranch.setBounds(150, 350, 200, 50);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel("Branch : ");
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch1.setBounds(153, 353, 150, 50);
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
		
		cbbranch.setBounds(350, 350, 250, 50);
		f.getContentPane().add(cbbranch);
		
		
		
		lblsem = new JLabel("Semester : ");
		lblsem.setForeground(new Color(205, 133, 63));
		lblsem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem.setBounds(650, 350, 200, 50);
		f.getContentPane().add(lblsem);
		
		lblsem1 = new JLabel("Semester : ");
		lblsem1.setForeground(new Color(0, 0, 0));
		lblsem1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem1.setBounds(653, 353, 200, 50);
		f.getContentPane().add(lblsem1);
		
		cbsem = new JComboBox<String>();
		cbsem.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		o.semester();
		cbsem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbname.removeAllItems();
				o.name();
			}
		});
		cbsem.setBounds(850, 350, 250, 50);
		f.getContentPane().add(cbsem);
		
		
		
		lblname = new JLabel("Subject : ");
		lblname.setForeground(new Color(205, 133, 63));
		lblname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname.setBounds(350, 500, 200, 50);
		f.getContentPane().add(lblname);
		
		lblname1 = new JLabel("Subject : ");
		lblname1.setForeground(Color.BLACK);
		lblname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname1.setBounds(353, 503, 200, 50);
		f.getContentPane().add(lblname1);
		
		cbname = new JComboBox<String>();
		cbname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		o.name();
		cbname.setBounds(650, 500, 250, 50);
		f.getContentPane().add(cbname);
		
		
		
		JLabel lblremove = new JLabel("Remove");
		lblremove.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblremove.setHorizontalAlignment(SwingConstants.CENTER);
		lblremove.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblremove);
		
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
