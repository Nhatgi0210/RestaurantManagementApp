package controller;

import java.sql.SQLException;

import com.google.gson.Gson;

import DAO.AreaDAO;
import DAO.EmployeeDAO;
import DAO.FoodCategoryDAO;
import DAO.FoodItemDAO;
import DAO.RestaurenDAO;
import models.Employee;
import models.FoodItem;
import models.Restaurant;

public class UpdateDataController {
	public static int setIdRestaurant(int idEmloyee, int idRestaurant) {
		return EmployeeDAO.getDAO().updateIdRestaurant(idEmloyee, idRestaurant);
	}
	public static int deleteByUsername(String username) {
		return EmployeeDAO.getDAO().deleteByUsername(username);
	}
	public static int updateNamePhonePos(String employeeJson) {
		Gson gson = new Gson();
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		return EmployeeDAO.getDAO().updateNamePhonePos(employee);
	}
	public static void deleteFoodCategory(int idType) {
		try {
			FoodCategoryDAO.getDAO().deleteById(idType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int updateProduct(String productJson) {
		Gson gson = new Gson();
		FoodItem foodItem = gson.fromJson(productJson, FoodItem.class);
		return FoodItemDAO.getDAO().update(foodItem);
	}
	public static int deleteArea(int idArea) {
		return AreaDAO.getDAO().deleteByID(idArea);
	}
}
