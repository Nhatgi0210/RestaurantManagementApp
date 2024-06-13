package controller;

import com.google.gson.Gson;

import DAO.AreaDAO;
import DAO.FoodCategoryDAO;
import DAO.FoodItemDAO;
import DAO.RestaurenDAO;
import DAO.TableDAO;
import models.Area;
import models.FoodCategory;
import models.FoodItem;
import models.Restaurant;
import models.Table;

public class AddDataController {
	public static void addRestaurant(String name, String address) {
		Restaurant restaurant = new Restaurant(name, address);
		RestaurenDAO.getDao().add(restaurant);
	}
	public static void addFoodCategory(int idRetaurant,String type) {
		FoodCategoryDAO.getDAO().addByIdrestaurant(idRetaurant, type);
	}
	public static int addFoodItem(String foodJson) {
		Gson gson = new Gson();
		FoodItem foodItem = gson.fromJson(foodJson, FoodItem.class);
		return FoodItemDAO.getDAO().add(foodItem);
	}
	public static int addArea(String areaJson) {
		Gson gson = new Gson();
		Area area = gson.fromJson(areaJson, Area.class);
		return AreaDAO.getDAO().add(area);
	}
	public static int addTable(String tableJson) {
		Gson gson = new Gson();
		Table table = gson.fromJson(tableJson, Table.class);
		return TableDAO.getDAO().add(table);
	}
}
