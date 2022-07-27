package homepage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Welcome{
	static JFrame f;
	String username;
	String password;
	JLabel lbl;
	JLabel label;
	JLabel lblAdminLogin;
	JButton btnAdminLogin;
	JLabel img6;
	JLabel img;
	boolean login=false;
	
	public static String mysql_localhost="";
	public static String mysql_username="";
	public static String mysql_password="";
	
	static Welcome o=new Welcome();
	public static void main(String[] args){
		o.mysql_connection();
		o.loginout();
	}

	void loginout()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+mysql_localhost+"/ttg", mysql_username, mysql_password);
			
			String s="Select * from loginout";
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(s);
			while(rs.next()) {
				login=rs.getBoolean(1);
			}
			
			if(login==true) {
				Homepage.main(null);
			}
			else {
				o.frame();
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
	
	void mysql_connection() {
		Connection conn=null;
		
		File  file = new File("Time_Table_Generator_MySQL_Credentials.txt");
		Scanner sc=null;
		try {
		sc=new Scanner(file);
		}
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Time_Table_Generator_MySQL_Credentials.txt not found");
			System.exit(0);
		}
		
		int i=0;
		while(sc.hasNextLine()&&i<3) {
			if(i==0) {
				mysql_localhost=sc.nextLine().substring(10);
			}
			if(i==1) {
				mysql_username=sc.nextLine().substring(9);
			}
			if(i==2) {
				mysql_password=sc.nextLine().substring(9);
			}
			i++;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:"+mysql_localhost, mysql_username, mysql_password);
		}
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Invalid Mysql Credentials");
			f.dispose();
		}
		
		try {
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("show databases");
			Boolean flag=false;
			while(rs.next()) {
				if(rs.getString(1).contentEquals("ttg")) {
					flag=true;
					break;
				}
			}
			if(flag==false) {
				Statement s1=conn.createStatement();
				s1.executeUpdate("create database ttg");
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
				Statement s2=conn.createStatement();
				s2.execute("use ttg");
				s2.execute("create table account(username varchar(25),password varchar(25))");
				s2.execute("create table bch(name varchar(50))");
				s2.execute("create table branch(id int(11),name varchar(100),sname varchar(30))");
				s2.execute("create table extrasubjects(sem int(11),branch varchar(50),sca_time int(11),lib_time int(11),sca_name varchar(50),lib_name varchar(50))");
				s2.execute("create table loginout(login tinyint(1))");
				s2.execute("create table schematype(schema_no int(11))");
				s2.execute("create table subject(id int(11),name varchar(100),sname varchar(30),th tinyint(1),pr tinyint(1),branch varchar(50),sem int(11),prcomb tinyint(1),prteacher int(11),ht int(11),hpr int(11),tname1 varchar(50),tname2 varchar(50),tname3 varchar(50))");
				s2.execute("create table teacher(id int(11),name varchar(50),schema_id int(11),branch varchar(50),year1 tinyint(1),year2 tinyint(1),year3 tinyint(1),sem1 tinyint(1),sem2 tinyint(1),sem3 tinyint(1),sem4 tinyint(1),sem5 tinyint(1),sem6 tinyint(1))");
				s2.execute("create table time1(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table time2(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table timing(la1 int(11),lz1 int(11),la2 int(11),lz2 int(11),ta int(11),tz int(11),la3 int(11),lz3 int(11),la4 int(11),lz4 int(11),ra int(11),rz int(11),la5 int(11),lz5 int(11),la6 int(11),lz6 int(11),la7 int(11),lz7 int(11))");
				s2.execute("create table sem01(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem02(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem03(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem04(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem05(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem06(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem11(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem12(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem13(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem14(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem15(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem16(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem21(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem22(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem23(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem24(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem25(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem26(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem31(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem32(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem33(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem34(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem35(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem36(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem41(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem42(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem43(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem44(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem45(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem46(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem51(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem52(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem53(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem54(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem55(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem56(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem61(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem62(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem63(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem64(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem65(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem66(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem71(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem72(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem73(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem74(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem75(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem76(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem81(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem82(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem83(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem84(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem85(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem86(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem91(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem92(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem93(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem94(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem95(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table sem96(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt11(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt12(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b11(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b12(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b13(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b14(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b15(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b16(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt21(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt22(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b21(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b22(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b23(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b24(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b25(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b26(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt31(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt32(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b31(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b32(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b33(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b34(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b35(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b36(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt41(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt42(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b41(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b42(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b43(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b44(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b45(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b46(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt51(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt52(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b51(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b52(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b53(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b54(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b55(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b56(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt61(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt62(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b61(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b62(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b63(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b64(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b65(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b66(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt71(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt72(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b71(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b72(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b73(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b74(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b75(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b76(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt81(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt82(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b81(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b82(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b83(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b84(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b85(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b86(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt91(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt92(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b91(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b92(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b93(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b94(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b95(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b96(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt101(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table bt102(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b101(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b102(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b103(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b104(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b105(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				s2.execute("create table b106(c1 varchar(50),c2 varchar(50),c3 varchar(50),c4 varchar(50),c5 varchar(50),c6 varchar(50),c7 varchar(50),c8 varchar(50),c9 varchar(50),c10 varchar(50))");
				
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
	void login()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:"+mysql_localhost+"/ttg", mysql_username, mysql_password);
			
			String str="select * from account";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(str);
			
			while(rs.next()) {
				username=rs.getString(1);
				password=rs.getString(2);
				}
			
			if(username==null&&password==null) {
				JOptionPane.showMessageDialog(null, "New user,Signup first");
				Signup.main(null);
				f.setEnabled(false);
			}
			else {
				
				Signin.main(null);
				f.setEnabled(false);
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
	void body(){
		
		lbl = new JLabel("Welcome");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Welcome");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 100, 1354, 125);
		f.getContentPane().add(label);
		
		JLabel lbla11 = new JLabel("\"An inch of");
		lbla11.setForeground(new Color(233, 150, 122));
		lbla11.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla11.setBounds(100, 300, 300, 50);
		f.getContentPane().add(lbla11);
		
		JLabel lbla12 = new JLabel("\"An inch of");
		lbla12.setForeground(new Color(0, 0, 0));
		lbla12.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla12.setBounds(102, 302, 300, 50);
		f.getContentPane().add(lbla12);
		
		JLabel lbla21 = new JLabel("time");
		lbla21.setForeground(new Color(192, 192, 192));
		lbla21.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla21.setBounds(310, 300, 300, 50);
		f.getContentPane().add(lbla21);
		
		JLabel lbla22 = new JLabel("time");
		lbla22.setForeground(new Color(0, 0, 0));
		lbla22.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla22.setBounds(312, 302, 300, 50);
		f.getContentPane().add(lbla22);
		
		JLabel lbla31 = new JLabel("is an inch of");
		lbla31.setForeground(new Color(233, 150, 122));
		lbla31.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla31.setBounds(390, 300, 300, 50);
		f.getContentPane().add(lbla31);
		
		JLabel lbla32 = new JLabel("is an inch of");
		lbla32.setForeground(new Color(0, 0, 0));
		lbla32.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla32.setBounds(392, 302, 300, 50);
		f.getContentPane().add(lbla32);
		
		JLabel lbla41 = new JLabel("gold");
		lbla41.setForeground(new Color(240, 230, 140));
		lbla41.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla41.setBounds(610, 300, 300, 50);
		f.getContentPane().add(lbla41);
		
		JLabel lbla42 = new JLabel("gold");
		lbla42.setForeground(new Color(0, 0, 0));
		lbla42.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla42.setBounds(612, 302, 300, 50);
		f.getContentPane().add(lbla42);
		
		JLabel lbla51 = new JLabel(",");
		lbla51.setForeground(new Color(233, 150, 122));
		lbla51.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla51.setBounds(690, 300, 300, 50);
		f.getContentPane().add(lbla51);
		
		JLabel lbla52 = new JLabel(",");
		lbla52.setForeground(new Color(0, 0, 0));
		lbla52.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla52.setBounds(692, 302, 300, 50);
		f.getContentPane().add(lbla52);
		
		JLabel lbla61 = new JLabel("but you can't buy that inch of ");
		lbla61.setForeground(new Color(233, 150, 122));
		lbla61.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla61.setBounds(310, 350, 500, 50);
		f.getContentPane().add(lbla61);
		
		JLabel lbla62 = new JLabel("but you can't buy that inch of ");
		lbla62.setForeground(new Color(0, 0, 0));
		lbla62.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla62.setBounds(312, 352, 500, 50);
		f.getContentPane().add(lbla62);
		
		JLabel lbla71 = new JLabel("time");
		lbla71.setForeground(new Color(192, 192, 192));
		lbla71.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla71.setBounds(810, 350, 300, 50);
		f.getContentPane().add(lbla71);
		
		JLabel lbla72 = new JLabel("time");
		lbla72.setForeground(Color.BLACK);
		lbla72.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla72.setBounds(812, 352, 300, 50);
		f.getContentPane().add(lbla72);
		
		JLabel lbla81 = new JLabel("with an inch of");
		lbla81.setForeground(new Color(233, 150, 122));
		lbla81.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla81.setBounds(890, 350, 300, 50);
		f.getContentPane().add(lbla81);
		
		JLabel lbla82 = new JLabel("with an inch of");
		lbla82.setForeground(Color.BLACK);
		lbla82.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla82.setBounds(892, 352, 300, 50);
		f.getContentPane().add(lbla82);
		
		JLabel lbla91 = new JLabel("gold");
		lbla91.setForeground(new Color(240, 230, 140));
		lbla91.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla91.setBounds(1150, 350, 100, 50);
		f.getContentPane().add(lbla91);
		
		JLabel lbla92 = new JLabel("gold");
		lbla92.setForeground(Color.BLACK);
		lbla92.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		lbla92.setBounds(1152, 352, 100, 50);
		f.getContentPane().add(lbla92);
		
		JLabel label_1 = new JLabel(".\"");
		label_1.setForeground(new Color(233, 150, 122));
		label_1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		label_1.setBounds(1230, 350, 100, 50);
		f.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel(".\"");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
		label_2.setBounds(1232, 352, 300, 50);
		f.getContentPane().add(label_2);
		
		
		lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setBounds(595, 500, 130, 100);
		f.getContentPane().add(lblAdminLogin);
		
		
		btnAdminLogin = new JButton("");
		btnAdminLogin.setIcon(new ImageIcon("img\\button.png"));
		btnAdminLogin.setBackground(null);
		btnAdminLogin.setOpaque(false);
		btnAdminLogin.setBorder(null);
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.login();
			}
		});
		
		btnAdminLogin.setBounds(595, 500, 130, 100);
		f.getContentPane().add(btnAdminLogin);
		
		
		img6 = new JLabel("");
		img6.setIcon(new ImageIcon("img\\buttonbg.png"));
		img6.setBounds(597, 505, 130, 100);
		f.getContentPane().add(img6);
		

		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}
}