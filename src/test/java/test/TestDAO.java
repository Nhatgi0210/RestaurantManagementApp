package test;

import java.sql.SQLException;

import DAO.EmployeeDAO;
import DAO.RestaurenDAO;
import models.Restaurant;

public class TestDAO {
	public static void main(String[] args) throws SQLException {
		Restaurant restaurant = RestaurenDAO.getDao().get(1);
		System.out.println(restaurant.toString());
	}
}
