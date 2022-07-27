package timetable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import homepage.Homepage;

public class Timetable {
	
	JFrame f;
	JLabel lbl;
	JLabel lbl1;
	JButton btnHome;
	JLabel lblViewcurrent;
	JLabel lblGeneratenew;
	JButton btnViewcurrent;
	JButton btnGeneratenew;
	JLabel img;
	
	
	static Timetable o=new Timetable();
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
	
	void viewcurrent() {
		View.main(null);
		f.dispose();
	}
	
	void generatenew() {
		Gen.main(null);
		f.dispose();
	}
	
	void body(){
		lbl = new JLabel("Time Table Section");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lbl1 = new JLabel("Time Table Section");
		lbl1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl1.setForeground(new Color(0, 0, 0));
		lbl1.setBackground(new Color(240, 240, 240));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lbl1.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl1);
		
		
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
		
		
		lblViewcurrent = new JLabel("View Current");
		lblViewcurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewcurrent.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblViewcurrent.setBounds(320, 350, 130, 100);
		f.getContentPane().add(lblViewcurrent);
		
		
		btnViewcurrent = new JButton("");
		btnViewcurrent.setIcon(new ImageIcon("img\\button.png"));
		btnViewcurrent.setBackground(null);
		btnViewcurrent.setOpaque(false);
		btnViewcurrent.setBorder(null);
		btnViewcurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.viewcurrent();
			}
		});
		
		btnViewcurrent.setBounds(320, 350, 130, 100);
		f.getContentPane().add(btnViewcurrent);
		
		lblGeneratenew = new JLabel("Generate New");
		lblGeneratenew.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneratenew.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblGeneratenew.setBounds(900, 350, 130, 100);
		f.getContentPane().add(lblGeneratenew);

		btnGeneratenew = new JButton("");
		btnGeneratenew.setIcon(new ImageIcon("img\\button.png"));
		btnGeneratenew.setBackground(null);
		btnGeneratenew.setOpaque(false);
		btnGeneratenew.setBorder(null);
		btnGeneratenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.generatenew();
			}
		});
		
		btnGeneratenew.setBounds(900, 350, 130, 100);
		f.getContentPane().add(btnGeneratenew);
		
		
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
