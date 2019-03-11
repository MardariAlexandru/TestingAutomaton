package com.TestingAutomaton.Lab1;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonCreate {
	public static void main(String[] args) {
		JSONArray skills = new JSONArray();
		skills.add("Communication");
		skills.add("Java");
		JSONObject employee = new JSONObject();
		employee.put("-empId", "001");
		employee.put("lastName", "Mardari");
		employee.put("firstName", "Alexandru");
		employee.put("birthDate", "16.06.1998");
		employee.put("position", "Department Manager");
		employee.put("skills", skills);
		employee.put("managerId", "0");
        JSONObject department = new JSONObject();
        department.put("-name", "Development");
        department.put("-depId", "1");
        department.put("employee", employee);
        JSONObject company = new JSONObject();
        company.put("department",department);
        JSONObject Json = new JSONObject();
        Json.put("company", company);
        try (FileWriter file = new FileWriter("./department1.json")) {

            file.write(Json.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(Json);


    }
}
