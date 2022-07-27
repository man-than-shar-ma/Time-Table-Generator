package timetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import homepage.Welcome;


public class Viewcurrent {
	JFrame f;
	Connection conn;
	static int page=0;
	static String sems;
	static String branchb="Computer Engineering";
	JLabel lbl;
	JLabel label;
	JScrollPane scrollPane;
	JTable table;
	JButton btnback;
	JLabel img;
	JLabel lbls;
	JLabel lbls1;
	JLabel lblbranch;
	JLabel lblbranch1;
	JLabel lblsemester;
	JLabel lblsemester1;
	JButton btnb1;
	JButton btnn1;
	JButton btnb2;
	JButton btnn2;
	String sss;
	JButton btnExporttoxls;
	int br=0;
	
	String branch[];
	int bcount;
	String sem[]=new String[3];
	int b=0;
	int s=0;
	String data[][][][]=new String[10][3][12][10];
	String colheading[];
	
	static GenCustom arr[][][][]=new GenCustom[10][3][12][10];
	String value="";
	
	JButton btnviewdata;
	
	static Viewcurrent subject[][][]=new Viewcurrent[10][3][17];
	String name;
	int ht;
	int hpr;
	String tnames[]=new String[3];
	
	HashSet<String> tempsubnames=new HashSet<String>();
	
