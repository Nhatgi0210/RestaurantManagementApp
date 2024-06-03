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
			String query = "SELECT fi.*,fc.name AS categoryName FROM food_item fi JOIN food_category fc ON fi.idCategory = fc.id   WHERE fc.idRestaurant = ? and fi.name LIKE N'%"+keyword+"%'";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idRestaurant);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
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
	public int add(FoodItem t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Food item rỗng");
	        }
	        String query = "INSERT INTO `food_item` (`name`, `description`, `urlImage`, `unitName`, `unitPrice`, `idCategory`) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setNString(1, t.getName());
	        statement.setNString(2, t.getDescription());
//	        statement.setNString(3, t.getUrlImage());
	        statement.setNString(4, t.getUnitName());
	        statement.setInt(5, t.getUnitPrice());
	        statement.setInt(6, t.getIdCategory());
	        int row = statement.executeUpdate();
	        return row;
	}

	@Override
	public void update(FoodItem t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Food item rỗng");
	        }
	        String query = "UPDATE `food_item` SET `name` = ?, `description` = ?, `urlImage` = ?, `unitName` = ?, `unitPrice` = ?, `idCategory` = ? WHERE `food_item`.`id` = ?";
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setNString(1, t.getName());
	        statement.setNString(2, t.getDescription());
//	        statement.setNString(3, t.getUrlImage());
	        statement.setNString(4, t.getUnitName());
	        statement.setInt(5, t.getUnitPrice());
	        statement.setInt(6, t.getIdCategory());
	        statement.setInt(7, t.getId());
	        int row = statement.executeUpdate();
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
