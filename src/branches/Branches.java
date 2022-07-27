package branches;

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

public class Branches {
	JFrame f;
	JLabel lbl;
	JLabel lblBranchesSection;
	JButton btnHome;
	JButton btnViewsbranches;
	JButton btnAddbranch;
	JButton btnRemovebranch;
	JButton btnUpdatebranch;
	JLabel img;
	static Branches o=new Branches();
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

	void viewbranch() {
		Viewbranch.main(null);
		f.dispose();
	}

	void addbranch() {
		Addbranch.main(null);
		f.dispose();
	}

	void updatebranch() {
		Updatebranch.main(null);
		f.dispose();
	}

	void removebranch() {
		Removebranch.main(null);
		f.dispose();
	}

	void body() {
		lbl = new JLabel("Branches Section");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		lblBranchesSection = new JLabel("Branches Section");
		lblBranchesSection.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBranchesSection.setForeground(new Color(0, 0, 0));
		lblBranchesSection.setBackground(new Color(240, 240, 240));
		lblBranchesSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblBranchesSection.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		lblBranchesSection.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lblBranchesSection);
		
		
		
		
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

		
		JLabel lblViewBranches = new JLabel("View Branches");
		lblViewBranches.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblViewBranches.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBranches.setBounds(610, 500, 130, 100);
		f.getContentPane().add(lblViewBranches);
		
		
		btnViewsbranches = new JButton("");
		btnViewsbranches.setIcon(new ImageIcon("img\\button.png"));
		btnViewsbranches.setBackground(null);
		btnViewsbranches.setOpaque(false);
		btnViewsbranches.setBorder(null);
		btnViewsbranches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.viewbranch();
			}
		});
		btnViewsbranches.setBounds(610, 500, 130, 100);
		f.getContentPane().add(btnViewsbranches);
		

		
		JLabel lblAddBranch = new JLabel("Add Branch");
		lblAddBranch.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblAddBranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBranch.setBounds(320, 350, 130, 100);
		f.getContentPane().add(lblAddBranch);
		
		btnAddbranch = new JButton("");
		btnAddbranch.setIcon(new ImageIcon("img\\button.png"));
		btnAddbranch.setBackground(null);
		btnAddbranch.setOpaque(false);
		btnAddbranch.setBorder(null);
		btnAddbranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.addbranch();
			}
		});
		btnAddbranch.setBounds(320, 350, 130, 100);
		f.getContentPane().add(btnAddbranch);

		

		lblRemoveSubject = new JLabel("Remove Branch");
		lblRemoveSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveSubject.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblRemoveSubject.setBounds(900, 350, 130, 100);
		f.getContentPane().add(lblRemoveSubject);
		
		btnRemovebranch = new JButton("");
		btnRemovebranch.setIcon(new ImageIcon("img\\button.png"));
		btnRemovebranch.setBackground(null);
		btnRemovebranch.setOpaque(false);
		btnRemovebranch.setBorder(null);
		btnRemovebranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.removebranch();
			}
		});
		
		btnRemovebranch.setBounds(900, 350, 130, 100);
		f.getContentPane().add(btnRemovebranch);
		
		
		JLabel lblUpdateBranch = new JLabel("Update Branch");
		lblUpdateBranch.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblUpdateBranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateBranch.setBounds(610, 300, 130, 100);
		f.getContentPane().add(lblUpdateBranch);
		
		btnUpdatebranch = new JButton("");
		btnUpdatebranch.setIcon(new ImageIcon("img\\button.png"));
		btnUpdatebranch.setBackground(null);
		btnUpdatebranch.setOpaque(false);
		btnUpdatebranch.setBorder(null);
		btnUpdatebranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.updatebranch();
			}
		});	
		
		btnUpdatebranch.setBounds(610, 300, 130, 100);
		f.getContentPane().add(btnUpdatebranch);
		
		
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
