package test;

import java.util.ArrayList;

import com.google.gson.Gson;

import models.Employee;
import others.EmployeePermission;

public class testGSON {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		ArrayList<Employee> a = new ArrayList<Employee>();
		a.add(new Employee("nhat", "12345"));
		a.add(new Employee("nhat", "12345"));
		a.add(new Employee("nhat", "12345"));
		String json = gson.toJson(a);
		ArrayList<Employee> employees = gson.fromJson(json, ArrayList.class);
		for(int i = 0; i < 3; i++)
			System.out.println(a.get(i).toString());
	}
}
