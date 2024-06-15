package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.FoodItem;
import models.Table;

public class TableDAO implements DAOinterface<Table>{

	public static TableDAO getDAO() {
		return new TableDAO();
	}
	@Override
	public ArrayList<Table> getAll(int idRestaurant) {
		ArrayList<Table> tables = new ArrayList<>();
        try {
			String query = "SELECT fi.*, fc.name AS Area FROM `table` fi JOIN area fc ON fi.idArea = fc.id WHERE fc.idRestaurant = ? ORDER BY fi.idArea ASC, fi.name ASC";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idRestaurant);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
			    Table table = Table.getFromResultSet(rs);
			    tables.add(table);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tables;
	}
	public ArrayList<Table> getByArea(int idArea){
		ArrayList<Table> tables = new ArrayList<>();
		try {
			String query = "SELECT fi.*, fc.name AS Area FROM `table` fi JOIN area fc ON fi.idArea = fc.id WHERE fi.idArea= ? ORDER BY fi.idArea ASC, fi.name ASC";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idArea);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				 Table table = Table.getFromResultSet(rs);
				    tables.add(table);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	@Override
	public Table get(int id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Table table = Table.getFromResultSet(rs);
            return table;
        }
        return null;
	}

	@Override
	public int add(Table t) {
		int row = 0;
        try {
			String query = "INSERT INTO `table`(`name`,`idArea`) VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setNString(1, t.getName());
			stmt.setInt(2, t.getArea().getId());
			row = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int update(Table t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Table rỗng");
	        }
	        String query = "UPDATE `table` SET `name` = ? , `status` = ? WHERE `table`.`id` = ?";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setNString(1, t.getName());
	        stmt.setNString(2, t.getStatus().getId());
	        stmt.setInt(3, t.getId());
	        int row = stmt.executeUpdate();
			return row;
	}

	@Override
	public void delete(Table t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`id` = ?");
		stmt.setInt(1, t.getId());
		stmt.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}

	public int isExist(String nameTable, int idRestaurant) {
		try {
			String query = "SELECT fi.* FROM `table` fi JOIN area fc ON fi.idArea = fc.id WHERE fc.idRestaurant = ? and fi.name = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idRestaurant);
			statement.setNString(2, nameTable);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return 1;
			else return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
public int updateStatus(int idTable) {
		
		try {
			String query = "UPDATE `table` SET `status`=? WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "serving");
			statement.setInt(2, idTable);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int updateStatus(int a,int idTable) {
		
		try {
			String query = "UPDATE `table` SET `status`=? WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "free");
			statement.setInt(2, idTable);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
