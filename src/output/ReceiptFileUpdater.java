package output;

import java.io.File;
import java.io.IOException;

import data.Address;
import data.Receipt;

public abstract class ReceiptFileUpdater {
	protected File fileToAppend;
	protected Receipt receipt;
	protected String[] receiptFields = {"Receipt ID", "Date", "Kind", "Sales", 
			"Items", "Company", "Country", "City", "Street", "Number"};
		
	public void appendFile() {
		try {
			initialiseWriter();
			for (int i = 0; i < receiptFields.length; i++) {
				writeField(receiptFields[i]);
			}
			closeWriter();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void initialiseWriter() throws IOException;
	protected abstract void writeField(String field) throws IOException;
	protected abstract void closeWriter() throws IOException;
	
	protected String getField(String field) {
		Address address = receipt.getCompany().getCompanyAddress();
		switch(field) {
			case "Receipt ID":
				return String.valueOf(receipt.getReceiptID());
			case "Date":
				return receipt.getDate();
			case "Kind":
				return receipt.getKind();
			case "Sales":
				return String.valueOf(receipt.getSales());
			case "Items":
				return String.valueOf(receipt.getItems());
			case "Company":
				return receipt.getCompany().getName();
			case "Country":
				return address.getCountry();
			case "City":
			    return address.getCity();
			case "Street":
			    return address.getStreet();
			case "Number":
				return String.valueOf(address.getStreetNumber());
			default:
				return "";
		}
	}

	public void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
	}
	
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
}

