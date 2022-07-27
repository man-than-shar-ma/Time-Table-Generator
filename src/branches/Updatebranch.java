package branches;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import homepage.Welcome;


public class Updatebranch {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JLabel lblbranch;
	JLabel lblbranch1;
	JComboBox<String> cbbranch;
	JButton btnback;
	JButton btnnext;
	JLabel img;
	static String branchname;
	
	static Updatebranch o = new Updatebranch();
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
			Branches.main(null);
			f.dispose();		
		}
		
	void next() {
			branchname=(String)cbbranch.getSelectedItem();
			Update.main(null);
			f.dispose();
		
		}
		
	void branch() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
	
				Statement s=conn.createStatement();
				ResultSet rs=s.executeQuery("Select name from branch");
				
				while(rs.next()) {
					String abc=rs.getString(1);
					cbbranch.addItem(abc);
				}
				
				conn.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	void body() {
			lbl = new JLabel("Update Branch");
			lbl.setForeground(new Color(205, 133, 63));
			lbl.setBackground(new Color(240, 240, 240));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
			lbl.setBounds(0, 100, 1354, 125);
			f.getContentPane().add(lbl);
			
			label = new JLabel("Update Branch");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setForeground(new Color(0, 0, 0));
			label.setBackground(new Color(240, 240, 240));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
			label.setBounds(0, 100, 1354, 125);
			f.getContentPane().add(label);
			
			lblbranch = new JLabel("Branch : ");
			lblbranch.setForeground(new Color(205, 133, 63));
			lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
			lblbranch.setBounds(350, 350, 200, 50);
			f.getContentPane().add(lblbranch);
			
			lblbranch1 = new JLabel("Branch : ");
			lblbranch1.setForeground(new Color(0, 0, 0));
			lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
			lblbranch1.setBounds(353, 353, 150, 50);
			f.getContentPane().add(lblbranch1);
			
			cbbranch = new JComboBox<String>();
			cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
			o.branch();
			
			cbbranch.setBounds(650, 350, 250, 50);
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
			f.getContentPane().add(img);;
		}
}
