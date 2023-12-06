package input;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;

public class TXTInputTest {
	private TXTInput txtInput;
	private File txtFile;
	
	@Before
	public void setUp() throws Exception {
		txtFile = new File("test/input/test-case-1-TXT.txt"); 
		txtInput = new TXTInput(txtFile);
	}

	@Test
	public void testReadFileReceiptsSize() {
        txtInput.readFile();
        assertEquals(6, txtInput.representative.getReceipts().size());
    }
	
	@Test
	public void testReadFileReceiptInfo() {
		txtInput.readFile();
		Receipt firstReceipt = txtInput.getRepresentative().getReceipts().get(0); 
		
		assertEquals("Apostolos Zarras", txtInput.name);
		assertEquals("130456093", txtInput.afm);
		assertEquals(1, firstReceipt.getReceiptID());
		assertEquals("25/2/2014", firstReceipt.getDate());
		assertEquals("Coat", firstReceipt.getKind());
		assertEquals(2000, firstReceipt.getSales(), 0.001);
		assertEquals(10, firstReceipt.getItems());
		assertEquals("Hand Made Clothes", firstReceipt.getCompany().getName());
		assertEquals("Greece", firstReceipt.getCompany().getCompanyAddress().getCountry());
		assertEquals("Ioannina", firstReceipt.getCompany().getCompanyAddress().getCity());
		assertEquals("Kaloudi", firstReceipt.getCompany().getCompanyAddress().getStreet());
		assertEquals(10, firstReceipt.getCompany().getCompanyAddress().getStreetNumber());
	}
	
	@Test
	public void testReadFileWrongReceiptInfo() {
		txtInput.readFile();
		Receipt secondtReceipt = txtInput.getRepresentative().getReceipts().get(5); 
		
		assertEquals("Apostolos Zarras", txtInput.name);
		assertEquals("130456093", txtInput.afm);
		assertNotEquals(1, secondtReceipt.getReceiptID());
		assertNotEquals("25/2/2023", secondtReceipt.getDate());
		assertNotEquals("Coat", secondtReceipt.getKind());
		assertNotEquals(2000, secondtReceipt.getSales(), 0.001);
		assertEquals(10, secondtReceipt.getItems());
		assertEquals("Hand Made Clothes", secondtReceipt.getCompany().getName());
		assertEquals("Greece", secondtReceipt.getCompany().getCompanyAddress().getCountry());
		assertEquals("Ioannina", secondtReceipt.getCompany().getCompanyAddress().getCity());
		assertEquals("Kaloudi", secondtReceipt.getCompany().getCompanyAddress().getStreet());
	}
}