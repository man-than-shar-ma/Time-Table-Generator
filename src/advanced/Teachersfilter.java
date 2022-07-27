package advanced;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;

public class Teachersfilter {
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
	
	JLabel lblresetall;
	JButton btnresetall;
	JLabel lblreset;
	JButton btnreset;
	
	int sbcountonlyth=0;
	int sbcountonlypr=0;
	int sbcountboth=0;
	
	int branchid[];
	String branchname[];
	int semid[];
	String semname[];
	String tempbrname[];
	
	int bid;
	String bname;
	int sid;
	String sname;
	
	String snameonlyth[];
	String snameonlypr[];
	String snameboth[];
	JLabel lonlyth[];
	JLabel lonlyth1[];
	JLabel lonlypr[];
	JLabel lonlypr1[];
	JLabel lboth[];
	JLabel lboth1[];
	
	JLabel lblsca;
	JLabel lblsca1;
	JLabel lbllib;
	JLabel lbllib1;
	
	JComboBox<String> cbonlyth[];
	JComboBox<String> cbonlypr[][];
	JComboBox<String> cbboth[];
	
	JComboBox<String> cbsca;
	JComboBox<String> cblib;
	int prteachercount[];
	
	int teacherscount=0;
	String teachersname[];
	
	
	JLabel img;
	
	static Teachersfilter o=new Teachersfilter();
	
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
	
	void retrieve(int countboth, int countonlyth,int countonlypr) {
		
		String tempboth[]=new String[countboth];
		String temponlyth[]=new String[countonlyth];
		String temponlypr[][]=new String[countonlypr][3];
		String tempsca="";
		String templib="";
		String idlike = Homepage.sch*1000+bid*10+sid+"%";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps=conn.prepareStatement("select tname1 from subject where id like ? && th=true && pr=true");
			ps.setString(1, idlike);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				tempboth[i]=rs.getString(1);
				i++;
			}
			
			PreparedStatement ps2=conn.prepareStatement("select tname1 from subject where id like ? && th=true && pr=false");
			ps2.setString(1, idlike);
			ResultSet rs2=ps2.executeQuery();
			int j=0;
			while(rs2.next()) {
				temponlyth[j]=rs2.getString(1);
				j++;
			}
			
			PreparedStatement ps3=conn.prepareStatement("select tname1,tname2,tname3 from subject where id like ? && th=false && pr=true");
			ps3.setString(1, idlike);
			ResultSet rs3=ps3.executeQuery();
			int k=0;
			while(rs3.next()) {
				temponlypr[k][0]=rs3.getString(1);
				temponlypr[k][1]=rs3.getString(2);
				temponlypr[k][2]=rs3.getString(3);			
				k++;
			}
			
