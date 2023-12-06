package output;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import data.Representative;

public class XMLReportWriter extends ReportWriter{
	private Document document;
	private Element representativeElem;
	
	public XMLReportWriter(Representative a){
		representative = a;
	}
	
	protected void openWriter() {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		document = documentBuilder.newDocument();
		representativeElem = document.createElement("Representative");
		document.appendChild(representativeElem);
	}
	
	protected void writeReport() throws IOException{
		for (int i = 0; i < representativeTags.length; i++) {
			writeStat(representativeTags[i]);
		}
		for (int i = 0; i < salesTags.length; i++) {
			writeStat(salesTags[i]);
		}
	}
	
	private void writeStat(String field) {
	    Element element = document.createElement(field.replaceAll(" ", ""));
	    element.appendChild(document.createTextNode(getField(field)));
	    representativeElem.appendChild(element);
	}
	
	protected void closeWriter() throws IOException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(getFullPathName()));
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}