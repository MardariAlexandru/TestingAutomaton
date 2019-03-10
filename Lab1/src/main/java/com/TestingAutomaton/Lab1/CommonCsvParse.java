package com.TestingAutomaton.Lab1;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonCsvParse {
	public static void main(String[] args) throws IOException {
            Reader reader = Files.newBufferedReader(Paths.get("./students.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
         
            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get("ID");
                String name = csvRecord.get("Name");
                String notes = csvRecord.get("Notes");
                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Notes : " + notes);
            }
            csvParser.close();
     
    }
}
