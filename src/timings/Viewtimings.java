package timings;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import homepage.Welcome;

import javax.swing.JTextField;

public class Viewtimings {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel l1;
	JLabel l11;
	JLabel l2;
	JLabel l21;
	JLabel img;
	JButton btnback;
	
	JTextField tfah1;
	JTextField tfam1;
	JTextField tfzh1;
	JTextField tfzm1;
	JTextField tfah2;
	JTextField tfam2;
	JTextField tfzh2;
	JTextField tfzm2;
	
	JTextField tfaht;
	JTextField tfamt;
	JTextField tfzht;
	JTextField tfzmt;
	
	JTextField tfah3;
	JTextField tfam3;
	JTextField tfzh3;
	JTextField tfzm3;
	JTextField tfah4;
	JTextField tfam4;
	JTextField tfzh4;
	JTextField tfzm4;
	
	JTextField tfahr;
	JTextField tfamr;
	JTextField tfzhr;
	JTextField tfzmr;
	
	JTextField tfah5;
	JTextField tfam5;
	JTextField tfzh5;
	JTextField tfzm5;
	JTextField tfah6;
	JTextField tfam6;
	JTextField tfzh6;
	JTextField tfzm6;
	JTextField tfah7;
	JTextField tfam7;
	JTextField tfzh7;
	JTextField tfzm7;
	
	private JLabel lbltott;
	
	
	int la1,lz1;
	int la2,lz2;
	int ta,tz;
	int la3,lz3;
	int la4,lz4;
	int ra,rz;
	int la5,lz5;
	int la6,lz6;
	int la7,lz7;
	
	
	static Viewtimings o=new Viewtimings();
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
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from timing");
			
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
			
			
			
			String lah1=String.format("%02d", lah11);
			String lam1=String.format("%02d", lam11);
			String lzh1=String.format("%02d", lzh11);
			String lzm1=String.format("%02d", lzm11);
			
			String lah2=String.format("%02d", lah22);
			String lam2=String.format("%02d", lam22);
			String lzh2=String.format("%02d", lzh22);
			String lzm2=String.format("%02d", lzm22);
			
			String tah=String.format("%02d", tahh);
			String tam=String.format("%02d", tamm);
			String tzh=String.format("%02d", tzhh);
			String tzm=String.format("%02d", tzmm);
			
			String lah3=String.format("%02d", lah33);
			String lam3=String.format("%02d", lam33);
			String lzh3=String.format("%02d", lzh33);
			String lzm3=String.format("%02d", lzm33);
			
			String lah4=String.format("%02d", lah44);
			String lam4=String.format("%02d", lam44);
			String lzh4=String.format("%02d", lzh44);
			String lzm4=String.format("%02d", lzm44);
			
			String rah=String.format("%02d", rahh);
			String ram=String.format("%02d", ramm);
			String rzh=String.format("%02d", rzhh);
			String rzm=String.format("%02d", rzmm);
			
			String lah5=String.format("%02d", lah55);
			String lam5=String.format("%02d", lam55);
			String lzh5=String.format("%02d", lzh55);
			String lzm5=String.format("%02d", lzm55);
			
			String lah6=String.format("%02d", lah66);
			String lam6=String.format("%02d", lam66);
			String lzh6=String.format("%02d", lzh66);
			String lzm6=String.format("%02d", lzm66);
			
			String lah7=String.format("%02d", lah77);
			String lam7=String.format("%02d", lam77);
			String lzh7=String.format("%02d", lzh77);
			String lzm7=String.format("%02d", lzm77);
			
			
			tfah1.setText(lah1);
			tfam1.setText(lam1);
			tfzh1.setText(lzh1);
			tfzm1.setText(lzm1);
			
			tfah2.setText(lah2);
			tfam2.setText(lam2);
			tfzh2.setText(lzh2);
			tfzm2.setText(lzm2);
			
			tfaht.setText(tah);
			tfamt.setText(tam);
			tfzht.setText(tzh);
			tfzmt.setText(tzm);
			
			tfah3.setText(lah3);
			tfam3.setText(lam3);
			tfzh3.setText(lzh3);
			tfzm3.setText(lzm3);
			
			tfah4.setText(lah4);
			tfam4.setText(lam4);
			tfzh4.setText(lzh4);
			tfzm4.setText(lzm4);
			
			tfahr.setText(rah);
			tfamr.setText(ram);
			tfzhr.setText(rzh);
			tfzmr.setText(rzm);
			
			tfah5.setText(lah5);
			tfam5.setText(lam5);
			tfzh5.setText(lzh5);
			tfzm5.setText(lzm5);
			
			tfah6.setText(lah6);
			tfam6.setText(lam6);
			tfzh6.setText(lzh6);
			tfzm6.setText(lzm6);
			
