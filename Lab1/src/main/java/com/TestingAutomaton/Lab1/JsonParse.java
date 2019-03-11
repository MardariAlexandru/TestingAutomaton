package com.TestingAutomaton.Lab1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonParse {
	 public static void main(String[] args) {

	        JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader("./department.json"));

	            JSONObject jsonObject = (JSONObject) obj;
	            System.out.println("Company:");
	            JSONObject company =(JSONObject)  jsonObject.get("company");
	            JSONArray departments = (JSONArray) company.get("department");
	            Iterator<JSONObject> depIterator = departments.iterator();
	            while (depIterator.hasNext()) {
	            	System.out.println("Department:");
	            	JSONObject department =depIterator.next();
	               System.out.println("DepName:"+ department.get("-name"));
	               System.out.println("DepId:"+department.get("-depId"));
	               JSONArray employees = (JSONArray) department.get("employee");
	               Iterator<JSONObject> empIterator =employees.iterator();
	               while (empIterator.hasNext()) {
	            	   JSONObject employee =empIterator.next();
	            	   System.out.println("empId :"+ employee.get("-empId") +"\nlastName :"+ employee.get("lastName") +
	            			   "\nfirstName :"+ employee.get("firstName") +"\nbirthDate :"+ employee.get("birthDate")+
	            			   "\nposition :"+ employee.get("position") + "\nSkills:");
	            	   JSONObject skillsObj = (JSONObject) employee.get("skills");
	            	   JSONArray skills = (JSONArray) skillsObj.get("skill");
	            	   Iterator<JSONObject> sklIterator =skills.iterator();
	            	   while (sklIterator.hasNext())
	            		   System.out.println("Skill:"+sklIterator.next());
	            	   System.out.println("managerId :"+ employee.get("managerId"));
	            	   System.out.println("----------------------");
	               }
	               System.out.println("*************");
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	    }
}
