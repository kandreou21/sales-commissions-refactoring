package output;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Address;
import data.Receipt;
import output.ReceiptFileUpdaterTXT;

public class ReceiptFileUpdaterTXTTest {
	private ReceiptFileUpdaterTXT receiptFileUpdaterTXT;
    private File testFile;

    @Before
    public void setUp() {
    	receiptFileUpdaterTXT = new ReceiptFileUpdaterTXT();
        testFile = new File("test_RECEIPTS.txt");
        receiptFileUpdaterTXT.setFileToAppend(testFile);
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
        receiptFileUpdaterTXT.setReceipt(receipt);
        
        receiptFileUpdaterTXT.appendFile();
		assertTrue(testFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
        	assertEquals("", reader.readLine());
            assertEquals("Receipt ID: 123", reader.readLine());
            assertEquals("Date: 18/11/2023", reader.readLine());
            assertEquals("Kind: Coats", reader.readLine());
            assertEquals("Sales: 1000.0", reader.readLine());
            assertEquals("Items: 10", reader.readLine());
            assertEquals("Company: Hand Made Clothes", reader.readLine());
            assertEquals("Country: Greece", reader.readLine());
            assertEquals("City: Ioannina", reader.readLine());
            assertEquals("Street: Kaloudi", reader.readLine());
            assertEquals("Number: 10", reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