			tfah7.setText(lah7);
			tfam7.setText(lam7);
			tfzh7.setText(lzh7);
			tfzm7.setText(lzm7);

			
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void body() {
		lbl = new JLabel("View Timings");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("View Timings");
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
		
		
		
		
		
		tfah1 = new JTextField();
		tfah1.setHorizontalAlignment(SwingConstants.CENTER);
		tfah1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah1.setBounds(600, 250, 50, 40);
		tfah1.setEditable(false);
		f.getContentPane().add(tfah1);
		
		tfam1 = new JTextField();
		tfam1.setHorizontalAlignment(SwingConstants.CENTER);
		tfam1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam1.setBounds(670, 250, 50, 40);
		tfam1.setEditable(false);
		f.getContentPane().add(tfam1);
		
		JLabel lblto1 = new JLabel("to");
		lblto1.setForeground(new Color(205, 133, 63));
		lblto1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto1.setBounds(800, 250, 50, 40);
		f.getContentPane().add(lblto1);
		
		JLabel lblto11 = new JLabel("to");
		lblto11.setForeground(new Color(0, 0, 0));
		lblto11.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblto11.setBounds(803, 253, 50, 40);
		f.getContentPane().add(lblto11);
		
		tfzh1 = new JTextField();
		tfzh1.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh1.setBounds(930, 250, 50, 40);
		tfzh1.setEditable(false);
		f.getContentPane().add(tfzh1);
		
		tfzm1 = new JTextField();
		tfzm1.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm1.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm1.setBounds(1000, 250, 50, 40);
		tfzm1.setEditable(false);
		f.getContentPane().add(tfzm1);
		
		
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
		
		tfah2 = new JTextField();
		tfah2.setHorizontalAlignment(SwingConstants.CENTER);
		tfah2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah2.setBounds(600, 300, 50, 40);
		tfah2.setEditable(false);
		f.getContentPane().add(tfah2);
		
		tfam2 = new JTextField();
		tfam2.setHorizontalAlignment(SwingConstants.CENTER);
		tfam2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam2.setBounds(670, 300, 50, 40);
		tfam2.setEditable(false);
		f.getContentPane().add(tfam2);
		
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
		
		tfzh2 = new JTextField();
		tfzh2.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh2.setBounds(930, 300, 50, 40);
		tfzh2.setEditable(false);
		f.getContentPane().add(tfzh2);
		
		tfzm2 = new JTextField();
		tfzm2.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm2.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm2.setBounds(1000, 300, 50, 40);
		tfzm2.setEditable(false);
		f.getContentPane().add(tfzm2);
		
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
		
		tfaht = new JTextField();
		tfaht.setHorizontalAlignment(SwingConstants.CENTER);
		tfaht.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfaht.setBounds(600, 350, 50, 40);
		tfaht.setEditable(false);
		f.getContentPane().add(tfaht);
		
		tfamt = new JTextField();
		tfamt.setHorizontalAlignment(SwingConstants.CENTER);
		tfamt.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfamt.setBounds(670, 350, 50, 40);
		tfamt.setEditable(false);
		f.getContentPane().add(tfamt);
		
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
		
		tfzht = new JTextField();
		tfzht.setHorizontalAlignment(SwingConstants.CENTER);
		tfzht.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzht.setBounds(930, 350, 50, 40);
		tfzht.setEditable(false);
		f.getContentPane().add(tfzht);
		
		tfzmt = new JTextField();
		tfzmt.setHorizontalAlignment(SwingConstants.CENTER);
		tfzmt.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzmt.setBounds(1000, 350, 50, 40);
		tfzmt.setEditable(false);
		f.getContentPane().add(tfzmt);
		
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
		
		tfah3 = new JTextField();
		tfah3.setHorizontalAlignment(SwingConstants.CENTER);
		tfah3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah3.setBounds(600, 400, 50, 40);
		tfah3.setEditable(false);
		f.getContentPane().add(tfah3);
		
		tfam3 = new JTextField();
		tfam3.setHorizontalAlignment(SwingConstants.CENTER);
		tfam3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam3.setBounds(670, 400, 50, 40);
		tfam3.setEditable(false);
		f.getContentPane().add(tfam3);
		
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
		
		tfzh3 = new JTextField();
		tfzh3.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh3.setBounds(930, 400, 50, 40);
		tfzh3.setEditable(false);
		f.getContentPane().add(tfzh3);
		
		tfzm3 = new JTextField();
		tfzm3.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm3.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm3.setBounds(1000, 400, 50, 40);
		tfzm3.setEditable(false);
		f.getContentPane().add(tfzm3);
		
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
		
		tfah4 = new JTextField();
		tfah4.setHorizontalAlignment(SwingConstants.CENTER);
		tfah4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah4.setBounds(600, 450, 50, 40);
		tfah4.setEditable(false);
		f.getContentPane().add(tfah4);
		
		tfam4 = new JTextField();
		tfam4.setHorizontalAlignment(SwingConstants.CENTER);
		tfam4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam4.setBounds(670, 450, 50, 40);
		tfam4.setEditable(false);
		f.getContentPane().add(tfam4);
		
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
		
		tfzh4 = new JTextField();
		tfzh4.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh4.setBounds(930, 450, 50, 40);
		tfzh4.setEditable(false);
		f.getContentPane().add(tfzh4);
		
		tfzm4 = new JTextField();
		tfzm4.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm4.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm4.setBounds(1000, 450, 50, 40);
		tfzm4.setEditable(false);
		f.getContentPane().add(tfzm4);
		
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
		
		tfahr = new JTextField();
		tfahr.setHorizontalAlignment(SwingConstants.CENTER);
		tfahr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfahr.setBounds(600, 500, 50, 40);
		tfahr.setEditable(false);
		f.getContentPane().add(tfahr);
		
		tfamr = new JTextField();
		tfamr.setHorizontalAlignment(SwingConstants.CENTER);
		tfamr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfamr.setBounds(670, 500, 50, 40);
		tfamr.setEditable(false);
		f.getContentPane().add(tfamr);
		
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
		
		tfzhr = new JTextField();
		tfzhr.setHorizontalAlignment(SwingConstants.CENTER);
		tfzhr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzhr.setBounds(930, 500, 50, 40);
		tfzhr.setEditable(false);
		f.getContentPane().add(tfzhr);
		
		tfzmr = new JTextField();
		tfzmr.setHorizontalAlignment(SwingConstants.CENTER);
		tfzmr.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzmr.setBounds(1000, 500, 50, 40);
		tfzmr.setEditable(false);
		f.getContentPane().add(tfzmr);
		
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
		
		tfah5 = new JTextField();
		tfah5.setHorizontalAlignment(SwingConstants.CENTER);
		tfah5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah5.setBounds(600, 550, 50, 40);
		tfah5.setEditable(false);
		f.getContentPane().add(tfah5);
		
		tfam5 = new JTextField();
		tfam5.setHorizontalAlignment(SwingConstants.CENTER);
		tfam5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam5.setBounds(670, 550, 50, 40);
		tfam5.setEditable(false);
		f.getContentPane().add(tfam5);
		
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
		
		tfzh5 = new JTextField();
		tfzh5.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh5.setBounds(930, 550, 50, 40);
		tfzh5.setEditable(false);
		f.getContentPane().add(tfzh5);
		
		tfzm5 = new JTextField();
		tfzm5.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm5.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm5.setBounds(1000, 550, 50, 40);
		tfzm5.setEditable(false);
		f.getContentPane().add(tfzm5);
		
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
		
		tfah6 = new JTextField();
		tfah6.setHorizontalAlignment(SwingConstants.CENTER);
		tfah6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah6.setBounds(600, 600, 50, 40);
		tfah6.setEditable(false);
		f.getContentPane().add(tfah6);
		
		tfam6 = new JTextField();
		tfam6.setHorizontalAlignment(SwingConstants.CENTER);
		tfam6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam6.setBounds(670, 600, 50, 40);
		tfam6.setEditable(false);
		f.getContentPane().add(tfam6);
		
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
		
		tfzh6 = new JTextField();
		tfzh6.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh6.setBounds(930, 600, 50, 40);
		tfzh6.setEditable(false);
		f.getContentPane().add(tfzh6);
		
		tfzm6 = new JTextField();
		tfzm6.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm6.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm6.setBounds(1000, 600, 50, 40);
		tfzm6.setEditable(false);
		f.getContentPane().add(tfzm6);
		
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
		
		tfah7 = new JTextField();
		tfah7.setHorizontalAlignment(SwingConstants.CENTER);
		tfah7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfah7.setBounds(600, 650, 50, 40);
		tfah7.setEditable(false);
		f.getContentPane().add(tfah7);
		
		tfam7 = new JTextField();
		tfam7.setHorizontalAlignment(SwingConstants.CENTER);
		tfam7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfam7.setBounds(670, 650, 50, 40);
		tfam7.setEditable(false);
		f.getContentPane().add(tfam7);
		
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
		
		tfzh7 = new JTextField();
		tfzh7.setHorizontalAlignment(SwingConstants.CENTER);
		tfzh7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzh7.setBounds(930, 650, 50, 40);
		tfzh7.setEditable(false);
		f.getContentPane().add(tfzh7);
		
		tfzm7 = new JTextField();
		tfzm7.setHorizontalAlignment(SwingConstants.CENTER);
		tfzm7.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfzm7.setBounds(1000, 650, 50, 40);
		tfzm7.setEditable(false);
		f.getContentPane().add(tfzm7);
		
		
		o.retrieve();
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
}
