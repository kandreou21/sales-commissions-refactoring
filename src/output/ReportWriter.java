package output;

import java.io.IOException;

import javax.swing.JOptionPane;

import data.Representative;

public abstract class ReportWriter {
	protected Representative representative;
	private String fullPathName;
	protected String[] representativeTags = {"Name", "AFM"};
	protected String[] salesTags = {"Total Sales", "Trousers Sales"
			, "Skirts Sales", "Shirts Sales", "Coats Sales", "Commission"}; 
	
	public void saveFile() {
		try {
			openWriter();
			writeReport();
			closeWriter();
		} catch (Exception ex){
			JOptionPane.showMessageDialog(null,"Υπήρξε κάποιο πρόβλημα κατά την αποθήκευση του αρχείου");
        }				
	}

	protected abstract void openWriter();
	protected abstract void writeReport() throws IOException;
	protected abstract void closeWriter() throws IOException;
	
	protected String getField(String field) {
		switch(field) {
			case "Name":
				return representative.getName();
			case "AFM":
				return representative.getAfm();
			case "Total Sales":
				return String.valueOf(representative.calculateTotalSales());
			case "Trousers Sales":
				return String.valueOf(representative.calculateTrouserSales());
			case "Skirts Sales":
				return String.valueOf(representative.calculateSkirtsSales());
			case "Shirts Sales":
				return String.valueOf(representative.calculateShirtsSales());
			case "Coats Sales":
				return String.valueOf(representative.calculateCoatsSales());
			case "Commission":
				return String.valueOf(representative.calculateCommission());
			default:
				return "";
		}
	}
	
	public void setFullPathName(String fullPathName) {
		this.fullPathName = fullPathName;
	}

	public String getFullPathName() {
		return fullPathName;
	}
}
