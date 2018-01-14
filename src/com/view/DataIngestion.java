package com.view;

import java.util.ArrayList;

import com.model.DataSetFactory;
import com.model.FinancialData;

public class DataIngestion {
	ConnectToDatabase databaseConnection = null;
	ArrayList<FinancialData> financials = null;
	DatabaseConnectionFactory dcf = null;
	public DataIngestion() {	
		
		dcf = DatabaseConnectionFactory.getInstance();
		
		DataSetFactory dsf = DataSetFactory.getInstance();
		financials = dsf.financialDataSet;
		
		@SuppressWarnings("unused")
		/* Add company details */
		IngestCompanyData icd = new IngestCompanyData(financials);
		System.out.println("Company current data ingested!");
		
		/* Add historical data */
		@SuppressWarnings("unused")
		IngestHistoricalData iassets = new IngestHistoricalData(financials);
		System.out.println("Historical data ingested!");
		
		/* Add quarterly profits data */
		@SuppressWarnings("unused")
		IngestQuarterlyProfitsData quarterly_profits_data = new IngestQuarterlyProfitsData(financials);
		System.out.println("Quarterly Profits data ingested!");
	
		/* Add quarterly sales data */
		@SuppressWarnings("unused")
		IngestQuarterlySalesData quarterly_sales_data = new IngestQuarterlySalesData(financials);
		System.out.println("Quarterly Sales data ingested!");		
		
		DatabaseConnectionFactory factory = DatabaseConnectionFactory.getInstance();		
		databaseConnection = factory.connection;		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		DataIngestion di = null;
		di = new DataIngestion();		
	}

}
