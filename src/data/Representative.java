package data;

import java.util.ArrayList;
import java.util.List;

import output.ReceiptFileUpdater;
import output.ReceiptFileUpdaterTXT;
import output.ReceiptFileUpdaterXML;

public class Representative {
	private String name;
	private String afm;
	private List<Receipt> allReceipts;
	private ReceiptFileUpdater receiptFileUpdater;
	
	public Representative(){
		allReceipts = new ArrayList<Receipt>();
	}
	
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			receiptFileUpdater = new ReceiptFileUpdaterTXT();
		}	
		else{
			receiptFileUpdater = new ReceiptFileUpdaterXML();
		}	
	}
	
	public List<Receipt> getReceipts(){
		return allReceipts;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAfm() {
		return afm;
	}
	
	public void setAfm(String afm) {
		this.afm = afm;
	}
	
	public ReceiptFileUpdater getReceiptFileUpdater() {
		return receiptFileUpdater;
	}

	public double calculateTotalSales(){
		double sumSales = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumSales += allReceipts.get(i).getSales();
		}
		return sumSales;
	}
	
	public int calculateTotalItems(){
		int sumItems = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumItems += allReceipts.get(i).getItems();
		}
		return sumItems;
	}

	public float calculateSkirtsSales() {
	    return calculateSalesByKind("Skirt");
	}

	public float calculateCoatsSales() {
	    return calculateSalesByKind("Coat");
	}

	public float calculateTrouserSales() {
	    return calculateSalesByKind("Trouser");
	}

	public float calculateShirtsSales() {
	    return calculateSalesByKind("Shirt");
	}
	
	public float calculateSalesByKind(String kind) {
	    float sum = 0;
	    for (int i = 0; i < allReceipts.size(); i++) {
	        if (allReceipts.get(i).getKind().equals(kind)) {
	            sum += allReceipts.get(i).getItems();
	        }
	    }
	    return sum;
	}
	
	public double calculateCommission() {
		double commission;
		double totalSales = this.calculateTotalSales();
		double[] salesThresholds = {6000, 10000, 40000};
		double[] rates = {0.1, 0.15, 0.2};
		
		if (totalSales <= salesThresholds[0]) {
	        commission = 0;
	    } else if (totalSales <= salesThresholds[1]) {
	        commission = rates[0]*(totalSales - salesThresholds[0]);
	    } else if (totalSales <= salesThresholds[2]) {
	        commission = rates[0]*salesThresholds[1] + rates[1]*(totalSales - salesThresholds[1]);
	    } else {
	        commission = rates[0] * salesThresholds[1] + rates[1] * 30000 + rates[2] * (totalSales - salesThresholds[2]);
	    }
		return commission;
	}
}
