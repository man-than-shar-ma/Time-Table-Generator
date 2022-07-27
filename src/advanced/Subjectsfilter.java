package advanced;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import homepage.Homepage;
import homepage.Welcome;

public class Subjectsfilter {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnback;
	JButton btnb1;
	JButton btnn1;
	JButton btnb2;
	JButton btnn2;
	
	JLabel lblbranch;
	JLabel lblbranch1;
	JLabel lblsemester;
	JLabel lblsemester1;
	
	JLabel st11;
	JLabel st12;
	
	JLabel lblresetall;
	JButton btnresetall;
	JLabel lblreset;
	JButton btnreset;
	
	JLabel lbllectureslefttext;
	JLabel lbllectureslefttext1;
	JLabel lbllecturesleftvalue;
	JLabel lbllecturesleftvalue1;
	
	int total_lectures;
	int lectures_used;
	int lectures_remaining;
	
	int sbcountth=0;
	int sbcountpr=0;
	int branchid[];
	String branchname[];
	int semid[];
	String semname[];
	String tempbrname[];
	
	int bid;
	String bname;
	int sid;
	String sname;
	
	String snameth[];
	String snamepr[];
	JLabel lth[];
	JLabel lth1[];
	JLabel lpr[];
	JLabel lpr1[];
	
	JLabel lblsca;
	JLabel lblsca1;
	JLabel lbllib;
	JLabel lbllib1;
	
	SpinnerModel sth[];
	SpinnerModel spr[];
	JSpinner jsth[];
	JSpinner jspr[];
	
	SpinnerModel ssca;
	SpinnerModel slib;
	JSpinner jssca;
	JSpinner jslib;
	
	JLabel img;
	static Subjectsfilter o=new Subjectsfilter();
	
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
		JOptionPane.showMessageDialog(null, "The data here will be automaically saved");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void back() {
		Advanced.main(null);
		f.dispose();
	}
	
