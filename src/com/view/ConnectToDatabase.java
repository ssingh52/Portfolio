package com.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDatabase {
	private static Connection con = null;
	public static int rowSize;
	public ConnectToDatabase(String username,String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/investmentportfolio",username,password);
			
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());			
		}
	}
	
	public static void executeStatement(String sql_command) {
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql_command);
			rowSize = 0;
			if(rs.next()) {
				rowSize = rowSize + 1;
				//System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String queryCellValue(String sql_command) {
		String cellValue = null;
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql_command);
			
			if(rs.next()) {
				cellValue = rs.getString(1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return cellValue;
	}
	
	public static void updateQuery(String sql_command) {		
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			@SuppressWarnings("unused")
			int rs = stmt.executeUpdate(sql_command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		ConnectToDatabase ctb = null;
		try {
			ctb = new ConnectToDatabase("root","Mysql@52");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ctb != null) {
			
		}
	}
}
