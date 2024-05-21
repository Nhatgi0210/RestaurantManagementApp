package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Restaurant {
	private int id;
	private String name, address;
	public Restaurant() {
	}
	
	public Restaurant(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Restaurant(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	public static Restaurant getFromResultSet(ResultSet rs) {
		Restaurant restaurant = new Restaurant();
		try {
			restaurant.setId(rs.getInt("id"));
			restaurant.setName(rs.getNString("name"));
			restaurant.setAddress(rs.getNString("address"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return restaurant;
		
	}
}
