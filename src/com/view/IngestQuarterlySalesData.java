package com.view;

import java.util.ArrayList;

import com.model.FinancialData;

public class IngestQuarterlySalesData {

	DatabaseConnectionFactory dcf = null;	
	private String table_name = "investmentportfolio.quarterlysales";
	
	IngestQuarterlySalesData(ArrayList<FinancialData> data) {
		dcf = DatabaseConnectionFactory.getInstance();
		for(FinancialData d:data) {
			String c = d.company;			
			boolean flag = companyInTable(c);
			if(flag == false) {
				return;
			} else {
				String command = "SELECT (id) FROM investmentportfolio.company WHERE name = '" + c +"'";				
				String id = ConnectToDatabase.queryCellValue(command);
				command = "SELECT * FROM " + table_name + " WHERE company_id='" + id +"'";
				
				ConnectToDatabase.executeStatement(command);
				
				ArrayList<String> q_sales_present = d.currentYearQuarterlySales;
				ArrayList<String> q_sales_previous = d.previousYearQuarterlySales;
				
				String present_q1 = q_sales_present.get(1);
				String present_q2 = q_sales_present.get(2);
				String present_q3 = q_sales_present.get(3);
				String present_q4 = q_sales_present.get(4);
				
				String previous_q1 = q_sales_previous.get(1);
				String previous_q2 = q_sales_previous.get(2);
				String previous_q3 = q_sales_previous.get(3);
				String previous_q4 = q_sales_previous.get(4);
				
				boolean fl = checkExistenceOfRow(id);
				if(fl == false) {
					command = "INSERT INTO " + table_name + " (q1_thisYear,q2_thisYear,q3_thisYear,q4_thisYear,q1_previousYear,q2_previousYear,q3_previousYear,q4_previousYear,company_id) VALUES (" + present_q1 + "," + present_q2 + "," 
							+ present_q3 + ","
							+ present_q4 + ","
							+ previous_q1 + ","
							+ previous_q2 + ","
							+ previous_q3 + ","
							+ previous_q4 + ","							
							+ id + ")";
					ConnectToDatabase.updateQuery(command);
				} else {
					/* Update q1 present */
					boolean asset_flag = checkValue(id,present_q1,"q1_thisYear");
					if(asset_flag == true) {
						updateValue(present_q1,id,"q1_thisYear");
					}
					
					/* Update q2 present */
					asset_flag = checkValue(id,present_q2,"q2_thisYear");
					if(asset_flag == true) {
						updateValue(present_q2,id,"q2_thisYear");
					}
					
					/* Update q3 present */
					asset_flag = checkValue(id,present_q3,"q3_thisYear");
					if(asset_flag == true) {
						updateValue(present_q3,id,"q3_thisYear");
					}
					
					/* Update q4 present */
					asset_flag = checkValue(id,present_q4,"q4_thisYear");
					if(asset_flag == true) {
						updateValue(present_q4,id,"q4_thisYear");
					}
					
					/* Update q1 previous */
					asset_flag = checkValue(id,previous_q1,"q1_previousYear");
					if(asset_flag == true) {
						updateValue(previous_q1,id,"q1_previousYear");
					}
					
					/* Update q2 previous */
					asset_flag = checkValue(id,previous_q2,"q2_previousYear");
					if(asset_flag == true) {
						updateValue(previous_q2,id,"q2_previousYear");
					}
					
					/* Update q3 previous */
					asset_flag = checkValue(id,previous_q3,"q3_previousYear");
					if(asset_flag == true) {
						updateValue(previous_q3,id,"q3_previousYear");
					}
					
					/* Update q4 previous */
					asset_flag = checkValue(id,previous_q4,"q4_previousYear");
					if(asset_flag == true) {
						updateValue(previous_q4,id,"q4_previousYear");
					}
				}			
			}
		}
	}
	
	private void updateValue(String value, String id,String column) {
		// TODO Auto-generated method stub				
		String command = "UPDATE " + table_name + " ";
		command = command + "SET " + column + "=\"" + value + "\" WHERE company_id = '" + id + "'";			
		ConnectToDatabase.updateQuery(command);		
	}

	private boolean checkValue(String id, String asset,String column) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String command = "SELECT (" + column + ") FROM " + table_name + " WHERE company_id = '" + id + "'";
		ConnectToDatabase.executeStatement(command);
		Float value = Float.parseFloat(ConnectToDatabase.queryCellValue(command));
		float asse = Float.parseFloat(asset);
		if(asse != value) {
			flag = true;
		}	
		return flag;
	}
	
	private boolean checkExistenceOfRow(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String command = "SELECT * FROM " + table_name + " WHERE company_id = '" + id +"'";
		ConnectToDatabase.executeStatement(command);
		if(ConnectToDatabase.rowSize != 0) {
			flag = true;
		}			
		return flag;
	}
	
	boolean companyInTable(String c) {
		boolean flag = false;
		@SuppressWarnings("unused")
		ConnectToDatabase ctd = dcf.connection;
		String command = "SELECT * FROM investmentportfolio.company WHERE name = '" + c +"'";
		ConnectToDatabase.executeStatement(command);
		int size = ConnectToDatabase.rowSize;
		if(size != 0) {
			flag = true;
		}
		return flag;		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
