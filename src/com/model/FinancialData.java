package com.model;

import java.util.ArrayList;
/**
 * Model class representing financial information about a stock
 * @author sudheendra
 * @since 31/12/2017
 */
public class FinancialData {
	public String company;
	public String code;
	public String outstandingNumberOfShares;
	public String cash;
	public String minorityInterest;
	public String debtQ;
	public String shareHoldersFundQ;
	public String currentLiabilitiesQ;
	public String nonCurrentLiabilitiesQ;
	public String assetsQ;
	public String reutersLink;
	public String sector;
	public ArrayList<String> year = new ArrayList<String>();
	public ArrayList<String> revenues = new ArrayList<String>();
	public ArrayList<String> profits = new ArrayList<String>();
	public ArrayList<String> ebit = new ArrayList<String>();
	public ArrayList<String> interest = new ArrayList<String>();
	public ArrayList<String> ocf = new ArrayList<String>();
	public ArrayList<String> capex = new ArrayList<String>();
	public ArrayList<String> debt = new ArrayList<String>();
	public ArrayList<String> assets = new ArrayList<String>();
	public ArrayList<String> currentLiabilities = new ArrayList<String>();
	public ArrayList<String> shareholdersFund = new ArrayList<String>();
	public ArrayList<String> dividends = new ArrayList<String>();
	public ArrayList<String> previousYearQuarterlySales = new ArrayList<String>();
	public ArrayList<String> previousYearQuarterlyProfits = new ArrayList<String>();
	public ArrayList<String> currentYearQuarterlySales = new ArrayList<String>();
	public ArrayList<String> currentYearQuarterlyProfits = new ArrayList<String>();
	public FinancialData() {
		
	}
}
