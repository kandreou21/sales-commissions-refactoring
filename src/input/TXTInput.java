package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTInput extends Input{
	private BufferedReader br;
	
	public TXTInput(File receiptFileTXT){
		this.inputFile = receiptFileTXT;
		inputFilePath =  inputFile.getAbsolutePath();	
	}

	public void initialiseRepresentative() throws IOException {
		br = new BufferedReader(new FileReader(inputFilePath));
		String line;
		for (int i = 0; i < 5; i++) {
			line = br.readLine();
		    if (line.startsWith("Name:")) {
		        name = line.substring(line.indexOf(":") + 1).trim();
		    } else if (line.startsWith("AFM")) {
		        afm = line.substring(line.indexOf(":") + 1).trim();
		        addRepresentative();
		    }
		}
	}

	public void readReceipts() throws Exception {
		String line;
		while ((line = br.readLine()) !=null) {
			receiptData = new ArrayList<>();
		    for (int j = 0; j < 10; j++) {
		    	receiptData.add(line.substring(line.indexOf(":") + 1).trim());
		    	line = br.readLine();
		    }
		    setReceipt();
		    addReceipt();
		}
		br.close();
	}
}
