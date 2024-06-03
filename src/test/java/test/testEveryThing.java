package test;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DAO.EmployeeDAO;
import DAO.RestaurenDAO;
import controller.GetDataController;
import models.Employee;
import models.FoodCategory;
import models.FoodItem;
import models.Restaurant;

public class testEveryThing {
	public static void main(String[] args) {
		String typesJson = GetDataController.getAllProduct(1);
		Gson gson = new Gson();
		ArrayList<FoodItem> products = gson.fromJson(typesJson, new TypeToken<ArrayList<FoodItem>>() {}.getType());
		for(FoodItem foodItem : products) {
			System.out.println(foodItem.toString());
		}
	}															
}
