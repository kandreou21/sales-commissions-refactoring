package output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Address;
import data.Receipt;
import output.ReceiptFileUpdaterXML;

public class ReceiptFileUpdaterXMLTest {
	private ReceiptFileUpdaterXML receiptFileUpdaterXML;
    private File testFile;

    @Before
    public void setUp() {
        receiptFileUpdaterXML = new ReceiptFileUpdaterXML();
        testFile = new File("test/data/test-case-2-XML.xml");
        receiptFileUpdaterXML.setFileToAppend(testFile);
    }
    
    @After
    public void tearDown() {
        //Delete the test file after the test
        testFile.delete();
    }

    @Test
    public void testAppendFile() {
    	Receipt receipt = new Receipt();
        receipt.setReceiptID(123);
        receipt.setDate("18/11/2023");
        receipt.setKind("Coats");
        receipt.setSales(1000);
        receipt.setItems(10);
        receipt.getCompany().setName(("Hand Made Clothes"));
        Address address = receipt.getCompany().getCompanyAddress();
        address.setCountry("Greece");
        address.setCity("Ioannina");
        address.setStreet("Kaloudi");
        address.setStreetNumber(10);
        receiptFileUpdaterXML.setReceipt(receipt);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))){
        	writer.write("<Representative>\n");
        	writer.write("<Name> Vassileios Zarras </Name>\n");
        	writer.write("<AFM> 130456097 </AFM>\n\n");
        	writer.write("<Receipts>\n");
        	writer.write("</Receipts>\n");
        	writer.write("</Representative>");
        	writer.flush();
        	writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        receiptFileUpdaterXML.appendFile();
		assertTrue(testFile.exists());
       
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
        	for (int i = 0; i < 10; i++) {
        		reader.readLine(); //skip useless xml lines
        	}
            assertEquals("<Receipt>", reader.readLine().trim());
            assertEquals("<ReceiptID>123</ReceiptID>", reader.readLine().trim());
            assertEquals("<Date>18/11/2023</Date>", reader.readLine().trim());
            assertEquals("<Kind>Coats</Kind>", reader.readLine().trim());
            assertEquals("<Sales>1000.0</Sales>", reader.readLine().trim());
            assertEquals("<Items>10</Items>", reader.readLine().trim());
            assertEquals("<Company>Hand Made Clothes</Company>", reader.readLine().trim());
            assertEquals("<Country>Greece</Country>", reader.readLine().trim());
            assertEquals("<City>Ioannina</City>", reader.readLine().trim());
            assertEquals("<Street>Kaloudi</Street>", reader.readLine().trim());
            assertEquals("<Number>10</Number>", reader.readLine().trim());
            assertEquals("</Receipt>", reader.readLine().trim());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}