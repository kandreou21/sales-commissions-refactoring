package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Representative;

public class TXTReportWriter extends ReportWriter{
	private BufferedWriter bufferedWriter = null;
	
	public TXTReportWriter(Representative a){
		representative = a;
	}
	
	protected void openWriter() {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File(getFullPathName())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeReport() throws IOException {
		for (int i = 0; i < representativeTags.length; i++) {
			writeStat(representativeTags[i]);
		}
		bufferedWriter.newLine();
		bufferedWriter.newLine();
		for (int i = 0; i < salesTags.length; i++) {
			writeStat(salesTags[i]);
		}
	}

	private void writeStat(String field) throws IOException {
		bufferedWriter.write(field + ": " + getField(field));
		bufferedWriter.newLine();
	}

	protected void closeWriter() throws IOException {
		bufferedWriter.close();
	}	
}
