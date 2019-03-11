package com.TestingAutomaton.Lab1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xpath {
	public static boolean containsTag(Document doc, String tag) throws XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile("//" + tag).evaluate(doc, XPathConstants.NODESET);
		if (nodeList.getLength() == 0)
			return false;
		else
			return true;
	}

	public static boolean hasNestedTags(Document doc, String tag) throws XPathExpressionException {
		int f = 0;
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile("//" + tag).evaluate(doc, XPathConstants.NODESET);
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			NodeList childNodes = nodeList.item(temp).getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
				if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					f++;
					break;
				}
		}
		if (f == 0)
			return false;
		else
			return true;
	}

	public static ArrayList<String> tagValues(Document doc, String tag) throws XPathExpressionException {
		ArrayList<String> values = new ArrayList<String>();
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile("//" + tag).evaluate(doc, XPathConstants.NODESET);
		for (int temp = 0; temp < nodeList.getLength(); temp++)
			if (nodeList.item(temp).getNodeType() == Node.ELEMENT_NODE)
				values.add(nodeList.item(temp).getTextContent());
		return values;
	}

	public static void main(String[] args) {
		try {
			File inputFile = new File("./department.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Enter Tag");
			Scanner input = new Scanner(System.in);
			String tag = input.nextLine();
			System.out
					.println("Tag present?" + containsTag(doc, tag) + "\nHas Child Nodes ?" + hasNestedTags(doc, tag)+"\nValues :");
			for(String value:tagValues(doc, tag))
				System.out.println(value);
			input.close();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
