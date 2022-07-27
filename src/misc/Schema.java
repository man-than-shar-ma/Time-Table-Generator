package misc;

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

public class Schema {
	JFrame f;
	JLabel lbl;
	JLabel label;
	JButton btnback;
	JButton btnchange;
	JLabel lblcurrentschema;
	JLabel lblcurrentschema1;
	JLabel img;
	static Schema o=new Schema();
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
		Misc.main(null);
		f.dispose();
	}
	void retrieve() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select * from schematype");
			while(rs.next()) {
				tfcs.setText(rs.getString(1));
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
	void change() {
		Changeschema.main(null);
		f.dispose();	
	}
	void body() {
		lbl = new JLabel("Current Schema");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Current Schema");
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
		
		lblcurrentschema = new JLabel("Current Schema : ");
		lblcurrentschema.setForeground(new Color(205, 133, 63));
		lblcurrentschema.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblcurrentschema.setBounds(300, 350, 300, 50);
		f.getContentPane().add(lblcurrentschema);
		
		lblcurrentschema1 = new JLabel("Current Schema : ");
		lblcurrentschema1.setForeground(new Color(0, 0, 0));
		lblcurrentschema1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 30));
		lblcurrentschema1.setBounds(303, 353, 300, 50);
		f.getContentPane().add(lblcurrentschema1);
		
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
		tfcs.setEditable(false);
		tfcs.setBounds(750, 350, 300, 40);
		f.getContentPane().add(tfcs);
		tfcs.setColumns(10);
		
		
		JLabel lblchange = new JLabel("Change");
		lblchange.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblchange.setHorizontalAlignment(SwingConstants.CENTER);
		lblchange.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblchange);
		
		btnchange = new JButton("");
		btnchange.setIcon(new ImageIcon("img\\button.png"));
		btnchange.setBackground(null);
		btnchange.setOpaque(false);
		btnchange.setBorder(null);
		btnchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.change();
			}
		});
		btnchange.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnchange);
		
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
