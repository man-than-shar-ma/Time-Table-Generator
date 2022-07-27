package subjects;

import java.awt.Color;
import java.awt.Dimension;
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

import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import homepage.Homepage;
import homepage.Welcome;

import javax.swing.JScrollPane;

public class View {

static JFrame f;
JButton btnback;
JScrollPane scrollPane;
static View o=new View();
JTable table;
JLabel img;

public static void main(String[] args) {
	o.frame();
	o.table();
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
	Viewsubject.main(null);
	f.dispose();
}

void table() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
		
		boolean sem1=Viewsubject.chbx1sem.isSelected();
		boolean sem2=Viewsubject.chbx2sem.isSelected();
		boolean sem3=Viewsubject.chbx3sem.isSelected();
		boolean sem4=Viewsubject.chbx4sem.isSelected();
		boolean sem5=Viewsubject.chbx5sem.isSelected();
		boolean sem6=Viewsubject.chbx6sem.isSelected();
		
		int sem01 = 0;
		int sem02 = 0;
		int sem03 = 0;
		int sem04 = 0;
		int sem05 = 0;
		int sem06 = 0;
		
		if(sem1==true) {
		sem01=1;
		}
		if(sem2==true) {
			sem02=2;
			}
		if(sem3==true) {
			sem03=3;
			}
		if(sem4==true) {
			sem04=4;
			}
		if(sem5==true) {
			sem05=5;
			}
		if(sem6==true) {
			sem06=6;
			}
		int bid=0;
		PreparedStatement ps1= conn.prepareStatement("Select id from branch where name = ?");
		ps1.setString(1, Viewsubject.cbbranch.getSelectedItem().toString());
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()) {
			bid = rs1.getInt(1);
		}
		
		PreparedStatement ps=conn.prepareStatement("select id as 'Subject ID',name as 'Subject Name',sname as 'Acronym',th as 'Theory',pr as 'Practical',branch as 'Branch',sem as 'Semester',prcomb as 'Combined_Pr.',prteacher as 'Teachers_Count' from subject where (sem=?||sem=?||sem=?||sem=?||sem=?||sem=?)&&id like ? order by id");
		ps.setInt(1, sem01);
		ps.setInt(2, sem02);
		ps.setInt(3, sem03);
		ps.setInt(4, sem04);
		ps.setInt(5, sem05);
		ps.setInt(6, sem06);
		ps.setString(7, Homepage.sch*100+bid+"%");
		ResultSet rs=ps.executeQuery();
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setMinWidth(400);
		table.getColumnModel().getColumn(1).setMaxWidth(400);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(75);
		table.getColumnModel().getColumn(2).setMaxWidth(75);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(3).setMaxWidth(75);
		
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMaxWidth(75);
		
		table.getColumnModel().getColumn(5).setPreferredWidth(219);
		table.getColumnModel().getColumn(5).setMinWidth(219);
		table.getColumnModel().getColumn(5).setMaxWidth(219);
		
		table.getColumnModel().getColumn(6).setPreferredWidth(75);
		table.getColumnModel().getColumn(6).setMinWidth(75);
		table.getColumnModel().getColumn(6).setMaxWidth(75);
		
		table.getColumnModel().getColumn(7).setPreferredWidth(125);
		table.getColumnModel().getColumn(7).setMinWidth(125);
		table.getColumnModel().getColumn(7).setMaxWidth(125);
		
		table.getColumnModel().getColumn(8).setPreferredWidth(125);
		table.getColumnModel().getColumn(8).setMinWidth(125);
		table.getColumnModel().getColumn(8).setMaxWidth(125);
		
		
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
	
	scrollPane = new JScrollPane();
	scrollPane.setBounds(37, 51, 1269, 545);
	f.getContentPane().add(scrollPane);
	table = new JTable();
	table.setRowHeight(table.getRowHeight()+30);
	table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),table.getRowHeight()));
	table.setEnabled(false);
	table.getTableHeader().setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	table.setFont(new Font("Kristen ITC", Font.PLAIN, 10));
	scrollPane.setViewportView(table);
	
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
