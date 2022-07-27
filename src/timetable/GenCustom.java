package timetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import homepage.Welcome;

public class GenCustom {
	Connection conn;
	Random rand=new Random();
	
	static JFrame f;
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
	JScrollPane scrollPane;
	JTable table;
	JButton btnsave;
	JButton btnGenerateNew;
	JLabel img;
	
	String l1,l2,t,l3,l4,r,l5,l6,l7;
	int la1,lz1;
	int la2,lz2;
	int ta,tz;
	int la3,lz3;
	int la4,lz4;
	int ra,rz;
	int la5,lz5;
	int la6,lz6;
	int la7,lz7;
	DefaultTableModel model;
	String data[][][][]=new String[10][3][12][10];
	String colheading[];
	
	String branch[]=new String[10];
	String sem[]=new String[3];
	int b=0;
	int s=0;
	int bcount=0;
	
	static GenCustom subject[][][]=new GenCustom[10][3][17];
	String name="";
	Boolean th=false;
	Boolean pr=false;
	Boolean combpr=false;
	int multch=0;
	int ht=0;
	int hpra=0;
	int hprb=0;
	String tnames[]=new String[3];
	int tno=0;
	
	int tchrcount;
	static GenCustom teacher[];
	String tname;
	HashSet<Integer> tchrt=new HashSet<Integer>();
	HashSet<Integer> tchra=new HashSet<Integer>();
	HashSet<Integer> tchrb=new HashSet<Integer>();
	
	static GenCustom arr[][][][]=new GenCustom[10][3][12][10];
	String value="";
	
	HashSet<Integer> l=new HashSet<Integer>();
	HashSet<Integer> lpra=new HashSet<Integer>();
	HashSet<Integer> lprb=new HashSet<Integer>();
	
	HashSet<Integer> temphs=new HashSet<Integer>();
	HashSet<String> temppraname=new HashSet<String>();
	HashSet<Integer> temppraused=new HashSet<Integer>();
	HashSet<String> tempbrchpraname=new HashSet<String>();
	HashSet<Integer> tempbrchpraused=new HashSet<Integer>();
	HashSet<String> tempthname=new HashSet<String>();
	HashSet<Integer> tempthused=new HashSet<Integer>();
	HashSet<String> tempallthname=new HashSet<String>();
	
	HashSet<Integer> allset=new HashSet<Integer>();
	
	
	static GenCustom lastsubstate[][][]=new GenCustom[10][3][17];
	static GenCustom lasttchstate[][][];
	
	int counttry=0;
	int counttry2=0;
	
	
	static GenCustom o=new GenCustom();
	public static void main(String[] args) {
		o.frame();
		o.counttry=0;
		o.b=0;
		o.s=0;
		o.getbranch();
		o.getsem();
		for(int z=0;z<10;z++) {
			for(int j=0;j<3;j++) {
				for(int i=0;i<17;i++) {
					subject[z][j][i]=new GenCustom();
					lastsubstate[z][j][i]=new GenCustom();
				}
			}
			for(int k=0;k<3;k++) {
				for(int i=0;i<12;i++) {
					for(int j=0;j<10;j++) {
						arr[z][k][i][j]=new GenCustom();
					}
				}
			}
		}

		o.collection();
	}
	void collection() {
		o.getteacherdata();
		o.getsubdata();
		o.allot();
		o.table();
		o.td(0, 0);
		o.setfirsttext();
	}
	void setfirsttext() {
		lblbranch.setText(branch[0]);
		lblbranch1.setText(branch[0]);
		lblsemester.setText(sem[0]);
		lblsemester1.setText(sem[0]);
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
		Gen.main(null);
		f.dispose();
	}
	
