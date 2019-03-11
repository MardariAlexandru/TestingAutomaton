package com.TestingAutomaton.Lab1;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxQuery {
	public static void main(String[] args) {

	      try {
	         File inputFile = new File("department.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandlerQuerry userhandler = new UserHandlerQuerry();
	         Scanner input = new Scanner(System.in);
	         System.out.println("Enter EmpId");
	         String empID=input.nextLine();
	         userhandler.setrEmpID(empID);
	         saxParser.parse(inputFile, userhandler);     
	         input.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   } 
}
