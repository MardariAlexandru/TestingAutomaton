package com.TestingAutomaton.Lab2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.xstream.XStream;

public class Deserial {
	static ArrayList<String> readStrings(String fileName) {
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		try (FileReader fRead = new FileReader(fileName); BufferedReader bReader = new BufferedReader(fRead)) {

			while ((line = bReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FNF");
		} catch (IOException e) {
			System.out.println("IO");
		}
		return lines;
	}

	public static void main(String[] args) {
		ArrayList<ValCurs> valCurses = new ArrayList<ValCurs>();
		StringBuffer xml = new StringBuffer();
		XStream xstream = new XStream();
		xstream.processAnnotations(ValCurs.class);
		xstream.processAnnotations(Valute.class);
		for (String data : readStrings("dates.txt")) {
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet("https://bnm.md/en/official_exchange_rates?get_xml=1&date=" + data);
				getRequest.addHeader("accept", "application/xml");

				HttpResponse response = httpClient.execute(getRequest);
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
				String output;
				xml.setLength(0);
				while ((output = br.readLine()) != null) {
					xml.append(output);
				}
			} catch (ClientProtocolException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}

			valCurses.add((ValCurs) xstream.fromXML(xml.toString()));
		}
		Workbook wb = new XSSFWorkbook();
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font hFont = wb.createFont();
		hFont.setBold(true);
		headerStyle.setFont(hFont);
		for (ValCurs valCurs : valCurses) {
			Sheet sheet = wb.createSheet(valCurs.getDate());
			Row header = sheet.createRow(0);
			Cell DataH = header.createCell(0);
			DataH.setCellStyle(headerStyle);
			DataH.setCellValue("Data:");
			Cell Data = header.createCell(1);
			Data.setCellValue(valCurs.getDate());
			Cell NameH = header.createCell(2);
			NameH.setCellStyle(headerStyle);
			NameH.setCellValue("Name:");
			Cell Name = header.createCell(3);
			Name.setCellValue(valCurs.getName());
			Row valHeader = sheet.createRow(1);
			Cell numCodeHeader = valHeader.createCell(0);
			numCodeHeader.setCellStyle(headerStyle);
			numCodeHeader.setCellValue("NumCode");
			Cell charCodeHeader = valHeader.createCell(1);
			charCodeHeader.setCellStyle(headerStyle);
			charCodeHeader.setCellValue("CharCode");
			Cell nominalHeader = valHeader.createCell(2);
			nominalHeader.setCellStyle(headerStyle);
			nominalHeader.setCellValue("Nominal");
			Cell nameHeader = valHeader.createCell(3);
			nameHeader.setCellStyle(headerStyle);
			nameHeader.setCellValue("Name");
			Cell valueHeader = valHeader.createCell(4);
			valueHeader.setCellStyle(headerStyle);
			valueHeader.setCellValue("Value");
			Cell idHeader = valHeader.createCell(5);
			idHeader.setCellStyle(headerStyle);
			idHeader.setCellValue("ID");
			int rowCounter = 2;
			for (Valute val : valCurs.getValutes()) {
				Row valRow = sheet.createRow(rowCounter++);
				Cell numCode = valRow.createCell(0);
				numCode.setCellValue(val.getNumCode());
				Cell charCode = valRow.createCell(1);
				charCode.setCellValue(val.getСharCode());
				Cell nominal = valRow.createCell(2);
				nominal.setCellValue(val.getNominal());
				Cell name = valRow.createCell(3);
				name.setCellValue(val.getName());
				Cell value = valRow.createCell(4);
				value.setCellValue(val.getValue());
				Cell id = valRow.createCell(5);
				id.setCellValue(val.getId());

			}
		}
		try (OutputStream fileOut = new FileOutputStream("./workbook.xlsx")) {
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Terminated");
}
}