	void save() {
		if(Gen.singlebranch.contentEquals("")) {
		int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Save this Time Table","Save Time Table",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			int ts=0;
			for(int b=0;b<10;b++) {
				for(int s=0;s<3;s++) {
					if(s==0) {
						ts=Gen.s1;
					}
					else if(s==1) {
						ts=Gen.s2;
					}
					else {
						ts=Gen.s3;
					}
					Statement st=conn.createStatement();
					st.executeUpdate("delete from sem"+b+""+ts);
				}
			}
			
			Statement st2=conn.createStatement();
			if(Gen.s1==1) {
				st2.executeUpdate("delete from time1");
			}
			else {
				st2.executeUpdate("delete from time2");
			}
			
			st2.executeUpdate("delete from bch");
			
			for(int bch=0;bch<bcount;bch++) {
				PreparedStatement ps3=conn.prepareStatement("insert into bch values(?)");
				ps3.setString(1, branch[bch]);
				ps3.executeUpdate();
			}
			
			
			PreparedStatement ps2;
			if(Gen.s1==1) {
				ps2=conn.prepareStatement("insert into time1 values(?,?,?,?,?,?,?,?,?,?)");
			}
			else{
				ps2=conn.prepareStatement("insert into time2 values(?,?,?,?,?,?,?,?,?,?)");
			}
			ps2.setString(1, "Day/Time");
			ps2.setString(2, l1);
			ps2.setString(3, l2);
			ps2.setString(4, t);
			ps2.setString(5, l3);
			ps2.setString(6, l4);
			ps2.setString(7, r);
			ps2.setString(8, l5);
			ps2.setString(9, l6);
			ps2.setString(10, l7);
			ps2.executeUpdate();
			
			for(int b=0;b<bcount;b++) {
				for(int s=0;s<3;s++) {
					if(s==0) {
						ts=Gen.s1;
					}
					else if(s==1) {
						ts=Gen.s2;
					}
					else {
						ts=Gen.s3;
					}
					for(int y=0;y<12;y++) {
						PreparedStatement ps=conn.prepareStatement("insert into sem"+b+""+ts+" values(?,?,?,?,?,?,?,?,?,?)");
						ps.setString(1, arr[b][s][y][0].value);
						ps.setString(2, arr[b][s][y][1].value);
						ps.setString(3, arr[b][s][y][2].value);
						ps.setString(4, arr[b][s][y][3].value);
						ps.setString(5, arr[b][s][y][4].value);
						ps.setString(6, arr[b][s][y][5].value);
						ps.setString(7, arr[b][s][y][6].value);
						ps.setString(8, arr[b][s][y][7].value);
						ps.setString(9, arr[b][s][y][8].value);
						ps.setString(10, arr[b][s][y][9].value);
						ps.executeUpdate();
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Gen.main(null);
		f.dispose();
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Note : This time table is Saved in Database based on the Branch Id,So branch with this id can overwrite this saved TimeTable");
			int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Save this Time Table","Save Time Table",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
				int ts=0;
				int br=0;
				PreparedStatement ps9=conn.prepareStatement("Select id from branch where name=?");
				ps9.setString(1, branch[0]);
				ResultSet rs9=ps9.executeQuery();
				while(rs9.next()) {
					br=rs9.getInt(1);
				}
				
					for(int s=0;s<3;s++) {
						if(s==0) {
							ts=Gen.s1;
						}
						else if(s==1) {
							ts=Gen.s2;
						}
						else {
							ts=Gen.s3;
						}
						Statement st=conn.createStatement();
						st.executeUpdate("delete from b"+br+""+ts);
					}
				
				Statement st2=conn.createStatement();
				if(Gen.s1==1) {
					st2.executeUpdate("delete from bt"+br+"1");
				}
				else {
					st2.executeUpdate("delete from bt"+br+"2");
				}
				
				
				PreparedStatement ps2;
				if(Gen.s1==1) {
					ps2=conn.prepareStatement("insert into bt"+br+"1 values(?,?,?,?,?,?,?,?,?,?)");
				}
				else{
					ps2=conn.prepareStatement("insert into bt"+br+"2 values(?,?,?,?,?,?,?,?,?,?)");
				}
				ps2.setString(1, "Day/Time");
				ps2.setString(2, l1);
				ps2.setString(3, l2);
				ps2.setString(4, t);
				ps2.setString(5, l3);
				ps2.setString(6, l4);
				ps2.setString(7, r);
				ps2.setString(8, l5);
				ps2.setString(9, l6);
				ps2.setString(10, l7);
				ps2.executeUpdate();
				
					for(int s=0;s<3;s++) {
						if(s==0) {
							ts=Gen.s1;
						}
						else if(s==1) {
							ts=Gen.s2;
						}
						else {
							ts=Gen.s3;
						}
						for(int y=0;y<12;y++) {
							PreparedStatement ps=conn.prepareStatement("insert into b"+br+""+ts+" values(?,?,?,?,?,?,?,?,?,?)");
							ps.setString(1, arr[b][s][y][0].value);
							ps.setString(2, arr[b][s][y][1].value);
							ps.setString(3, arr[b][s][y][2].value);
							ps.setString(4, arr[b][s][y][3].value);
							ps.setString(5, arr[b][s][y][4].value);
							ps.setString(6, arr[b][s][y][5].value);
							ps.setString(7, arr[b][s][y][6].value);
							ps.setString(8, arr[b][s][y][7].value);
							ps.setString(9, arr[b][s][y][8].value);
							ps.setString(10, arr[b][s][y][9].value);
							ps.executeUpdate();
						}
					}
				}
			
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Gen.main(null);
			f.dispose();
			}
		}
	}
	
	void generatenew() {
		f.dispose();
		GenCustom.main(null);
	}
	
	void td(int b, int s) {
		table.setModel(new DefaultTableModel(data[b][s],colheading));
	}
	
	void table() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from Timing");
			
			while(rs.next()) {
				la1=rs.getInt(1);
				lz1=rs.getInt(2);
				la2=rs.getInt(3);
				lz2=rs.getInt(4);
				ta=rs.getInt(5);
				tz=rs.getInt(6);
				la3=rs.getInt(7);
				lz3=rs.getInt(8);
				la4=rs.getInt(9);
				lz4=rs.getInt(10);
				ra=rs.getInt(11);
				rz=rs.getInt(12);
				la5=rs.getInt(13);
				lz5=rs.getInt(14);
				la6=rs.getInt(15);
				lz6=rs.getInt(16);
				la7=rs.getInt(17);
				lz7=rs.getInt(18);
			}
			
			int lah11=la1/100;
			int lam11=la1%100;
			int lzh11=lz1/100;
			int lzm11=lz1%100;
			
			int lah22=la2/100;
			int lam22=la2%100;
			int lzh22=lz2/100;
			int lzm22=lz2%100;
			
			int tahh=ta/100;
			int tamm=ta%100;
			int tzhh=tz/100;
			int tzmm=tz%100;
			
			int lah33=la3/100;
			int lam33=la3%100;
			int lzh33=lz3/100;
			int lzm33=lz3%100;
			
			int lah44=la4/100;
			int lam44=la4%100;
			int lzh44=lz4/100;
			int lzm44=lz4%100;
			
			int rahh=ra/100;
			int ramm=ra%100;
			int rzhh=rz/100;
			int rzmm=rz%100;
			
			int lah55=la5/100;
			int lam55=la5%100;
			int lzh55=lz5/100;
			int lzm55=lz5%100;
			
			int lah66=la6/100;
			int lam66=la6%100;
			int lzh66=lz6/100;
			int lzm66=lz6%100;
			
			int lah77=la7/100;
			int lam77=la7%100;
			int lzh77=lz7/100;
			int lzm77=lz7%100;
			
			
			
			l1=String.format("%d:%02d - %d:%02d", lah11,lam11,lzh11,lzm11);
			
			l2=String.format("%d:%02d - %d:%02d", lah22,lam22,lzh22,lzm22);
			
			t=String.format("%d:%02d - %d:%02d", tahh,tamm,tzhh, tzmm);
			
			l3=String.format("%d:%02d - %d:%02d", lah33,lam33,lzh33,lzm33);
			
			l4=String.format("%d:%02d - %d:%02d", lah44,lam44,lzh44,lzm44);
			
			r=String.format("%d:%02d - %d:%02d", rahh,ramm,rzhh,rzmm);
			
			l5=String.format("%d:%02d - %d:%02d", lah55,lam55,lzh55,lzm55);
			
			l6=String.format("%d:%02d - %d:%02d", lah66,lam66,lzh66,lzm66);
			
			l7=String.format("%d:%02d - %d:%02d", lah77,lam77,lzh77,lzm77);
			
			
			
			colheading=new String[] 
					{
						"Day/Time",l1,l2,t,l3,l4,r,l5,l6,l7
					};
			for(int s1=0;s1<3;s1++) {
				for(int b1=0;b1<bcount;b1++) {
					arr[b1][s1][0][0].value="Monday";
					arr[b1][s1][2][0].value="Tuesday";
					arr[b1][s1][4][0].value="Wednesday";
					arr[b1][s1][6][0].value="Thursday";
					arr[b1][s1][8][0].value="Friday";
					arr[b1][s1][10][0].value="Saturday";
					for(int i=0;i<12;i=i+2) {
						arr[b1][s1][i][3].value="Tea Break";
						arr[b1][s1][i][6].value="Recess";
					}
				}
			}
			
			
			for(int y=0;y<bcount;y++) {
				for(int x=0;x<3;x++) {
					data[y][x]=new String[][]{
						{arr[y][x][0][0].value,arr[y][x][0][1].value,arr[y][x][0][2].value,arr[y][x][0][3].value,arr[y][x][0][4].value,arr[y][x][0][5].value,arr[y][x][0][6].value,arr[y][x][0][7].value,arr[y][x][0][8].value,arr[y][x][0][9].value},
						{arr[y][x][1][0].value,arr[y][x][1][1].value,arr[y][x][1][2].value,arr[y][x][1][3].value,arr[y][x][1][4].value,arr[y][x][1][5].value,arr[y][x][1][6].value,arr[y][x][1][7].value,arr[y][x][1][8].value,arr[y][x][1][9].value},
						{arr[y][x][2][0].value,arr[y][x][2][1].value,arr[y][x][2][2].value,arr[y][x][2][3].value,arr[y][x][2][4].value,arr[y][x][2][5].value,arr[y][x][2][6].value,arr[y][x][2][7].value,arr[y][x][2][8].value,arr[y][x][2][9].value},
						{arr[y][x][3][0].value,arr[y][x][3][1].value,arr[y][x][3][2].value,arr[y][x][3][3].value,arr[y][x][3][4].value,arr[y][x][3][5].value,arr[y][x][3][6].value,arr[y][x][3][7].value,arr[y][x][3][8].value,arr[y][x][3][9].value},
						{arr[y][x][4][0].value,arr[y][x][4][1].value,arr[y][x][4][2].value,arr[y][x][4][3].value,arr[y][x][4][4].value,arr[y][x][4][5].value,arr[y][x][4][6].value,arr[y][x][4][7].value,arr[y][x][4][8].value,arr[y][x][4][9].value},
						{arr[y][x][5][0].value,arr[y][x][5][1].value,arr[y][x][5][2].value,arr[y][x][5][3].value,arr[y][x][5][4].value,arr[y][x][5][5].value,arr[y][x][5][6].value,arr[y][x][5][7].value,arr[y][x][5][8].value,arr[y][x][5][9].value},
						{arr[y][x][6][0].value,arr[y][x][6][1].value,arr[y][x][6][2].value,arr[y][x][6][3].value,arr[y][x][6][4].value,arr[y][x][6][5].value,arr[y][x][6][6].value,arr[y][x][6][7].value,arr[y][x][6][8].value,arr[y][x][6][9].value},
						{arr[y][x][7][0].value,arr[y][x][7][1].value,arr[y][x][7][2].value,arr[y][x][7][3].value,arr[y][x][7][4].value,arr[y][x][7][5].value,arr[y][x][7][6].value,arr[y][x][7][7].value,arr[y][x][7][8].value,arr[y][x][7][9].value},
						{arr[y][x][8][0].value,arr[y][x][8][1].value,arr[y][x][8][2].value,arr[y][x][8][3].value,arr[y][x][8][4].value,arr[y][x][8][5].value,arr[y][x][8][6].value,arr[y][x][8][7].value,arr[y][x][8][8].value,arr[y][x][8][9].value},
						{arr[y][x][9][0].value,arr[y][x][9][1].value,arr[y][x][9][2].value,arr[y][x][9][3].value,arr[y][x][9][4].value,arr[y][x][9][5].value,arr[y][x][9][6].value,arr[y][x][9][7].value,arr[y][x][9][8].value,arr[y][x][9][9].value},
						{arr[y][x][10][0].value,arr[y][x][10][1].value,arr[y][x][10][2].value,arr[y][x][10][3].value,arr[y][x][10][4].value,arr[y][x][10][5].value,arr[y][x][10][6].value,arr[y][x][10][7].value,arr[y][x][10][8].value,arr[y][x][10][9].value},
						{arr[y][x][11][0].value,arr[y][x][11][1].value,arr[y][x][11][2].value,arr[y][x][11][3].value,arr[y][x][11][4].value,arr[y][x][11][5].value,arr[y][x][11][6].value,arr[y][x][11][7].value,arr[y][x][11][8].value,arr[y][x][11][9].value}
					};
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	void getbranch() {
		if(Gen.singlebranch.contentEquals("")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			int i=0;
			Statement s1=conn.createStatement();
			ResultSet rs1=s1.executeQuery("Select name from branch order by name");
			while(rs1.next()) {
				branch[i]=rs1.getString(1);
				i++;
			}
			ResultSet rs2=s1.executeQuery("Select count(*) from branch");
			while(rs2.next()) {
				bcount=rs2.getInt(1);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else {
			branch[0]=Gen.singlebranch;
			bcount=1;
			
		}
	}

	void getsem() {
		if(Gen.s1==1&&Gen.s2==3&&Gen.s3==5) {
			sem[0]="1st";
			sem[1]="3rd";
			sem[2]="5th";
		}
		else {
			sem[0]="2nd";
			sem[1]="4th";
			sem[2]="6th";
		}
		
	}
	
	void getsubdata() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			int b=0;
			while(b<10&&branch[b]!=null) {
				for(int s=0;s<3;s++) {
					int i=0;
					int ss;
					if(s==0) {
						ss=Gen.s1;
					}
					else if(s==1) {
						ss=Gen.s2;
					}
					else {
						ss=Gen.s3;
					}
					PreparedStatement ps1=conn.prepareStatement("Select sname,th,pr,prcomb,prteacher,ht,hpr,tname1,tname2,tname3 from subject where branch=? and sem=?");
					ps1.setString(1, branch[b]);
					ps1.setInt(2, ss);
					ResultSet rs1=ps1.executeQuery();
					while(rs1.next()) {
						subject[b][s][i].name=rs1.getString(1);
						subject[b][s][i].th=rs1.getBoolean(2);
						subject[b][s][i].pr=rs1.getBoolean(3);
						subject[b][s][i].combpr=rs1.getBoolean(4);
						subject[b][s][i].multch=rs1.getInt(5);
						subject[b][s][i].ht=rs1.getInt(6);
						subject[b][s][i].hpra=rs1.getInt(7);
						subject[b][s][i].hprb=rs1.getInt(7);
						subject[b][s][i].tnames[0]=rs1.getString(8);
						subject[b][s][i].tnames[1]=rs1.getString(9);
						subject[b][s][i].tnames[2]=rs1.getString(10);
						subject[b][s][i].tno=0;
						i++;
					}
					
					PreparedStatement ps2=conn.prepareStatement("Select sca_time,sca_name,lib_time,lib_name from extrasubjects where branch=? and sem=?");
					ps2.setString(1, branch[b]);
					ps2.setInt(2, ss);
					ResultSet rs2=ps2.executeQuery();
					while(rs2.next()){
						subject[b][s][i].name="SCA";
						subject[b][s][i].th=true;
						subject[b][s][i].pr=false;
						subject[b][s][i].combpr=false;
						subject[b][s][i].multch=1;
						subject[b][s][i].ht=rs2.getInt(1);
						subject[b][s][i].hpra=0;
						subject[b][s][i].hprb=0;
						subject[b][s][i].tnames[0]=rs2.getString(2);
						subject[b][s][i].tnames[1]="none";
						subject[b][s][i].tnames[2]="none";
						subject[b][s][i].tno=0;
						
						i++;
						
						subject[b][s][i].name="Lib";
						subject[b][s][i].th=true;
						subject[b][s][i].pr=false;
						subject[b][s][i].combpr=false;
						subject[b][s][i].multch=1;
						subject[b][s][i].ht=rs2.getInt(3);
						subject[b][s][i].hpra=0;
						subject[b][s][i].hprb=0;
						subject[b][s][i].tnames[0]=rs2.getString(4);
						subject[b][s][i].tnames[1]="none";
						subject[b][s][i].tnames[2]="none";
						subject[b][s][i].tno=0;
					}
				}
				b++;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void getteacherdata() {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(*) from teacher");
			while(rs.next()) {
				tchrcount=rs.getInt(1);
				teacher=new GenCustom[rs.getInt(1)+1];
				lasttchstate=new GenCustom[bcount][3][rs.getInt(1)+1];
				for(int i=0;i<rs.getInt(1)+1;i++) {
					teacher[i]=new GenCustom();
					for(int x=0;x<bcount;x++) {
						for(int y=0;y<3;y++) {
							lasttchstate[x][y][i]=new GenCustom();
						}
					}
					
				}
				
			}
			
			int i=0;
			ResultSet rs2=s.executeQuery("Select name from teacher order by id");
			while(rs2.next()) {
				teacher[i].tname=rs2.getString(1);
				Collections.addAll(teacher[i].tchrt, 1,2,4,5,7,8,9,21,22,24,25,27,28,29,41,42,44,45,47,48,49,61,62,64,65,67,68,69,81,82,84,85,87,88,89,101,102,104,105,107,108,109);
				Collections.addAll(teacher[i].tchra, 1,4,7,8,21,24,27,28,41,44,47,48,61,64,67,68,81,84,87,88,101,104,107,108);
				Collections.addAll(teacher[i].tchrb, 2,5,8,9,22,25,28,29,42,45,48,49,62,65,68,69,82,85,88,89,102,105,108,109);
			i++;
			}
			ResultSet rs3=s.executeQuery("Select distinct lib_name from extrasubjects");
			while(rs3.next()) {
				teacher[i].tname=rs3.getString(1);
				Collections.addAll(teacher[i].tchrt, 1,2,4,5,7,8,9,21,22,24,25,27,28,29,41,42,44,45,47,48,49,61,62,64,65,67,68,69,81,82,84,85,87,88,89,101,102,104,105,107,108,109);
				Collections.addAll(teacher[i].tchra, 1,4,7,8,21,24,27,28,41,44,47,48,61,64,67,68,81,84,87,88,101,104,107,108);
				Collections.addAll(teacher[i].tchrb, 2,5,8,9,22,25,28,29,42,45,48,49,62,65,68,69,82,85,88,89,102,105,108,109);i++;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	void allot() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			int s=0;
			while(s<3) {
			int b=0;
			while(b<bcount&&branch[b]!=null) {
				o.savestate(b, s);
				
				while(true) {
				try {
					o.allotsinglepage(b, s);
					counttry=0;
					break;
				}
				catch (Exception e) {
					
					for(int x3=0;x3<10;x3++) {
						for(int y3=0;y3<12;y3++) {
							arr[b][s][y3][x3].value="";
						}
					}
					o.loadstate(b, s);
					
					counttry++;
				}
				if(counttry==50) {
					o.collection();
				}
				}
				b++;
				}
			s++;	
			}
	}
	
	void allotsinglepage(int b,int s) {
		int tc=0;
		l.clear();
		lpra.clear();
		lprb.clear();
		temphs.clear();
		temppraname.clear();
		temppraused.clear();
		tempthname.clear();
		tempthused.clear();
		
		
		Collections.addAll(l, 1,2,4,5,7,8,9,21,22,24,25,27,28,29,41,42,44,45,47,48,49,61,62,64,65,67,68,69,81,82,84,85,87,88,89,101,102,104,105,107,108,109);
		Collections.addAll(lpra, 1,4,7,8,21,24,27,28,41,44,47,48,61,64,67,68,81,84,87,88,101,104,107,108);
		
		
		// alloting practical a values
		for(int i=0;i<17;i++) {
			if(subject[b][s][i].name=="") {
				break;
			}
			
			int tempsubpra=subject[b][s][0].hpra;
			int temploc=0;
			for(int j=1;j<17;j++) {
				if(tempsubpra<subject[b][s][j].hpra) {
					tempsubpra=subject[b][s][j].hpra;
					temploc=j;
				}
			}
			if(tempsubpra==0) {
				break;
			}

			Boolean flag=true;
			while(flag) {
				if(subject[b][s][temploc].pr==true&&subject[b][s][temploc].hpra==0) {
					flag=false;
					break;
				}
				
				tc=0;
				while(tc<tchrcount) {
					if(subject[b][s][temploc].tnames[subject[b][s][temploc].tno].contentEquals(teacher[tc].tname)) {
						break;
					}
					tc++;

				}
				
				temphs.clear();
				temphs.addAll(lpra);
				temphs.retainAll(teacher[tc].tchra);
				if(!tempbrchpraused.isEmpty()) {
					temphs.retainAll(tempbrchpraused);
				}
				
			int x=(int) temphs.toArray()[rand.nextInt(temphs.toArray().length)];
			int x1=x%10;
			int y1=x/10;
				
			if(subject[b][s][temploc].pr==true&&subject[b][s][temploc].hpra>0) {
				if(subject[b][s][temploc].combpr==true) {
					arr[b][s][y1][x1].value=subject[b][s][temploc].name+ " (p)";
					arr[b][s][y1][x1+1].value=subject[b][s][temploc].name+" (p)";
					arr[b][s][y1+1][x1].value=subject[b][s][temploc].tnames[subject[b][s][temploc].tno];
					arr[b][s][y1+1][x1+1].value=subject[b][s][temploc].tnames[subject[b][s][temploc].tno];
					l.remove(Integer.parseInt(y1+""+(x1+1)));
					subject[b][s][temploc].hprb-=2;
					
					if(subject[b][s][temploc].multch>0) {
						subject[b][s][temploc].tno++;
						if(subject[b][s][temploc].tno+1>subject[b][s][temploc].multch) {
							subject[b][s][temploc].tno=0;
						}
					}
				}
				else {
					arr[b][s][y1][x1].value=subject[b][s][temploc].name+" (a)";
					arr[b][s][y1+1][x1].value=subject[b][s][temploc].tnames[subject[b][s][temploc].tno];
					if(subject[b][s][temploc].multch>0) {
						subject[b][s][temploc].tno++;
						if(subject[b][s][temploc].tno+1>subject[b][s][temploc].multch) {
							subject[b][s][temploc].tno=0;
						}
					}
			}
				if(x1==7) {
					lpra.remove(Integer.parseInt(y1+""+8));
				}
				else if(x1==8) {
					lpra.remove(Integer.parseInt(y1+""+7));
				}
				
				subject[b][s][temploc].hpra-=2;
				l.remove(x);
				lpra.remove(x);
				teacher[tc].tchra.remove(x);
				teacher[tc].tchra.remove(x+1);
				teacher[tc].tchrb.remove(x);
				teacher[tc].tchrb.remove(x+1);
				teacher[tc].tchrt.remove(x);
				teacher[tc].tchrt.remove(x+1);
				
				if(subject[b][s][temploc].combpr==false) {
					lprb.add(x+1);
				}
			}
			
			}
			
			}
		
	
		// alloting practical b values
		for(int i=0;i<17;i++) {
			if(subject[b][s][i].name.contentEquals("")) {
				break;
			}
			
			int tempsubprb=subject[b][s][0].hprb;
			int temploc=0;
			for(int j=1;j<17;j++) {
				if(tempsubprb<subject[b][s][j].hprb) {
					tempsubprb=subject[b][s][j].hprb;
					temploc=j;
				}
			}
			if(tempsubprb==0) {
				break;
			}
			
			Boolean flag=true;
			while(flag) {
				
				if(subject[b][s][temploc].pr==true&&subject[b][s][temploc].hprb==0) {
					flag=false;
					break;
				}
				
				if(lprb.isEmpty()) {
					flag=false;
					break;
				}
				
				tc=0;
				while(tc<tchrcount) {
					if(subject[b][s][temploc].tnames[subject[b][s][temploc].tno].contentEquals(teacher[tc].tname)) {
						break;
					}
					tc++;

				}
				
				temppraname.clear();
				temppraused.clear();
				
				for(int x=0;x<17;x++) {
					if(subject[b][s][x].hprb!=0&&x!=temploc) {
						for(int y=0;y<subject[b][s][x].multch;y++) {
							temppraname.add(subject[b][s][x].tnames[y]);
						}
					}
				}
				
				//JOptionPane.showMessageDialog(null, temppraname);
				if(!temppraname.isEmpty()) {
				for(int a1=0;a1<10;a1++) {
					for(int b1=1;b1<12;b1=b1+2) {
						if((a1==1||a1==4||a1==7||a1==8) && temppraname.contains(arr[b][s][b1][a1].value) && arr[b][s][b1-1][a1].value.substring(arr[b][s][b1-1][a1].value.length()-3).contentEquals("(a)") && arr[b][s][b1][a1+1].value.contentEquals("")) {
							temppraused.add(((b1-1)*10)+(a1+1));
						}
					}
				}
				}				
				
				temphs.clear();
				temphs.addAll(lprb);
				temphs.retainAll(teacher[tc].tchrb);
				
				if(!temppraused.isEmpty()) {
					temphs.retainAll(temppraused);
				}
				
				
			int x=(int) temphs.toArray()[rand.nextInt(temphs.toArray().length)];
			int x1=(x%10);
			int y1=x/10;
				
			if(subject[b][s][temploc].pr==true&&subject[b][s][temploc].hprb>0) {

					arr[b][s][y1][x1].value=subject[b][s][temploc].name+" (b)";
					arr[b][s][y1+1][x1].value=subject[b][s][temploc].tnames[subject[b][s][temploc].tno];
					if(subject[b][s][temploc].multch>0) {
						subject[b][s][temploc].tno++;
						if(subject[b][s][temploc].tno+1>subject[b][s][temploc].multch) {
							subject[b][s][temploc].tno=0;
						}
					}
				
				subject[b][s][temploc].hprb-=2;
				l.remove(x);
				lprb.remove(x);
				teacher[tc].tchra.remove(x);
				teacher[tc].tchra.remove(x-1);
				teacher[tc].tchrb.remove(x);
				teacher[tc].tchrb.remove(x-1);
				teacher[tc].tchrt.remove(x);
				teacher[tc].tchrt.remove(x-1);
			}
			}
			
		}
		
		
		//alloting th subjects
		for(int i=0;i<17;i++) {
			if(subject[b][s][i].name.contentEquals("")) {
				break;
			}
			
			int tempsubht=0;
			int temploc=0;
			tempallthname.clear();
			for(int x=0;x<17;x++) {
					if(subject[b][s][x].name.contentEquals("")) {
						break;
					}
				Boolean flag2=false;
				for(int s2=0;s2<3;s2++) {
					for(int b2=0;b2<bcount;b2++) {
						if(b2==b&&s2==s) {
							flag2=true;
							break;
						}
						for(int y=0;y<17;y++) {
							if(subject[b2][s2][y].name.contentEquals("")) {
								break;
							}
							for(int z=0;z<subject[b2][s2][y].multch;z++) {
								if(subject[b][s][x].tnames[0].contentEquals(subject[b2][s2][y].tnames[z])&&subject[b][s][x].ht!=0) {
									tempallthname.add(subject[b][s][x].tnames[0]);
								}
							}
						}
					}
					if(flag2)
						break;
				}
			}
			
			if(!tempallthname.isEmpty()) {
			for(int j=0;j<17;j++) {
				if((tempsubht>subject[b][s][j].ht&&subject[b][s][j].ht!=0&&tempallthname.contains(subject[b][s][j].tnames[0])||(tempsubht==0&&tempallthname.contains(subject[b][s][j].tnames[0])))) {
					tempsubht=subject[b][s][j].ht;
					temploc=j;
				}
			}
			}
			else {
				for(int j=0;j<17;j++) {
					if((tempsubht>subject[b][s][j].ht&&subject[b][s][j].ht!=0)||tempsubht==0) {
						tempsubht=subject[b][s][j].ht;
						temploc=j;
					}
				}
			}
			if(tempsubht==0) {
				break;
			}
			Boolean flag=true;
			while(flag) {
				
				if(subject[b][s][temploc].th==true&&subject[b][s][temploc].ht==0) {
					flag=false;
					break;
				}
				
				if(l.isEmpty()) {
					flag=false;
					break;
				}
				
				tc=0;
				while(tc<tchrcount) {
					if(subject[b][s][temploc].tnames[subject[b][s][temploc].tno].contentEquals(teacher[tc].tname)) {
						break;
					}
					tc++;

				}
				
				tempthname.clear();
				tempthused.clear();
				
				for(int x=0;x<17;x++) {
					if(subject[b][s][x].ht!=0&&x!=temploc) {
							tempthname.add(subject[b][s][x].tnames[0]);
					}
				}
				
				if(!tempthname.isEmpty()) {
					Boolean f1=false;
					for(int s1=0;s1<3;s1++) {
						for(int br=0;br<bcount;br++) {
							if(br==b&&s1==s) {
								f1=true;
								break;
							}
							for(int a1=0;a1<10;a1++) {
								for(int b1=1;b1<12;b1=b1+2) {
									if(tempthname.contains(arr[br][s1][b1][a1].value) && arr[b][s][b1][a1].value.contentEquals("")) {
										tempthused.add(((b1-1)*10)+(a1));
									}
								}
							}
						}
						if(f1==true) {
							break;
						}
					}
				
				}
				
				temphs.clear();
				temphs.addAll(l);
				temphs.retainAll(teacher[tc].tchrt);
				if(!tempthused.isEmpty()) {
					temphs.retainAll(tempthused);
				}
				
			int x=(int) temphs.toArray()[rand.nextInt(temphs.toArray().length)];
			int x1=x%10;
			int y1=x/10;
				
			if(subject[b][s][temploc].th==true&&subject[b][s][temploc].ht>0) {
		
					arr[b][s][y1][x1].value=subject[b][s][temploc].name;
					arr[b][s][y1+1][x1].value=subject[b][s][temploc].tnames[subject[b][s][temploc].tno];
			
				subject[b][s][temploc].ht-=1;
				l.remove(x);
				teacher[tc].tchrt.remove(x);
				teacher[tc].tchra.remove(x);
				teacher[tc].tchra.remove(x-1);
				teacher[tc].tchrb.remove(x);
				teacher[tc].tchrb.remove(x+1);
				
			}
			
			}
		}
	}
	
	void savestate(int b,int s) {
		
		for(int i=0;i<tchrcount;i++) {
				lasttchstate[b][s][i].tchrt.clear();
				lasttchstate[b][s][i].tchra.clear();
				lasttchstate[b][s][i].tchrb.clear();
				lasttchstate[b][s][i].tchrt.addAll(teacher[i].tchrt);
				lasttchstate[b][s][i].tchra.addAll(teacher[i].tchra);
				lasttchstate[b][s][i].tchrb.addAll(teacher[i].tchrb);
			}

		for(int j=0;j<17;j++) {
			if(subject[b][s][j].name.contentEquals("")) {
				break;
			}
			lastsubstate[b][s][j].ht=subject[b][s][j].ht;
			lastsubstate[b][s][j].hpra=subject[b][s][j].hpra;
			lastsubstate[b][s][j].hprb=subject[b][s][j].hprb;
			lastsubstate[b][s][j].tno=subject[b][s][j].tno;
		}
		
	}
	
	void loadstate(int b, int s) {
		for(int i=0;i<tchrcount;i++) {
			teacher[i].tchrt.clear();
			teacher[i].tchra.clear();
			teacher[i].tchrb.clear();
			teacher[i].tchrt.addAll(lasttchstate[b][s][i].tchrt);
			teacher[i].tchra.addAll(lasttchstate[b][s][i].tchra);
			teacher[i].tchrb.addAll(lasttchstate[b][s][i].tchrb);
		}
		for(int j=0;j<17;j++) {
			if(subject[b][s][j].name.contentEquals("")) {
				break;
			}
			subject[b][s][j].ht=lastsubstate[b][s][j].ht;
			subject[b][s][j].hpra=lastsubstate[b][s][j].hpra;
			subject[b][s][j].hprb=lastsubstate[b][s][j].hprb;
			subject[b][s][j].tno=lastsubstate[b][s][j].tno;
		}
	}
	
	
	void body() {
		lbl = new JLabel("New Time Table");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 15, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("New Time Table");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 15, 1354, 125);
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
		
		JLabel lblb1 = new JLabel("<---");
		lblb1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb1.setHorizontalAlignment(SwingConstants.CENTER);
		lblb1.setBounds(220, 110, 70, 55);
		f.getContentPane().add(lblb1);
		
		btnb1 = new JButton("");
		btnb1.setIcon(new ImageIcon("img\\button2.png"));
		btnb1.setBackground(null);
		btnb1.setOpaque(false);
		btnb1.setBorder(null);
		btnb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b--;
				if(b<0) {
					b=bcount-1;
				}
				o.td(b, s);
				lblbranch.setText(branch[b]);
				lblbranch1.setText(branch[b]);
			}
		});
		btnb1.setBounds(220, 110, 70, 55);
		f.getContentPane().add(btnb1);
		
		
		lblbranch = new JLabel();
		lblbranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch.setBackground(SystemColor.menu);
		lblbranch.setBounds(290, 120, 760, 35);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel();
		lblbranch1.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch1.setBackground(SystemColor.menu);
		lblbranch1.setBounds(293, 123, 760, 35);
		f.getContentPane().add(lblbranch1);
		
		
		JLabel lbln1 = new JLabel("--->");
		lbln1.setHorizontalAlignment(SwingConstants.CENTER);
		lbln1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln1.setBounds(1050, 110, 70, 55);
		f.getContentPane().add(lbln1);
		
		
		btnn1 = new JButton("");
		btnn1.setIcon(new ImageIcon("img\\button2.png"));
		btnn1.setBackground(null);
		btnn1.setOpaque(false);
		btnn1.setBorder(null);
		btnn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b++;
				if(b>=bcount) {
					b=0;
				}
				o.td(b, s);
				lblbranch.setText(branch[b]);
				lblbranch1.setText(branch[b]);
			}
		});
		btnn1.setBounds(1050, 110, 70, 55);
		f.getContentPane().add(btnn1);
		
		JLabel lblb2 = new JLabel("<---");
		lblb2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb2.setHorizontalAlignment(SwingConstants.CENTER);
		lblb2.setBounds(220, 180, 70, 55);
		f.getContentPane().add(lblb2);
		
		
		btnb2 = new JButton("");
		btnb2.setIcon(new ImageIcon("img\\button2.png"));
		btnb2.setBackground(null);
		btnb2.setOpaque(false);
		btnb2.setBorder(null);
		btnb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s--;
				if(s<0) {
					s=2;
				}
				o.td(b, s);
				lblsemester.setText(sem[s]);
				lblsemester1.setText(sem[s]);
			}
		});
		btnb2.setBounds(220, 180, 70, 55);
		f.getContentPane().add(btnb2);
		
		lblsemester = new JLabel();
		lblsemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester.setForeground(new Color(205, 133, 63));
		lblsemester.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester.setBackground(SystemColor.menu);
		lblsemester.setBounds(290, 190, 760, 35);
		f.getContentPane().add(lblsemester);
		
		lblsemester1 = new JLabel();
		lblsemester1.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester1.setForeground(new Color(0, 0, 0));
		lblsemester1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester1.setBackground(SystemColor.menu);
		lblsemester1.setBounds(293, 193, 760, 35);
		f.getContentPane().add(lblsemester1);
		
		JLabel lbln2 = new JLabel("--->");
		lbln2.setHorizontalAlignment(SwingConstants.CENTER);
		lbln2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln2.setBounds(1050, 180, 70, 55);
		f.getContentPane().add(lbln2);
		
		
		btnn2 = new JButton("");
		btnn2.setIcon(new ImageIcon("img\\button2.png"));
		btnn2.setBackground(null);
		btnn2.setOpaque(false);
		btnn2.setBorder(null);
		btnn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s++;
				if(s>2) {
					s=0;
				}
				o.td(b, s);
				lblsemester.setText(sem[s]);
				lblsemester1.setText(sem[s]);
			}
		});
		btnn2.setBounds(1050, 180, 70, 55);
		f.getContentPane().add(btnn2);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 250, 1269, 341);
		f.getContentPane().add(scrollPane);
		table=new JTable();
		table.setRowHeight(table.getRowHeight()+10);
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),table.getRowHeight()));
		table.setEnabled(false);
		table.getTableHeader().setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		table.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		
		JLabel lblsave = new JLabel("Save");
		lblsave.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblsave.setHorizontalAlignment(SwingConstants.CENTER);
		lblsave.setBounds(610, 600, 130, 100);
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
		
		btnsave.setBounds(610, 600, 130, 100);
		f.getContentPane().add(btnsave);
		
		
		JLabel lblGenerateNew = new JLabel("Generate New");
		lblGenerateNew.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblGenerateNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerateNew.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblGenerateNew);
		
		btnGenerateNew = new JButton("");
		btnGenerateNew.setIcon(new ImageIcon("img\\button.png"));
		btnGenerateNew.setBackground(null);
		btnGenerateNew.setOpaque(false);
		btnGenerateNew.setBorder(null);
		btnGenerateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Generate new Time Table","Generate New",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Please wait, It may take some time..");
					o.generatenew();
					
			}
			}
		});
		btnGenerateNew.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnGenerateNew);
		
		JLabel img11 = new JLabel("");
		img11.setIcon(new ImageIcon("img\\button2bg.png"));
		img11.setBounds(217, 113, 70, 55);
		f.getContentPane().add(img11);
		
		JLabel img12 = new JLabel("");
		img12.setIcon(new ImageIcon("img\\button2bg.png"));
		img12.setBounds(1053, 113, 70, 55);
		f.getContentPane().add(img12);
		
		JLabel img21 = new JLabel("");
		img21.setIcon(new ImageIcon("img\\button2bg.png"));
		img21.setBounds(217, 183, 70, 55);
		f.getContentPane().add(img21);
		
		JLabel img22 = new JLabel("");
		img22.setIcon(new ImageIcon("img\\button2bg.png"));
		img22.setBounds(1053, 183, 70, 55);
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
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
}
