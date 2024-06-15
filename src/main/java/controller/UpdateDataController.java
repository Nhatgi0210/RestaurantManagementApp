package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DAO.AreaDAO;
import DAO.EmployeeDAO;
import DAO.FoodCategoryDAO;
import DAO.FoodItemDAO;
import DAO.OrderDAO;
import DAO.OrderItemDAO;
import DAO.RestaurenDAO;
import DAO.TableDAO;
import models.Employee;
import models.FoodItem;
import models.Order;
import models.OrderItem;
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
	public static int newOrder(String orderJson) {
		Gson gson = new Gson();
		Order order = gson.fromJson(orderJson, Order.class);
		return OrderDAO.getDao().newOrder(order);
	}
	public static int addOrderItem(String listJson) {
		Gson gson = new Gson();
		ArrayList<OrderItem> orderItems = gson.fromJson(listJson,new TypeToken<ArrayList<OrderItem>>() {
		}.getType() );
		return OrderItemDAO.getDAO().addOrderItems(orderItems);
	}
	public static int updateStatus(int idTable) {
		return TableDAO.getDAO().updateStatus(idTable);
	}
	public static int pay(int idOrder, int idTable) {
		return OrderDAO.getDao().pay(idOrder, idTable);
	}
}
