package com.TestingAutomaton.Lab1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {
	boolean bLastName = false;
	boolean bFirstName = false;
	boolean bBirthDate = false;
	boolean bPosition = false;
	boolean bSkill = false;
	boolean bMId = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("department")) {
			String dName = attributes.getValue("name");
			String depId = attributes.getValue("depId");
			System.out.println("Name : " + dName + "\ndepID:" + depId);
		}
		else if (qName.equalsIgnoreCase("employee")) {
			String empId = attributes.getValue("empId");
			System.out.println("EmpId : " + empId);
		}else if (qName.equalsIgnoreCase("lastName")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("firstName")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("birthDate")) {
			bBirthDate = true;
		}else if (qName.equalsIgnoreCase("skills")) {
			System.out.println("Skills:");
		}else if (qName.equalsIgnoreCase("skill")) {
			bSkill = true;
		} else if (qName.equalsIgnoreCase("position")) {
			bPosition = true;
		} else if (qName.equalsIgnoreCase("managerId")) {
			bMId = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("department")) {
			System.out.println("End Element :" + qName);
		}
		if (qName.equalsIgnoreCase("employee")) {
			System.out.println("End Element :" + qName);
		}
		if (qName.equalsIgnoreCase("skills")) {
			System.out.println("End Element :" + qName);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bLastName) {
			System.out.println("Last Name: " + new String(ch, start, length));
			bLastName = false;
		} else if (bFirstName) {
			System.out.println("First Name:  " + new String(ch, start, length));
			bFirstName = false;
		} else if (bBirthDate) {
			System.out.println("Birth Date: " + new String(ch, start, length));
			bBirthDate = false;
		} else if (bPosition) {
			System.out.println("Position: " + new String(ch, start, length));
			bPosition = false;
		} else if (bSkill) {
			System.out.println("Skill: " + new String(ch, start, length));
			bSkill = false;
		}else if (bMId) {
			System.out.println("Manager Id: " + new String(ch, start, length));
			bMId = false;
		}
	}
}