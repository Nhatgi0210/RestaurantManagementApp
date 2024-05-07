package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class FoodItem implements Model{
	private int id;
	private String name, description, urlImage, unitName;
	private int unitPrice, idCategory;
	DecimalFormat formatter = new DecimalFormat("###,###,###");
	
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


	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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

	public static FoodItem getFromResultSet(ResultSet rs) throws SQLException {
        FoodItem f = new FoodItem();
        f.setId(rs.getInt("id"));
        f.setName(rs.getNString("name"));
        f.setDescription(rs.getNString("description"));
        f.setUrlImage(rs.getNString("urlImage"));
        f.setUnitName(rs.getNString("unitName"));
        f.setUnitPrice(rs.getInt("unitPrice"));
        f.setIdCategory(rs.getInt("idCategory"));
        return f;
    }

	@Override
	public String toStringvn() {
		// TODO Auto-generated method stub
		return String.format("%s (%s)", name, formatter.format(unitPrice));
	}

	@Override
	public Object[] toRowTable() {
		// TODO Auto-generated method stub
		return new Object[] {
				id,name,description, urlImage, unitName,unitPrice, idCategory 
		};
	}

}
