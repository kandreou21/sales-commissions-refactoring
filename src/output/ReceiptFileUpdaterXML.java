package output;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ReceiptFileUpdaterXML  extends ReceiptFileUpdater{
	private Document doc;
	private Element receiptElem;
		
	protected void initialiseWriter() throws IOException {
		try {
			doc = parseDocument();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		createElement(doc);
	}

	private Document parseDocument() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(fileToAppend);
		return doc;
	}
	
	private void createElement(Document doc) throws DOMException {
		Node representative = doc.getFirstChild();
		receiptElem = doc.createElement("Receipt");
		representative.appendChild(receiptElem);
	}

	protected void writeField(String field) throws IOException{
	    Element element = doc.createElement(field.replaceAll(" ", ""));
	    element.appendChild(doc.createTextNode(getField(field)));
	    receiptElem.appendChild(element);
	}
	
	protected void closeWriter() throws IOException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileToAppend);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
