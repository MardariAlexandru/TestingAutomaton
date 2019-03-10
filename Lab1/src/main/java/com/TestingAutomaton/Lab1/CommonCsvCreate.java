package com.TestingAutomaton.Lab1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonCsvCreate {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("./students.csv"));
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Notes"));
		csvPrinter.printRecord("1", "Mardari Alexandru", "8 9 10 7 8");
		csvPrinter.printRecord("2", "John Doe", "10 10 10 9 9");
		csvPrinter.printRecord("3", "Vasya Pupkin", "7 5 6 7 5");
		csvPrinter.printRecord("4", "Freddy Krueger", "8 8 7 8 10");
		csvPrinter.flush();
		csvPrinter.close();
	}

}
