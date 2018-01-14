package com.view;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.model.FinancialData;

public class IngestCompanyData {
	DatabaseConnectionFactory dcf = null;
	IngestCompanyData(ArrayList<FinancialData> data) {
		dcf = DatabaseConnectionFactory.getInstance();
		for(FinancialData d:data) {
			String c = d.company;			
			boolean flag = companyInTable(c);
			if(flag == false) {
				String command = "INSERT INTO investmentportfolio.company ";
				command = command + "(name,code,sharesoutstanding,cash,minorityinterest,latest_debt,latest_shareholderfund,latest_currentliability,latest_totalAssets,latest_nonCurrentLiability,date,marketcaptype_id,sector_id)";
				command = command +" VALUES ";
				command = command + "(";
				command = command + "'" + d.company + "'" + ",";
				command = command + "'" + d.code + "'" + ",";
				command = command + Double.parseDouble(d.outstandingNumberOfShares) + ",";
				command = command + Double.parseDouble(d.cash) +",";
				command = command + Double.parseDouble(d.minorityInterest)+",";
				command = command + Double.parseDouble(d.debtQ)+",";
				command = command + Double.parseDouble(d.shareHoldersFundQ)+",";
				command = command + Double.parseDouble(d.currentLiabilitiesQ)+",";
				command = command + Double.parseDouble(d.assetsQ)+",";				
				command = command + Double.parseDouble(d.nonCurrentLiabilitiesQ)+",";
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date = new Date();
				String dt = sdf.format(date);
				command = command + "'" + dt + "'" + ",";
				command = command + null +",";
				command = command + null;
				command = command + ")";				
				
				ConnectToDatabase.updateQuery(command);				
			} else {
								
				/* company name */
				String company_name = d.company;
				updateCell("name",d.code,company_name);
				
				/* Cash */
				String cash = d.cash;
				updateCell("cash",d.code,cash);				
				
				/* outstanding number of shares */
				String outstanding_shares = d.outstandingNumberOfShares;
				updateCell("sharesoutstanding",d.code,outstanding_shares);
				
				/* minority interest */
				String minority_interest = d.minorityInterest;
				updateCell("minorityinterest",d.code,minority_interest);
				
				/* latest debt */
				String latest_debt = d.debtQ;
				updateCell("latest_debt",d.code,latest_debt);
				
				/* latest share holders fund */
				String latest_shareholdersfund = d.shareHoldersFundQ;
				updateCell("latest_shareholderfund",d.code,latest_shareholdersfund);
				
				/* latest current liability */
				String latest_currentLiability = d.currentLiabilitiesQ;
				updateCell("latest_currentliability",d.code,latest_currentLiability);
				
				/* latest total assets */
				String latest_totalAssets = d.assetsQ;
				updateCell("latest_totalAssets",d.code,latest_totalAssets);
				
				/* latest non current liabilities */
				String latest_noncurrentLiabilities = d.nonCurrentLiabilitiesQ;
				updateCell("latest_nonCurrentLiability",d.code,latest_noncurrentLiabilities);
				
				/* update date */
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date = new Date();
				String dt = sdf.format(date);
				String command = "UPDATE investmentportfolio.company ";
				command = command + "SET date=\"" + dt + "\" WHERE code='"+d.code+"'";
				ConnectToDatabase.updateQuery(command);
				
				/* market cap type */
				StockPrice sp = new StockPrice(d.reutersLink);
				String stockPrice = sp.marketPrice;
				float marketCap = Float.parseFloat(stockPrice) * Float.parseFloat(outstanding_shares);
				String marketCapType = null;
				if(marketCap < 5000.0 && marketCap > 1000.0) {
					marketCapType = "SMALL";
				} else if(marketCap > 5000.0 && marketCap < 20000) {
					marketCapType = "MID";
				} else if(marketCap > 20000.0) {
					marketCapType = "LARGE";
				} else 
					marketCapType = "TINY";
								
				String comm = "SELECT id";
				comm = comm +" FROM"
						+ " investmentportfolio.marketcaptype WHERE marketcap = '" + marketCapType + "'";
				String mcID = ConnectToDatabase.queryCellValue(comm);				
				updateCell("marketcaptype_id",d.code,mcID);
				
				/* sector */
				comm = "SELECT id";
				comm = comm +" FROM"
						+ " investmentportfolio.sector WHERE sector = '" + d.sector + "'";
				String scID = ConnectToDatabase.queryCellValue(comm);
				updateCell("sector_id",d.code,scID);	
				
			}
		}
	}

	private void updateCell(String columnName,String code,String referenceValue) {
		String command = "SELECT " + columnName;
		command = command +" FROM"
				+ " investmentportfolio.company WHERE code = '" + code+"'";
		String content = ConnectToDatabase.queryCellValue(command);
		if(content != null) {
			if(!content.equals(referenceValue)) {
				command = "UPDATE investmentportfolio.company ";
				command = command + "SET " + columnName + "=";					
				command = command + referenceValue;					
				command = command + " WHERE code='" +code+"'"; 
				//System.out.println(command);
				ConnectToDatabase.updateQuery(command);
			}
		} else {
			command = "UPDATE investmentportfolio.company ";
			command = command + "SET " + columnName + "=";					
			command = command + referenceValue;					
			command = command + " WHERE code='" +code+"'"; 
			//System.out.println(command);
			ConnectToDatabase.updateQuery(command);
		}
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
}
