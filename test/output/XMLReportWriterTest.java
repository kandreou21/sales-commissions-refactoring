package output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;
import data.Representative;

public class XMLReportWriterTest {
	private Representative representative;
	private XMLReportWriter reportWriter;

    @Before
    public void setUp() {
        representative = new Representative();
        representative.setName("Apostolos Zarras");
        representative.setAfm("123456789");
        
        Receipt receipt = new Receipt();
        receipt.setKind("Coats");
        receipt.setItems(5);
        receipt.setSales(6001);
        
        representative.getReceipts().add(receipt);
        reportWriter = new XMLReportWriter(representative);
        reportWriter.setFullPathName("test/output/sales_report.xml");
    }

    @Test
    public void testXMLReportContent() {
        reportWriter.saveFile();

        try (BufferedReader br = new BufferedReader(
        		new FileReader(reportWriter.getFullPathName()))) {
        	br.readLine();
        	assertEquals("<Representative>", br.readLine());
        	assertEquals("<Name>" + representative.getName() + "</Name>", br.readLine().trim());
            assertEquals("<AFM>" + representative.getAfm() + "</AFM>", br.readLine().trim());
            assertEquals("<TotalSales>" + representative.calculateTotalSales() + "</TotalSales>", br.readLine().trim());
            assertEquals("<TrousersSales>" + representative.calculateTrouserSales() + "</TrousersSales>", br.readLine().trim());
            assertEquals("<SkirtsSales>" + representative.calculateSkirtsSales() + "</SkirtsSales>", br.readLine().trim());
            assertEquals("<ShirtsSales>" + representative.calculateShirtsSales() + "</ShirtsSales>", br.readLine().trim());
            assertEquals("<CoatsSales>" + representative.calculateCoatsSales() + "</CoatsSales>", br.readLine().trim());
            assertEquals("<Commission>" + representative.calculateCommission() + "</Commission>", br.readLine().trim());
            assertEquals("</Representative>", br.readLine());
            br.close();
         } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testXMLReportWrongContent() {
    	reportWriter.saveFile();

        try (BufferedReader br = new BufferedReader(
        		new FileReader(reportWriter.getFullPathName()))) {
        	br.readLine();
        	assertEquals("<Representative>", br.readLine());
        	assertEquals("<Name>" + representative.getName() + "</Name>", br.readLine().trim());
            assertEquals("<AFM>" + representative.getAfm() + "</AFM>", br.readLine().trim());
            assertNotEquals("<TotalSales>" + 1.2 + "</TotalSales>", br.readLine().trim());
            assertNotEquals("<TrousersSales>" + 1.0f + "</TrousersSales>", br.readLine().trim());
            assertNotEquals("<SkirtsSales>" + 1.0f + "</SkirtsSales>", br.readLine().trim());
            assertNotEquals("<ShirtsSales>" + 1.0f + "</ShirtsSales>", br.readLine().trim());
            assertNotEquals("<CoatsSales>" + 1.0f + "</CoatsSales>", br.readLine().trim());
            assertNotEquals("<Commission>" + 1.2 + "</Commission>", br.readLine().trim());
            assertEquals("</Representative>", br.readLine());
            br.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