	static Viewcurrent o=new Viewcurrent();
	public static void main(String[] args) {

		for(int bch=0;bch<10;bch++) {
			for(int sm=0;sm<3;sm++) {
				for(int i=0;i<17;i++) {
						subject[bch][sm][i]=new Viewcurrent();
				}
			}
		}
		o.getbranch();
		o.getsem();
		for(int z=0;z<10;z++) {
			for(int k=0;k<3;k++) {
				for(int i=0;i<12;i++) {
					for(int j=0;j<10;j++) {
						arr[z][k][i][j]=new GenCustom();
					}
				}
			}
		}
		o.frame();
		o.table();
		o.td(0, 0);
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
	
	void table() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			
			
			Statement s=conn.createStatement();
			colheading=new String[10];
			ResultSet rs=null;
			if(View.singlebranch.contentEquals("")) {
				rs=s.executeQuery("Select * from time"+View.s1);
			}
			else {
				rs=s.executeQuery("Select * from bt"+br+""+View.s1);
			}
			
			while(rs.next()) {
				for(int c=0;c<10;c++) {
				colheading[c]=rs.getString(c+1);
				}
			}
			
			int ts=0;
			for(int b4=0;b4<bcount;b4++) {
				for(int s4=0;s4<3;s4++) {
					int y=0;
					if(s4==0) {
						ts=View.s1;
					}
					else if(s4==1) {
						ts=View.s2;
					}
					else {
						ts=View.s3;
					}
					ResultSet rs2=null;
					if(View.singlebranch.contentEquals("")) {
						rs2=s.executeQuery("Select * from sem"+b4+""+ts);
					}
					else {
						rs2=s.executeQuery("Select * from b"+br+""+ts);
					}
					while(rs2.next()) {
						for(int x=0;x<10;x++) {
							arr[b4][s4][y][x].value=rs2.getString(x+1);
						}
						y++;
					}
				}
			}
			
			
			for(int y=0;y<bcount;y++) {
				for(int x=0;x<3;x++) {
					data[y][x]=new String[][]{
						{arr[y][x][0][0].value,arr[y][x][0][1].value,arr[y][x][0][2].value,arr[y][x][0][3].value,arr[y][x][0][4].value,arr[y][x][0][5].value,arr[y][x][0][6].value,arr[y][x][0][7].value,arr[y][x][0][8].value,arr[y][x][0][9].value},
						{arr[y][x][1][0].value,arr[y][x][1][1].value,arr[y][x][1][2].value,arr[y][x][1][3].value,arr[y][x][1][4].value,arr[y][x][1][5].value,arr[y][x][1][6].value,arr[y][x][1][7].value,arr[y][x][1][8].value,arr[y][x][1][9].value},
						{arr[y][x][2][0].value,arr[y][x][2][1].value,arr[y][x][2][2].value,arr[y][x][2][3].value,arr[y][x][2][4].value,arr[y][x][2][5].value,arr[y][x][2][6].value,arr[y][x][2][7].value,arr[y][x][2][8].value,arr[y][x][2][9].value},
						{arr[y][x][3][0].value,arr[y][x][3][1].value,arr[y][x][3][2].value,arr[y][x][3][3].value,arr[y][x][3][4].value,arr[y][x][3][5].value,arr[y][x][3][6].value,arr[y][x][3][7].value,arr[y][x][3][8].value,arr[y][x][3][9].value},
						{arr[y][x][4][0].value,arr[y][x][4][1].value,arr[y][x][4][2].value,arr[y][x][4][3].value,arr[y][x][4][4].value,arr[y][x][4][5].value,arr[y][x][4][6].value,arr[y][x][4][7].value,arr[y][x][4][8].value,arr[y][x][4][9].value},
						{arr[y][x][5][0].value,arr[y][x][5][1].value,arr[y][x][5][2].value,arr[y][x][5][3].value,arr[y][x][5][4].value,arr[y][x][5][5].value,arr[y][x][5][6].value,arr[y][x][5][7].value,arr[y][x][5][8].value,arr[y][x][5][9].value},
						{arr[y][x][6][0].value,arr[y][x][6][1].value,arr[y][x][6][2].value,arr[y][x][6][3].value,arr[y][x][6][4].value,arr[y][x][6][5].value,arr[y][x][6][6].value,arr[y][x][6][7].value,arr[y][x][6][8].value,arr[y][x][6][9].value},
						{arr[y][x][7][0].value,arr[y][x][7][1].value,arr[y][x][7][2].value,arr[y][x][7][3].value,arr[y][x][7][4].value,arr[y][x][7][5].value,arr[y][x][7][6].value,arr[y][x][7][7].value,arr[y][x][7][8].value,arr[y][x][7][9].value},
						{arr[y][x][8][0].value,arr[y][x][8][1].value,arr[y][x][8][2].value,arr[y][x][8][3].value,arr[y][x][8][4].value,arr[y][x][8][5].value,arr[y][x][8][6].value,arr[y][x][8][7].value,arr[y][x][8][8].value,arr[y][x][8][9].value},
						{arr[y][x][9][0].value,arr[y][x][9][1].value,arr[y][x][9][2].value,arr[y][x][9][3].value,arr[y][x][9][4].value,arr[y][x][9][5].value,arr[y][x][9][6].value,arr[y][x][9][7].value,arr[y][x][9][8].value,arr[y][x][9][9].value},
						{arr[y][x][10][0].value,arr[y][x][10][1].value,arr[y][x][10][2].value,arr[y][x][10][3].value,arr[y][x][10][4].value,arr[y][x][10][5].value,arr[y][x][10][6].value,arr[y][x][10][7].value,arr[y][x][10][8].value,arr[y][x][10][9].value},
						{arr[y][x][11][0].value,arr[y][x][11][1].value,arr[y][x][11][2].value,arr[y][x][11][3].value,arr[y][x][11][4].value,arr[y][x][11][5].value,arr[y][x][11][6].value,arr[y][x][11][7].value,arr[y][x][11][8].value,arr[y][x][11][9].value}
					};
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	void back() {
		View.main(null);
		f.dispose();
	}
	
	void td(int b, int s) {
		table.setModel(new DefaultTableModel(data[b][s],colheading));
	}
	
	void getbranch() {
		br=0;
		if(View.singlebranch.contentEquals("")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
			
			
			int c=0;
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("Select count(name) from bch");
			while(rs.next()) {
				bcount=rs.getInt(1);
			}
			branch=new String[bcount];
			ResultSet rs2=s.executeQuery("Select name from bch order by name");
			while(rs2.next()) {
				branch[c]=rs2.getString(1);
				c++;
			}
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:"+Welcome.mysql_localhost+"/ttg", Welcome.mysql_username, Welcome.mysql_password);
				
			PreparedStatement ps=conn.prepareStatement("Select id from branch where name=?");
			ps.setString(1, View.singlebranch);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				br=rs.getInt(1);
			}
			bcount=1;
			branch=new String[bcount];
			branch[0]=View.singlebranch;
			conn.close();
			}
			
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	void getsem() {

		if(View.s1==1&&View.s2==3&&View.s3==5) {
			sem[0]="1st";
			sem[1]="3rd";
			sem[2]="5th";
		}
		else {
			sem[0]="2nd";
			sem[1]="4th";
			sem[2]="6th";
		}
		
	}
	
	void viewdata(int b,int s) {
		tempsubnames.clear();
		for(int y=0;y<12;y=y+2) {
			for(int x=0;x<10;x++) {
				if(x!=0&&x!=3&&x!=6) {
					if(arr[b][s][y][x].value.length()<=4) {
						tempsubnames.add(arr[b][s][y][x].value);
					}
					else {
						if(!arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(a)")&&!arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(b)")&&!arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(p)")) {
							tempsubnames.add(arr[b][s][y][x].value);
						}
						else {
							tempsubnames.add(arr[b][s][y][x].value.substring(0, arr[b][s][y][x].value.length()-4));
						}
					}
				}
			}
		}
		
		int subcount=0;
		
		for(int i=0;i<17;i++) {
			if(tempsubnames.isEmpty()) {
				break;
			}
			subject[b][s][i].name=(String) tempsubnames.toArray()[0];
			subject[b][s][i].ht=0;
			subject[b][s][i].hpr=0;
			subject[b][s][i].tnames[0]="";
			subject[b][s][i].tnames[1]="";
			subject[b][s][i].tnames[2]="";
			tempsubnames.remove(subject[b][s][i].name);		
			subcount++;
		}
		
		for(int i=0;i<17;i++) {
			if(i==subcount) {
				break;
			}
			for(int y=0;y<12;y=y+2) {
				for(int x=0;x<10;x++){
					if(x!=0&&x!=3&&x!=6) {
						if(arr[b][s][y][x].value.contentEquals(subject[b][s][i].name)) {
							subject[b][s][i].ht++;
							Boolean f=true;
							for(int c=0;c<3;c++) {
								if(subject[b][s][i].tnames[c].contentEquals(arr[b][s][y+1][x].value)) {
									f=false;
									break;
								}
							}
							if(f) {
								for(int c=0;c<3;c++) {
									if(subject[b][s][i].tnames[c].contentEquals("")) {
										subject[b][s][i].tnames[c]=arr[b][s][y+1][x].value;
										break;
									}
								}
							}
						}
						else if(arr[b][s][y][x].value.length()>4&&arr[b][s][y][x].value.substring(0, arr[b][s][y][x].value.length()-4).contentEquals(subject[b][s][i].name)&&arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals(("(a)"))) {
							subject[b][s][i].hpr=subject[b][s][i].hpr+2;
							Boolean f=true;
							for(int c=0;c<3;c++) {
								if(subject[b][s][i].tnames[c].contentEquals(arr[b][s][y+1][x].value)) {
									f=false;
									break;
								}
							}
							if(f) {
								for(int c=0;c<3;c++) {
									if(subject[b][s][i].tnames[c].contentEquals("")) {
										subject[b][s][i].tnames[c]=arr[b][s][y+1][x].value;
										break;
									}
								}
							}
						}
						else if(arr[b][s][y][x].value.length()>4&&arr[b][s][y][x].value.substring(0, arr[b][s][y][x].value.length()-4).contentEquals(subject[b][s][i].name)&&arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals(("(p)"))){
							subject[b][s][i].hpr++;
							Boolean f=true;
							for(int c=0;c<3;c++) {
								if(subject[b][s][i].tnames[c].contentEquals(arr[b][s][y+1][x].value)) {
									f=false;
									break;
								}
							}
							if(f) {
								for(int c=0;c<3;c++) {
									if(subject[b][s][i].tnames[c].contentEquals("")) {
										subject[b][s][i].tnames[c]=arr[b][s][y+1][x].value;
										break;
									}
								}
							}
						}
						}
					
					}
				}
			}
		
	String text="";
	int i=0;
	
	while(i<subcount) {
		text=text+"Name : "+subject[b][s][i].name+" | "+"Hours : "+subject[b][s][i].ht+" (th)"+" , "+subject[b][s][i].hpr+" (pr)"+" | "+"Teachers : ";
				for(int c=0;c<3;c++) {
					if(subject[b][s][i].tnames[c].contentEquals("")) {
						break;
					}
					else {
						if(c!=0) {
							text=text+" , ";
						}
						text=text+subject[b][s][i].tnames[c];
					}
				}
				text=text+"\n";
		i++;
	}
	
	JOptionPane.showMessageDialog(null, text);
		
	}
	
	void export() {
		
		int i=1;
		while(true) {
			File  file = new File("export\\TTG Exported Data "+i+".xls");
			Scanner sc=null;
			try {
			sc=new Scanner(file);
			sc.close();
			}
			catch (Exception e) {
				// TODO: handle exception
				break;
			}
			i++;
		}
		
		String filename="export\\TTG Exported Data "+i+".xls";
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet("TTG Exported Data");
		
		for(int i1=0;i1<10;i1++) {
			sheet.setColumnWidth(i1, 6000);
		}
		
		CellStyle center1 = wb.createCellStyle();
		center1.setAlignment(HorizontalAlignment.CENTER);
		center1.setVerticalAlignment(VerticalAlignment.CENTER);
		center1.setBorderTop(BorderStyle.THICK);
		center1.setBorderBottom(BorderStyle.THICK);
		center1.setBorderLeft(BorderStyle.THICK);
		center1.setBorderRight(BorderStyle.THICK);
		center1.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center11 = wb.createCellStyle();
		center11.setAlignment(HorizontalAlignment.CENTER);
		center11.setVerticalAlignment(VerticalAlignment.CENTER);
		center11.setBorderBottom(BorderStyle.THICK);
		center11.setBorderLeft(BorderStyle.THICK);
		center11.setBorderRight(BorderStyle.THICK);
		center11.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center11.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center11.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center12 = wb.createCellStyle();
		center12.setAlignment(HorizontalAlignment.CENTER);
		center12.setVerticalAlignment(VerticalAlignment.CENTER);
		center12.setBorderTop(BorderStyle.THICK);
		center12.setBorderLeft(BorderStyle.THICK);
		center12.setBorderRight(BorderStyle.THICK);
		center12.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center12.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center12.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		
		CellStyle center2 = wb.createCellStyle();
		center2.setAlignment(HorizontalAlignment.CENTER);
		center2.setVerticalAlignment(VerticalAlignment.CENTER);
		center2.setBorderTop(BorderStyle.THICK);
		center2.setBorderBottom(BorderStyle.THICK);
		center2.setBorderLeft(BorderStyle.THICK);
		center2.setBorderRight(BorderStyle.THICK);
		center2.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center2.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		
		CellStyle center31 = wb.createCellStyle();
		center31.setAlignment(HorizontalAlignment.CENTER);
		center31.setVerticalAlignment(VerticalAlignment.CENTER);	
		center31.setBorderTop(BorderStyle.THICK);
		center31.setBorderLeft(BorderStyle.THICK);
		center31.setBorderRight(BorderStyle.THICK);
		center31.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center31.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center31.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center311 = wb.createCellStyle();
		center311.setAlignment(HorizontalAlignment.CENTER);
		center311.setVerticalAlignment(VerticalAlignment.CENTER);	
		center311.setBorderTop(BorderStyle.THICK);
		center311.setBorderLeft(BorderStyle.THICK);
		center311.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center311.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center312 = wb.createCellStyle();
		center312.setAlignment(HorizontalAlignment.CENTER);
		center312.setVerticalAlignment(VerticalAlignment.CENTER);	
		center312.setBorderTop(BorderStyle.THICK);
		center312.setBorderRight(BorderStyle.THICK);
		center312.setTopBorderColor(IndexedColors.BLACK.getIndex());
		center312.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center32 = wb.createCellStyle();
		center32.setAlignment(HorizontalAlignment.CENTER);
		center32.setVerticalAlignment(VerticalAlignment.CENTER);	
		center32.setBorderBottom(BorderStyle.THICK);
		center32.setBorderLeft(BorderStyle.THICK);
		center32.setBorderRight(BorderStyle.THICK);
		center32.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center32.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		center32.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center321 = wb.createCellStyle();
		center321.setAlignment(HorizontalAlignment.CENTER);
		center321.setVerticalAlignment(VerticalAlignment.CENTER);	
		center321.setBorderBottom(BorderStyle.THICK);
		center321.setBorderLeft(BorderStyle.THICK);
		center321.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center321.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		
		CellStyle center322 = wb.createCellStyle();
		center322.setAlignment(HorizontalAlignment.CENTER);
		center322.setVerticalAlignment(VerticalAlignment.CENTER);	
		center322.setBorderBottom(BorderStyle.THICK);
		center322.setBorderRight(BorderStyle.THICK);
		center322.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		center322.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		
		org.apache.poi.ss.usermodel.Font bold1=wb.createFont();
		bold1.setBold(true);
		bold1.setFontHeightInPoints((short) 16);
		bold1.setFontName("Times New Roman");
		
		org.apache.poi.ss.usermodel.Font bold2=wb.createFont();
		bold2.setBold(true);
		bold2.setFontHeightInPoints((short) 14);
		bold2.setFontName("Times New Roman");
		
		org.apache.poi.ss.usermodel.Font normal=wb.createFont();
		normal.setFontHeightInPoints((short) 12);
		normal.setFontName("Times New Roman");
		
		center1.setFont(bold1);
		center11.setFont(bold1);
		center12.setFont(bold1);
		center2.setFont(bold2);
		center31.setFont(normal);
		center311.setFont(normal);
		center312.setFont(normal);
		center32.setFont(normal);
		center321.setFont(normal);
		center322.setFont(normal);
		
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		Row row1=sheet.createRow(0);
		for(int i2=0;i2<10;i2++) {
			Cell cell1=row1.createCell(i2);
			cell1.setCellValue("This File is Created using a Project Created by Students of Comp. Engg. Branch of DR. BR Ambedkar Govt Pokytechnic Ambota");
			cell1.setCellStyle(center1);
		}
		int r=4;
		int c=0;
		
		
		for(int b=0;b<bcount;b++) {
			for(int s=0;s<3;s++) {
				c=0;
				sheet.addMergedRegion(new CellRangeAddress(r, r, 0,9));
				Row rowb=sheet.createRow(r);
				for(int rb=0;rb<10;rb++) {
					Cell cellb=rowb.createCell(rb);
					cellb.setCellValue(branch[b]);
					cellb.setCellStyle(center12);
				}
				r++;
				sheet.addMergedRegion(new CellRangeAddress(r, r, 0,9));
				Row rows=sheet.createRow(r);
				for(int rs=0;rs<10;rs++) {
					Cell cells=rows.createCell(rs);
					cells.setCellValue(sem[s]);
					cells.setCellStyle(center11);
				}
				r=r+2;
				
				Row heading=sheet.createRow(r);
				for(int h=0;h<10;h++) {
					Cell cellh=heading.createCell(c);
					cellh.setCellValue(colheading[h]);
					cellh.setCellStyle(center2);
					c++;
				}
				r++;
				sheet.addMergedRegion(new CellRangeAddress(r, r+11, 3, 3));
				sheet.addMergedRegion(new CellRangeAddress(r, r+11, 6, 6));
				for(int d=0;d<6;d++) {
					sheet.addMergedRegion(new CellRangeAddress(r+(d*2), r+((d+1)*2)-1, 0, 0));
				}
				for(int y=0;y<12;y++) {
					c=0;
					Row row=sheet.createRow(r);
					for(int x=0;x<10;x++) {
						Cell cell=row.createCell(c);
						if(y%2==0&&(x==1||x==4||x==7||x==8)&&arr[b][s][y][x].value.length()>4&&arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(p)")) {
							try {
								sheet.addMergedRegion(new CellRangeAddress(r, r, c, c+1));
								sheet.addMergedRegion(new CellRangeAddress(r+1, r+1, c, c+1));
							}
							catch (Exception e) {
								// TODO: handle exception
							}
							cell.setCellValue(arr[b][s][y][x].value.substring(0, arr[b][s][y][c].value.length()-4));
						}
						else {
							cell.setCellValue(arr[b][s][y][x].value);
						}
						if(x==0) {
							cell.setCellStyle(center2);
						}
						else {
							if(y%2==0) {
								if((x==1||x==4||x==7||x==8)&&arr[b][s][y][x].value.length()>4&&arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(a)")){
									cell.setCellStyle(center311);
								}
								else if((x==2||x==5||x==8||x==9)&&arr[b][s][y][x].value.length()>4&&arr[b][s][y][x].value.substring(arr[b][s][y][x].value.length()-3).contentEquals("(b)")){
									cell.setCellStyle(center312);
								}
								else {
								cell.setCellStyle(center31);
								}
							}
							else {
								if((x==1||x==4||x==7||x==8)&&arr[b][s][y-1][x].value.length()>4&&arr[b][s][y-1][x].value.substring(arr[b][s][y-1][x].value.length()-3).contentEquals("(a)")){
									cell.setCellStyle(center321);
								}
								else if((x==2||x==5||x==8||x==9)&&arr[b][s][y-1][x].value.length()>4&&arr[b][s][y-1][x].value.substring(arr[b][s][y-1][x].value.length()-3).contentEquals("(b)")){
									cell.setCellStyle(center322);
								}
								else {
								cell.setCellStyle(center32);
								}
							}
						}
						c++;
					}
					r++;
				}
				r=r+3;
			}
		}
		
		try {
		FileOutputStream fos=new FileOutputStream(filename);
		wb.write(fos);
		fos.close();
		wb.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		JOptionPane.showMessageDialog(null, "File At : "+filename+" created.");
	}
	
	void body() {
		lbl = new JLabel("Current Time Table");
		lbl.setForeground(new Color(205, 133, 63));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 90));
		lbl.setBounds(0, 15, 1354, 125);
		f.getContentPane().add(lbl);
		
		label = new JLabel("Current Time Table");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(new Color(240, 240, 240));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 92));
		label.setBounds(0, 15, 1354, 125);
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
		
