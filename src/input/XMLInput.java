package input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLInput extends Input {
	private NodeList receiptsNodeList;
	
	public XMLInput(File receiptFileXML ){
		this.inputFile = receiptFileXML;	
	}

	public void initialiseRepresentative() throws IOException {
		DocumentBuilderFactory docBuilderFactory 
		= DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(inputFile);
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		 
		doc.getDocumentElement().normalize();
		NodeList nodeLst = doc.getElementsByTagName("Agent");
		
		name = ((Element) nodeLst.item(0)).getElementsByTagName("Name").
		item(0).getChildNodes().item(0).getNodeValue().trim();
		afm = ((Element) nodeLst.item(0)).getElementsByTagName("AFM").
		item(0).getChildNodes().item(0).getNodeValue().trim();
		addRepresentative();
		receiptsNodeList = ((Element) nodeLst.
		item(0)).getElementsByTagName("Receipt");
	}

	public void readReceipts( ) throws Exception {
		int size = receiptsNodeList.getLength();
		for(int i = 0; i < size; i++){
			receiptData = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				receiptData.add(((Element) receiptsNodeList.item(i))
						.getElementsByTagName(receiptTags[j]).item(0)
						.getChildNodes().item(0).getNodeValue().trim());
			} 
			setReceipt();
			addReceipt();
		}
	}   
}