package controller;

import DAO.TableDAO;

public class CheckDataController {
	public static int checkTableExist(String tableName, int idRestaurant) {
		return TableDAO.getDAO().isExist(tableName, idRestaurant);
	}
}
