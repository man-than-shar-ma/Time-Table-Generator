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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Addsubject {
static JFrame f;
JLabel lbl;
JLabel label;
JButton btnback;
JButton btnsave;
JLabel lblfullname;
JLabel lbltype;
JLabel lblbranch;
JLabel lblsemester;
JCheckBox chbxth;
JCheckBox chbxpr; 
JCheckBox chbxCombinedPr;
JComboBox<String> cbbranch;
JLabel lblTeachers;
JComboBox<String> cbTeachers;
JRadioButton rb1;
JRadioButton rb2;
JRadioButton rb3;
JRadioButton rb4;
JRadioButton rb5;
JRadioButton rb6;
static Addsubject o=new Addsubject();
JTextField tffullname;
JTextField tfname;
JLabel img;
private final ButtonGroup buttonGroup = new ButtonGroup();

int id;
int maxid;
int tempid;
private JLabel lblfullname1;
private JLabel lblname1;
private JLabel lbltype1;
private JLabel lblbranch1;
private JLabel lblrb1;
private JLabel lblrb2;
private JLabel lblrb3;
private JLabel lblrb4;
private JLabel lblrb6;
private JLabel lblTheory;
private JLabel lblPractical;
private JLabel lblteachers1;


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

void save(){
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		
		String fname=tffullname.getText();
		String name=tfname.getText();
		boolean th=chbxth.isSelected();
		boolean pr=chbxpr.isSelected();
		String branch=cbbranch.getSelectedItem().toString();
		String sem1=buttonGroup.getSelection().getActionCommand();
		int sem=Integer.parseInt(sem1);
		Boolean prcomb=chbxCombinedPr.isSelected();
		int prteacher = Integer.parseInt(cbTeachers.getSelectedItem().toString());
		
		String tempname1="",tempsname1="";
		String tempname,tempsname;
		String tempname2,tempsname2;
		
		tempname=fname.toLowerCase();
		tempsname=name.toLowerCase();
		
		
		PreparedStatement s4=conn.prepareStatement("Select name,sname from subject where branch=? && id like ?");
		s4.setString(1, branch);
		s4.setString(2, Homepage.sch+"%");
		ResultSet rs4=s4.executeQuery();
		while(rs4.next()) {
			  tempname1=rs4.getString(1);
			  tempsname1=rs4.getString(2);
			  tempname2=tempname1.toLowerCase();
				tempsname2=tempsname1.toLowerCase();
			  
			  if(tempname.contentEquals(tempname2)||tempsname.contentEquals(tempsname2)) {
					JOptionPane.showMessageDialog(null, "Name already exist");
					return;
				}
		 }
		
		
		
		if(fname.contentEquals("")) {
			JOptionPane.showMessageDialog(null,"Subject Name can't be Empty");
		}
		else if(name.contentEquals("")){
			JOptionPane.showMessageDialog(null, "Acronym Can't be Empty");
		}
		else if(th==false&&pr==false) {
			JOptionPane.showMessageDialog(null, "Select at least one subject type");
		}
		else if(sem==0) {
			JOptionPane.showMessageDialog(null, "You have to select the Semester");
		}
		
		else {
			
			PreparedStatement s1=conn.prepareStatement("Select id from subject where branch=? && id like ?");
			s1.setString(1, branch);
			s1.setString(2, 10*Homepage.sch+sem+"%");
			ResultSet rs1=s1.executeQuery();
			int count=0;
			while(rs1.next()) {
				count++;
			}
			if(count>15) {
				JOptionPane.showMessageDialog(null, "Max Limit Reached i.e. 15");
			}
			
			else {
			int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Add this Subject","Add Subject",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				id=id(branch,sem);
				
				
				
				PreparedStatement s2=conn.prepareStatement("insert into subject values(?,?,?,?,?,?,?,?,?,0,0,'none','none','none')");
				s2.setInt(1, id);
				s2.setString(2, fname);
				s2.setString(3, name);
				s2.setBoolean(4, th);
				s2.setBoolean(5, pr);
				s2.setString(6, branch);
				s2.setInt(7, sem);
				s2.setBoolean(8, prcomb);
				s2.setInt(9, prteacher);
				s2.executeUpdate();
				
				
				JOptionPane.showMessageDialog(null, "Subject Added");
				conn.close();
				Subjects.main(null);
				f.dispose();
			}
			}
		}
	} 
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

