package controller;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.EmployeeDAO;
import models.Employee;

public class LoginController {
	public static String Login(String employeeJson) {
		Gson gson = new Gson();
		Employee employeeInput = gson.fromJson(employeeJson, Employee.class);
		Employee employee = EmployeeDAO.getDAO().login(employeeInput.getUsername(),employeeInput.getPassword());
		if(employee == null) return "0";
		String employeeResult = gson.toJson(employee);
		return employeeResult;
	}
}
