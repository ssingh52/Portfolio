package controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StockPrice {
	public String marketPrice;
	public StockPrice(String path) {
		Document doc;
		try {			
			doc = Jsoup.connect(path).get();
			Elements contents = doc.select("div.sectionQuoteDetail");	
			Elements cons = contents.select("span");	             
	        Element elem = cons.get(1);	        
	        marketPrice = elem.text().replace(",","");	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		StockPrice sp = new StockPrice("https://in.reuters.com/finance/stocks/overview/AIAE.NS");
		System.out.println(sp.marketPrice);		
	}
}
