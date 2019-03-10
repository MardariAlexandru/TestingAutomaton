package com.marAlex.dataprocessing;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class ParseXml {
	 public static void main(String[] args) {
		 try {
	         File inputFile = new File("films.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("comedy");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("Country : " 
	                  + eElement.getAttribute("country"));
	               NodeList nList2 = eElement.getElementsByTagName("filmname");
	               for(int temp2 = 0; temp2 < nList2.getLength(); temp2++){
	            	 Element eElement2 = (Element) nList2.item(temp2);
	               System.out.println("Film Name : " 
	                  + eElement2
	                  .getTextContent());
	               System.out.println("Year : " 
	 	                  + eElement2.getAttribute("year"));
	            }
	            //   
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
}
}