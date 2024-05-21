package test;

import java.util.ArrayList;

import DAO.EmployeeDAO;
import DAO.RestaurenDAO;
import models.Employee;
import models.Restaurant;

public class testEveryThing {
	public static void main(String[] args) {
		Employee employee = null;
		employee = new Employee();
		employee.setName("nhat");
		System.out.println(employee.toString());
	}
}
