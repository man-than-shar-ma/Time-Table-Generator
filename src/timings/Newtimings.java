package timings;

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
import javax.swing.SwingConstants;

import homepage.Welcome;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Newtimings {
	
	JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel l1;
	JLabel l11;
	JLabel l2;
	JLabel l21;
	JLabel img;
	JButton btnback;
	JButton btnsave;

	SpinnerModel ha1=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel ma1=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz1=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz1=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz2=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz2=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hzt=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mzt=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz3=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz3=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz4=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz4=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hzr=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mzr=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz5=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz5=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz6=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz6=new SpinnerNumberModel(0,0,59,5);
	SpinnerModel hz7=new SpinnerNumberModel(0,0,23,1);
	SpinnerModel mz7=new SpinnerNumberModel(0,0,59,5);
	
	
	JSpinner sah1;
	JSpinner sam1;
	JSpinner szh1;
	JSpinner szm1;
	JSpinner sah2;
	JSpinner sam2;
	JSpinner szh2;
	JSpinner szm2;
	
	JSpinner saht;
	JSpinner samt;
	JSpinner szht;
	JSpinner szmt;
	
	JSpinner sah3;
	JSpinner sam3;
	JSpinner szh3;
	JSpinner szm3;
	JSpinner sah4;
	JSpinner sam4;
	JSpinner szh4;
	JSpinner szm4;
	
	JSpinner sahr;
	JSpinner samr;
	JSpinner szhr;
	JSpinner szmr;
	
	JSpinner sah5;
	JSpinner sam5;
	JSpinner szh5;
	JSpinner szm5;
	JSpinner sah6;
	JSpinner sam6;
	JSpinner szh6;
	JSpinner szm6;
	JSpinner sah7;
	JSpinner sam7;
	JSpinner szh7;
	JSpinner szm7;
	
	static Newtimings o=new Newtimings();
	private JLabel lbltott;
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
		Timings.main(null);
		f.dispose();
	}
	 
	void retrieve() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
	
			int lah1=0;
			int lam1=0;
			int lzh1=0;
			int lzm1=0;
			int lah2=0;
			int lam2=0;
			int lzh2=0;
			int lzm2=0;
			int tah=0;
			int tam=0;
			int tzh=0;
			int tzm=0;
			int lah3=0;
			int lam3=0;
			int lzh3=0;
			int lzm3=0;
			int lah4=0;
			int lam4=0;
			int lzh4=0;
			int lzm4=0;
			int rah=0;
			int ram=0;
			int rzh=0;
			int rzm=0;
			int lah5=0;
			int lam5=0;
			int lzh5=0;
			int lzm5=0;
			int lah6=0;
			int lam6=0;
			int lzh6=0;
			int lzm6=0;
			int lah7=0;
			int lam7=0;
			int lzh7=0;
			int lzm7=0;
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from timing");
			while(rs.next()) {
				lah1=(rs.getInt(1)/100);
				lam1=(rs.getInt(1)%100);
				lzh1=(rs.getInt(2)/100);
				lzm1=(rs.getInt(2)%100);
				lah2=(rs.getInt(3)/100);
				lam2=(rs.getInt(3)%100);
				lzh2=(rs.getInt(4)/100);
				lzm2=(rs.getInt(4)%100);
				tah=(rs.getInt(5)/100);
				tam=(rs.getInt(5)%100);
				tzh=(rs.getInt(6)/100);
				tzm=(rs.getInt(6)%100);
				lah3=(rs.getInt(7)/100);
				lam3=(rs.getInt(7)%100);
				lzh3=(rs.getInt(8)/100);
				lzm3=(rs.getInt(8)%100);
				lah4=(rs.getInt(9)/100);
				lam4=(rs.getInt(9)%100);
				lzh4=(rs.getInt(10)/100);
				lzm4=(rs.getInt(10)%100);
				rah=(rs.getInt(11)/100);
				ram=(rs.getInt(11)%100);
				rzh=(rs.getInt(12)/100);
				rzm=(rs.getInt(12)%100);
				lah5=(rs.getInt(13)/100);
				lam5=(rs.getInt(13)%100);
				lzh5=(rs.getInt(14)/100);
				lzm5=(rs.getInt(14)%100);
				lah6=(rs.getInt(15)/100);
				lam6=(rs.getInt(15)%100);
				lzh6=(rs.getInt(16)/100);
				lzm6=(rs.getInt(16)%100);
				lah7=(rs.getInt(17)/100);
				lam7=(rs.getInt(17)%100);
				lzh7=(rs.getInt(18)/100);
				lzm7=(rs.getInt(18)%100);
			}
			
			sah1.setValue(lah1);
			sam1.setValue(lam1);
			szh1.setValue(lzh1);
			szm1.setValue(lzm1);
			sah2.setValue(lah2);
			sam2.setValue(lam2);
			szh2.setValue(lzh2);
			szm2.setValue(lzm2);
			
			saht.setValue(tah);
			samt.setValue(tam);
			szht.setValue(tzh);
			szmt.setValue(tzm);
			
			sah3.setValue(lah3);
			sam3.setValue(lam3);
			szh3.setValue(lzh3);
			szm3.setValue(lzm3);
			sah4.setValue(lah4);
			sam4.setValue(lam4);
			szh4.setValue(lzh4);
			szm4.setValue(lzm4);
			
			sahr.setValue(rah);
			samr.setValue(ram);
			szhr.setValue(rzh);
			szmr.setValue(rzm);
			
			sah5.setValue(lah5);
			sam5.setValue(lam5);
			szh5.setValue(lzh5);
			szm5.setValue(lzm5);
			sah6.setValue(lah6);
			sam6.setValue(lam6);
			szh6.setValue(lzh6);
			szm6.setValue(lzm6);
			sah7.setValue(lah7);
			sam7.setValue(lam7);
			szh7.setValue(lzh7);
			szm7.setValue(lzm7);

		conn.close();	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void save() {
		
		int la1=0;
		int lz1=0;
		int la2=0;
		int lz2=0;
		int ta=0;
		int tz=0;
		int la3=0;
		int lz3=0;
		int la4=0;
		int lz4=0;
		int ra=0;
		int rz=0;
		int la5=0;
		int lz5=0;
		int la6=0;
		int lz6=0;
		int la7=0;
		int lz7=0;
		
		String sah11=sah1.getValue().toString();
		String sam11=sam1.getValue().toString();
		int sah111=Integer.parseInt(sah11);
		int sam111=Integer.parseInt(sam11);
		la1=sah111*100+sam111;
		
		String szh11=szh1.getValue().toString();
		String szm11=szm1.getValue().toString();
		int szh111=Integer.parseInt(szh11);
		int szm111=Integer.parseInt(szm11);
		lz1=szh111*100+szm111;
		
		String sah22=sah2.getValue().toString();
		String sam22=sam2.getValue().toString();
		int sah222=Integer.parseInt(sah22);
		int sam222=Integer.parseInt(sam22);
		la2=sah222*100+sam222;
		
		String szh22=szh2.getValue().toString();
		String szm22=szm2.getValue().toString();
		int szh222=Integer.parseInt(szh22);
		int szm222=Integer.parseInt(szm22);
		lz2=szh222*100+szm222;
		
		
		String sahtt=saht.getValue().toString();
		String samtt=samt.getValue().toString();
		int sahttt=Integer.parseInt(sahtt);
		int samttt=Integer.parseInt(samtt);
		ta=sahttt*100+samttt;
		
		String szhtt=szht.getValue().toString();
		String szmtt=szmt.getValue().toString();
		int szhttt=Integer.parseInt(szhtt);
		int szmttt=Integer.parseInt(szmtt);
		tz=szhttt*100+szmttt;
		
		
		String sah33=sah3.getValue().toString();
		String sam33=sam3.getValue().toString();
		int sah333=Integer.parseInt(sah33);
		int sam333=Integer.parseInt(sam33);
		la3=sah333*100+sam333;
		
		String szh33=szh3.getValue().toString();
		String szm33=szm3.getValue().toString();
		int szh333=Integer.parseInt(szh33);
		int szm333=Integer.parseInt(szm33);
		lz3=szh333*100+szm333;
		
		String sah44=sah4.getValue().toString();
		String sam44=sam4.getValue().toString();
		int sah444=Integer.parseInt(sah44);
		int sam444=Integer.parseInt(sam44);
		la4=sah444*100+sam444;
		
		String szh44=szh4.getValue().toString();
		String szm44=szm4.getValue().toString();
		int szh444=Integer.parseInt(szh44);
		int szm444=Integer.parseInt(szm44);
		lz4=szh444*100+szm444;
		
		
		String sahrr=sahr.getValue().toString();
		String samrr=samr.getValue().toString();
		int sahrrr=Integer.parseInt(sahrr);
		int samrrr=Integer.parseInt(samrr);
		ra=sahrrr*100+samrrr;
		
		String szhrr=szhr.getValue().toString();
		String szmrr=szmr.getValue().toString();
		int szhrrr=Integer.parseInt(szhrr);
		int szmrrr=Integer.parseInt(szmrr);
		rz=szhrrr*100+szmrrr;
		
		
		String sah55=sah5.getValue().toString();
		String sam55=sam5.getValue().toString();
		int sah555=Integer.parseInt(sah55);
		int sam555=Integer.parseInt(sam55);
		la5=sah555*100+sam555;
		
		String szh55=szh5.getValue().toString();
		String szm55=szm5.getValue().toString();
		int szh555=Integer.parseInt(szh55);
		int szm555=Integer.parseInt(szm55);
		lz5=szh555*100+szm555;
		
		String sah66=sah6.getValue().toString();
		String sam66=sam6.getValue().toString();
		int sah666=Integer.parseInt(sah66);
		int sam666=Integer.parseInt(sam66);
		la6=sah666*100+sam666;
		
		String szh66=szh6.getValue().toString();
		String szm66=szm6.getValue().toString();
		int szh666=Integer.parseInt(szh66);
		int szm666=Integer.parseInt(szm66);
		lz6=szh666*100+szm666;
		
		String sah77=sah7.getValue().toString();
		String sam77=sam7.getValue().toString();
		int sah777=Integer.parseInt(sah77);
		int sam777=Integer.parseInt(sam77);
		la7=sah777*100+sam777;
		
		String szh77=szh7.getValue().toString();
		String szm77=szm7.getValue().toString();
		int szh777=Integer.parseInt(szh77);
		int szm777=Integer.parseInt(szm77);
		lz7=szh777*100+szm777;
		
		
		if(la1>=lz1) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 1");
		}
		else if(la2>=lz2) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 2");
		}
		else if(ta>=tz) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Tea Break");
		}
		else if(la3>=lz3) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 3");
		}
		else if(la4>=lz4) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 4");
		}
		else if(ra>=rz) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Recess");
		}
		else if(la5>=lz5) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 5");
		}
		else if(la6>=lz6) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 6");
		}
		else if(la7>=lz7) {
			JOptionPane.showMessageDialog(null, "Error: Check Timings of Lecture 7");
		}
		else {
		try {
			
			int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Change Timings","Timings",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			s.executeUpdate("Delete from timing");
			
			PreparedStatement ps=conn.prepareStatement("Insert into timing values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, la1);
			ps.setInt(2, lz1);
			ps.setInt(3, la2);
			ps.setInt(4, lz2);
			ps.setInt(5, ta);
			ps.setInt(6, tz);
			ps.setInt(7, la3);
			ps.setInt(8, lz3);
			ps.setInt(9, la4);
			ps.setInt(10, lz4);
			ps.setInt(11, ra);
			ps.setInt(12, rz);
			ps.setInt(13, la5);
			ps.setInt(14, lz5);
			ps.setInt(15, la6);
			ps.setInt(16, lz6);
			ps.setInt(17, la7);
			ps.setInt(18, lz7);
			ps.executeUpdate();
			
			conn.close();
			JOptionPane.showMessageDialog(null, "Timings Set Successfully");
			Timings.main(null);
			f.dispose();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		
	}
	
	void body() {
		lbl = new JLabel("New Timings");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("New Timings");
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
		
		JLabel lblhour = new JLabel("hh");
		lblhour.setForeground(new Color(205, 133, 63));
		lblhour.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblhour.setBounds(600, 210, 50, 40);
		f.getContentPane().add(lblhour);
		
		JLabel lbls1 = new JLabel(":");
		lbls1.setForeground(new Color(205, 133, 63));
		lbls1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbls1.setBounds(650, 210, 20, 40);
		f.getContentPane().add(lbls1);
		
		JLabel lblminute = new JLabel("mm");
		lblminute.setForeground(new Color(205, 133, 63));
		lblminute.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblminute.setBounds(670, 210, 60, 40);
		f.getContentPane().add(lblminute);
		
		JLabel lblhour1 = new JLabel("hh");
		lblhour1.setForeground(new Color(0, 0, 0));
		lblhour1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblhour1.setBounds(603, 213, 50, 40);
		f.getContentPane().add(lblhour1);
		
		JLabel lbls11 = new JLabel(":");
		lbls11.setForeground(new Color(0, 0, 0));
		lbls11.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbls11.setBounds(653, 213, 20, 40);
		f.getContentPane().add(lbls11);
		
		JLabel lblminute1 = new JLabel("mm");
		lblminute1.setForeground(new Color(0, 0, 0));
		lblminute1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblminute1.setBounds(673, 213, 60, 40);
		f.getContentPane().add(lblminute1);
		
		JLabel lblhour2 = new JLabel("hh");
		lblhour2.setForeground(new Color(205, 133, 63));
		lblhour2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblhour2.setBounds(930, 210, 60, 40);
		f.getContentPane().add(lblhour2);
		
		JLabel lbls2 = new JLabel(":");
		lbls2.setForeground(new Color(205, 133, 63));
		lbls2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbls2.setBounds(980, 210, 20, 40);
		f.getContentPane().add(lbls2);
		
		JLabel lblminute2 = new JLabel("mm");
		lblminute2.setForeground(new Color(205, 133, 63));
		lblminute2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblminute2.setBounds(1000, 210, 60, 40);
		f.getContentPane().add(lblminute2);
		
		JLabel lblhour3 = new JLabel("hh");
		lblhour3.setForeground(Color.BLACK);
		lblhour3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblhour3.setBounds(933, 213, 60, 40);
		f.getContentPane().add(lblhour3);
		
		JLabel lbls22 = new JLabel(":");
		lbls22.setForeground(Color.BLACK);
		lbls22.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbls22.setBounds(982, 213, 20, 40);
		f.getContentPane().add(lbls22);
		
		JLabel lblminute3 = new JLabel("mm");
		lblminute3.setForeground(Color.BLACK);
		lblminute3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblminute3.setBounds(1003, 213, 60, 40);
		f.getContentPane().add(lblminute3);
		
		
		
		l1 = new JLabel("Lecture 1 : ");
		l1.setForeground(new Color(205, 133, 63));
		l1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l1.setBounds(300, 250, 300, 40);
		f.getContentPane().add(l1);
		
		l11 = new JLabel("Lecture 1 : ");
		l11.setForeground(new Color(0, 0, 0));
		l11.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l11.setBounds(303, 253, 300, 40);
		f.getContentPane().add(l11);
		
		
		
		
		
		sah1 = new JSpinner(ha1);
		sah1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah1.setBounds(600, 250, 50, 40);
		((JSpinner.DefaultEditor)sah1.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah1);
		
		sam1 = new JSpinner(ma1);
		sam1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam1.setBounds(670, 250, 50, 40);
		((JSpinner.DefaultEditor)sam1.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam1);
		
		JLabel lblto1 = new JLabel("to");
		lblto1.setForeground(new Color(205, 133, 63));
		lblto1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto1.setBounds(800, 250, 50, 40);
		f.getContentPane().add(lblto1);
		
		JLabel lblto11 = new JLabel("to");
		lblto11.setForeground(new Color(0, 0, 0));
		lblto11.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto11.setBounds(803, 253, 50, 40);
		
		szh1 = new JSpinner(hz1);
		szh1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh1.setBounds(930, 250, 50, 40);
		((JSpinner.DefaultEditor)szh1.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(lblto11);
		f.getContentPane().add(szh1);
		
		szm1 = new JSpinner(mz1);
		szm1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm1.setBounds(1000, 250, 50, 40);
		((JSpinner.DefaultEditor)szm1.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm1);
		
		
		l2 = new JLabel("Lecture 2 : ");
		l2.setForeground(new Color(205, 133, 63));
		l2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l2.setBounds(300, 300, 300, 40);
		f.getContentPane().add(l2);
		
		l21 = new JLabel("Lecture 2 : ");
		l21.setForeground(new Color(0, 0, 0));
		l21.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l21.setBounds(303, 303, 300, 40);
		f.getContentPane().add(l21);
		
		sah2 = new JSpinner(hz1);
		sah2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah2.setBounds(600, 300, 50, 40);
		((JSpinner.DefaultEditor)sah2.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah2);
		
		sam2 = new JSpinner(mz1);
		sam2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam2.setBounds(670, 300, 50, 40);
		((JSpinner.DefaultEditor)sam2.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam2);
		
		JLabel lblto2 = new JLabel("to");
		lblto2.setForeground(new Color(205, 133, 63));
		lblto2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto2.setBounds(800, 300, 50, 40);
		f.getContentPane().add(lblto2);
		
		JLabel lblto22 = new JLabel("to");
		lblto22.setForeground(new Color(0, 0, 0));
		lblto22.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto22.setBounds(803, 303, 50, 40);
		f.getContentPane().add(lblto22);
		
		szh2 = new JSpinner(hz2);
		szh2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh2.setBounds(930, 300, 50, 40);
		((JSpinner.DefaultEditor)szh2.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szh2);
		
		szm2 = new JSpinner(mz2);
		szm2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm2.setBounds(1000, 300, 50, 40);
		((JSpinner.DefaultEditor)szm2.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm2);
		
		JLabel tb = new JLabel("Tea Break : ");
		tb.setForeground(new Color(205, 133, 63));
		tb.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		tb.setBounds(300, 350, 300, 40);
		f.getContentPane().add(tb);
		
		JLabel tb1 = new JLabel("Tea Break : ");
		tb1.setForeground(Color.BLACK);
		tb1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		tb1.setBounds(303, 353, 300, 40);
		f.getContentPane().add(tb1);
		
		saht = new JSpinner(hz2);
		saht.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		saht.setBounds(600, 350, 50, 40);
		((JSpinner.DefaultEditor)saht.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(saht);
		
		samt = new JSpinner(mz2);
		samt.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		samt.setBounds(670, 350, 50, 40);
		((JSpinner.DefaultEditor)samt.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(samt);
		
		JLabel lbltot = new JLabel("to");
		lbltot.setForeground(new Color(205, 133, 63));
		lbltot.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbltot.setBounds(800, 350, 50, 40);
		f.getContentPane().add(lbltot);
		
		lbltott = new JLabel("to");
		lbltott.setForeground(Color.BLACK);
		lbltott.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbltott.setBounds(803, 353, 50, 40);
		f.getContentPane().add(lbltott);
		
		szht = new JSpinner(hzt);
		szht.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szht.setBounds(930, 350, 50, 40);
		((JSpinner.DefaultEditor)szht.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szht);
		
		szmt = new JSpinner(mzt);
		szmt.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szmt.setBounds(1000, 350, 50, 40);
		((JSpinner.DefaultEditor)szmt.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szmt);
		
		JLabel l3 = new JLabel("Lecture 3 : ");
		l3.setForeground(new Color(205, 133, 63));
		l3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l3.setBounds(300, 400, 300, 40);
		f.getContentPane().add(l3);
		
		JLabel l31 = new JLabel("Lecture 3 : ");
		l31.setForeground(Color.BLACK);
		l31.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l31.setBounds(303, 403, 300, 40);
		f.getContentPane().add(l31);
		
		sah3 = new JSpinner(hzt);
		sah3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah3.setBounds(600, 400, 50, 40);
		((JSpinner.DefaultEditor)sah3.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah3);
		
		sam3 = new JSpinner(mzt);
		sam3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam3.setBounds(670, 400, 50, 40);
		((JSpinner.DefaultEditor)sam3.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam3);
		
		JLabel lblto3 = new JLabel("to");
		lblto3.setForeground(new Color(205, 133, 63));
		lblto3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto3.setBounds(800, 400, 50, 40);
		f.getContentPane().add(lblto3);
		
		JLabel lblto33 = new JLabel("to");
		lblto33.setForeground(Color.BLACK);
		lblto33.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto33.setBounds(803, 403, 50, 40);
		f.getContentPane().add(lblto33);
		
		szh3 = new JSpinner(hz3);
		szh3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh3.setBounds(930, 400, 50, 40);
		((JSpinner.DefaultEditor)szh3.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szh3);
		
		szm3 = new JSpinner(mz3);
		szm3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm3.setBounds(1000, 400, 50, 40);
		((JSpinner.DefaultEditor)szm3.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm3);
		
		JLabel l4 = new JLabel("Lecture 4 : ");
		l4.setForeground(new Color(205, 133, 63));
		l4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l4.setBounds(300, 450, 300, 40);
		f.getContentPane().add(l4);
		
		JLabel l41 = new JLabel("Lecture 4 : ");
		l41.setForeground(Color.BLACK);
		l41.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l41.setBounds(303, 453, 300, 40);
		f.getContentPane().add(l41);
		
		sah4 = new JSpinner(hz3);
		sah4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah4.setBounds(600, 450, 50, 40);
		((JSpinner.DefaultEditor)sah4.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah4);
		
		sam4 = new JSpinner(mz3);
		sam4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam4.setBounds(670, 450, 50, 40);
		((JSpinner.DefaultEditor)sam4.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam4);
		
		JLabel lblto4 = new JLabel("to");
		lblto4.setForeground(new Color(205, 133, 63));
		lblto4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto4.setBounds(800, 450, 50, 40);
		f.getContentPane().add(lblto4);
		
		JLabel lblto44 = new JLabel("to");
		lblto44.setForeground(Color.BLACK);
		lblto44.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto44.setBounds(803, 453, 50, 40);
		f.getContentPane().add(lblto44);
		
		szh4 = new JSpinner(hz4);
		szh4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh4.setBounds(930, 450, 50, 40);
		((JSpinner.DefaultEditor)szh4.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szh4);
		
		szm4 = new JSpinner(mz4);
		szm4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm4.setBounds(1000, 450, 50, 40);
		((JSpinner.DefaultEditor)szm4.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm4);
		
		JLabel r = new JLabel("Recess : ");
		r.setForeground(new Color(205, 133, 63));
		r.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		r.setBounds(300, 500, 300, 40);
		f.getContentPane().add(r);
		
		JLabel r1 = new JLabel("Recess : ");
		r1.setForeground(Color.BLACK);
		r1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		r1.setBounds(303, 503, 300, 40);
		f.getContentPane().add(r1);
		
		sahr = new JSpinner(hz4);
		sahr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sahr.setBounds(600, 500, 50, 40);
		((JSpinner.DefaultEditor)sahr.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sahr);
		
		samr = new JSpinner(mz4);
		samr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		samr.setBounds(670, 500, 50, 40);
		((JSpinner.DefaultEditor)samr.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(samr);
		
		JLabel lbltor = new JLabel("to");
		lbltor.setForeground(new Color(205, 133, 63));
		lbltor.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbltor.setBounds(800, 500, 50, 40);
		f.getContentPane().add(lbltor);
		
		JLabel lbltorr = new JLabel("to");
		lbltorr.setForeground(Color.BLACK);
		lbltorr.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbltorr.setBounds(803, 503, 50, 40);
		f.getContentPane().add(lbltorr);
		
		szhr = new JSpinner(hzr);
		szhr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szhr.setBounds(930, 500, 50, 40);
		((JSpinner.DefaultEditor)szhr.getEditor()).getTextField().setEditable(false);		
		f.getContentPane().add(szhr);
		
		szmr = new JSpinner(mzr);
		szmr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szmr.setBounds(1000, 500, 50, 40);
		((JSpinner.DefaultEditor)szmr.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szmr);
		
		JLabel l5 = new JLabel("Lecture 5 : ");
		l5.setForeground(new Color(205, 133, 63));
		l5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l5.setBounds(300, 550, 300, 40);
		f.getContentPane().add(l5);
		
		JLabel l51 = new JLabel("Lecture 5 : ");
		l51.setForeground(Color.BLACK);
		l51.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l51.setBounds(303, 553, 300, 40);
		f.getContentPane().add(l51);
		
		sah5 = new JSpinner(hzr);
		sah5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah5.setBounds(600, 550, 50, 40);
		((JSpinner.DefaultEditor)sah5.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah5);
		
		sam5 = new JSpinner(mzr);
		sam5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam5.setBounds(670, 550, 50, 40);
		((JSpinner.DefaultEditor)sam5.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam5);
		
		JLabel lblto5 = new JLabel("to");
		lblto5.setForeground(new Color(205, 133, 63));
		lblto5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto5.setBounds(800, 550, 50, 40);
		f.getContentPane().add(lblto5);
		
		JLabel tblto55 = new JLabel("to");
		tblto55.setForeground(Color.BLACK);
		tblto55.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		tblto55.setBounds(803, 553, 50, 40);
		f.getContentPane().add(tblto55);
		
		szh5 = new JSpinner(hz5);
		szh5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh5.setBounds(930, 550, 50, 40);
		((JSpinner.DefaultEditor)szh5.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szh5);
		
		szm5 = new JSpinner(mz5);
		szm5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm5.setBounds(1000, 550, 50, 40);
		((JSpinner.DefaultEditor)szm5.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm5);
		
		JLabel l6 = new JLabel("Lecture 6 : ");
		l6.setForeground(new Color(205, 133, 63));
		l6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l6.setBounds(300, 600, 300, 40);
		f.getContentPane().add(l6);
		
		JLabel l61 = new JLabel("Lecture 6 : ");
		l61.setForeground(Color.BLACK);
		l61.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l61.setBounds(303, 603, 300, 40);
		f.getContentPane().add(l61);
		
		sah6 = new JSpinner(hz5);
		sah6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah6.setBounds(600, 600, 50, 40);
		((JSpinner.DefaultEditor)sah6.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah6);
		
		sam6 = new JSpinner(mz5);
		sam6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam6.setBounds(670, 600, 50, 40);
		((JSpinner.DefaultEditor)sam6.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam6);
		
		JLabel lblto6 = new JLabel("to");
		lblto6.setForeground(new Color(205, 133, 63));
		lblto6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto6.setBounds(800, 600, 50, 40);
		f.getContentPane().add(lblto6);
		
		JLabel lblto66 = new JLabel("to");
		lblto66.setForeground(Color.BLACK);
		lblto66.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto66.setBounds(803, 603, 50, 40);
		f.getContentPane().add(lblto66);
		
		szh6 = new JSpinner(hz6);
		szh6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh6.setBounds(930, 600, 50, 40);
		((JSpinner.DefaultEditor)szh6.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szh6);
		
		szm6 = new JSpinner(mz6);
		szm6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm6.setBounds(1000, 600, 50, 40);
		((JSpinner.DefaultEditor)szm6.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm6);
		
		JLabel l7 = new JLabel("Lecture 7 : ");
		l7.setForeground(new Color(205, 133, 63));
		l7.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l7.setBounds(300, 650, 300, 40);
		f.getContentPane().add(l7);
		
		JLabel l71 = new JLabel("Lecture 7 : ");
		l71.setForeground(Color.BLACK);
		l71.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		l71.setBounds(303, 653, 300, 40);
		f.getContentPane().add(l71);
		
		sah7 = new JSpinner(hz6);
		sah7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sah7.setBounds(600, 650, 50, 40);
		((JSpinner.DefaultEditor)sah7.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sah7);
		
		sam7 = new JSpinner(mz6);
		sam7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		sam7.setBounds(670, 650, 50, 40);
		((JSpinner.DefaultEditor)sam7.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(sam7);
		
		JLabel lblto7 = new JLabel("to");
		lblto7.setForeground(new Color(205, 133, 63));
		lblto7.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto7.setBounds(800, 650, 50, 40);
		f.getContentPane().add(lblto7);
		
		JLabel lblto77 = new JLabel("to");
		lblto77.setForeground(Color.BLACK);
		lblto77.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto77.setBounds(803, 653, 50, 40);
		f.getContentPane().add(lblto77);
		
		szh7 = new JSpinner(hz7);
		szh7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szh7.setBounds(930, 650, 50, 40);
		((JSpinner.DefaultEditor)szh7.getEditor()).getTextField().setEditable(false);		
		f.getContentPane().add(szh7);
		
		szm7 = new JSpinner(mz7);
		szm7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		szm7.setBounds(1000, 650, 50, 40);
		((JSpinner.DefaultEditor)szm7.getEditor()).getTextField().setEditable(false);
		f.getContentPane().add(szm7);
		
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
