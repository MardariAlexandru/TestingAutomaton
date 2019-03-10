package com.TestingAutomaton.Lab1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class OpenCsvCreate {
	public static void main(String[] args) {
		File file = new File("./students.csv");

		try {
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "ID", "Name", "Notes" });
			data.add(new String[] { "1", "Mardari Alexandru", "8 9 10 7 8" });
			data.add(new String[] { "2", "John Doe", "10 10 10 9 9" });
			data.add(new String[] { "3", "Vasya Pupkin", "7 5 6 7 5" });
			data.add(new String[] { "4", "Freddy Krueger", "8 8 7 8 10" });
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
