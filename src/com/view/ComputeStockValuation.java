package com.view;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputeStockValuation {

	public ComputeStockValuation() {
		String comm = "SELECT * FROM investmentportfolio.company";
		DatabaseConnectionFactory.getInstance();
		ConnectToDatabase.executeStatement(comm);
		@SuppressWarnings("unused")
		int number_of_rows = ConnectToDatabase.rowSize;
		ResultSet rs = ConnectToDatabase.result_set;
		try {
			if(rs.next()) {
				String code = rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")	
		
		ComputeStockValuation di = new ComputeStockValuation();		
	}

}
