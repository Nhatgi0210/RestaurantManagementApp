package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Employee;
import models.FoodItem;

public class FoodItemDAO implements DAOinterface<FoodItem> {
	public static FoodItemDAO getDAO() {
		return new FoodItemDAO();
	}

	@Override
	public ArrayList<FoodItem> getAll(int idRestaurant) {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		try {
			String query = "SELECT fi.*, fc.name AS categoryName FROM food_item fi JOIN food_category fc ON fi.idCategory = fc.id WHERE fc.idRestaurant = ? ORDER BY fi.idCategory ASC, fi.name ASC";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idRestaurant);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				FoodItem foodItem = FoodItem.getFromResultSet(rs);
				foodItems.add(foodItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodItems;

	}

	public ArrayList<FoodItem> getByIdCategory(int id) {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		try {
			String query = "SELECT fi.*, fc.name AS categoryName FROM food_item fi JOIN food_category fc ON fi.idCategory = fc.id WHERE fi.idCategory = ? ORDER BY fi.name ASC";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				FoodItem foodItem = FoodItem.getFromResultSet(rs);
				foodItems.add(foodItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodItems;
	}

	public ArrayList<FoodItem> getByWord(int idRestaurant, String keyword) {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		try {
			String query = "SELECT fi.*,fc.name AS categoryName FROM food_item fi JOIN food_category fc ON fi.idCategory = fc.id   WHERE fc.idRestaurant = ? and fi.name LIKE N'%"
					+ keyword + "%'";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idRestaurant);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				FoodItem foodItem = FoodItem.getFromResultSet(rs);
				foodItems.add(foodItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodItems;
	}

	@Override
	public int add(FoodItem t) {
		String query = "INSERT INTO `food_item`( `name`, `imagePath`, `unitName`, `unitPrice`, `idCategory`) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setNString(1, t.getName());
			statement.setString(2, t.getImagePath());
			statement.setNString(3, t.getUnitName());
			statement.setInt(4, t.getUnitPrice());
			statement.setInt(5, t.getIdCategory());
			int row = statement.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(FoodItem t) {
		String query = "UPDATE `food_item` SET `name`=?,`imagePath`=?,`unitName`=?,`unitPrice`=?,`idCategory`=? WHERE id = ?";
		int row = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setNString(1, t.getName());
			statement.setString(2, t.getImagePath());
			statement.setNString(3, t.getUnitName());
			statement.setInt(4, t.getUnitPrice());
			statement.setInt(5, t.getIdCategory());
			statement.setInt(6, t.getId());
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public void delete(FoodItem t) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("DELETE FROM `food_item` WHERE `food_item`.`id` = ?");
		statement.setInt(1, t.getId());
		statement.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("DELETE FROM `food_item` WHERE `food_item`.`id` = ?");
		statement.setInt(1, id);
		statement.executeUpdate();
	}

	@Override
	public FoodItem get(int id) throws SQLException {
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM `food_item` WHERE `id` = " + id;
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			FoodItem foodItem = FoodItem.getFromResultSet(rs);
			return foodItem;
		}
		return null;
	}

}
