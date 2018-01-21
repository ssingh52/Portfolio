package com.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.model.FinancialData;

public class ReadFromFile {	
	
	public FinancialData data = null;	
	public ReadFromFile(String path) throws IOException {
		data = new FinancialData();
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				
				String[] list = line.split(",");
				int size = list.length;				
				
				if(list.length > 0) {
					switch(list[0]) {
						
						case "Company":data.company = list[1];break;
						case "Code":data.code = list[1];break;
						case "cash":data.cash = list[1];break;
						case "mi":data.minorityInterest = list[1];break;
						case "S":data.outstandingNumberOfShares = list[1];break;
						case "debt_q":data.debtQ = list[1];break;
						case "SF_q":data.shareHoldersFundQ = list[1];break;
						case "CL_q":data.currentLiabilitiesQ = list[1];break;
						case "TA_q":data.assetsQ = list[1];break;
						case "NCL_q":data.nonCurrentLiabilitiesQ = list[1];break;
						case "ReutersLink":data.reutersLink = list[1];break;
						case "Sector":data.sector = list[1];break;
						case "Y":
							for(int i = 0;i < size;i++) {
								data.year.add(list[i]);
							}							
							break;
						case "R":
							for(int i = 0;i < size;i++) {
								data.revenues.add(list[i]);
							}							
							break;
						case "NP":
							for(int i = 0;i < size;i++) {
								data.profits.add(list[i]);
							}							
							break;
						case "EBIT":
							for(int i = 0;i < size;i++) {
								data.ebit.add(list[i]);
							}							
							break;
						case "I":
							for(int i = 0;i < size;i++) {
								data.interest.add(list[i]);
							}							
							break;
						case "OCF":
							for(int i = 0;i < size;i++) {
								data.ocf.add(list[i]);
							}							
							break;
						case "capital_ex":
							for(int i = 0;i < size;i++) {
								data.capex.add(list[i]);
							}							
							break;
						case "Debt":
							for(int i = 0;i < size;i++) {
								data.debt.add(list[i]);
							}							
							break;
						case "TA":
							for(int i = 0;i < size;i++) {
								data.assets.add(list[i]);
							}							
							break;
						case "CL":
							for(int i = 0;i < size;i++) {
								data.currentLiabilities.add(list[i]);
							}							
							break;
						case "SF":
							for(int i = 0;i < size;i++) {
								data.shareholdersFund.add(list[i]);
							}							
							break;
						case "D":
							for(int i = 0;i < size;i++) {
								data.dividends.add(list[i]);
							}							
							break;
						case "PY_S":
							for(int i = 0;i < size;i++) {
								data.previousYearQuarterlySales.add(list[i]);
							}							
							break;
						case "CY_S":
							for(int i = 0;i < size;i++) {
								data.currentYearQuarterlySales.add(list[i]);
							}							
							break;
						case "PY_P":
							for(int i = 0;i < size;i++) {
								data.previousYearQuarterlyProfits.add(list[i]);
							}							
							break;
						case "CY_P":
							for(int i = 0;i < size;i++) {
								data.currentYearQuarterlyProfits.add(list[i]);
							}							
							break;
						default:
							String op = "Unknown parameter " + list[0] + " found\n";
							System.out.print(op);
							break;
					}	
				}		
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fr.close();
		
	}
	
	public static void main(String[] args) {
		ReadFromFile rff = null;
		try {
			rff = new ReadFromFile("F:/Personal/JavaApp/Data_Staging_Area/AIAENG.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rff != null) {
			
		}		
	}
}
