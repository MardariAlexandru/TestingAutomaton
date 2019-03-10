package com.TestingAutomaton.Lab1;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class OpenCsvParse {
	 public static void main(String[] args) {

	        String csvFile = "./students.csv";

	        CSVReader reader = null;
	        try {
	            reader = new CSVReader(new FileReader(csvFile));
	            String[] line;
	            while ((line = reader.readNext()) != null) {
	                System.out.println(line[0]+"|"+line[1]+"|"+line[2]);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }


	    }
}