int id(String b,int s) {
	int id=1;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		int subid=1;
		int maxid=1;
		int semid=s;
		int bid=0;
		
		PreparedStatement ps= conn.prepareStatement("Select id from branch where name = ?");
		ps.setString(1, b);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bid = rs.getInt(1);
		}
		
		
		int sid=Homepage.sch*100000+bid*1000+semid*100+subid;
		int lid=Homepage.sch*100000+bid*1000+(semid+1)*100+subid;
		
		PreparedStatement ps1=conn.prepareStatement("select max(id) from subject where id>=?&&id<?");
		ps1.setInt(1, sid);
		ps1.setInt(2, lid);
		
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next()) {
			  maxid=rs1.getInt(1);
		 }
		
		for(id=sid;id<=maxid+1;id++)
		{
		tempid=0;
		PreparedStatement s3=conn.prepareStatement("select id from subject where id=?");
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
	Subjects.main(null);
	f.dispose();
}

void body() {
	lbl = new JLabel("Add Subject");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("Add Subject");
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
	
	lblfullname = new JLabel("Subject Name : ");
	lblfullname.setForeground(new Color(205, 133, 63));
	lblfullname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblfullname.setBounds(200, 250, 400, 40);
	f.getContentPane().add(lblfullname);
	
	lblfullname1 = new JLabel("Subject Name : ");
	lblfullname1.setForeground(new Color(0, 0, 0));
	lblfullname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblfullname1.setBounds(203, 253, 400, 40);
	f.getContentPane().add(lblfullname1);
	
	JLabel lblname = new JLabel("Acronym :");
	lblname.setForeground(new Color(205, 133, 63));
	lblname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblname.setBounds(200, 300, 400, 40);
	f.getContentPane().add(lblname);
	
	lblname1 = new JLabel("Acronym :");
	lblname1.setForeground(new Color(0, 0, 0));
	lblname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblname1.setBounds(203, 303, 400, 40);
	f.getContentPane().add(lblname1);
	
	lbltype = new JLabel("Subject Type : ");
	lbltype.setForeground(new Color(205, 133, 63));
	lbltype.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbltype.setBounds(200, 350, 400, 40);
	f.getContentPane().add(lbltype);
	
	lbltype1 = new JLabel("Subject Type : ");
	lbltype1.setForeground(new Color(0, 0, 0));
	lbltype1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbltype1.setBounds(203, 353, 400, 40);
	f.getContentPane().add(lbltype1);
	
	JLabel lbladditionaloptions = new JLabel("Additional Options : ");
	lbladditionaloptions.setForeground(new Color(205, 133, 63));
	lbladditionaloptions.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbladditionaloptions.setBounds(200, 400, 400, 40);
	f.getContentPane().add(lbladditionaloptions);
	
	JLabel lbladditionaloptions1 = new JLabel("Additional Options : ");
	lbladditionaloptions1.setForeground(new Color(0, 0, 0));
	lbladditionaloptions1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lbladditionaloptions1.setBounds(203, 403, 400, 40);
	f.getContentPane().add(lbladditionaloptions1);
	
	lblbranch = new JLabel("Branch : ");
	lblbranch.setForeground(new Color(205, 133, 63));
	lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblbranch.setBounds(200, 450, 400, 40);
	f.getContentPane().add(lblbranch);
	
	lblbranch1 = new JLabel("Branch : ");
	lblbranch1.setForeground(new Color(0, 0, 0));
	lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblbranch1.setBounds(203, 453, 400, 40);
	f.getContentPane().add(lblbranch1);
	
	lblsemester = new JLabel("Semester : ");
	lblsemester.setForeground(new Color(205, 133, 63));
	lblsemester.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblsemester.setBounds(200, 500, 400, 40);
	f.getContentPane().add(lblsemester);
	
	JLabel lblsemester1 = new JLabel("Semester : ");
	lblsemester1.setForeground(new Color(0, 0, 0));
	lblsemester1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblsemester1.setBounds(203, 503, 400, 40);
	f.getContentPane().add(lblsemester1);
	
	tffullname = new JTextField();
	tffullname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
	tffullname.setBounds(600, 250, 600, 40);
	f.getContentPane().add(tffullname);
	tffullname.setColumns(10);
	
	tfname = new JTextField();
	tfname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
	tfname.setColumns(10);
	tfname.setBounds(600, 300, 600, 40);
	f.getContentPane().add(tfname);
	
	chbxth = new JCheckBox("Theory");
	chbxth.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if(chbxth.isSelected()) {
				chbxCombinedPr.setSelected(false);
				chbxCombinedPr.setEnabled(false);
				lblTeachers.setEnabled(false);
				cbTeachers.setSelectedItem("1");
				cbTeachers.setEnabled(false);
			}
			else {
					if(chbxpr.isSelected()) {
					
					chbxCombinedPr.setEnabled(true);
					lblTeachers.setEnabled(true);
					cbTeachers.setEnabled(true);
				}
					else {
					chbxCombinedPr.setSelected(false);
					chbxCombinedPr.setEnabled(false);
					lblTeachers.setEnabled(false);
					cbTeachers.setSelectedItem("1");
					cbTeachers.setEnabled(false);
				}
			}
		}
	});
	chbxth.setForeground(new Color(205, 133, 63));
	chbxth.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	chbxth.setOpaque(false);
	chbxth.setBounds(600, 350, 300, 40);
	f.getContentPane().add(chbxth);
	
	lblTheory = new JLabel("Theory");
	lblTheory.setForeground(new Color(0, 0, 0));
	lblTheory.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblTheory.setBounds(623, 353, 300, 40);
	f.getContentPane().add(lblTheory);
	
	chbxpr = new JCheckBox("Practical");
	chbxpr.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if(!chbxth.isSelected()) {
				if(chbxpr.isSelected()) {
					chbxCombinedPr.setEnabled(true);
					lblTeachers.setEnabled(true);
					cbTeachers.setEnabled(true);
				}
			else {
				chbxCombinedPr.setSelected(false);
				chbxCombinedPr.setEnabled(false);
				lblTeachers.setEnabled(false);
				cbTeachers.setSelectedItem("1");
				cbTeachers.setEnabled(false);
			}
			}
		}
	});
	
	chbxpr.setForeground(new Color(205, 133, 63));
	chbxpr.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	chbxpr.setOpaque(false);
	chbxpr.setBounds(900, 350, 300, 40);
	f.getContentPane().add(chbxpr);
	
	lblPractical = new JLabel("Practical");
	lblPractical.setForeground(new Color(0, 0, 0));
	lblPractical.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblPractical.setBounds(923, 353, 300, 40);
	f.getContentPane().add(lblPractical);
	
	chbxCombinedPr = new JCheckBox("Combined Pr.");
	chbxCombinedPr.setEnabled(false);
	chbxCombinedPr.setOpaque(false);
	chbxCombinedPr.setForeground(new Color(205, 133, 63));
	chbxCombinedPr.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	chbxCombinedPr.setBounds(600, 400, 300, 40);
	f.getContentPane().add(chbxCombinedPr);
	
	JLabel lblCombinedPr = new JLabel("Combined pr.");
	lblCombinedPr.setForeground(new Color(0, 0, 0));
	lblCombinedPr.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblCombinedPr.setBounds(623, 403, 300, 40);
	f.getContentPane().add(lblCombinedPr);
	
	lblTeachers = new JLabel("Teachers :");
	lblTeachers.setEnabled(false);
	lblTeachers.setForeground(new Color(205, 133, 63));
	lblTeachers.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblTeachers.setBounds(920, 400, 300, 40);
	f.getContentPane().add(lblTeachers);
	
	
	lblteachers1 = new JLabel("Teachers :");
	lblteachers1.setForeground(new Color(0, 0, 0));
	lblteachers1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblteachers1.setBounds(923, 403, 300, 40);
	f.getContentPane().add(lblteachers1);
	
	cbTeachers = new JComboBox<String>();
	cbTeachers.setEnabled(false);
	cbTeachers.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3"}));
	cbTeachers.setBounds(1100, 400, 50, 40);
	f.getContentPane().add(cbTeachers);
	
	cbbranch = new JComboBox<String>();
	cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
	o.retrievebranch();
	cbbranch.setBounds(600, 450, 300, 40);
	f.getContentPane().add(cbbranch);
	
	
	rb1 = new JRadioButton("1");
	rb1.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb1);
	rb1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb1.setOpaque(false);
	rb1.setBounds(600, 500, 50, 40);
	rb1.setActionCommand("1");
	f.getContentPane().add(rb1);
	
	lblrb1 = new JLabel("1");
	lblrb1.setForeground(new Color(0, 0, 0));
	lblrb1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb1.setBounds(623, 503, 50, 40);
	f.getContentPane().add(lblrb1);
	
	rb2 = new JRadioButton("2");
	rb2.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb2);
	rb2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb2.setOpaque(false);
	rb2.setBounds(600, 550, 50, 40);
	rb2.setActionCommand("2");
	f.getContentPane().add(rb2);
	
	lblrb2 = new JLabel("2");
	lblrb2.setForeground(new Color(0, 0, 0));
	lblrb2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb2.setBounds(623, 553, 50, 40);
	f.getContentPane().add(lblrb2);
	
	rb3 = new JRadioButton("3");
	rb3.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb3);
	rb3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb3.setOpaque(false);
	rb3.setBounds(700, 500, 50, 40);
	rb3.setActionCommand("3");
	f.getContentPane().add(rb3);
	
	lblrb3 = new JLabel("3");
	lblrb3.setForeground(new Color(0, 0, 0));
	lblrb3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb3.setBounds(723, 503, 50, 40);
	f.getContentPane().add(lblrb3);
	
	rb4 = new JRadioButton("4");
	rb4.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb4);
	rb4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb4.setOpaque(false);
	rb4.setBounds(700, 550, 50, 40);
	rb4.setActionCommand("4");
	f.getContentPane().add(rb4);
	
	lblrb4 = new JLabel("4");
	lblrb4.setForeground(new Color(0, 0, 0));
	lblrb4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb4.setBounds(723, 553, 50, 40);
	f.getContentPane().add(lblrb4);
	
	rb5 = new JRadioButton("5");
	rb5.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb5);
	rb5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb5.setOpaque(false);
	rb5.setBounds(800, 500, 50, 40);
	rb5.setActionCommand("5");
	f.getContentPane().add(rb5);
	
	JLabel lblrb5 = new JLabel("5");
	lblrb5.setForeground(new Color(0, 0, 0));
	lblrb5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb5.setBounds(823, 503, 50, 40);
	f.getContentPane().add(lblrb5);
	
	rb6 = new JRadioButton("6");
	rb6.setForeground(new Color(205, 133, 63));
	buttonGroup.add(rb6);
	rb6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb6.setOpaque(false);
	rb6.setBounds(800, 550, 50, 40);
	rb6.setActionCommand("6");
	f.getContentPane().add(rb6);
	
	lblrb6 = new JLabel("6");
	lblrb6.setForeground(new Color(0, 0, 0));
	lblrb6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	lblrb6.setBounds(823, 553, 50, 40);
	f.getContentPane().add(lblrb6);
	
	
	
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
	
	JRadioButton rb0 = new JRadioButton("0");
	rb0.setEnabled(false);
	buttonGroup.add(rb0);
	rb0.setForeground(new Color(205, 133, 63));
	rb0.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
	rb0.setOpaque(false);
	rb0.setSelected(true);
	rb0.setActionCommand("0");
	rb0.setBounds(510, 500, 50, 40);
	f.getContentPane().add(rb0);
	
}
}
