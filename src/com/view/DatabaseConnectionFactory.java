package com.view;


import java.sql.SQLException;
public class DatabaseConnectionFactory {
	
	private static DatabaseConnectionFactory instance = null;
	public ConnectToDatabase connection = null;
	
	private DatabaseConnectionFactory() {
		try {
			if(connection == null) {
				connection = new ConnectToDatabase("root","Mysql@52");
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DatabaseConnectionFactory getInstance() {
		if(instance == null) {
			instance = new DatabaseConnectionFactory();
		}
		return instance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
