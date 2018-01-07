package controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.factory.DatabaseConnectionFactory;
import com.model.FinancialData;

public class IngestHistoricalData {
	DatabaseConnectionFactory dcf = null;
	
	private String table_name = "investmentportfolio.historicaldata";
	IngestHistoricalData(ArrayList<FinancialData> data) {
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
								
				ArrayList<String> yList = d.year;
				ArrayList<String> aList = d.assets;
				ArrayList<String> debtList = d.debt;
				ArrayList<String> ocfList = d.ocf;
				ArrayList<String> clList = d.currentLiabilities;
				ArrayList<String> npList = d.profits;
				ArrayList<String> diviList = d.dividends;
				ArrayList<String> ebitList = d.ebit;
				ArrayList<String> sfList = d.shareholdersFund;
				ArrayList<String> revList = d.revenues;
				ArrayList<String> capexList = d.capex;
				ArrayList<String> interestList = d.interest;
				HashMap<String,String> year_asset_map = new HashMap<String,String>();
				HashMap<String,String> year_debt_map = new HashMap<String,String>();
				HashMap<String,String> year_ocf_map = new HashMap<String,String>();
				HashMap<String,String> year_cl_map = new HashMap<String,String>();
				HashMap<String,String> year_np_map = new HashMap<String,String>();
				HashMap<String,String> year_divi_map = new HashMap<String,String>();
				HashMap<String,String> year_ebit_map = new HashMap<String,String>();
				HashMap<String,String> year_sf_map = new HashMap<String,String>();
				HashMap<String,String> year_rev_map = new HashMap<String,String>();
				HashMap<String,String> year_capex_map = new HashMap<String,String>();
				HashMap<String,String> year_interest_map = new HashMap<String,String>();
				for (int i = 1; i < yList.size(); i++) {
					String year = yList.get(i);
					year_asset_map.put(year, aList.get(i));
					year_debt_map.put(year, debtList.get(i));
					year_ocf_map.put(year, ocfList.get(i));
					year_cl_map.put(year, clList.get(i));
					year_np_map.put(year, npList.get(i));
					year_divi_map.put(year, diviList.get(i));
					year_ebit_map.put(year, ebitList.get(i));
					year_sf_map.put(year, sfList.get(i));
					year_rev_map.put(year, revList.get(i));
					year_capex_map.put(year, capexList.get(i));
					year_interest_map.put(year, interestList.get(i));
				}
				for (int i = 1; i < yList.size(); i++) {
					boolean fl = checkExistenceOfRow(yList.get(i),id);
					if(fl == false) {
						command = "INSERT INTO " + table_name + " (year,assets,Debt,OCF,CurrentLiabilities,NetProfit,Dividend,EBIT,SF,Revenues,Capex,Interest,company_id) VALUES (" + yList.get(i) + "," + year_asset_map.get(yList.get(i)) + "," 
								+ year_debt_map.get(yList.get(i)) + ","
								+ year_ocf_map.get(yList.get(i)) + ","
								+ year_cl_map.get(yList.get(i)) + ","
								+ year_np_map.get(yList.get(i)) + ","
								+ year_divi_map.get(yList.get(i)) + ","
								+ year_ebit_map.get(yList.get(i)) + ","
								+ year_sf_map.get(yList.get(i)) + ","
								+ year_rev_map.get(yList.get(i)) + ","
								+ year_capex_map.get(yList.get(i)) + ","
								+ year_interest_map.get(yList.get(i)) + ","
								+ id + ")";
						ConnectToDatabase.updateQuery(command);
					} else {
						/* Update Assets */
						boolean asset_flag = checkValue(id,yList.get(i),aList.get(i),"assets");
						if(asset_flag == true) {
							updateValue(aList.get(i),id,yList.get(i),"assets");
						}
						
						/* Update Debt */
						boolean debt_flag = checkValue(id,yList.get(i),debtList.get(i),"Debt");
						if(debt_flag == true) {
							updateValue(debtList.get(i),id,yList.get(i),"Debt");
						}
						
						/* Update OCF */
						boolean ocf_flag = checkValue(id,yList.get(i),ocfList.get(i),"OCF");
						if(ocf_flag == true) {
							updateValue(ocfList.get(i),id,yList.get(i),"OCF");
						}
						
						/* Update Current Liabilities */
						boolean cl_flag = checkValue(id,yList.get(i),clList.get(i),"CurrentLiabilities");
						if(cl_flag == true) {
							updateValue(clList.get(i),id,yList.get(i),"CurrentLiabilities");
						}
						
						/* Update Net Profit */
						boolean np_flag = checkValue(id,yList.get(i),npList.get(i),"NetProfit");
						if(np_flag == true) {
							updateValue(npList.get(i),id,yList.get(i),"NetProfit");
						}
						
						/* Update Dividend */
						boolean divi_flag = checkValue(id,yList.get(i),diviList.get(i),"Dividend");
						if(divi_flag == true) {
							updateValue(diviList.get(i),id,yList.get(i),"Dividend");
						}
						
						/* Update EBIT */
						boolean ebit_flag = checkValue(id,yList.get(i),ebitList.get(i),"EBIT");
						if(ebit_flag == true) {
							updateValue(ebitList.get(i),id,yList.get(i),"EBIT");
						}
						
						/* Update SF */
						boolean sf_flag = checkValue(id,yList.get(i),sfList.get(i),"SF");
						if(sf_flag == true) {
							updateValue(sfList.get(i),id,yList.get(i),"SF");
						}
						
						/* Update revenues */
						boolean rev_flag = checkValue(id,yList.get(i),revList.get(i),"Revenues");
						if(rev_flag == true) {
							updateValue(revList.get(i),id,yList.get(i),"Revenues");
						}
						
						/* Update capex */
						boolean capex_flag = checkValue(id,yList.get(i),capexList.get(i),"Capex");
						if(capex_flag == true) {
							updateValue(capexList.get(i),id,yList.get(i),"Capex");
						}
						
						/* Update interest */
						boolean interest_flag = checkValue(id,yList.get(i),interestList.get(i),"Interest");
						if(interest_flag == true) {
							updateValue(interestList.get(i),id,yList.get(i),"Interest");
						}
					}
				}				
			}
		}
	}
	
	private void updateValue(String value, String id,String year,String column) {
		// TODO Auto-generated method stub				
		String command = "UPDATE " + table_name + " ";
		command = command + "SET " + column + "=\"" + value + "\" WHERE year='"+year+"'" + " AND company_id = '" + id + "'";			
		ConnectToDatabase.updateQuery(command);		
	}

	private boolean checkValue(String id, String year, String asset,String column) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String command = "SELECT (" + column + ") FROM " + table_name + " WHERE year = '"+year+"'" + " AND " + "company_id = '" + id + "'";
		ConnectToDatabase.executeStatement(command);
		Float value = Float.parseFloat(ConnectToDatabase.queryCellValue(command));
		float asse = Float.parseFloat(asset);
		if(asse != value) {
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
