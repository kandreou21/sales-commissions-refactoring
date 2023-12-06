package input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import data.Representative;
import data.Receipt;

public abstract class Input {
	protected Representative representative;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected Receipt receipt;
	protected List<String> receiptData;
	protected String receiptTags [] = {"ReceiptID", "Date", "Kind", "Sales",
			"Items", "Company", "Country", "City","Street", "Number"};
	
	public Input() {
		representative = new Representative();
	}
	
	public void readFile() {
		try {
			initialiseRepresentative();
			readReceipts();
		} catch (Exception e) {
        	JOptionPane.showMessageDialog
			(null,"Προέκυψε κάποιο πρόβλημα κατά το διάβασμα του αρχείου");
		}
	}
	
	public abstract void initialiseRepresentative() throws IOException;
	public abstract void readReceipts() throws Exception;
	
	public void addRepresentative() {
		representative.setName(name);
		representative.setAfm(afm);
	}
	
	public Representative getRepresentative() {
		return representative;
	}
	
	public void addReceipt(){
		representative.getReceipts().add(receipt);
	}
	
	public void setReceipt() {
		receipt = new Receipt();
		receipt.setReceiptID(Integer.parseInt(receiptData.get(0)));
		receipt.setDate(receiptData.get(1));
		receipt.setKind(receiptData.get(2).substring(0, receiptData.get(2).length()-1));
		receipt.setSales(Double.parseDouble(receiptData.get(3)));
		receipt.setItems(Integer.parseInt(receiptData.get(4)));
		receipt.getCompany().setName(receiptData.get(5));
		receipt.getCompany().getCompanyAddress().setCountry(receiptData.get(6));
		receipt.getCompany().getCompanyAddress().setCity(receiptData.get(7));
		receipt.getCompany().getCompanyAddress().setStreet(receiptData.get(8));
		receipt.getCompany().getCompanyAddress().setStreetNumber(Integer.parseInt(receiptData.get(9)));
	}
}
