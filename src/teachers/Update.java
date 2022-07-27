package teachers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;


public class Update {

	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnback;
	JButton btnsave;
	JLabel lblname;
	JLabel lblname1;
	JLabel lblbranch;
	JLabel lblbranch1;
	JComboBox<String> cbbranch;
	
	JLabel lbl1;
	
	JTextField tfname;
	JLabel lblfav;
	
	JCheckBox chbx1stYear;
	JCheckBox chbx2ndYear;
	JCheckBox chbx3rdYear;
	JLabel lbl1styear;
	JLabel lbl2ndyear;
	JLabel lbl3rdyear;
	
	JCheckBox chbxsem1;
	JCheckBox chbxsem2;
	JCheckBox chbxsem3;
	JCheckBox chbxsem4;
	JCheckBox chbxsem5;
	JCheckBox chbxsem6;
	JLabel lblSem1;
	JLabel lblSem2;
	JLabel lblSem3;
	JLabel lblSem4;
	JLabel lblSem5;
	JLabel lblSem6;
	
	Boolean flag1=false;
	Boolean flag2=false;
	Boolean flag3=false;
	
	
	JLabel img;
	static Update o=new Update();
	int id;
	int maxid;
	int tempid;
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
	void back()
	{
	Updateteacher.main(null);
	f.dispose();
	}
	
	
	void retrieve() {
		String name="",branch="";
		boolean y1=false,y2=false,y3=false;
		boolean s1=false,s2=false,s3=false,s4=false,s5=false,s6=false;
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			PreparedStatement s=conn.prepareStatement("Select name,branch,year1,year2,year3,sem1,sem2,sem3,sem4,sem5,sem6 from teacher where id=? && schema_id=?");
			s.setInt(1, Updateteacher.tid);
			s.setInt(2, Homepage.sch);
			ResultSet rs=s.executeQuery();
			
			
			
			while(rs.next()) {
			
				
				name=rs.getString(1);
				branch=rs.getString(2);
				y1=rs.getBoolean(3);
				y2=rs.getBoolean(4);
				y3=rs.getBoolean(5);
				s1=rs.getBoolean(6);
				s2=rs.getBoolean(7);
				s3=rs.getBoolean(8);
				s4=rs.getBoolean(9);
				s5=rs.getBoolean(10);
				s6=rs.getBoolean(11);
			}
				
				
			tfname.setText(name);
			cbbranch.setSelectedItem(branch);
			chbx1stYear.setSelected(y1);
			chbx2ndYear.setSelected(y2);
			chbx3rdYear.setSelected(y3);
			chbxsem1.setSelected(s1);
			chbxsem2.setSelected(s2);
			chbxsem3.setSelected(s3);
			chbxsem4.setSelected(s4);
			chbxsem5.setSelected(s5);
			chbxsem6.setSelected(s6);
			
			conn.close();
		}
			catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	void retrievebranch() {
		cbbranch.addItem("Common");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select name from branch");
			
			while(rs.next()) {
				String branch = rs.getString(1);
				cbbranch.addItem(branch);
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void save(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			
			String name=tfname.getText();
			String branch=cbbranch.getSelectedItem().toString();
			boolean y1=chbx1stYear.isSelected();
			boolean y2=chbx2ndYear.isSelected();
			boolean y3=chbx3rdYear.isSelected();
			boolean s1=chbxsem1.isSelected();
			boolean s2=chbxsem2.isSelected();
			boolean s3=chbxsem3.isSelected();
			boolean s4=chbxsem4.isSelected();
			boolean s5=chbxsem5.isSelected();
			boolean s6=chbxsem6.isSelected();
			
			
			String tempname1="";
			String tempname;
			String tempname2;
			tempname=name.toLowerCase();
			
			int i=0;
			
			PreparedStatement ps4=conn.prepareStatement("Select name from teacher where branch=? && id != ?");
			ps4.setString(1, branch);
			ps4.setInt(2, Updateteacher.tid);
			ResultSet rs4=ps4.executeQuery();
			while(rs4.next()) {
				  tempname1=rs4.getString(1);
				  tempname2=tempname1.toLowerCase();
				  
				  if(tempname.contentEquals(tempname2)) {
					i++;
					}
			 }
			if(i>=1) {
				JOptionPane.showMessageDialog(null, "Name already exist");
				return;
			}			
			
			
			if(name.contentEquals("")) {
				JOptionPane.showMessageDialog(null, "Name Can't be Empty");
			}
			else {
				
				int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Update this Teacher","Update Teacher",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
				
			PreparedStatement ps2=conn.prepareStatement("update teacher set name=?,branch=?,year1=?,year2=?,year3=?,sem1=?,sem2=?,sem3=?,sem4=?,sem5=?,sem6=? where id=?");
			ps2.setString(1, name);
			ps2.setString(2, branch);
			ps2.setBoolean(3, y1);
			ps2.setBoolean(4, y2);
			ps2.setBoolean(5, y3);
			ps2.setBoolean(6, s1);
			ps2.setBoolean(7, s2);
			ps2.setBoolean(8, s3);
			ps2.setBoolean(9, s4);
			ps2.setBoolean(10, s5);
			ps2.setBoolean(11, s6);
			ps2.setInt(12, Updateteacher.tid);
			ps2.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Teacher Successfully Updated");
			conn.close();
			Updateteacher.main(null);
			f.dispose();
				}
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	void body() {
		lbl = new JLabel("Update Faculty");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Update Faculty");
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
		
		lblname = new JLabel("Faculty Name : ");
		lblname.setForeground(new Color(205, 133, 63));
		lblname.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname.setBounds(200, 300, 400, 40);
		f.getContentPane().add(lblname);
		
		lblname1 = new JLabel("Faculty Name : ");
		lblname1.setForeground(new Color(0, 0, 0));
		lblname1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblname1.setBounds(203, 303, 400, 40);
		f.getContentPane().add(lblname1);
		
		lblbranch = new JLabel("Branch : ");
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch.setBounds(200, 400, 400, 40);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel("Branch : ");
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblbranch1.setBounds(203, 403, 400, 40);
		f.getContentPane().add(lblbranch1);
		
		JLabel lblyear = new JLabel("Year : ");
		lblyear.setForeground(new Color(205, 133, 63));
		lblyear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblyear.setBounds(200, 500, 400, 40);
		f.getContentPane().add(lblyear);
		
		JLabel lblyear1 = new JLabel("Year : ");
		lblyear1.setForeground(new Color(0, 0, 0));
		lblyear1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblyear1.setBounds(203, 503, 400, 40);
		f.getContentPane().add(lblyear1);
		
		tfname = new JTextField();
		tfname.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfname.setBounds(600, 300, 600, 40);
		f.getContentPane().add(tfname);
		tfname.setColumns(10);
		
		cbbranch = new JComboBox<String>();
		cbbranch.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		o.retrievebranch();
		cbbranch.setBounds(600, 400, 300, 40);
		f.getContentPane().add(cbbranch);
		
		chbx1stYear = new JCheckBox("1st Year");
		chbx1stYear.setOpaque(false);
		chbx1stYear.setForeground(new Color(205, 133, 63));
		chbx1stYear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		chbx1stYear.setBounds(600, 500, 200, 50);
		chbx1stYear.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(chbx1stYear.isSelected()) {
					chbxsem1.setEnabled(true);
					chbxsem2.setEnabled(true);
					chbxsem1.setSelected(true);
					chbxsem2.setSelected(true);
					flag1=true;
				}
				else {
					flag1=false;
					chbxsem1.setSelected(false);
					chbxsem2.setSelected(false);
					chbxsem1.setEnabled(false);
					chbxsem2.setEnabled(false);
				}
			}
		});
		f.getContentPane().add(chbx1stYear);
		
		lbl1styear = new JLabel("1st Year");
		lbl1styear.setOpaque(false);
		lbl1styear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbl1styear.setBounds(623, 503, 200, 50);
		f.getContentPane().add(lbl1styear);
		
		chbx2ndYear = new JCheckBox("2nd Year");
		chbx2ndYear.setOpaque(false);
		chbx2ndYear.setForeground(new Color(205, 133, 63));
		chbx2ndYear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		chbx2ndYear.setBounds(800, 500, 200, 50);
		chbx2ndYear.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(chbx2ndYear.isSelected()) {
					chbxsem3.setEnabled(true);
					chbxsem4.setEnabled(true);
					chbxsem3.setSelected(true);
					chbxsem4.setSelected(true);
					flag2=true;
				}
				else {
					flag2=false;
					chbxsem3.setSelected(false);
					chbxsem4.setSelected(false);
					chbxsem3.setEnabled(false);
					chbxsem4.setEnabled(false);
				}
			}
		});
		f.getContentPane().add(chbx2ndYear);
		
		lbl2ndyear = new JLabel("2nd Year");
		lbl2ndyear.setOpaque(false);
		lbl2ndyear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbl2ndyear.setBounds(823, 503, 200, 50);
		f.getContentPane().add(lbl2ndyear);
		
		
		chbx3rdYear = new JCheckBox("3rd Year");
		chbx3rdYear.setOpaque(false);
		chbx3rdYear.setForeground(new Color(205, 133, 63));
		chbx3rdYear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		chbx3rdYear.setBounds(1000, 500, 200, 50);
		chbx3rdYear.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(chbx3rdYear.isSelected()) {
					chbxsem5.setEnabled(true);
					chbxsem6.setEnabled(true);
					chbxsem5.setSelected(true);
					chbxsem6.setSelected(true);
					flag3=true;
				}
				else {
					flag3=false;
					chbxsem5.setSelected(false);
					chbxsem6.setSelected(false);
					chbxsem5.setEnabled(false);
					chbxsem6.setEnabled(false);
				}
			}
		});
		f.getContentPane().add(chbx3rdYear);
		
		lbl3rdyear = new JLabel("3rd Year");
		lbl3rdyear.setOpaque(false);
		lbl3rdyear.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lbl3rdyear.setBounds(1023, 503, 200, 50);
		f.getContentPane().add(lbl3rdyear);
		
		chbxsem1 = new JCheckBox("Sem1");
		chbxsem1.setOpaque(false);
		chbxsem1.setEnabled(false);
		chbxsem1.setForeground(new Color(205, 133, 63));
		chbxsem1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem1.setBounds(600, 550, 90, 50);
		chbxsem1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem1.isSelected()||chbxsem2.isSelected())&&flag1) {
					chbx1stYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem1);
		
		lblSem1 = new JLabel("Sem1");
		lblSem1.setOpaque(false);
		lblSem1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem1.setBounds(623, 553, 90, 50);
		f.getContentPane().add(lblSem1);
		
		chbxsem2 = new JCheckBox("Sem2");
		chbxsem2.setOpaque(false);
		chbxsem2.setEnabled(false);
		chbxsem2.setForeground(new Color(205, 133, 63));
		chbxsem2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem2.setBounds(700, 550, 90, 50);
		chbxsem2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem1.isSelected()||chbxsem2.isSelected())&&flag1) {
					chbx1stYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem2);
		
		lblSem2 = new JLabel("Sem2");
		lblSem2.setOpaque(false);
		lblSem2.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem2.setBounds(723, 553, 90, 50);
		f.getContentPane().add(lblSem2);
		
		chbxsem3 = new JCheckBox("Sem3");
		chbxsem3.setOpaque(false);
		chbxsem3.setEnabled(false);
		chbxsem3.setForeground(new Color(205, 133, 63));
		chbxsem3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem3.setBounds(800, 550, 90, 50);
		chbxsem3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem3.isSelected()||chbxsem4.isSelected())&&flag2) {
					chbx2ndYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem3);
		
		lblSem3 = new JLabel("Sem3");
		lblSem3.setOpaque(false);
		lblSem3.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem3.setBounds(823, 553, 90, 50);
		f.getContentPane().add(lblSem3);
		
		chbxsem4 = new JCheckBox("Sem4");
		chbxsem4.setOpaque(false);
		chbxsem4.setEnabled(false);
		chbxsem4.setForeground(new Color(205, 133, 63));
		chbxsem4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem4.setBounds(900, 550, 90, 50);
		chbxsem4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem3.isSelected()||chbxsem4.isSelected())&&flag2) {
					chbx2ndYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem4);
		
		lblSem4 = new JLabel("Sem4");
		lblSem4.setOpaque(false);
		lblSem4.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem4.setBounds(923, 553, 90, 50);
		f.getContentPane().add(lblSem4);
		
		chbxsem5 = new JCheckBox("Sem5");
		chbxsem5.setOpaque(false);
		chbxsem5.setEnabled(false);
		chbxsem5.setForeground(new Color(205, 133, 63));
		chbxsem5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem5.setBounds(1000, 550, 90, 50);
		chbxsem5.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem5.isSelected()||chbxsem6.isSelected())&&flag3) {
					chbx3rdYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem5);
		
		lblSem5 = new JLabel("Sem5");
		lblSem5.setOpaque(false);
		lblSem5.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem5.setBounds(1023, 553, 90, 50);
		f.getContentPane().add(lblSem5);
		
		chbxsem6 = new JCheckBox("Sem6");
		chbxsem6.setOpaque(false);
		chbxsem6.setEnabled(false);
		chbxsem6.setForeground(new Color(205, 133, 63));
		chbxsem6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		chbxsem6.setBounds(1100, 550, 90, 50);
		chbxsem6.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(!(chbxsem5.isSelected()||chbxsem6.isSelected())&&flag3) {
					chbx3rdYear.setSelected(false);
				}
			}
		});
		f.getContentPane().add(chbxsem6);
		
		lblSem6 = new JLabel("Sem6");
		lblSem6.setOpaque(false);
		lblSem6.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 20));
		lblSem6.setBounds(1123, 553, 90, 50);
		f.getContentPane().add(lblSem6);
		
		
		JLabel lblsave = new JLabel("Update");
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
