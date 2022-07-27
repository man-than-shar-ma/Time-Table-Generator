package timetable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.DefaultComboBoxModel;

public class Gen {

	JFrame f;
	JLabel lbl;
	JLabel lbl1;
	JButton btnback;
	JButton btnnext;
	JLabel lblsem;
	JLabel lblsem1;
	JLabel lblallocation;
	JLabel lblallocation1;
	JComboBox<String> cbsem;
	JComboBox<String> cballocation;
	JLabel img;
	static int s1,s2,s3;
	JComboBox<String> cbbranch;
	static String singlebranch;
	
	
	int sub[][][]=new int[10][6][2];
	int tch[][]=new int[10][6];
	
	int branch;
	
	static Gen o=new Gen();
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
		o.retrievebranches();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void back() {
		Timetable.main(null);
		f.dispose();
	}
	void retrievebranches() {
		
		cbbranch.addItem("All");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select name from branch order by name");
			while(rs.next()) {
				cbbranch.addItem(rs.getString(1));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	void next() {
		singlebranch="";
		branch=0;
		String bname1[]=new String[10];
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs = s.executeQuery("select name from branch order by name");
			while(rs.next()) {
				bname1[branch]=rs.getString(1);
				int sem=1;
				while(sem<=6) {
				PreparedStatement ps=conn.prepareStatement("Select count(*) from teacher where (branch = ? or branch=?) and sem"+sem+"=true");
				ps.setString(1, rs.getString(1));
				ps.setString(2, "common");
				ResultSet rsp=ps.executeQuery();
				while(rsp.next()) {
					tch[branch][sem-1]=rsp.getInt(1);
				}
				
				PreparedStatement ps2=conn.prepareStatement("Select count(*) from subject where branch=? and th=true");
				ps2.setString(1, rs.getString(1));
				ResultSet rsp2=ps2.executeQuery();
				while(rsp2.next()) {
					sub[branch][sem-1][0]=rsp2.getInt(1);
				}
				
				PreparedStatement ps3=conn.prepareStatement("Select count(*) from subject where branch=? and pr=true");
				ps3.setString(1, rs.getString(1));
				ResultSet rsp3=ps3.executeQuery();
				while(rsp3.next()) {
					sub[branch][sem-1][1]=rsp3.getInt(1);
				}
				
				sem++;
				}
				branch++;
			}
			conn.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for(int b=0;b<branch;b++) {
			for(int s=0;s<6;s++) {
				if(sub[b][s][0]<4) {
					JOptionPane.showMessageDialog(null, "The amount of theoritical subjects in "+bname1[b]+" sem"+(s+1)+" is very less");
				}
				else if(sub[b][s][1]<4) {
					JOptionPane.showMessageDialog(null, "The amount of practical subjects in "+bname1[b]+" sem"+(s+1)+" is very less");
				}
				else if(tch[b][s]<4) {
					JOptionPane.showMessageDialog(null, "The amount of faculty in "+bname1[b]+" sem"+(s+1)+" is less");
					return;
				}
			}
		}
		
		String cbs=cbsem.getSelectedItem().toString();
		String cba=cballocation.getSelectedItem().toString();
		if(cbs.contentEquals("1st sem, 3rd sem and 5th sem")) {
			s1=1;
			s2=3;
			s3=5;
		}
		else {
			s1=2;
			s2=4;
			s3=6;
		}
		
		if(cba.contentEquals("Random")) {
			if(cbbranch.getSelectedItem().toString().contentEquals("All")) {
				JOptionPane.showMessageDialog(null, "Please wait, It may take some time...");
				Generatenew.main(null);
				f.dispose();
			}
			else {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
					
					PreparedStatement s=conn.prepareStatement("Select id,name from branch where name=?");
					s.setString(1, cbbranch.getSelectedItem().toString());
					ResultSet rs=s.executeQuery();
					while(rs.next()) {
						singlebranch=rs.getString(2);
					}
					conn.close();
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Note : This Time Table will be like an independent time table");
				Generatenew.main(null);
				f.dispose();
			}
		}
		else {
			if(cbbranch.getSelectedItem().toString().contentEquals("All")) {
			try {
				
				Boolean flag=false;
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
				Statement s=conn.createStatement();
				ResultSet rs=s.executeQuery("Select id,name from branch order by id");
				while(rs.next()) {
				for(int sid=s1;sid<6;sid=sid+2) {
				int bid=rs.getInt(1);
				String bname=rs.getString(2);
				String idlike = Homepage.sch*1000+bid*10+sid+"%";
				int tempcount=0;
				int tempcounttch=0;
				
				
				PreparedStatement ps1=conn.prepareStatement("Select ht from subject where id like ? && th=true");
				ps1.setString(1, idlike);
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next()) {
					tempcount=tempcount+rs1.getInt(1);
					if(rs1.getInt(1)==0) {
						flag=true;
					}
				}
				PreparedStatement ps2=conn.prepareStatement("Select hpr from subject where id like ? && pr=true");
				ps2.setString(1, idlike);
				ResultSet rs2=ps2.executeQuery();
				while(rs2.next()) {
					tempcount=tempcount+rs2.getInt(1);
					if(rs2.getInt(1)==0) {
						flag=true;
					}
				}
				PreparedStatement ps3=conn.prepareStatement("Select sca_time,lib_time from extrasubjects where sem=? and branch=?");
				ps3.setInt(1, sid);
				ps3.setString(2, bname);
				ResultSet rs3=ps3.executeQuery();
				while(rs3.next()) {
					tempcount=tempcount+rs3.getInt(1);
					tempcount=tempcount+rs3.getInt(2);
					if(rs3.getInt(1)==0||rs3.getInt(2)==0) {
						flag=true;
					}
				}
				if(tempcount!=42||flag==true) {
					JOptionPane.showMessageDialog(null, "There is some problem in the subjects timing in branch : "+bname+" sem"+sid+", please check it in subject filter page");
					return;
				}
				
				PreparedStatement ps4=conn.prepareStatement("Select count(*) from subject where id like ? and tname1=? and tname2=? and tname3=?");
				ps4.setString(1, idlike);
				ps4.setString(2, "none");
				ps4.setString(3, "none");
				ps4.setString(4, "none");
				ResultSet rs4=ps4.executeQuery();
				while(rs4.next()) {
					tempcounttch=tempcounttch+rs4.getInt(1);
				}
				PreparedStatement ps5=conn.prepareStatement("Select count(*) from extrasubjects where sem=? and branch=? and (sca_name=? or lib_time=?)");
				ps5.setInt(1, sid);
				ps5.setString(2, bname);
				ps5.setString(3, "none");
				ps5.setString(4, "none");
				ResultSet rs5=ps5.executeQuery();
				while(rs5.next()) {
					tempcounttch=tempcounttch+rs5.getInt(1);
				}
				if(tempcounttch>0) {
					JOptionPane.showMessageDialog(null, "There is some problem in the teachers allocation in branch : "+bname+" sem"+sid+", please check it in faculty filter page");
					return;
				}
				
				}
				}
				conn.close();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "Please wait, It may take some time...");
			GenCustom.main(null);
			f.dispose();
			
		}
		else {
			try {
				
			Boolean flag=false;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement s=conn.prepareStatement("Select id,name from branch where name=?");
			s.setString(1, cbbranch.getSelectedItem().toString());
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
			for(int sid=s1;sid<6;sid=sid+2) {
			int bid=rs.getInt(1);
			String bname=rs.getString(2);
			singlebranch=rs.getString(2);
			String idlike = Homepage.sch*1000+bid*10+sid+"%";
			int tempcount=0;
			int tempcounttch=0;
			
			
			PreparedStatement ps1=conn.prepareStatement("Select ht from subject where id like ? && th=true");
			ps1.setString(1, idlike);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next()) {
				tempcount=tempcount+rs1.getInt(1);
				if(rs1.getInt(1)==0) {
					flag=true;
				}
			}
			PreparedStatement ps2=conn.prepareStatement("Select hpr from subject where id like ? && pr=true");
			ps2.setString(1, idlike);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				tempcount=tempcount+rs2.getInt(1);
				if(rs2.getInt(1)==0) {
					flag=true;
				}
			}
			PreparedStatement ps3=conn.prepareStatement("Select sca_time,lib_time from extrasubjects where sem=? and branch=?");
			ps3.setInt(1, sid);
			ps3.setString(2, bname);
			ResultSet rs3=ps3.executeQuery();
			while(rs3.next()) {
				tempcount=tempcount+rs3.getInt(1);
				tempcount=tempcount+rs3.getInt(2);
				if(rs3.getInt(1)==0||rs3.getInt(2)==0) {
					flag=true;
				}
			}
			if(tempcount!=42||flag==true) {
				JOptionPane.showMessageDialog(null, "There is some problem in the subjects timing in branch : "+bname+" sem"+sid+", please check it in subject filter page");
				return;
			}
			
			PreparedStatement ps4=conn.prepareStatement("Select count(*) from subject where id like ? and tname1=? and tname2=? and tname3=?");
			ps4.setString(1, idlike);
			ps4.setString(2, "none");
			ps4.setString(3, "none");
			ps4.setString(4, "none");
			ResultSet rs4=ps4.executeQuery();
			while(rs4.next()) {
				tempcounttch=tempcounttch+rs4.getInt(1);
			}
			PreparedStatement ps5=conn.prepareStatement("Select count(*) from extrasubjects where sem=? and branch=? and (sca_name=? or lib_time=?)");
			ps5.setInt(1, sid);
			ps5.setString(2, bname);
			ps5.setString(3, "none");
			ps5.setString(4, "none");
			ResultSet rs5=ps5.executeQuery();
			while(rs5.next()) {
				tempcounttch=tempcounttch+rs5.getInt(1);
			}
			if(tempcounttch>0) {
				JOptionPane.showMessageDialog(null, "There is some problem in the teachers allocation in branch : "+bname+" sem"+sid+", please check it in faculty filter page");
				return;
			}
			
			}
			}
			conn.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		JOptionPane.showMessageDialog(null, "Note : This Time Table will be like an independent time table");
		GenCustom.main(null);
		f.dispose();
		
	}

		}
		
	}
	
	void body() {
		lbl = new JLabel("Time Table");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lbl1 = new JLabel("Time Table");
		lbl1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl1.setForeground(new Color(0, 0, 0));
		lbl1.setBackground(new Color(240, 240, 240));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lbl1.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl1);
		
		
		
		lblsem = new JLabel("Select Semester : ");
		lblsem.setForeground(new Color(205, 133, 63));
		lblsem.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem.setBounds(200, 300, 400, 40);
		f.getContentPane().add(lblsem);
		
		lblsem1 = new JLabel("Select Semester : ");
		lblsem1.setForeground(new Color(0, 0, 0));
		lblsem1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsem1.setBounds(203, 303, 400, 40);
		f.getContentPane().add(lblsem1);
		
		
		lblallocation = new JLabel("Select Allocation Type : ");
		lblallocation.setForeground(new Color(205, 133, 63));
		lblallocation.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblallocation.setBounds(200, 400, 400, 40);
		f.getContentPane().add(lblallocation);
		
		lblallocation1 = new JLabel("Select Allocation Type : ");
		lblallocation1.setForeground(new Color(0, 0, 0));
		lblallocation1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblallocation1.setBounds(203, 403, 400, 40);
		f.getContentPane().add(lblallocation1);
		
		JLabel lblbranch = new JLabel("Select Branch : ");
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch.setBounds(200, 500, 400, 40);
		f.getContentPane().add(lblbranch);
		
		JLabel lblSelectBranch = new JLabel("Select Branch : ");
		lblSelectBranch.setForeground(Color.BLACK);
		lblSelectBranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblSelectBranch.setBounds(203, 503, 400, 40);
		f.getContentPane().add(lblSelectBranch);
		
		cbsem = new JComboBox<String>();
		cbsem.setModel(new DefaultComboBoxModel<String>(new String[] {"1st sem, 3rd sem and 5th sem", "2nd sem, 4th sem and 6th sem"}));
		cbsem.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cbsem.setBounds(600, 300, 500, 40);
		f.getContentPane().add(cbsem);
		
		cballocation = new JComboBox<String>();
		cballocation.setModel(new DefaultComboBoxModel<String>(new String[] {"Random", "Custom"}));
		cballocation.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cballocation.setBounds(600, 400, 250, 40);
		f.getContentPane().add(cballocation);
		
		cbbranch = new JComboBox<String>();
		cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cbbranch.setBounds(600, 500, 500, 40);
		f.getContentPane().add(cbbranch);
		
		
		
		
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
		
		
		JLabel lblnext = new JLabel("Next");
		lblnext.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblnext.setHorizontalAlignment(SwingConstants.CENTER);
		lblnext.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblnext);		
		
		btnnext = new JButton("");
		btnnext.setIcon(new ImageIcon("img\\button.png"));
		btnnext.setBackground(null);
		btnnext.setOpaque(false);
		btnnext.setBorder(null);
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.next();
			}
		});
		btnnext.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnnext);
		
		
		
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
