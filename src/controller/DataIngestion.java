package controller;

import java.util.ArrayList;

import com.factory.DataSetFactory;
import com.factory.DatabaseConnectionFactory;
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
		
		/* Add assets */
		@SuppressWarnings("unused")
		IngestHistoricalData iassets = new IngestHistoricalData(financials);
		System.out.println("Historical data ingested!");
		
		/* Add Capital Expenditure */
		/* Add Company Data */
		/* Add Current Liabilities */
		/* Add Current Yearly Quarterly Profits */
		/* Add Current Yearly Quarterly Sales */
		/* Add Debt */
		/* Add Dividends */
		/* Add EBIT */
		/* Add Interest */
		/* Add Net Profit */
		/* Add Operating Cash Flow */
		/* Add Previous Year Quarterly Profits */
		/* Add Previous Year Quarterly Sales */
		/* Add Revenues */
		/* Add Share Holders Fund */
		
		
		
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
