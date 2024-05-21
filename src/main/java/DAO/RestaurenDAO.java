package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import models.Employee;
import models.Restaurant;

public class RestaurenDAO implements DAOinterface<Restaurant> {

	public static RestaurenDAO getDao() {
		return new RestaurenDAO();
	};

	public int getID(Restaurant t) {
		String query = "SELECT `id` FROM `restaurant` WHERE name = ? AND address= ?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setNString(1, t.getName());
			statement.setNString(2, t.getAddress());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public ArrayList<Restaurant> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant get(int id)  {
		Statement statement;
		try {
			statement = conn.createStatement();
			String query = "SELECT * FROM `restaurant` WHERE id = " + id;
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				Restaurant restaurant = Restaurant.getFromResultSet(rs);
				return restaurant;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return null;
	}

	@Override
	public int add(Restaurant t){
		String query = "INSERT INTO `restaurant`( `name`, `address`) VALUES (?,?)";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setNString(1, t.getName());
			statement.setNString(2, t.getAddress());
			// TODO Auto-generated method stub
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(Restaurant t) throws SQLException {
	       String query = 
	}

	@Override
	public void delete(Restaurant t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
