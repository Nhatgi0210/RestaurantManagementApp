package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Area;

public class AreaDAO implements DAOinterface<Area> {

	public static AreaDAO getDAO() {
		return new AreaDAO();
	}
	@Override
	public ArrayList<Area> getAll(int idRestaurant) {
		ArrayList<Area> areas = new ArrayList<>();
		try {
			String query = "SELECT * FROM `area` WHERE idRestaurant = " + idRestaurant;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				Area area = Area.getFromResultSet(rs);
				areas.add(area);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}

	@Override
	public Area get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Area t) {
		int result = 0;
		String query = "INSERT INTO `area`(`id`, `name`, `idRestaurant`) VALUES (?,?,?)";
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, t.getId());
			statement.setNString(2, t.getName());
			statement.setInt(3, t.getIdRestaurant());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int update(Area t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Area t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int deleteByID(int id) {
		int result = 0;
		try {
			String query = "DELETE FROM `area` WHERE id = " + id;
			Statement statement = conn.createStatement();
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
