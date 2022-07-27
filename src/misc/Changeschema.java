package misc;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import homepage.Welcome;

public class Changeschema {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnback;
	JButton btnsave;
	JLabel lblnewschema;
	JLabel lblnewschema1;
	JLabel img;
	int sch=0;
	int no;
	static Changeschema o=new Changeschema();
	private JTextField tfcs;
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
		Schema.main(null);
		f.dispose();
	}
	void teachers() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			int i=0;
			PreparedStatement pss=conn.prepareStatement("Select id from teacher where schema_id=?");
			pss.setInt(1, no);
			ResultSet rss=pss.executeQuery();
			while(rss.next()) {
				i++;
			}
			if(i==0) {
			PreparedStatement ps=conn.prepareStatement("Select id,name,branch from teacher where schema_id = ?");
			ps.setInt(1, sch);
			ResultSet rs=ps.executeQuery();
			int count=0;
			
			int result = JOptionPane.showConfirmDialog(null, "Do you want to copy Teachers info to this new Schema","Update Teacher",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
			while(rs.next()) {
				int tempid=rs.getInt(1);
				String tempname=rs.getString(2);
				String tempbranch=rs.getString(3);
				PreparedStatement ps1=conn.prepareStatement("Insert into teacher (id,name,schema_id,branch) values(?,?,?,?)");
				ps1.setInt(1, tempid);
				ps1.setString(2, tempname);
				ps1.setInt(3, no);
				ps1.setString(4, tempbranch);
				ps1.executeUpdate();
				count++;
			}
			
			if(count!=0) {
				JOptionPane.showMessageDialog(null, "Please Update the Teachers Profile.");
				}
			}
			}
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			String no1=tfcs.getText();
			no=Integer.parseInt(no1);
			
			int schemano=0;
			for(int i=0;i<20;i++) {
				schemano=0;
				schemano=(5*i)+2;
				if(no==schemano) {
					break;
				}
			}
			
			if(no!=schemano) {
				JOptionPane.showMessageDialog(null, "This Schema Can't Exist");
				return;
			}
			
			int result = JOptionPane.showConfirmDialog(null, "Are You Sure","Schema",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from schematype");
			while(rs.next()) {
				sch=rs.getInt(1);
			}
			
			if(sch==0) {
				PreparedStatement ps=conn.prepareStatement("insert into schematype values(?)");
				ps.setInt(1, no);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Schema Successfully Added");
				conn.close();
				o.teachers();
				Schema.main(null);
				f.dispose();
			}
			else {
				PreparedStatement ps=conn.prepareStatement("update schematype set schema_no=?");
				ps.setInt(1, no);
				ps.executeUpdate();
				conn.close();
				JOptionPane.showMessageDialog(null, "Schema Successfully Updated");
				o.teachers();
				Schema.main(null);
				f.dispose();
			}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please Enter a Valid Schema");
		}
		
	}
	void body() {
		lbl = new JLabel("Schema");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Schema");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(label);;
	
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
		
		lblnewschema = new JLabel("New Schema : ");
		lblnewschema.setForeground(new Color(205, 133, 63));
		lblnewschema.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblnewschema.setBounds(300, 350, 300, 50);
		f.getContentPane().add(lblnewschema);
		
		lblnewschema1 = new JLabel("New Schema : ");
		lblnewschema1.setForeground(new Color(0, 0, 0));
		lblnewschema1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblnewschema1.setBounds(303, 353, 300, 50);
		f.getContentPane().add(lblnewschema1);
		
		JLabel lblN = new JLabel("N - ");
		lblN.setForeground(new Color(205, 133, 63));
		lblN.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblN.setBounds(650, 350, 100, 50);
		f.getContentPane().add(lblN);
		
		JLabel lblN1 = new JLabel("N -");
		lblN1.setForeground(Color.BLACK);
		lblN1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblN1.setBounds(653, 353, 100, 50);
		f.getContentPane().add(lblN1);
		
		tfcs = new JTextField();
		tfcs.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		tfcs.setBounds(750, 350, 300, 40);
		f.getContentPane().add(tfcs);
		tfcs.setColumns(10);
		
		
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

