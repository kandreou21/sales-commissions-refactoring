package output;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptFileUpdaterTXT extends ReceiptFileUpdater{
	private FileWriter fileWriter;
	
	protected void initialiseWriter() throws IOException {
		fileWriter = new FileWriter(fileToAppend,true);		
		fileWriter.write("\n");
	}
	
	protected void writeField(String field) throws IOException {
		fileWriter.write(field + ": " + getField(field));
		fileWriter.write("\n");
	}
	
	protected void closeWriter() throws IOException {
		fileWriter.close();
	}	
}
