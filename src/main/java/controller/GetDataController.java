package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import DAO.AreaDAO;
import DAO.EmployeeDAO;
import DAO.FoodCategoryDAO;
import DAO.FoodItemDAO;
import DAO.OrderDAO;
import DAO.RestaurenDAO;
import DAO.TableDAO;
import models.Area;
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Order;
import models.Restaurant;
import models.Table;

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
	public static String getAllType(int idRestaurant) {
		ArrayList<FoodCategory> foodTypes = FoodCategoryDAO.getDAO().getAll(idRestaurant);
		Gson gson = new Gson();
		String foodTypesJson = gson.toJson(foodTypes);
		return foodTypesJson;
	}
	public static String getAllProduct(int idRestaurant) {
		ArrayList<FoodItem> foodItems = FoodItemDAO.getDAO().getAll(idRestaurant);
		Gson gson = new Gson();
		String foodItemsJson = gson.toJson(foodItems);
		return foodItemsJson;		
	}
	public static String getProductByType(int idType) {
		ArrayList<FoodItem> foodItems = FoodItemDAO.getDAO().getByIdCategory(idType);
		Gson gson = new Gson();
		String FoodItemsJson = gson.toJson(foodItems);
		return FoodItemsJson;
	}
	public static String getProductByWord(int idRestaurant, String keyword) {
		ArrayList<FoodItem> foodItems = FoodItemDAO.getDAO().getByWord(idRestaurant, keyword);
		Gson gson = new Gson();
		String FoodItemsJson = gson.toJson(foodItems);
		return FoodItemsJson;
	}
	public static String getAllArea(int idRestaurant) {
		ArrayList<Area> areas = AreaDAO.getDAO().getAll(idRestaurant);
		Gson gson = new Gson();
		String areaJson = gson.toJson(areas);
		return areaJson;
	}
	public static String getAllTable(int idRestaurant) {
		ArrayList<Table> tables = TableDAO.getDAO().getAll(idRestaurant);
		Gson gson = new Gson();
		String tableJson = gson.toJson(tables);
		return tableJson;
	}
	public static String getTableByArea(int idArea) {
		ArrayList<Table> tables = TableDAO.getDAO().getByArea(idArea);
		Gson gson = new Gson();
		String tableJson = gson.toJson(tables);
		return tableJson;
	}
	public static String searchOrder(int idTable) {
		Order order = OrderDAO.getDao().search(idTable);
		Gson gson = new Gson();
		String orderJson = gson.toJson(order);
		return orderJson;
	}
}
