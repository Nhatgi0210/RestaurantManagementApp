package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import others.EmployeePermission;

public class Area {
	private int id, idRestaurant;
	private String name;
	
	public Area() {
	}

	public Area(int id, int idRestaurant, String name) {
		super();
		this.id = id;
		this.idRestaurant = idRestaurant;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Area getFromResultSet(ResultSet rs) throws SQLException {
       Area area = new Area();
        area.setId(rs.getInt("id"));
        area.setName(rs.getNString("name"));
        area.setIdRestaurant(rs.getInt("idRestaurant"));
        return area;
    }
	
	
}
