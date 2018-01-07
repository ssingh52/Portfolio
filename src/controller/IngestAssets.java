package controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.factory.DatabaseConnectionFactory;
import com.model.FinancialData;

public class IngestAssets {
	DatabaseConnectionFactory dcf = null;
	private float assetValue = (float) 0.0;
	private String table_name = "investmentportfolio.assets";
	IngestAssets(ArrayList<FinancialData> data) {
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
				//int rSize = ConnectToDatabase.rowSize;
				
				
				ArrayList<String> yList = d.year;
				ArrayList<String> aList = d.assets;
				HashMap<String,String> year_asset_map = new HashMap<String,String>();
				for (int i = 1; i < yList.size(); i++) {
					String year = yList.get(i);
					year_asset_map.put(year, aList.get(i));
				}
				for (int i = 1; i < yList.size(); i++) {
					boolean fl = checkExistenceOfRow(yList.get(i),id);
					if(fl == false) {
						command = "INSERT INTO " + table_name + " (year,assets,company_id) VALUES (" + yList.get(i) + "," + year_asset_map.get(yList.get(i)) + "," + id + ")";
						ConnectToDatabase.updateQuery(command);
					} else {
						boolean asset_flag = checkAssetValue(id,yList.get(i),aList.get(i));
						if(asset_flag == true) {
							updateAssetValue(aList.get(i),id,yList.get(i));
						}
					}
				}				
			}
		}
	}
	
	private void updateAssetValue(String value, String id,String year) {
		// TODO Auto-generated method stub				
		String command = "UPDATE " + table_name + " ";
		command = command + "SET assets=\"" + value + "\" WHERE year='"+year+"'" + " AND company_id = '" + id + "'";			
		ConnectToDatabase.updateQuery(command);
		
	}

	private boolean checkAssetValue(String id, String year, String asset) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String command = "SELECT (assets) FROM " + table_name + " WHERE year = '"+year+"'" + " AND " + "company_id = '" + id + "'";
		ConnectToDatabase.executeStatement(command);
		assetValue = Float.parseFloat(ConnectToDatabase.queryCellValue(command));
		float asse = Float.parseFloat(asset);
		if(asse != assetValue) {
			flag = true;
		}	
		return flag;
	}

	private boolean checkExistenceOfRow(String year, String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String command = "SELECT * FROM " + table_name + " WHERE year = '"+year+"'" + " AND " + "company_id = '" + id +"'";
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