		JLabel lblb1 = new JLabel("<---");
		lblb1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb1.setHorizontalAlignment(SwingConstants.CENTER);
		lblb1.setBounds(220, 110, 70, 55);
		f.getContentPane().add(lblb1);
		
		btnb1 = new JButton("");
		btnb1.setIcon(new ImageIcon("img\\button2.png"));
		btnb1.setBackground(null);
		btnb1.setOpaque(false);
		btnb1.setBorder(null);
		btnb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b--;
				if(b<0) {
					b=bcount-1;
				}
				o.td(b, s);
				lblbranch.setText(branch[b]);
				lblbranch1.setText(branch[b]);
			}
		});
		btnb1.setBounds(220, 110, 70, 55);
		f.getContentPane().add(btnb1);
		
		
		lblbranch = new JLabel();
		lblbranch.setText(branch[0]);
		lblbranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch.setForeground(new Color(205, 133, 63));
		lblbranch.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch.setBackground(SystemColor.menu);
		lblbranch.setBounds(290, 120, 760, 35);
		f.getContentPane().add(lblbranch);
		
		lblbranch1 = new JLabel();
		lblbranch1.setText(branch[0]);
		lblbranch1.setHorizontalAlignment(SwingConstants.CENTER);
		lblbranch1.setForeground(new Color(0, 0, 0));
		lblbranch1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblbranch1.setBackground(SystemColor.menu);
		lblbranch1.setBounds(293, 123, 760, 35);
		f.getContentPane().add(lblbranch1);
		
		
		JLabel lbln1 = new JLabel("--->");
		lbln1.setHorizontalAlignment(SwingConstants.CENTER);
		lbln1.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln1.setBounds(1050, 110, 70, 55);
		f.getContentPane().add(lbln1);
		
		
		btnn1 = new JButton("");
		btnn1.setIcon(new ImageIcon("img\\button2.png"));
		btnn1.setBackground(null);
		btnn1.setOpaque(false);
		btnn1.setBorder(null);
		btnn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b++;
				if(b>=bcount) {
					b=0;
				}
				o.td(b, s);
				lblbranch.setText(branch[b]);
				lblbranch1.setText(branch[b]);
			}
		});
		btnn1.setBounds(1050, 110, 70, 55);
		f.getContentPane().add(btnn1);
		
		JLabel lblb2 = new JLabel("<---");
		lblb2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lblb2.setHorizontalAlignment(SwingConstants.CENTER);
		lblb2.setBounds(220, 180, 70, 55);
		f.getContentPane().add(lblb2);
		
		
		btnb2 = new JButton("");
		btnb2.setIcon(new ImageIcon("img\\button2.png"));
		btnb2.setBackground(null);
		btnb2.setOpaque(false);
		btnb2.setBorder(null);
		btnb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s--;
				if(s<0) {
					s=2;
				}
				o.td(b, s);
				lblsemester.setText(sem[s]);
				lblsemester1.setText(sem[s]);
			}
		});
		btnb2.setBounds(220, 180, 70, 55);
		f.getContentPane().add(btnb2);
		
		lblsemester = new JLabel();
		lblsemester.setText(sem[0]);
		lblsemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester.setForeground(new Color(205, 133, 63));
		lblsemester.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester.setBackground(SystemColor.menu);
		lblsemester.setBounds(290, 190, 760, 35);
		f.getContentPane().add(lblsemester);
		
		lblsemester1 = new JLabel();
		lblsemester1.setText(sem[0]);
		lblsemester1.setHorizontalAlignment(SwingConstants.CENTER);
		lblsemester1.setForeground(new Color(0, 0, 0));
		lblsemester1.setFont(new Font("Hobo Std", Font.BOLD | Font.ITALIC, 25));
		lblsemester1.setBackground(SystemColor.menu);
		lblsemester1.setBounds(293, 193, 760, 35);
		f.getContentPane().add(lblsemester1);
		
		JLabel lbln2 = new JLabel("--->");
		lbln2.setHorizontalAlignment(SwingConstants.CENTER);
		lbln2.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		lbln2.setBounds(1050, 180, 70, 55);
		f.getContentPane().add(lbln2);
		
		
		btnn2 = new JButton("");
		btnn2.setIcon(new ImageIcon("img\\button2.png"));
		btnn2.setBackground(null);
		btnn2.setOpaque(false);
		btnn2.setBorder(null);
		btnn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s++;
				if(s>2) {
					s=0;
				}
				o.td(b, s);
				lblsemester.setText(sem[s]);
				lblsemester1.setText(sem[s]);
			}
		});
		btnn2.setBounds(1050, 180, 70, 55);
		f.getContentPane().add(btnn2);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 250, 1269, 341);
		f.getContentPane().add(scrollPane);
		table=new JTable();
		table.setRowHeight(table.getRowHeight()+10);
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),table.getRowHeight()));
		table.setEnabled(false);
		table.getTableHeader().setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		table.setFont(new Font("Kristen ITC", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JLabel lblviewdata = new JLabel("View Data");
		lblviewdata.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblviewdata.setHorizontalAlignment(SwingConstants.CENTER);
		lblviewdata.setBounds(610, 600, 130, 100);
		f.getContentPane().add(lblviewdata);
		
		btnviewdata = new JButton("");
		btnviewdata.setIcon(new ImageIcon("img\\button.png"));
		btnviewdata.setBackground(null);
		btnviewdata.setOpaque(false);
		btnviewdata.setBorder(null);
		btnviewdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.viewdata(b, s);
			}
		});
		
		btnviewdata.setBounds(610, 600, 130, 100);
		f.getContentPane().add(btnviewdata);
		
		JLabel lblExporttoxls = new JLabel("Export To .xls");
		lblExporttoxls.setFont(new Font("Hobo Std", Font.PLAIN, 15));
		lblExporttoxls.setHorizontalAlignment(SwingConstants.CENTER);
		lblExporttoxls.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(lblExporttoxls);
		
		btnExporttoxls = new JButton("");
		btnExporttoxls.setIcon(new ImageIcon("img\\button.png"));
		btnExporttoxls.setBackground(null);
		btnExporttoxls.setOpaque(false);
		btnExporttoxls.setBorder(null);
		btnExporttoxls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Do you Really want to Export the time table in a .xls format (Spreadsheet file)","Export",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					o.export();
					
			}
			}
		});
		btnExporttoxls.setBounds(1130, 600, 130, 100);
		f.getContentPane().add(btnExporttoxls);
		
		
		JLabel img11 = new JLabel("");
		img11.setIcon(new ImageIcon("img\\button2bg.png"));
		img11.setBounds(217, 113, 70, 55);
		f.getContentPane().add(img11);
		
		JLabel img12 = new JLabel("");
		img12.setIcon(new ImageIcon("img\\button2bg.png"));
		img12.setBounds(1053, 113, 70, 55);
		f.getContentPane().add(img12);
		
		JLabel img21 = new JLabel("");
		img21.setIcon(new ImageIcon("img\\button2bg.png"));
		img21.setBounds(217, 183, 70, 55);
		f.getContentPane().add(img21);
		
		JLabel img22 = new JLabel("");
		img22.setIcon(new ImageIcon("img\\button2bg.png"));
		img22.setBounds(1053, 183, 70, 55);
		f.getContentPane().add(img22);
		
		JLabel img1 = new JLabel("");
		img1.setIcon(new ImageIcon("img\\buttonbg.png"));
		img1.setBounds(65, 605, 130, 100);
		f.getContentPane().add(img1);
		
		JLabel img5 = new JLabel("");
		img5.setIcon(new ImageIcon("img\\buttonbg.png"));
		img5.setBounds(1137, 605, 130, 100);
		f.getContentPane().add(img5);
		
		JLabel img6 = new JLabel("");
		img6.setIcon(new ImageIcon("img\\buttonbg.png"));
		img6.setBounds(612, 605, 130, 100);
		f.getContentPane().add(img6);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon("img\\background.jpg"));
		img.setBounds(0, 0, 1354, 739);
		f.getContentPane().add(img);
	}	
}
