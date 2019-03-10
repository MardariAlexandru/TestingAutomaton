package com.TestingAutomaton.Lab1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomCreate {
	public static void main(String argv[]) {

	      try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         Element rootElement = doc.createElement("company");
	         doc.appendChild(rootElement);
	         Element department = doc.createElement("department");
	         rootElement.appendChild(department);
	         Attr dname = doc.createAttribute("name");
	         dname.setValue("Development");
	         department.setAttributeNode(dname);
	         Attr dId = doc.createAttribute("depId");
	         dId.setValue("1");
	         department.setAttributeNode(dId);
	         Element employee = doc.createElement("employee");
	         Attr empId = doc.createAttribute("empId");
	         empId.setValue("001");
	         employee.setAttributeNode(empId);
	         department.appendChild(employee);
	         Element lName = doc.createElement("lastName");
	         lName.setTextContent("Mardari");
	         employee.appendChild(lName);
	         Element fName = doc.createElement("firstName");
	         fName.setTextContent("Alexandru");
	         employee.appendChild(fName);
	         Element bDate = doc.createElement("birthDate");
	         bDate.setTextContent("16.06.1998");
	         employee.appendChild(bDate);
	         Element pos = doc.createElement("position");
	         pos.setTextContent("Department Manager");
	         employee.appendChild(pos);
	         Element skills = doc.createElement("skills");
	         Element skill1 = doc.createElement("skill");
	         skill1.setTextContent("Communication");
	         skills.appendChild(skill1);
	         Element skill2 = doc.createElement("skill");
	         skill2.setTextContent("Java");
	         skills.appendChild(skill2);
	         employee.appendChild(skills);
	         Element mId = doc.createElement("managerId");
	         mId.setTextContent("0");
	         employee.appendChild(mId);
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("department1.xml"));
	         transformer.transform(source, result);

	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
