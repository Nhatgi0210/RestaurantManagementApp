package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodCategory implements Model{

	@Override
	public String toString() {
		return "FoodCategory [id=" + id + ", name=" + name + "]";
	} 

	private int id;
	private String name;
	
	
	public FoodCategory() {
		// TODO Auto-generated constructor stub
	}

	public FoodCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public static FoodCategory getFromResultSet(ResultSet rs) throws SQLException {
        FoodCategory fc = new FoodCategory();
        fc.setId(rs.getInt("id"));
        fc.setName(rs.getNString("name"));
        return fc;
    }
	
	

	@Override
	public Object[] toRowTable() {
		// TODO Auto-generated method stub
		return new Object[]{
	            this.id, this.name
	        };
	}

}
