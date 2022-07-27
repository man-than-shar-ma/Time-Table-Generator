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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;


public class Subjects {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnHome;
	JButton btnViewsubjects;
	JButton btnAddsubject;
	JButton btnRemovesubject;
	JButton btnUpdatesubject;
	JLabel img;
	static Subjects o=new Subjects();
	private JLabel lblRemoveSubject;
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

void viewsubject() {
	Viewsubject.main(null);
	f.dispose();
}

void addsubject() {
		Addsubject.main(null);
		f.dispose();
}

void updatesubject() {
	int temp=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);

		PreparedStatement s=conn.prepareStatement("Select distinct branch from subject where id like ?");
		s.setString(1, Homepage.sch+"%");
		ResultSet rs=s.executeQuery();
		
		
		while(rs.next()) {
			temp++;
		}
		
		conn.close();
	}catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	if(temp==0) {
		JOptionPane.showMessageDialog(null, "No subject Found");
	}
	else {
	UpdateSubject.main(null);
	f.dispose();
	}
}

void removesubject() {
	int temp=0;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);

		PreparedStatement s=conn.prepareStatement("Select distinct branch from subject where id like ?");
		s.setString(1, Homepage.sch+"%");
		ResultSet rs=s.executeQuery();
		
		
		while(rs.next()) {
			temp++;
		}
		conn.close();
	}catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	if(temp==0) {
		JOptionPane.showMessageDialog(null, "No subject Found");
	}
	else {
	Removesubject.main(null);
	f.dispose();
	}
}

void body() {
	lbl = new JLabel("Subjects Section");
	lbl.setForeground(new Color(205, 133, 63));
	lbl.setBackground(new Color(240, 240, 240));
	lbl.setHorizontalAlignment(SwingConstants.CENTER);
	lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
	lbl.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(lbl);
	
	label = new JLabel("Subjects Section");
	label.setVerticalAlignment(SwingConstants.BOTTOM);
	label.setForeground(new Color(0, 0, 0));
	label.setBackground(new Color(240, 240, 240));
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
	label.setBounds(0, 100, 1354, 125);
	f.getContentPane().add(label);
	
	
	
	
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

	
	JLabel lblViewSubjects = new JLabel("View Subjects");
	lblViewSubjects.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblViewSubjects.setHorizontalAlignment(SwingConstants.CENTER);
	lblViewSubjects.setBounds(610, 500, 130, 100);
	f.getContentPane().add(lblViewSubjects);
	
	
	btnViewsubjects = new JButton("");
	btnViewsubjects.setIcon(new ImageIcon("img\\button.png"));
	btnViewsubjects.setBackground(null);
	btnViewsubjects.setOpaque(false);
	btnViewsubjects.setBorder(null);
	btnViewsubjects.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.viewsubject();
		}
	});
	btnViewsubjects.setBounds(610, 500, 130, 100);
	f.getContentPane().add(btnViewsubjects);
	

	
	JLabel lblAddSubject = new JLabel("Add Subject");
	lblAddSubject.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblAddSubject.setHorizontalAlignment(SwingConstants.CENTER);
	lblAddSubject.setBounds(320, 350, 130, 100);
	f.getContentPane().add(lblAddSubject);
	
	btnAddsubject = new JButton("");
	btnAddsubject.setIcon(new ImageIcon("img\\button.png"));
	btnAddsubject.setBackground(null);
	btnAddsubject.setOpaque(false);
	btnAddsubject.setBorder(null);
	btnAddsubject.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.addsubject();
		}
	});
	btnAddsubject.setBounds(320, 350, 130, 100);
	f.getContentPane().add(btnAddsubject);

	

	lblRemoveSubject = new JLabel("Remove Subject");
	lblRemoveSubject.setHorizontalAlignment(SwingConstants.CENTER);
	lblRemoveSubject.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblRemoveSubject.setBounds(900, 350, 130, 100);
	f.getContentPane().add(lblRemoveSubject);
	
	btnRemovesubject = new JButton("");
	btnRemovesubject.setIcon(new ImageIcon("img\\button.png"));
	btnRemovesubject.setBackground(null);
	btnRemovesubject.setOpaque(false);
	btnRemovesubject.setBorder(null);
	btnRemovesubject.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		o.removesubject();
		}
	});
	
	btnRemovesubject.setBounds(900, 350, 130, 100);
	f.getContentPane().add(btnRemovesubject);
	
	
	JLabel lblUpdateSubject = new JLabel("Update Subject");
	lblUpdateSubject.setFont(new Font("Hobo Std", Font.PLAIN, 15));
	lblUpdateSubject.setHorizontalAlignment(SwingConstants.CENTER);
	lblUpdateSubject.setBounds(610, 300, 130, 100);
	f.getContentPane().add(lblUpdateSubject);
	
	btnUpdatesubject = new JButton("");
	btnUpdatesubject.setIcon(new ImageIcon("img\\button.png"));
	btnUpdatesubject.setBackground(null);
	btnUpdatesubject.setOpaque(false);
	btnUpdatesubject.setBorder(null);
	btnUpdatesubject.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			o.updatesubject();
		}
	});	
	
	btnUpdatesubject.setBounds(610, 300, 130, 100);
	f.getContentPane().add(btnUpdatesubject);
	
	
	JLabel img1 = new JLabel("");
	img1.setIcon(new ImageIcon("img\\buttonbg.png"));
	img1.setBounds(65, 605, 130, 100);
	f.getContentPane().add(img1);
	
	JLabel img2 = new JLabel("");
	img2.setIcon(new ImageIcon("img\\buttonbg.png"));
	img2.setBounds(317, 355, 130, 100);
	f.getContentPane().add(img2);
	
	JLabel img3 = new JLabel("");
	img3.setIcon(new ImageIcon("img\\buttonbg.png"));
	img3.setBounds(612, 305, 130, 100);
	f.getContentPane().add(img3);
	
	JLabel img4 = new JLabel("");
	img4.setIcon(new ImageIcon("img\\buttonbg.png"));
	img4.setBounds(900, 355, 130, 100);
	f.getContentPane().add(img4);
	
	JLabel img5 = new JLabel("");
	img5.setIcon(new ImageIcon("img\\buttonbg.png"));
	img5.setBounds(612, 505, 130, 100);
	f.getContentPane().add(img5);
	
	img = new JLabel("");
	img.setIcon(new ImageIcon("img\\background.jpg"));
	img.setBounds(0, 0, 1354, 739);
	f.getContentPane().add(img);
}
}