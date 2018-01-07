package com.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.model.FinancialData;

import controller.ReadFromFile;

public class DataSetFactory {
	public ArrayList<FinancialData> financialDataSet = null; 
	private static DataSetFactory instance = null;
	private DataSetFactory() {
		if(financialDataSet == null) {
			financialDataSet = new ArrayList<FinancialData>();			
			String path = "F:/Personal/JavaApp/Data_Staging_Area";
			
			File folder = new File(path);
			System.out.println(path);
			File[] listOfFiles = folder.listFiles();
			for(File f:listOfFiles) {
				System.out.println(f.getName());
				String p = path + "/" + f.getName();
				ReadFromFile rff = null;
				try {
					rff = new ReadFromFile(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				financialDataSet.add(rff.data);
			}				
		}
	}
	
	public static DataSetFactory getInstance() {
		if(instance == null) {
			instance = new DataSetFactory();
		}
		return instance;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
