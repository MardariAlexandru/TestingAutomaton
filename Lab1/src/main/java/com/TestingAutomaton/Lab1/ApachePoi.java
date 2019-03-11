package com.TestingAutomaton.Lab1;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ApachePoi {
	public static void main(String[] args) {
		 JSONParser parser = new JSONParser();
			Workbook wb = new XSSFWorkbook();
			CellStyle header=wb.createCellStyle();
			header.setAlignment(HorizontalAlignment.CENTER);
			header.setVerticalAlignment(VerticalAlignment.CENTER);
			Font hFont= wb.createFont();
			hFont.setBold(true);
			header.setFont(hFont);
			CellStyle skl = wb.createCellStyle();
		    skl.setWrapText(true);
			int rowCounter;
	        try {
	            Object obj = parser.parse(new FileReader("./department.json"));
	            JSONObject jsonObject = (JSONObject) obj;
	            JSONObject company =(JSONObject)  jsonObject.get("company");
	            JSONArray departments = (JSONArray) company.get("department");
	            Iterator<JSONObject> depIterator = departments.iterator();
	            while (depIterator.hasNext()) {
	            	JSONObject department =depIterator.next();
	        		Sheet sheet = wb.createSheet((String)department.get("-name"));
	            	   Row row = sheet.createRow(0);
	            	   rowCounter=1;
	            	   Cell EmpId = row.createCell(0);
	            	   EmpId.setCellStyle(header);
	            	   EmpId.setCellValue("Emp ID");
	            	   Cell Lastname = row.createCell(1);
	            	   Lastname.setCellStyle(header);
	            	   Lastname.setCellValue("Lastname");
	            	   Cell Firstname = row.createCell(2);
	            	   Firstname.setCellStyle(header);
	            	   Firstname.setCellValue("Firstname");
	            	   Cell Birthdate = row.createCell(3);
	            	   Birthdate.setCellStyle(header);
	            	   Birthdate.setCellValue("Birthdate");
	            	   Cell ManagerID = row.createCell(4);
	            	   ManagerID.setCellStyle(header);
	            	   ManagerID.setCellValue("Manager ID");
	            	   Cell Skills = row.createCell(5);
	            	   Skills.setCellStyle(header);
	            	   Skills.setCellValue("Skills");
	               JSONArray employees = (JSONArray) department.get("employee");
	               Iterator<JSONObject> empIterator =employees.iterator();
	               while (empIterator.hasNext()) {
	            	   JSONObject employee =empIterator.next();
	            	   Row empRow = sheet.createRow(rowCounter);
	            	   rowCounter++;
	            	   empRow.createCell(0).setCellValue((String)employee.get("-empId"));
	            	   empRow.createCell(1).setCellValue((String)employee.get("lastName"));
	            	   empRow.createCell(2).setCellValue((String)employee.get("firstName"));
	            	   empRow.createCell(3).setCellValue((String)employee.get("birthDate"));
	            	   empRow.createCell(4).setCellValue((String)employee.get("managerId"));
	            	   Cell empSkills =empRow.createCell(5);
	            	   empSkills.setCellStyle(skl);
	            	   JSONObject skillsObj = (JSONObject) employee.get("skills");
	            	   JSONArray skills = (JSONArray) skillsObj.get("skill");
	            	   Iterator<String> sklIterator =skills.iterator();
	            	   String rezult="";
	            	   while (sklIterator.hasNext()) { 
	            		   rezult = rezult.concat(sklIterator.next()+"\n");
	            	   }
	            	   empSkills.setCellValue(rezult);
	            		   
	               }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        try (OutputStream fileOut = new FileOutputStream("./workbook.xls")) {
	            wb.write(fileOut);
	        }catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
