package output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;
import data.Representative;

public class TXTReportWriterTest {
	private Representative representative;
	private TXTReportWriter reportWriter;

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
        reportWriter = new TXTReportWriter(representative);
        reportWriter.setFullPathName("test/output/sales_report.txt");
    }

    @Test
    public void testTXTReportContent() {
        reportWriter.saveFile();

        try (BufferedReader br = new BufferedReader(
        		new FileReader(reportWriter.getFullPathName()))) {
            assertEquals(representative.getName(), br.readLine().split(": ")[1]);
            assertEquals(representative.getAfm(), br.readLine().split(": ")[1]);
            for (int i = 0; i < 2; i++) { 
            	br.readLine();	//skip lines
            }
            assertEquals(representative.calculateTotalSales(), Double.parseDouble(br.readLine().split(": ")[1]), 0.001);
            assertEquals(representative.calculateTrouserSales(), Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertEquals(representative.calculateSkirtsSales(), Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertEquals(representative.calculateShirtsSales(), Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertEquals(representative.calculateCoatsSales(), Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertEquals(representative.calculateCommission(), Double.parseDouble(br.readLine().split(": ")[1]), 0.0001);
            br.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testTXTReportWrongContent() {
        reportWriter.saveFile();

        try (BufferedReader br = new BufferedReader(
        		new FileReader(reportWriter.getFullPathName()))) {
            assertNotEquals("apostolos zarras", br.readLine().split(": ")[1]);
            assertNotEquals("123456", br.readLine().split(": ")[1]);
            for (int i = 0; i < 2; i++) { 
            	br.readLine();	//skip lines
            }
            assertNotEquals(1.2, Double.parseDouble(br.readLine().split(": ")[1]), 0.001);
            assertNotEquals(1.0f, Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertNotEquals(1.0f, Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertNotEquals(1.0f, Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertNotEquals(1.0f, Float.parseFloat(br.readLine().split(": ")[1]), 0.001);
            assertNotEquals(1.2, Double.parseDouble(br.readLine().split(": ")[1]), 0.0001);
            br.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
