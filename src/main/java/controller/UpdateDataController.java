package controller;

import com.google.gson.Gson;

import DAO.EmployeeDAO;
import DAO.RestaurenDAO;
import models.Restaurant;

public class UpdateDataController {
	public static int setIdRestaurant(int idEmloyee, int idRestaurant) {
		return EmployeeDAO.getDAO().updateIdRestaurant(idEmloyee, idRestaurant);
	}
	public static void deleteByUsername(String username) {
		EmployeeDAO.getDAO().deleteByUsername(username);
	}
}
