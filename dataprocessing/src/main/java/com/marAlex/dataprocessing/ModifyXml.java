package com.marAlex.dataprocessing;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class ModifyXml {
	 public static void main(String argv[]) {

	      try {
	         File inputFile = new File("films.xml");
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document doc = docBuilder.parse(inputFile);
	         Node comedy = doc.getElementsByTagName("comedy").item(0);

	         NamedNodeMap attr = comedy.getAttributes();
	         Node nodeAttr = attr.getNamedItem("country");
	         nodeAttr.setTextContent("Russia");
	         
	         NodeList list = comedy.getChildNodes();
	         
	         for (int temp = 0; temp < list.getLength(); temp++) {
	            Node node = list.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               if ("filmname".equals(eElement.getNodeName())) {
	                  if("Shaun of the Dead".equals(eElement.getTextContent())) {
	                     eElement.setTextContent("Ёлки 2");
	                  }
	                  if("Johnny English".equals(eElement.getTextContent()))
	                     eElement.setTextContent("Ёлки 3");
	               }
	            }
	         }
	         // write the content on console
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         System.out.println("-----------Modified File-----------");
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
