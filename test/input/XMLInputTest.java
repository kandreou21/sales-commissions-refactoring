package input;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import data.Receipt;

public class XMLInputTest {
	private XMLInput xmlInput;
	private File xmlFile;
	
	@Before
	public void setUp() throws Exception {
		xmlFile = new File("test/input/test-case-2-XML.xml"); 
		xmlInput = new XMLInput(xmlFile);
	}

	@Test
	public void testReadFileReceiptsSize() {
        xmlInput.readFile();
        assertEquals(13, xmlInput.representative.getReceipts().size());
    }
	
	@Test
	public void testReadFileReceiptInfo() {
		xmlInput.readFile();
		Receipt firstReceipt = xmlInput.getRepresentative().getReceipts().get(0); 
		
		assertEquals("Vassileios Zarras", xmlInput.name);
		assertEquals("130456097", xmlInput.afm);
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
		xmlInput.readFile();
		Receipt secondtReceipt = xmlInput.getRepresentative().getReceipts().get(5); 
		
		assertEquals("Vassileios Zarras", xmlInput.name);
		assertEquals("130456097", xmlInput.afm);
		assertNotEquals(1, secondtReceipt.getReceiptID());
		assertNotEquals("25/2/2023", secondtReceipt.getDate());
		assertNotEquals("Coat", secondtReceipt.getKind());
		assertNotEquals(2000, secondtReceipt.getSales(), 0.001);
		assertEquals(10, secondtReceipt.getItems());
		assertEquals("Hand Made Clothes", secondtReceipt.getCompany().getName());
		assertEquals("Greece", secondtReceipt.getCompany().getCompanyAddress().getCountry());
		assertEquals("Ioannina", secondtReceipt.getCompany().getCompanyAddress().getCity());
		assertEquals("Kaloudi", secondtReceipt.getCompany().getCompanyAddress().getStreet());
		assertEquals(10, secondtReceipt.getCompany().getCompanyAddress().getStreetNumber());
	}

}
