package com.TestingAutomaton.Lab1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParse {
	public static void main(String[] args) {
		try {
			File inputFile = new File("./department.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList departments = doc.getElementsByTagName("department");
			System.out.println("----------------------------");

			for (int temp = 0; temp < departments.getLength(); temp++) {
				Node nNode = departments.item(temp);
				System.out.println("\nDepartment :" + nNode.getAttributes().getNamedItem("name").getTextContent()
						+ "  Department ID:" + nNode.getAttributes().getNamedItem("depId").getTextContent());
				System.out.println("***********************");
				NodeList employees = nNode.getChildNodes();
				for (int i = 0; i < employees.getLength(); i++) {
					Node employee = employees.item(i);
					if (employee.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(
								"Employee Id:" + employee.getAttributes().getNamedItem("empId").getTextContent());
						NodeList nodes = employee.getChildNodes();
						System.out.println("Last Name:" + nodes.item(1).getTextContent() + "\nFirst Name:"
								+ nodes.item(3).getTextContent() + "\nBirth Date:" + nodes.item(5).getTextContent()
								+ "\nPosition:" + nodes.item(7).getTextContent());
						NodeList skills = nodes.item(9).getChildNodes();
						System.out.println("Skills:");
						for (int j = 0; j < skills.getLength(); j++)
							if (skills.item(j).getNodeType() == Node.ELEMENT_NODE)
								System.out.println(skills.item(j).getTextContent());
						System.out.println("Manager ID:" + nodes.item(11).getTextContent());
						System.out.println("--------------------");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
