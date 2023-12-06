package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import output.ReceiptFileUpdaterTXT;
import output.ReceiptFileUpdaterXML;

public class RepresentativeTest {
	private Representative representative;

    @Before
    public void setUp() {
    	representative = new Representative();
    	
    	Receipt skirtReceipt = new Receipt();
        skirtReceipt.setKind("Skirt");
        skirtReceipt.setSales(20.0);
        skirtReceipt.setItems(5);

        Receipt coatReceipt = new Receipt();
        coatReceipt.setKind("Coat");
        coatReceipt.setSales(50.0);
        coatReceipt.setItems(3);

        representative.getReceipts().add(skirtReceipt);
        representative.getReceipts().add(coatReceipt);
    }

    @Test
    public void testSetFileTypeTXT() {
        representative.setFileType("TXT");
        assertEquals(ReceiptFileUpdaterTXT.class, representative.getReceiptFileUpdater().getClass());
    }

    @Test
    public void testSetFileTypeXML() {
        representative.setFileType("XML");
        assertEquals(ReceiptFileUpdaterXML.class, representative.getReceiptFileUpdater().getClass());
    }
    
    @Test
    public void testCalculateTotalSalesNoReceipts() {
    	representative.getReceipts().clear();
        assertEquals(0.0, representative.calculateTotalSales(), 0.001);
    }

    @Test
    public void testCalculateTotalSalesWithReceipts() {
       assertEquals(20.0 + 50.0, representative.calculateTotalSales(), 0.001);
    }

    @Test
    public void testCalculateTotalItems() {
        assertEquals(5 + 3, representative.calculateTotalItems());
    }
    
    @Test
    public void testCalculateTotalItemsNoItems() {
    	representative.getReceipts().clear();
        assertEquals(0, representative.calculateTotalItems());
    }
    
    @Test
    public void testCalculateSkirtsSales() {
        assertEquals(5, representative.calculateSkirtsSales(), 0.001);
    }

    @Test
    public void testCalculateCoatsSales() {
        assertEquals(3, representative.calculateCoatsSales(), 0.001);
    }

    @Test
    public void testCalculateTrouserSales() {
        Receipt trouserReceipt = new Receipt();
        trouserReceipt.setKind("Trouser");
        trouserReceipt.setSales(30.0);
        trouserReceipt.setItems(4);

        representative.getReceipts().add(trouserReceipt);
        assertEquals(4, representative.calculateTrouserSales(), 0.001);
        
        representative.getReceipts().remove(trouserReceipt);
    }

    @Test
    public void testCalculateShirtsSales() {
    	Receipt shirtReceipt = new Receipt();
        shirtReceipt.setKind("Shirt");
        shirtReceipt.setSales(25.0);
        shirtReceipt.setItems(2);
        
        representative.getReceipts().add(shirtReceipt);
        assertEquals(2, representative.calculateShirtsSales(), 0.001);
        
        representative.getReceipts().remove(shirtReceipt);
    }
    
    @Test
    public void testCalculateCommissionWithNoSales() {
        assertEquals(0.0, representative.calculateCommission(), 0.001);
    }
    
    @Test
    public void testCalculateCommissionLowSales() {
    	representative.getReceipts().clear();
    	Receipt receipt = new Receipt();
    	receipt.setSales(6001);
    	representative.getReceipts().add(receipt);
    	
        assertEquals(0.1, representative.calculateCommission(), 0.001);
        representative.getReceipts().remove(receipt);
    }

    @Test
    public void testCalculateCommissionMediumSales() {
    	representative.getReceipts().clear();
    	Receipt receipt = new Receipt();
    	receipt.setSales(10001);
    	representative.getReceipts().add(receipt);
    	
        assertEquals(1000.15, representative.calculateCommission(), 0.001);
        representative.getReceipts().remove(receipt);
    }

    @Test
    public void testCalculateCommissionHighSales() {
    	representative.getReceipts().clear();
    	Receipt receipt = new Receipt();
    	receipt.setSales(40001);
    	representative.getReceipts().add(receipt);
    	
       assertEquals(5500.2, representative.calculateCommission(), 0.001);
       representative.getReceipts().remove(receipt);
    }
}
