package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.FoodCategory;

public class FoodCategoryDAO implements DAOinterface<FoodCategory>{
	
	@Override
	public ArrayList<FoodCategory> getAll() throws SQLException {
		ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_category`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            FoodCategory foodCategory = FoodCategory.getFromResultSet(rs);
            foodCategories.add(foodCategory);
        }
        return foodCategories;
	}

	@Override
	public FoodCategory get(int id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM `food_category` WHERE `id` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            FoodCategory foodCategory = FoodCategory.getFromResultSet(rs);
            return foodCategory;
        }
        return null;
    }

	@Override
	public void add(FoodCategory t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("FoodCategory rỗng");
	        }
	        String query = "INSERT INTO `food_category` (`name`) VALUES (?)";

	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setNString(1, t.getName());
	        int row = statement.executeUpdate();
	}

	@Override
	public void update(FoodCategory t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("FoodCategory rỗng");
	        }
	        String query = "UPDATE `food_category` SET `name` = ?, WHERE `id` = ?";

	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setNString(1, t.getName());
	        statement.setInt(2, t.getId());
	        int row = statement.executeUpdate();
	}

	@Override
	public void delete(FoodCategory t) throws SQLException {
		  PreparedStatement statement = conn.prepareStatement("DELETE FROM `food_category` WHERE `id` = ?");
	        statement.setInt(1, t.getId());
	        statement.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		
		PreparedStatement statement = conn.prepareStatement("DELETE FROM `food_category` WHERE `id` = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
	}

}
