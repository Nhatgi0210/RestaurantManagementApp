package controller;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.EmployeeDAO;
import models.Employee;

public class SignupController {

	public SignupController() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static int signup(String employeeJson) {
		Gson gson = new Gson();
		Employee e = gson.fromJson(employeeJson, Employee.class);
		
		int rs;
		rs = EmployeeDAO.getDAO().add(e);
		return rs;
		
		
	}
}
