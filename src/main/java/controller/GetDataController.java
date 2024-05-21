package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import DAO.EmployeeDAO;
import DAO.RestaurenDAO;
import models.Employee;
import models.Restaurant;

public class GetDataController {
	public static String getRestaurentById(int id) {
		Restaurant restaurant = RestaurenDAO.getDao().get(id);
		Gson gson = new Gson();
		String restaurantJSon = gson.toJson(restaurant);
		return restaurantJSon;
	}
	public static int getId(String name, String address) {
		Restaurant restaurant = new Restaurant(name, address);
		return RestaurenDAO.getDao().getID(restaurant);
	}
	public static String getAllEmployee(int idRestaurant) {
		try {
			ArrayList<Employee> rs = EmployeeDAO.getDAO().getByIdRestaurant(idRestaurant);
			Gson gson = new Gson();
			String result = gson.toJson(rs);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getEmployeeByPos(int idRestaurant, String pos) {
		try {
			ArrayList<Employee> rs = EmployeeDAO.getDAO().getByPos(idRestaurant, pos);
			Gson gson = new Gson();
			String result = gson.toJson(rs);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getEmployeeByWord(int idRestaurant,String keyword) {
		try {
			ArrayList<Employee> rs = EmployeeDAO.getDAO().getByWord(idRestaurant, keyword);
			Gson gson = new Gson();
			String result = gson.toJson(rs);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