	void retrieve(int countth,int countpr) {
		
		String idlike = Homepage.sch*1000+bid*10+sid+"%";
		int thvalue[]=new int[countth];
		int prvalue[]=new int[countpr];
		int countextrasca=0;
		int countextralib=0;
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps=conn.prepareStatement("Select ht from subject where id like ? && th=true");
			ps.setString(1, idlike);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				thvalue[i]=rs.getInt(1);
				i++;
			}
			i=0;
			PreparedStatement ps2=conn.prepareStatement("Select hpr from subject where id like ? && pr=true");
			ps2.setString(1, idlike);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				prvalue[i]=rs2.getInt(1);
				i++;
			}
			
			
			PreparedStatement ps3=conn.prepareStatement("Select sca_time,lib_time from extrasubjects where sem=? and branch=?");
			ps3.setInt(1, sid);
			ps3.setString(2, bname);
			ResultSet rs3=ps3.executeQuery();
			while(rs3.next()) {
				countextrasca=rs3.getInt(1);
				countextralib=rs3.getInt(2);
			}
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		for(i=0;i<countth;i++) {
			jsth[i].setValue(thvalue[i]);
		}
		for(i=0;i<countpr;i++) {
			jspr[i].setValue(prvalue[i]);
		}
		
		jssca.setValue(countextrasca);
		jslib.setValue(countextralib);
		
	}
	
	void filter(int countth,int countpr)
	{	

		int margin=30;
		
		int totalwidth=1360;
		int remainingthwidth=0;
		int remainingprwidth=0;
		int lbwidthth=0;
		int lbwidthpr=0;
		
		
		if(countth!=0) {
			remainingthwidth = totalwidth-(countth+1)*margin;
			lbwidthth= remainingthwidth/countth;
		}
		if(countpr!=0) {
			remainingprwidth = totalwidth-(countpr+1)*margin;
			lbwidthpr= remainingprwidth/countpr;
		}
		
		String idlike = Homepage.sch*1000+bid*10+sid+"%";
		snameth=new String[countth];
		snamepr=new String[countpr];
		
		int j=0;
		int k=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps= conn.prepareStatement("Select sname from subject where id like ? && th=true");
			ps.setString(1, idlike);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				snameth[j]=rs.getString(1)+" (th)";
				j++;
			}
			
			PreparedStatement ps2=conn.prepareStatement("Select sname from subject where id like ? && pr=true");
			ps2.setString(1, idlike);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				snamepr[k]=rs2.getString(1)+" (pr)";
				k++;
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		lth = new JLabel[countth];
		lth1 = new JLabel[countth];
		sth=new SpinnerNumberModel[countth];
		for(int i=0; i<countth; i++) {
		sth[i]=new SpinnerNumberModel(0,0,12,1);	
		}
		jsth= new JSpinner[countth];
		for(int i=0; i<countth; i++) {
			jsth[i]=new JSpinner(sth[i]);
		}
		
		lpr=new JLabel[countpr];
		lpr1=new JLabel[countpr];
		spr=new SpinnerNumberModel[countpr];
		for(int i= 0; i<countpr ;  i++) {
			spr[i]=new SpinnerNumberModel(0,0,12,2);
		}
		jspr= new JSpinner[countpr];
		for(int i=0; i<countpr; i++) {
			jspr[i]=new JSpinner(spr[i]);
		}
		
		int pos=margin;
		j=0;
		for(int i=0;i<countth;i++) {
			
			lth[i] = new JLabel(snameth[j]);
			lth[i].setForeground(new Color(205, 133, 63));
			lth[i].setHorizontalAlignment(SwingConstants.CENTER);
			lth[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countth));
			lth[i].setBounds(pos, 300, lbwidthth, 40);
			f.getContentPane().add(lth[i]);
			
			lth1[i] = new JLabel(snameth[j]);
			lth1[i].setForeground(new Color(0, 0, 0));
			lth1[i].setHorizontalAlignment(SwingConstants.CENTER);
			lth1[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countth));
			lth1[i].setBounds(pos, 303, lbwidthth, 40);
			f.getContentPane().add(lth1[i]);
			
			jsth[i].setFont(new Font("Kristen ITC", Font.PLAIN, 15-(countth/2)));
			jsth[i].setBounds(pos, 350, lbwidthth, 40);
			((JSpinner.DefaultEditor)jsth[i].getEditor()).getTextField().setEditable(false);
			jsth[i].addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					o.lectures();
				}
			});
			f.getContentPane().add(jsth[i]);
			
			j++;
			pos=pos+lbwidthth+margin;
			
		}
		
		pos=margin;
		k=0;
		for(int i=0;i<countpr;i++) {
			
			lpr[i] = new JLabel(snamepr[k]);
			lpr[i].setForeground(new Color(205, 133, 63));
			lpr[i].setHorizontalAlignment(SwingConstants.CENTER);
			lpr[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countpr));
			lpr[i].setBounds(pos, 450, lbwidthpr, 40);
			f.getContentPane().add(lpr[i]);
			
			lpr1[i] = new JLabel(snamepr[k]);
			lpr1[i].setForeground(new Color(0, 0, 0));
			lpr1[i].setHorizontalAlignment(SwingConstants.CENTER);
			lpr1[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countpr));
			lpr1[i].setBounds(pos, 453, lbwidthpr, 40);
			f.getContentPane().add(lpr1[i]);
			
			jspr[i].setFont(new Font("Kristen ITC", Font.PLAIN, 15-(countpr/2)));
			jspr[i].setBounds(pos, 500, lbwidthpr, 40);
			((JSpinner.DefaultEditor)jspr[i].getEditor()).getTextField().setEditable(false);
			jspr[i].addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					o.lectures();
				}
			});
			f.getContentPane().add(jspr[i]);
			
			k++;
			pos=pos+lbwidthpr+margin;
			
		}
		
		lblsca=new JLabel("SCA");
		lblsca.setForeground(new Color(205, 133, 63));
		lblsca.setHorizontalAlignment(SwingConstants.CENTER);
		lblsca.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsca.setBounds(350, 600, 100, 40);
		f.getContentPane().add(lblsca);
		
		lblsca1=new JLabel("SCA");
		lblsca1.setForeground(new Color(0,0,0));
		lblsca1.setHorizontalAlignment(SwingConstants.CENTER);
		lblsca1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblsca1.setBounds(353, 603, 100, 40);
		f.getContentPane().add(lblsca1);
		
		lbllib=new JLabel("Lib.");
		lbllib.setForeground(new Color(205, 133, 63));
		lbllib.setHorizontalAlignment(SwingConstants.CENTER);
		lbllib.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbllib.setBounds(900, 600, 100, 40);
		f.getContentPane().add(lbllib);
		
		lbllib1=new JLabel("Lib.");
		lbllib1.setForeground(new Color(0, 0, 0));
		lbllib1.setHorizontalAlignment(SwingConstants.CENTER);
		lbllib1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbllib1.setBounds(903, 603, 100, 40);
		f.getContentPane().add(lbllib1);
		
		ssca=new SpinnerNumberModel(0,0,4,1);
		slib=new SpinnerNumberModel(0,0,4,1);
		jssca=new JSpinner(ssca);
		jslib = new JSpinner(slib);
		
		jssca.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		jssca.setBounds(350, 650, 100, 40);
		((JSpinner.DefaultEditor)jssca.getEditor()).getTextField().setEditable(false);
		jssca.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				o.lectures();
			}
		});
		f.getContentPane().add(jssca);
		
		jslib.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		jslib.setBounds(900, 650, 100, 40);
		((JSpinner.DefaultEditor)jslib.getEditor()).getTextField().setEditable(false);
		jslib.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				o.lectures();
			}
		});
		f.getContentPane().add(jslib);
		
		
		
		
	}
	
	void getBranch() {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(distinct(branch)) from subject");
			
			while(rs.next()) {
				branchid=new int[rs.getInt(1)];
				branchname= new String[rs.getInt(1)];
				tempbrname=new String[rs.getInt(1)];
			}
			int i=0;
			int count=0;
			
			Statement s2=conn.createStatement();
			ResultSet rs2=s2.executeQuery("Select distinct(branch) from subject");
			
			while(rs2.next()) {
				tempbrname[i]=rs2.getString(1);
				i++;
				count++;
			}
			
			i=0;
			
			while(i<count) {
			PreparedStatement s1=conn.prepareStatement("Select id,name from branch where name=?");
			s1.setString(1, tempbrname[i]);
			ResultSet rs1=s1.executeQuery();
			while(rs1.next()) {
				branchid[i]=rs1.getInt(1);
				branchname[i]=rs1.getString(2);
				i++;
			}
		}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		bid=branchid[0];
		bname=branchname[0];
	}
	
	void getSem() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);

			PreparedStatement ps=conn.prepareStatement("Select count(distinct(sem)) from subject where branch=?");
			ps.setString(1, bname);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				semid=new int[rs.getInt(1)];
				semname=new String[rs.getInt(1)];
			}
			int i=0;
			PreparedStatement ps1=conn.prepareStatement("Select distinct(sem) from subject where branch=?");
			ps1.setString(1, bname);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next()) {
				semid[i]=rs1.getInt(1);
				if(rs1.getInt(1)==1) {
					semname[i]="1st";
				}
				if(rs1.getInt(1)==2) {
					semname[i]="2nd";
				}
				if(rs1.getInt(1)==3) {
					semname[i]="3rd";
				}
				if(rs1.getInt(1)==4) {
					semname[i]="4th";
				}
				if(rs1.getInt(1)==5) {
					semname[i]="5th";
				}
				if(rs1.getInt(1)==6) {
					semname[i]="6th";
				}
				i++;
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		sid=semid[0];
		sname=semname[0];
		
	}
	
	void fbsubcount() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);

			String idlike = Homepage.sch*1000+bid*10+sid+"%";

			PreparedStatement ps=conn.prepareStatement("Select count(id) from subject where id like ? && th=true");
			ps.setString(1, idlike);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				sbcountth=rs.getInt(1);
			}
			
			PreparedStatement ps2=conn.prepareStatement("Select count(id) from subject where id like ? && pr=true");
			ps2.setString(1, idlike);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				sbcountpr=rs2.getInt(1);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	void settext() {
		lblbranch.setText(bname);
		lblbranch1.setText(bname);
		lblsemester.setText(sname);
		lblsemester1.setText(sname);
	}
	
	void whenchanged(int z) {
		if(z==1||z==-1) {
			int i=0;
			while(i<branchid.length) {
				if(bid==branchid[i]) {
					break;
				}
				i++;
			}
			
			int k=0;
			
			if(i+z>=branchid.length) {
				k=-branchid.length;
			}
			else if(i+z<0) {
				k=branchid.length;
			}
			
			bid=branchid[i+z+k];
			bname=branchname[i+z+k];
		}
		else if(z==2||z==-2) {
			z=z-(z/2);
			int i=0;
			while(i<semid.length) {
				if(sid==semid[i]) {
					break;
				}
				i++;
			}
			
			int k=0;
			
			if(i+z>=semid.length) {
				k=-semid.length;
			}
			else if(i+z<0) {
				k=semid.length;
			}
			
			sid=semid[i+z+k];
			sname=semname[i+z+k];
			
		}
	}
	
	void removedata(int countth,int countpr) {
		for(int i=0;i<countth;i++) {
		f.getContentPane().remove(lth[i]);
		f.getContentPane().remove(lth1[i]);
		f.getContentPane().remove(jsth[i]);
		}
		for(int i=0;i<countpr;i++) {
		f.getContentPane().remove(lpr[i]);
		f.getContentPane().remove(lpr1[i]);
		f.getContentPane().remove(jspr[i]);
		}
		f.getContentPane().remove(jssca);
		f.getContentPane().remove(jslib);
	}
	
	void addbackgroundimage() {
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
	void removebackgroundimage() {
		f.getContentPane().remove(img);
	}
	
	void save() {
		if(lectures_remaining<0) {
			JOptionPane.showMessageDialog(null, "The lectures can not be saved as lectures remaining can't be less than 0");
		}
		else {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String idlike = Homepage.sch*1000+bid*10+sid+"%";
			for(int i=0;i<snameth.length;i++) {
				String tempsname=snameth[i].substring(0,snameth[i].length()-5);
				
				PreparedStatement ps=conn.prepareStatement("Update subject set ht=? where sname=? && id like ?");
				ps.setInt(1, Integer.parseInt(jsth[i].getValue().toString()));
				ps.setString(2, tempsname);
				ps.setString(3, idlike);
				ps.executeUpdate();
				
			}
			for(int i=0;i<snamepr.length;i++) {
				String tempsname=snamepr[i].substring(0,snamepr[i].length()-5);
				
				PreparedStatement ps=conn.prepareStatement("Update subject set hpr=? where sname=? && id like ?");
				ps.setInt(1, Integer.parseInt(jspr[i].getValue().toString()));
				ps.setString(2, tempsname);
				ps.setString(3, idlike);
				ps.executeUpdate();
			}
			int count=0;
			
			PreparedStatement ps2=conn.prepareStatement("select count(*) from extrasubjects where sem=? && branch=?");
			ps2.setInt(1, sid);
			ps2.setString(2, bname);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				count=rs2.getInt(1);
			}
			
			if(count==0) {
				
			PreparedStatement ps3=conn.prepareStatement("Insert into extrasubjects values(?,?,?,?,'none','none')");
			ps3.setInt(1, sid);
			ps3.setString(2, bname);
			ps3.setInt(3, Integer.parseInt(jssca.getValue().toString()));
			ps3.setInt(4, Integer.parseInt(jslib.getValue().toString()));
			ps3.executeUpdate();
			
			}
			else {
				PreparedStatement ps3=conn.prepareStatement("Update extrasubjects set sca_time=?, lib_time=? where sem=? && branch=?");
				ps3.setInt(1, Integer.parseInt(jssca.getValue().toString()));
				ps3.setInt(2, Integer.parseInt(jslib.getValue().toString()));
				ps3.setInt(3, sid);
				ps3.setString(4, bname);
				ps3.executeUpdate();
			}
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	void resetall() {
		int result = JOptionPane.showConfirmDialog(null, "Do you really want to reset all the subjects to 0, you can not reverse this action","Reset All",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			s.executeUpdate("Update subject set ht=0,hpr=0");
			
			Statement s2=conn.createStatement();
			s2.executeUpdate("Update extrasubjects set sca_time=0, lib_time=0");
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "All Subjects are reset to 0");
		}
	}
	
	void reset() {
		int result = JOptionPane.showConfirmDialog(null, "Do you really want to reset this section's subject to 0, you can not reverse this action","Reset",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			String idlike = Homepage.sch*1000+bid*10+sid+"%";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps = conn.prepareStatement("Update subject set ht=0,hpr=0 where id like ?");
			ps.setString(1, idlike);
			ps.executeUpdate();
			
			PreparedStatement ps2=conn.prepareStatement("Update extrasubjects set sca_time=0,lib_time=0 where sem=? && branch =?");
			ps2.setInt(1, sid);
			ps2.setString(2, bname);
			ps2.executeUpdate();
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Subjects of branch : "+bname+" and sem : "+sname+" are reset to 0");
		}
	}
	
	void lectures() {
		total_lectures=42;
		lectures_used=0;
		lectures_remaining=42;
		for(int i=0;i<jsth.length;i++) {
			lectures_used += Integer.parseInt(jsth[i].getValue().toString());
		}
		for(int i=0;i<jspr.length;i++) {
			lectures_used += Integer.parseInt(jspr[i].getValue().toString());
		}
		
		lectures_used += Integer.parseInt(jssca.getValue().toString());
		lectures_used += Integer.parseInt(jslib.getValue().toString());
		
		lectures_remaining=total_lectures-lectures_used;
		
		lbllecturesleftvalue.setText(Integer.toString(lectures_remaining));
		lbllecturesleftvalue1.setText(Integer.toString(lectures_remaining));
	}
	
	void body() {
		lbl = new JLabel("Subjects Filter");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 25, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Subjects Filter");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 25, 1354, 125);
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
				o.save();
				o.back();
			}
		});
		btnback.setBounds(70, 600, 130, 100);
		f.getContentPane().add(btnback);
		
		JLabel lblb1 = new JLabel("<---");
		lblb1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb1.setHorizontalAlignment(SwingConstants.CENTER);
		lblb1.setBounds(220, 140, 70, 55);
		f.getContentPane().add(lblb1);
		
		btnb1 = new JButton("");
		btnb1.setIcon(new ImageIcon("img\\button2.png"));
		btnb1.setBackground(null);
		btnb1.setOpaque(false);
		btnb1.setBorder(null);
		btnb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.save();
				o.removedata(sbcountth, sbcountpr);
				o.whenchanged(-1);
				o.getSem();
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountth,sbcountpr);
				o.retrieve(sbcountth, sbcountpr);
				o.settext();
				o.lectures();
				o.addbackgroundimage();
				f.getContentPane().revalidate();
				f.getContentPane().repaint();

			}
		});
		btnb1.setBounds(220, 140, 70, 55);
		f.getContentPane().add(btnb1);
		
		
		lblbranch = new JLabel();
		lblbranch.setText(bname);
		lblbranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch.setBackground(SystemColor.menu);
		lblbranch.setBounds(290, 150, 760, 35);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel();
		lblbranch1.setText(bname);
		lblbranch1.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch1.setBackground(SystemColor.menu);
		lblbranch1.setBounds(293, 153, 760, 35);
		f.getContentPane().add(lblbranch1);
		
		
		JLabel lbln1 = new JLabel("--->");
		lbln1.setHorizontalAlignment(SwingConstants.CENTER);
		lbln1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln1.setBounds(1050, 140, 70, 55);
		f.getContentPane().add(lbln1);
		
		
		btnn1 = new JButton("");
		btnn1.setIcon(new ImageIcon("img\\button2.png"));
		btnn1.setBackground(null);
		btnn1.setOpaque(false);
		btnn1.setBorder(null);
		btnn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.save();
				o.removedata(sbcountth, sbcountpr);
				o.whenchanged(1);
				o.getSem();
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountth,sbcountpr);
				o.retrieve(sbcountth, sbcountpr);
				o.settext();
				o.lectures();
				o.addbackgroundimage();
				f.getContentPane().revalidate();
				f.getContentPane().repaint();
			}
		});
		btnn1.setBounds(1050, 140, 70, 55);
		f.getContentPane().add(btnn1);
		
		JLabel lblb2 = new JLabel("<---");
		lblb2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb2.setHorizontalAlignment(SwingConstants.CENTER);
		lblb2.setBounds(220, 210, 70, 55);
		f.getContentPane().add(lblb2);
		
		
		btnb2 = new JButton("");
		btnb2.setIcon(new ImageIcon("img\\button2.png"));
		btnb2.setBackground(null);
		btnb2.setOpaque(false);
		btnb2.setBorder(null);
		btnb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.save();
				o.removedata(sbcountth, sbcountpr);
				o.whenchanged(-2);
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountth,sbcountpr);
				o.retrieve(sbcountth, sbcountpr);
				o.settext();
				o.lectures();
				o.addbackgroundimage();
				f.getContentPane().revalidate();
				f.getContentPane().repaint();
			}
		});
		btnb2.setBounds(220, 210, 70, 55);
		f.getContentPane().add(btnb2);
		
		lblsemester = new JLabel();
		lblsemester.setText(sname);
		lblsemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester.setForeground(new Color(205, 133, 63));
		lblsemester.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester.setBackground(SystemColor.menu);
		lblsemester.setBounds(290, 220, 760, 35);
		f.getContentPane().add(lblsemester);
		
		lblsemester1 = new JLabel();
		lblsemester1.setText(sname);
		lblsemester1.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester1.setForeground(new Color(0, 0, 0));
		lblsemester1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester1.setBackground(SystemColor.menu);
		lblsemester1.setBounds(293, 223, 760, 35);
		f.getContentPane().add(lblsemester1);
		
		JLabel lbln2 = new JLabel("--->");
		lbln2.setHorizontalAlignment(SwingConstants.CENTER);
		lbln2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln2.setBounds(1050, 210, 70, 55);
		f.getContentPane().add(lbln2);
		
		
		btnn2 = new JButton("");
		btnn2.setIcon(new ImageIcon("img\\button2.png"));
		btnn2.setBackground(null);
		btnn2.setOpaque(false);
		btnn2.setBorder(null);
		btnn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.save();
				o.removedata(sbcountth, sbcountpr);
				o.whenchanged(2);
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountth,sbcountpr);
				o.retrieve(sbcountth, sbcountpr);
				o.settext();
				o.lectures();
				o.addbackgroundimage();
				f.getContentPane().revalidate();
				f.getContentPane().repaint();
			}
		});
		btnn2.setBounds(1050, 210, 70, 55);
		f.getContentPane().add(btnn2);
		
		lbllectureslefttext = new JLabel();
		lbllectureslefttext.setText("Lectures Left :");
		lbllectureslefttext.setHorizontalAlignment(SwingConstants.CENTER);
		lbllectureslefttext.setForeground(new Color(205, 133, 63));
		lbllectureslefttext.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lbllectureslefttext.setBackground(SystemColor.menu);
		lbllectureslefttext.setBounds(1000, 50, 250, 50);
		f.getContentPane().add(lbllectureslefttext);
		
		lbllectureslefttext1 = new JLabel();
		lbllectureslefttext1.setText("Lectures Left :");
		lbllectureslefttext1.setHorizontalAlignment(SwingConstants.CENTER);
		lbllectureslefttext1.setForeground(new Color(0, 0, 0));
		lbllectureslefttext1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lbllectureslefttext1.setBackground(SystemColor.menu);
		lbllectureslefttext1.setBounds(1003, 53, 250, 50);
		f.getContentPane().add(lbllectureslefttext1);
		
		lbllecturesleftvalue = new JLabel();
		lbllecturesleftvalue.setHorizontalAlignment(SwingConstants.CENTER);
		lbllecturesleftvalue.setForeground(new Color(205, 133, 63));
		lbllecturesleftvalue.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lbllecturesleftvalue.setBackground(SystemColor.menu);
		lbllecturesleftvalue.setBounds(1250, 50, 100, 50);
		f.getContentPane().add(lbllecturesleftvalue);
		
		lbllecturesleftvalue1 = new JLabel();
		lbllecturesleftvalue1.setHorizontalAlignment(SwingConstants.CENTER);
		lbllecturesleftvalue1.setForeground(new Color(0, 0, 0));
		lbllecturesleftvalue1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lbllecturesleftvalue1.setBackground(SystemColor.menu);
		lbllecturesleftvalue1.setBounds(1253, 53, 100, 50);
		f.getContentPane().add(lbllecturesleftvalue1);
		
		lblresetall = new JLabel("Reset All");
		lblresetall.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblresetall.setHorizontalAlignment(SwingConstants.CENTER);
		lblresetall.setBounds(610, 600, 130, 100);
		f.getContentPane().add(lblresetall);
		
		btnresetall = new JButton("");
		btnresetall.setIcon(new ImageIcon("img\\button.png"));
		btnresetall.setBackground(null);
		btnresetall.setOpaque(false);
		btnresetall.setBorder(null);
		btnresetall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.resetall();
				o.retrieve(sbcountth, sbcountpr);
			}
		});
		btnresetall.setBounds(610, 600, 130, 100);
		f.getContentPane().add(btnresetall);
		
		lblreset = new JLabel("Reset");
		lblreset.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblreset.setHorizontalAlignment(SwingConstants.CENTER);
		lblreset.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblreset);
		
		btnreset = new JButton("");
		btnreset.setIcon(new ImageIcon("img\\button.png"));
		btnreset.setBackground(null);
		btnreset.setOpaque(false);
		btnreset.setBorder(null);
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.reset();
				o.retrieve(sbcountth, sbcountpr);
			}
		});
		btnreset.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnreset);
		
		JLabel img11 = new JLabel("");
		img11.setIcon(new ImageIcon("img\\button2bg.png"));
		img11.setBounds(217, 143, 70, 55);
		f.getContentPane().add(img11);
		
		JLabel img12 = new JLabel("");
		img12.setIcon(new ImageIcon("img\\button2bg.png"));
		img12.setBounds(1053, 143, 70, 55);
		f.getContentPane().add(img12);
		
		JLabel img21 = new JLabel("");
		img21.setIcon(new ImageIcon("img\\button2bg.png"));
		img21.setBounds(217, 213, 70, 55);
		f.getContentPane().add(img21);
		
		JLabel img22 = new JLabel("");
		img22.setIcon(new ImageIcon("img\\button2bg.png"));
		img22.setBounds(1053, 213, 70, 55);
		f.getContentPane().add(img22);
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		JLabel img5 = new JLabel("");
		img5.setIcon(new ImageIcon("img\\buttonbg.png"));
		img5.setBounds(1137, 605, 130, 100);
		f.getContentPane().add(img5);
		
		JLabel img6 = new JLabel("");
		img6.setIcon(new ImageIcon("img\\buttonbg.png"));
		img6.setBounds(612, 605, 130, 100);
		f.getContentPane().add(img6);
		
		
		o.getBranch();
		o.getSem();
		o.fbsubcount();
		o.filter(sbcountth,sbcountpr);
		o.retrieve(sbcountth, sbcountpr);
		o.settext();
		o.lectures();
		o.addbackgroundimage();
		
		
	}
}
