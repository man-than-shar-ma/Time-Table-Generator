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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;

public class Timings {
	JFrame f;
	JLabel lbl;
	JLabel lblTimingsSection;
	JLabel img;
	JButton btnHome;
	JButton btnViewtimings;
	JButton btnNewtimings;
	private JLabel lblNewtimings;
	private JLabel lblViewtimings;
	static Timings o=new Timings();

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
	void home() {
		Homepage.main(null);
		f.dispose();
	}
	
	void viewtimings() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from timing");
			
			int count=0;
			while(rs.next()) {
				count++;
			}
			conn.close();
			if(count==0) {
				JOptionPane.showMessageDialog(null, "Create New Timings First");
			}
			else {
				Viewtimings.main(null);
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
	void newtimings() {
		Newtimings.main(null);
		f.dispose();
	}
	
	
	void body() {
		lbl = new JLabel("Timings Section");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lblTimingsSection = new JLabel("Timings Section");
		lblTimingsSection.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTimingsSection.setForeground(new Color(0, 0, 0));
		lblTimingsSection.setBackground(new Color(240, 240, 240));
		lblTimingsSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimingsSection.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lblTimingsSection.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lblTimingsSection);


		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(70, 600, 130, 100);
		f.getContentPane().add(lblHome);
		
		btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon("img\\button.png"));
		btnHome.setBackground(null);
		btnHome.setOpaque(false);
		btnHome.setBorder(null);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.home();
			}
		});
		btnHome.setBounds(70, 600, 130, 100);
		f.getContentPane().add(btnHome);

		btnViewtimings = new JButton("");
		btnViewtimings.setIcon(new ImageIcon("img\\button.png"));
		btnViewtimings.setBackground(null);
		btnViewtimings.setOpaque(false);
		btnViewtimings.setBorder(null);
		btnViewtimings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.viewtimings();
			}
		});
		
		lblViewtimings = new JLabel("View Timings");
		lblViewtimings.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewtimings.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblViewtimings.setBounds(320, 350, 130, 100);
		f.getContentPane().add(lblViewtimings);
		btnViewtimings.setBounds(320, 350, 130, 100);
		f.getContentPane().add(btnViewtimings);

		btnNewtimings = new JButton("");
		btnNewtimings.setIcon(new ImageIcon("img\\button.png"));
		btnNewtimings.setBackground(null);
		btnNewtimings.setOpaque(false);
		btnNewtimings.setBorder(null);
		btnNewtimings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.newtimings();
			}
		});
		
		lblNewtimings = new JLabel("New Timings");
		lblNewtimings.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewtimings.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblNewtimings.setBounds(900, 350, 130, 100);
		f.getContentPane().add(lblNewtimings);
		btnNewtimings.setBounds(900, 350, 130, 100);
		f.getContentPane().add(btnNewtimings);
		
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setIcon(new ImageIcon("img\\buttonbg.png"));
		img2.setBounds(317, 355, 130, 100);
		f.getContentPane().add(img2);
		
		JLabel img4 = new JLabel("");
		img4.setIcon(new ImageIcon("img\\buttonbg.png"));
		img4.setBounds(905, 355, 130, 100);
		f.getContentPane().add(img4);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);

	}

}
