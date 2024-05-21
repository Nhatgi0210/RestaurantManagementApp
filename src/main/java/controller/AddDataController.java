package controller;

import com.google.gson.Gson;

import DAO.RestaurenDAO;
import models.Restaurant;

public class AddDataController {
	public static void addRestaurant(String name, String address) {
		Restaurant restaurant = new Restaurant(name, address);
		RestaurenDAO.getDao().add(restaurant);
	}
}
