package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class FoodItem {
	private int id;
	private String name, description, unitName,categoryName;
	private int unitPrice, idCategory;
	private  byte[] image;
//	DecimalFormat formatter = new DecimalFormat("###,###,###");
	
	public FoodItem() {
		
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public int getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getIdCategory() {
		return idCategory;
	}


	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", description=" + description 
				+ ", unitName=" + unitName + ", categoryName=" + categoryName + ", unitPrice=" + unitPrice
				+ ", idCategory=" + idCategory + "]";
	}


	public static FoodItem getFromResultSet(ResultSet rs) throws SQLException {
        FoodItem f = new FoodItem();
        f.setId(rs.getInt("id"));
        f.setName(rs.getNString("name"));
        f.setDescription(rs.getNString("description"));
        f.setImage(rs.getBytes("image"));
        f.setUnitName(rs.getNString("unitName"));
        f.setUnitPrice(rs.getInt("unitPrice"));
        f.setIdCategory(rs.getInt("idCategory"));
        f.setCategoryName(rs.getNString("categoryName"));
        return f;
    }


	
}
