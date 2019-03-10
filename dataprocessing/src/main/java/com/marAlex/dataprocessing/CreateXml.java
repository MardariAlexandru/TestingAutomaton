package com.marAlex.dataprocessing;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
public class CreateXml {
	public static void main(String argv[]) {

	      try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         Element rootElement = doc.createElement("films");
	         doc.appendChild(rootElement);
	         Element comedy = doc.createElement("comedy");
	         rootElement.appendChild(comedy);
	         Attr attr = doc.createAttribute("country");
	         attr.setValue("England");
	         comedy.setAttributeNode(attr);
	         Element filmname = doc.createElement("filmname");
	         Attr attrYear = doc.createAttribute("year");
	         attrYear.setValue("2004");
	         filmname.setAttributeNode(attrYear);
	         filmname.appendChild(doc.createTextNode("Shaun of the Dead"));
	         comedy.appendChild(filmname);

	         Element filmname2 = doc.createElement("filmname");
	         Attr attrYear2 = doc.createAttribute("year");
	         attrYear2.setValue("2003");
	         filmname2.setAttributeNode(attrYear2);
	         filmname2.appendChild(doc.createTextNode("Johnny English"));
	         comedy.appendChild(filmname2);
	         
	         Element comedy2 = doc.createElement("comedy");
	         rootElement.appendChild(comedy2);
	         Attr attr3 = doc.createAttribute("country");
	         attr3.setValue("USA");
	         comedy2.setAttributeNode(attr3);
	         Element filmname3 = doc.createElement("filmname");
	         Attr attrYear3 = doc.createAttribute("year");
	         attrYear3.setValue("2017");
	         filmname3.setAttributeNode(attrYear3);
	         filmname3.appendChild(doc.createTextNode("Coco"));
	         comedy2.appendChild(filmname3);

	         Element filmname4 = doc.createElement("filmname");
	         Attr attrYear4 = doc.createAttribute("year");
	         attrYear4.setValue("2014");
	         filmname4.setAttributeNode(attrYear4);
	         filmname4.appendChild(doc.createTextNode("The Lego Movie"));
	         comedy2.appendChild(filmname4);

	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("films.xml"));
	         transformer.transform(source, result);

	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
