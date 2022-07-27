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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class Viewsubject {
JFrame f;
JLabel lbl;
JLabel label;
JButton btnback;
JLabel lbl1;
static JComboBox<String> cbbranch;
static JCheckBox chbx1sem;
static JCheckBox chbx2sem;
static JCheckBox chbx3sem;
static JCheckBox chbx4sem;
static JCheckBox chbx5sem;
static JCheckBox chbx6sem;

JButton btnview;
JLabel img;
static Viewsubject o=new Viewsubject();
private JLabel lbl4;
private JLabel lbl5;
private JLabel lbl6;

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
Subjects.main(null);
f.dispose();
}

void next() {
	
	
	boolean s1=chbx1sem.isSelected();
	boolean s2=chbx2sem.isSelected();
	boolean s3=chbx3sem.isSelected();
	boolean s4=chbx4sem.isSelected();
	boolean s5=chbx5sem.isSelected();
	boolean s6=chbx6sem.isSelected();
	
	int sem01 = 0;
	int sem02 = 0;
	int sem03 = 0;
	int sem04 = 0;
	int sem05 = 0;
	int sem06 = 0;
	
	if(s1==true) {
	sem01=1;
	}
	if(s2==true) {
		sem02=2;
		}
	if(s3==true) {
		sem03=3;
		}
	if(s4==true) {
		sem04=4;
		}
	if(s5==true) {
		sem05=5;
		}
	if(s6==true) {
		sem06=6;
		}
	
	
	
	if(s1==false&&s2==false&&s3==false&&s4==false&&s5==false&&s6==false) {
		JOptionPane.showMessageDialog(null, "Select at least one semester");
	}
	else {
		int temp=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			int bid=0;
			
			PreparedStatement ps= conn.prepareStatement("Select id from branch where name = ?");
			ps.setString(1, cbbranch.getSelectedItem().toString());
			ResultSet rs1 = ps.executeQuery();
			while(rs1.next()) {
				bid = rs1.getInt(1);
			}
			
			PreparedStatement s=conn.prepareStatement("Select distinct branch from subject where (sem=?||sem=?||sem=?||sem=?||sem=?||sem=?)&&id like ?&&branch = ?");
			s.setInt(1, sem01);
			s.setInt(2, sem02);
			s.setInt(3, sem03);
			s.setInt(4, sem04);
			s.setInt(5, sem05);
			s.setInt(6, sem06);
			s.setString(7, Homepage.sch*100+bid+"%");
			s.setString(8, cbbranch.getSelectedItem().toString());
			ResultSet rs=s.executeQuery();
			
			
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
	JOptionPane.showMessageDialog(null, "No Subjects found");
	}
	else {
		View.main(null);
		f.dispose();
	}
	}
	}

void retrievebranch() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("Select name from branch");
		
		while(rs.next()) {
			String branch = rs.getString(1);
			cbbranch.addItem(branch);
		}
		
		conn.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
	

void body() {
	lbl = new JLabel("View Subjects");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("View Subjects");
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
	
	cbbranch = new JComboBox<String>();
	cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
	o.retrievebranch();
	cbbranch.setBounds(550, 250, 300, 40);
	f.getContentPane().add(cbbranch);
	
	chbx1sem = new JCheckBox("1st Sem");
	chbx1sem.setForeground(new Color(205, 133, 63));
	chbx1sem.setOpaque(false);
	chbx1sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx1sem.setBounds(250, 350, 250, 100);
	f.getContentPane().add(chbx1sem);
	
	lbl1 = new JLabel("1st Sem");
	lbl1.setForeground(new Color(0, 0, 0));
	lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl1.setBounds(273, 353, 300, 100);
	f.getContentPane().add(lbl1);
	
	
	chbx2sem = new JCheckBox("2nd Sem");
	chbx2sem.setForeground(new Color(205, 133, 63));
	chbx2sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx2sem.setOpaque(false);
	chbx2sem.setBounds(550, 350, 250, 100);
	f.getContentPane().add(chbx2sem);
	
	JLabel lbl2 = new JLabel("2nd Sem");
	lbl2.setForeground(new Color(0, 0, 0));
	lbl2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl2.setBounds(573, 353, 300, 100);
	f.getContentPane().add(lbl2);
	
	chbx3sem = new JCheckBox("3rd Sem");
	chbx3sem.setForeground(new Color(205, 133, 63));
	chbx3sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx3sem.setOpaque(false);
	chbx3sem.setBounds(850, 350, 250, 100);
	f.getContentPane().add(chbx3sem);
	
	JLabel lbl3 = new JLabel("3rd Sem");
	lbl3.setForeground(new Color(0, 0, 0));
	lbl3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl3.setBounds(873, 353, 300, 100);
	f.getContentPane().add(lbl3);
	
	
	chbx4sem = new JCheckBox("4th Sem");
	chbx4sem.setForeground(new Color(205, 133, 63));
	chbx4sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx4sem.setOpaque(false);
	chbx4sem.setBounds(250, 450, 250, 100);
	f.getContentPane().add(chbx4sem);
	
	lbl4 = new JLabel("4th Sem");
	lbl4.setForeground(new Color(0, 0, 0));
	lbl4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl4.setBounds(273, 453, 300, 100);
	f.getContentPane().add(lbl4);
	
	
	chbx5sem = new JCheckBox("5th Sem");
	chbx5sem.setForeground(new Color(205, 133, 63));
	chbx5sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx5sem.setOpaque(false);
	chbx5sem.setBounds(550, 450, 250, 100);
	f.getContentPane().add(chbx5sem);
	
	lbl5 = new JLabel("5th Sem");
	lbl5.setForeground(new Color(0, 0, 0));
	lbl5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl5.setBounds(573, 453, 300, 100);
	f.getContentPane().add(lbl5);
	
	
	chbx6sem = new JCheckBox("6th Sem");
	chbx6sem.setForeground(new Color(205, 133, 63));
	chbx6sem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	chbx6sem.setOpaque(false);
	chbx6sem.setBounds(850, 450, 250, 100);
	f.getContentPane().add(chbx6sem);
	
	lbl6 = new JLabel("6th Sem");
	lbl6.setForeground(new Color(0, 0, 0));
	lbl6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 50));
	lbl6.setBounds(873, 453, 300, 100);
	f.getContentPane().add(lbl6);
	
	
	JLabel lblview = new JLabel("View");
	lblview.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblview.setHorizontalAlignment(SwingConstants.CENTER);
	lblview.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(lblview);
	
	btnview = new JButton("");
	btnview.setIcon(new ImageIcon("img\\button.png"));
	btnview.setBackground(null);
	btnview.setOpaque(false);
	btnview.setBorder(null);
	btnview.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.next();
		}
	});
	btnview.setBounds(1130, 600, 130, 100);
	f.getContentPane().add(btnview);
	
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