			PreparedStatement ps4=conn.prepareStatement("select sca_name,lib_name from extrasubjects where branch=?&&sem=?");
			ps4.setString(1, bname);
			ps4.setInt(2, sid);
			ResultSet rs4=ps4.executeQuery();
			while(rs4.next()) {
				tempsca=rs4.getString(1);
				templib=rs4.getString(2);
			}
			
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		for(int i=1;i<(countboth+countonlyth+countonlypr+1+1+1);i++) {
			
		for(int x=0;x<sbcountboth;x++) {
			
			cbboth[x].setSelectedItem(tempboth[x]);
		}
		for(int x=0;x<sbcountonlyth;x++) {
			
			cbonlyth[x].setSelectedItem(temponlyth[x]);
		}
		for(int x=0;x<sbcountonlypr;x++) {
			for(int y=0;y<prteachercount[x];y++) {
				cbonlypr[x][y].setSelectedItem(temponlypr[x][y]);
			}
		}
		
		cbsca.setSelectedItem(tempsca);
		cblib.setSelectedItem(templib);
		}

	}
	
	@SuppressWarnings("unchecked")
	void filter(int countboth, int countonlyth, int countonlypr)
	{	


		int margin=30;
		
		int totalwidth=1360;
		int remainingbothwidth=0;
		int remainingonlythwidth=0;
		int remainingonlyprwidth=0;
		int lbwidthboth=0;
		int lbwidthonlyth=0;
		int lbwidthonlypr=0;
		
		
		if(countboth!=0) {
			remainingbothwidth = totalwidth-(countboth+1)*margin;
			lbwidthboth= remainingbothwidth/countboth;
		}
		if(countonlyth!=0) {
			remainingonlythwidth = totalwidth-(countonlyth+1)*margin;
			lbwidthonlyth= remainingonlythwidth/countonlyth;
		}
		if(countonlypr!=0) {
			remainingonlyprwidth = totalwidth-(countonlypr+1)*margin;
			lbwidthonlypr= remainingonlyprwidth/countonlypr;
		}
		
		String idlike = Homepage.sch*1000+bid*10+sid+"%";
		snameboth=new String[countboth];
		snameonlyth=new String[countonlyth];
		snameonlypr=new String[countonlypr];
		prteachercount=new int[countonlypr];
		
		int j=0;
		int k=0;
		int l=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps= conn.prepareStatement("Select sname from subject where id like ? && th=true && pr=false");
			ps.setString(1, idlike);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				snameonlyth[j]=rs.getString(1);
				j++;
			}
			
			PreparedStatement ps2=conn.prepareStatement("Select sname,prteacher from subject where id like ? && pr=true && th=false");
			ps2.setString(1, idlike);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				snameonlypr[k]=rs2.getString(1);
				prteachercount[k]=rs2.getInt(2);
				k++;
			}
			
			PreparedStatement ps3=conn.prepareStatement("Select sname from subject where id like ? && th=true && pr=true");
			ps3.setString(1, idlike);
			ResultSet rs3=ps3.executeQuery();
			while(rs3.next()) {
				snameboth[l]=rs3.getString(1);
				l++;
			}
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		lboth = new JLabel[countboth];
		lboth1 = new JLabel[countboth];
		cbboth = new JComboBox[countboth];
		
		lonlyth = new JLabel[countonlyth];
		lonlyth1 = new JLabel[countonlyth];
		cbonlyth = new JComboBox[countonlyth];
		
		lonlypr=new JLabel[countonlypr];
		lonlypr1=new JLabel[countonlypr];
		cbonlypr = new JComboBox[countonlypr][3];
		
		int pos=margin;
		j=0;
		for(int i=0;i<countboth;i++) {
			
			lboth[i] = new JLabel(snameboth[j]);
			lboth[i].setForeground(new Color(205, 133, 63));
			lboth[i].setHorizontalAlignment(SwingConstants.CENTER);
			lboth[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countboth));
			lboth[i].setBounds(pos, 300, lbwidthboth, 40);
			f.getContentPane().add(lboth[i]);
			
			lboth1[i] = new JLabel(snameboth[j]);
			lboth1[i].setForeground(new Color(0, 0, 0));
			lboth1[i].setHorizontalAlignment(SwingConstants.CENTER);
			lboth1[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countboth));
			lboth1[i].setBounds(pos, 303, lbwidthboth, 40);
			f.getContentPane().add(lboth1[i]);
			
			cbboth[i] = new JComboBox<String>();
			cbboth[i].setFont(new Font("Kristen ITC", Font.PLAIN, 15-countboth));
			//o.retrievebranch();
			cbboth[i].setBounds(pos, 350, lbwidthboth, 40);
			cbboth[i].addItem("none");
			cbboth[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(e.getStateChange()==1) {
						teacherfilter(sbcountboth, sbcountonlyth, sbcountonlypr);
					}
				}
			});
			f.getContentPane().add(cbboth[i]);
			
			
			j++;
			pos=pos+lbwidthboth+margin;
			
		}
		
		
		pos=margin;
		j=0;
		for(int i=0;i<countonlyth;i++) {
			
			lonlyth[i] = new JLabel(snameonlyth[j]);
			lonlyth[i].setForeground(new Color(205, 133, 63));
			lonlyth[i].setHorizontalAlignment(SwingConstants.CENTER);
			lonlyth[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countonlyth));
			lonlyth[i].setBounds(pos, 400, lbwidthonlyth, 40);
			f.getContentPane().add(lonlyth[i]);
			
			lonlyth1[i] = new JLabel(snameonlyth[j]);
			lonlyth1[i].setForeground(new Color(0, 0, 0));
			lonlyth1[i].setHorizontalAlignment(SwingConstants.CENTER);
			lonlyth1[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countonlyth));
			lonlyth1[i].setBounds(pos, 403, lbwidthonlyth, 40);
			f.getContentPane().add(lonlyth1[i]);
			
			cbonlyth[i] = new JComboBox<String>();
			cbonlyth[i].setFont(new Font("Kristen ITC", Font.PLAIN, 15-countonlyth));
			cbonlyth[i].setBounds(pos, 450, lbwidthonlyth, 40);
			cbonlyth[i].addItem("none");
			cbonlyth[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if(e.getStateChange()==1) {
					teacherfilter(sbcountboth, sbcountonlyth, sbcountonlypr);
					}
				}
			});
			f.getContentPane().add(cbonlyth[i]);
			
			
			j++;
			pos=pos+lbwidthonlyth+margin;
			
		}
		
		pos=margin;
		k=0;
		for(int i=0;i<countonlypr;i++) {
			
			lonlypr[i] = new JLabel(snameonlypr[k]);
			lonlypr[i].setForeground(new Color(205, 133, 63));
			lonlypr[i].setHorizontalAlignment(SwingConstants.CENTER);
			lonlypr[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countonlypr));
			lonlypr[i].setBounds(pos, 500, lbwidthonlypr, 40);
			f.getContentPane().add(lonlypr[i]);
			
			lonlypr1[i] = new JLabel(snameonlypr[k]);
			lonlypr1[i].setForeground(new Color(0, 0, 0));
			lonlypr1[i].setHorizontalAlignment(SwingConstants.CENTER);
			lonlypr1[i].setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30-countonlypr));
			lonlypr1[i].setBounds(pos, 503, lbwidthonlypr, 40);
			f.getContentPane().add(lonlypr1[i]);
			
			int separate=prteachercount[i];
			int innermargin=margin/4;
			int temppos=pos+innermargin;
			int remaininginnerwidth=lbwidthonlypr-(separate+1)*innermargin;
			int innerwidthpr=remaininginnerwidth/separate;
			
			for(int m=0;m<prteachercount[i];m++) {
				
				cbonlypr[i][m] = new JComboBox<String>();
				cbonlypr[i][m].setFont(new Font("Kristen ITC", Font.PLAIN, 15-countonlypr));
				cbonlypr[i][m].setBounds(temppos, 550, innerwidthpr, 40);
				cbonlypr[i][m].addItem("none");
				cbonlypr[i][m].addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if(e.getStateChange()==1) {
						teacherfilter(sbcountboth, sbcountonlyth, sbcountonlypr);
						}
						
					}
				});
				f.getContentPane().add(cbonlypr[i][m]);
				
				temppos=temppos+innerwidthpr+innermargin;
			}
			
			k++;
			pos=pos+lbwidthonlypr+margin;
			
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
		
		cbsca = new JComboBox<String>();
		cbsca.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cbsca.setBounds(250, 650, 300, 40);
		cbsca.addItem("none");
		cbsca.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==1) {
				teacherfilter(sbcountboth, sbcountonlyth, sbcountonlypr);
				}
			}
		});
		f.getContentPane().add(cbsca);
		
		cblib = new JComboBox<String>();
		cblib.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		cblib.setBounds(800, 650, 300, 40);
		cblib.addItem("Librarian");
		f.getContentPane().add(cblib);
		
		
		
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
	
	void teacherfirstadd(int countboth, int countonlyth, int countonlypr) {	
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps1=conn.prepareStatement("Select count(name) from teacher where (branch=?||branch='common') && sem"+sid+"=true");
			ps1.setString(1, bname);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next()) {
				teacherscount=rs1.getInt(1);
			}
			
			teachersname=new String[teacherscount];
			
			int i=0;
			PreparedStatement ps=conn.prepareStatement("Select name from teacher where (branch=?||branch='common') && sem"+sid+"=true");
			ps.setString(1, bname);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				teachersname[i]=rs.getString(1);
				i++;
			}
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<countboth;i++) {
			for(int j=0;j<teacherscount;j++) {
				cbboth[i].addItem(teachersname[j]);
			}
		}
		for(int i=0;i<countonlyth;i++) {
			for(int j=0;j<teacherscount;j++) {
				cbonlyth[i].addItem(teachersname[j]);
			}
		}
		for(int i=0;i<countonlypr;i++) {
			for(int l=0;l<prteachercount[i];l++) {
				for(int j=0;j<teacherscount;j++) {
					cbonlypr[i][l].addItem(teachersname[j]);
				}
			}
		}
		
		for(int j=0;j<teacherscount;j++) {
			cbsca.addItem(teachersname[j]);
		}
		
	}
	
	void teacherfilter(int countboth, int countonlyth, int countonlypr) {
		
		int tempprcount=0;
		for(int i=0;i<countonlypr;i++) {
			for(int j=0;j<prteachercount[i];j++) {
				tempprcount++;
			}
		}
		
		String remainingteachers[]=new String[teacherscount];
		String tempusedteachers[]=new String[countboth+countonlyth+tempprcount+1];
		int tempteacherscount[]= new int[teacherscount];
		
		int count=0;
		for(int i=0;i<countboth;i++) {
			if(!cbboth[i].getSelectedItem().toString().contentEquals("none")) {	
					tempusedteachers[count]=cbboth[i].getSelectedItem().toString();
					count++;
				
			}
		}
		for(int i=0;i<countonlyth;i++) {
			if(!cbonlyth[i].getSelectedItem().toString().contentEquals("none")) {	
					tempusedteachers[count]=cbonlyth[i].getSelectedItem().toString();
					count++;
				
			}
		}
		for(int i=0;i<countonlypr;i++) {
			for(int l=0;l<prteachercount[i];l++) {
			if(!cbonlypr[i][l].getSelectedItem().toString().contentEquals("none")) {	
					tempusedteachers[count]=cbonlypr[i][l].getSelectedItem().toString();
					count++;
				}
			}
		}
		
		if(!cbsca.getSelectedItem().toString().contentEquals("none")) {	
			tempusedteachers[count]=cbsca.getSelectedItem().toString();
			count++;
		}
		
		
		for(int i=0;i<teacherscount;i++) {
			int tempcount=0;
			int j=0;
			while(j<=countboth+countonlyth+tempprcount && tempusedteachers[j]!=null) {
				if(tempusedteachers[j]==teachersname[i]) {
					tempcount++;
				}
				j++;
			}
			tempteacherscount[i]=tempcount;
		}
		
		
		int maxindexitem=tempteacherscount[0];
		for(int i=1;i<teacherscount;i++) {	
			if(tempteacherscount[i]>maxindexitem) {
				maxindexitem=tempteacherscount[i];
			}
		}
		
		int m=0;
		for(int i=0;i<teacherscount;i++) {
			if(tempteacherscount[i]<maxindexitem) {
				remainingteachers[m]=teachersname[i];
				m++;
			}
		}

		if(remainingteachers[0]==null) {
			remainingteachers=teachersname;
		}
		
		for(int j=0;j<countboth;j++) {
				if(cbboth[j].getSelectedItem().toString().contentEquals("none")) {
					while(cbboth[j].getItemCount()>1) {
						cbboth[j].removeItemAt(cbboth[j].getItemCount()-1);
					}
				int c=0;
					while(c<remainingteachers.length) {
						if(remainingteachers[c]==null) {
							break;
						}
					cbboth[j].addItem(remainingteachers[c]);
					c++;
			}
			}
				else {
					int k=0;
					while(cbboth[j].getItemCount()-k>1) {
						if(cbboth[j].getItemCount()-1-k==cbboth[j].getSelectedIndex()) {
							k=1;
							continue;
						}
						cbboth[j].removeItemAt(cbboth[j].getItemCount()-1-k);
					}
					}
				
		}
		
		for(int j=0;j<countonlyth;j++) {
			if(cbonlyth[j].getSelectedItem().toString().contentEquals("none")) {
				while(cbonlyth[j].getItemCount()>1) {
					cbonlyth[j].removeItemAt(cbonlyth[j].getItemCount()-1);
				}
			int c=0;
				while(c<remainingteachers.length) {
					if(remainingteachers[c]==null) {
						break;
					}
				cbonlyth[j].addItem(remainingteachers[c]);
				c++;
		}
		}
			else {
				int k=0;
				while(cbonlyth[j].getItemCount()-k>1) {
					if(cbonlyth[j].getItemCount()-1-k==cbonlyth[j].getSelectedIndex()) {
						k=1;
						continue;
					}
					cbonlyth[j].removeItemAt(cbonlyth[j].getItemCount()-1-k);
				}
				}
			
	}
		
		for(int j=0;j<countonlypr;j++) {
			for(int l=0;l<prteachercount[j];l++) {
			if(cbonlypr[j][l].getSelectedItem().toString().contentEquals("none")) {
				while(cbonlypr[j][l].getItemCount()>1) {
					cbonlypr[j][l].removeItemAt(cbonlypr[j][l].getItemCount()-1);
				}
			int c=0;
				while(c<remainingteachers.length) {
					if(remainingteachers[c]==null) {
						break;
					}
					cbonlypr[j][l].addItem(remainingteachers[c]);
				c++;
		}
		}
			else {
				int k=0;
				while(cbonlypr[j][l].getItemCount()-k>1) {
					if(cbonlypr[j][l].getItemCount()-1-k==cbonlypr[j][l].getSelectedIndex()) {
						k=1;
						continue;
					}
					cbonlypr[j][l].removeItemAt(cbonlypr[j][l].getItemCount()-1-k);
				}
				}
			}
			
	}
		
			if(cbsca.getSelectedItem().toString().contentEquals("none")) {
				while(cbsca.getItemCount()>1) {
					cbsca.removeItemAt(cbsca.getItemCount()-1);
				}
			int c=0;
				while(c<remainingteachers.length) {
					if(remainingteachers[c]==null) {
						break;
					}
				cbsca.addItem(remainingteachers[c]);
				c++;
		}
		}
			else {
				int k=0;
				while(cbsca.getItemCount()-k>1) {
					if(cbsca.getItemCount()-1-k==cbsca.getSelectedIndex()) {
						k=1;
						continue;
					}
					cbsca.removeItemAt(cbsca.getItemCount()-1-k);
				}
				}
				
	}
	
	boolean isContentSame(String firstarray[],String secondarray[]) {
		
		for(int i=0;i<firstarray.length;i++) {
			if(firstarray[i]==null) {
				break;
			}
			for(int j=0;j<secondarray.length;j++) {
				if(secondarray[j]==firstarray[i]) {
					break;
				}
				else if(secondarray[j]==null) {
				return false;	
				}
			}
		}
		
		return true;
	}
	
	void fbsubcount() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);

			String idlike = Homepage.sch*1000+bid*10+sid+"%";

			PreparedStatement ps=conn.prepareStatement("Select count(id) from subject where id like ? && th=true && pr=false");
			ps.setString(1, idlike);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				sbcountonlyth=rs.getInt(1);
			}
			
			PreparedStatement ps2=conn.prepareStatement("Select count(id) from subject where id like ? && pr=true && th=false");
			ps2.setString(1, idlike);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				sbcountonlypr=rs2.getInt(1);
			}
			
			PreparedStatement ps3=conn.prepareStatement("Select count(id) from subject where id like ? && th=true && pr=true");
			ps3.setString(1, idlike);
			ResultSet rs3 = ps3.executeQuery();
			while(rs3.next()) {
				sbcountboth=rs3.getInt(1);
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
	
	void removedata(int countboth, int countonlyth,int countonlypr) {
		for(int i=0;i<countboth;i++) {
			f.getContentPane().remove(lboth[i]);
			f.getContentPane().remove(lboth1[i]);
			f.getContentPane().remove(cbboth[i]);
		}
		for(int i=0;i<countonlyth;i++) {
			f.getContentPane().remove(lonlyth[i]);
			f.getContentPane().remove(lonlyth1[i]);
			f.getContentPane().remove(cbonlyth[i]);
		}
		for(int i=0;i<countonlypr;i++) {
			f.getContentPane().remove(lonlypr[i]);
			f.getContentPane().remove(lonlypr1[i]);
			for(int j=0;j<prteachercount[i];j++) {			
				f.getContentPane().remove(cbonlypr[i][j]);
			}
		}
		
		f.getContentPane().remove(cbsca);
		f.getContentPane().remove(cblib);
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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String idlike = Homepage.sch*1000+bid*10+sid+"%";
			for(int i=0;i<sbcountboth;i++) {
				String tempsname=snameboth[i];
				
				PreparedStatement ps=conn.prepareStatement("Update subject set tname1=? where sname=? && id like ?");
				ps.setString(1, cbboth[i].getSelectedItem().toString());
				ps.setString(2, tempsname);
				ps.setString(3, idlike);
				ps.executeUpdate();
				
			}
			for(int i=0;i<sbcountonlyth;i++) {
				String tempsname=snameonlyth[i];
				
				PreparedStatement ps=conn.prepareStatement("Update subject set tname1=? where sname=? && id like ?");
				ps.setString(1, cbonlyth[i].getSelectedItem().toString());
				ps.setString(2, tempsname);
				ps.setString(3, idlike);
				ps.executeUpdate();
			}
			
			for(int i=0;i<sbcountonlypr;i++) {
				String tempsname=snameonlypr[i];
				for(int j=0;j<prteachercount[i];j++) {
					
					PreparedStatement ps=conn.prepareStatement("Update subject set tname"+(j+1)+"=? where sname=? && id like ?");
					ps.setString(1, cbonlypr[i][j].getSelectedItem().toString());
					ps.setString(2, tempsname);
					ps.setString(3, idlike);
					ps.executeUpdate();
				}
			}
			
			PreparedStatement ps1=conn.prepareStatement("Update extrasubjects set sca_name=?,lib_name=? where branch=? && sem=?");
			ps1.setString(1, cbsca.getSelectedItem().toString());
			ps1.setString(2, cblib.getSelectedItem().toString());
			ps1.setString(3, bname);
			ps1.setInt(4, sid);
			ps1.executeUpdate();
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	void resetall() {
		int result = JOptionPane.showConfirmDialog(null, "Do you really want to reset all the allocation, you can not reverse this action","Reset All",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			s.executeUpdate("Update subject set tname1='none',tname2='none',tname3='none'");
			
			
			Statement s2=conn.createStatement();
			s2.executeUpdate("Update extrasubjects set sca_name='none',lib_name='none'");
			
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Allocation of all faculty is reset");
		}
	}
	
	void reset() {
		int result = JOptionPane.showConfirmDialog(null, "Do you really want to reset this section's allocation, you can not reverse this action","Reset",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
			String idlike = Homepage.sch*1000+bid*10+sid+"%";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			PreparedStatement ps = conn.prepareStatement("Update subject set tname1='none',tname2='none',tname2='none' where id like ?");
			ps.setString(1, idlike);
			ps.executeUpdate();
			
			PreparedStatement ps2=conn.prepareStatement("Update extrasubjects set sca_name='none',lib_name='none' where branch=?&&sem=?");
			ps2.setString(1, bname);
			ps2.setInt(2, sid);
			ps2.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Allocation reset successfully");
		}
	}
	
	void body() {
		lbl = new JLabel("Teachers Filter");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 25, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Teachers Filter");
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
				o.removedata(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.whenchanged(-1);
				o.getSem();
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountboth, sbcountonlyth,sbcountonlypr);
				o.teacherfirstadd(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.settext();
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
				o.removedata(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.whenchanged(1);
				o.getSem();
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountboth, sbcountonlyth,sbcountonlypr);
				o.teacherfirstadd(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.settext();
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
				o.removedata(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.whenchanged(-2);
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountboth, sbcountonlyth,sbcountonlypr);
				o.teacherfirstadd(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.settext();
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
				o.removedata(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.whenchanged(2);
				o.fbsubcount();
				o.removebackgroundimage();
				o.filter(sbcountboth, sbcountonlyth,sbcountonlypr);
				o.teacherfirstadd(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
				o.settext();
				o.addbackgroundimage();
				f.getContentPane().revalidate();
				f.getContentPane().repaint();
			}
		});
		btnn2.setBounds(1050, 210, 70, 55);
		f.getContentPane().add(btnn2);
		
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
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
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
				o.retrieve(sbcountboth, sbcountonlyth, sbcountonlypr);
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
		o.filter(sbcountboth, sbcountonlyth,sbcountonlypr);
		o.teacherfirstadd(sbcountboth, sbcountonlyth,sbcountonlypr);
		o.retrieve(sbcountboth, sbcountonlyth,sbcountonlypr);
		o.settext();
		o.addbackgroundimage();
		
		
	}
}
