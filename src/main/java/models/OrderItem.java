package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItem implements Model{

	 	private int idOrder,  quantity;
	    private FoodItem foodItem;

	    public OrderItem() {
	        quantity = 1;
	    }

	    public int getIdOrder() {
	        return idOrder;
	    }

	    public void setIdOrder(int idOrder) {
	        this.idOrder = idOrder;
	    }

	    public int getIdFoodItem() {
	        return foodItem.getId();
	    }

	    public String getFoodName() {
	    	return foodItem.getName();
	    }
	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        if (quantity >= 0) {
	            this.quantity = quantity;
	        } else {
	            this.quantity = 0;
	        }
	    }

	    public int getFoodPrice() {
	        return foodItem.getUnitPrice();
	    }

	  

	    public FoodItem getFoodItem() {
	        return foodItem;
	    }

	    public void setFoodItem(FoodItem foodItem) {
	        this.foodItem = foodItem;
	    }

	   
	   

	    public int getAmount() {
	        return quantity * (foodItem.getUnitPrice());
	    }

	    public static OrderItem getFromResultSet(ResultSet rs) throws SQLException {
	        OrderItem oi = new OrderItem();
	        oi.foodItem.setId(rs.getInt("idFoodItem"));
	        oi.setIdOrder(rs.getInt("idOrder"));
	        oi.setQuantity(rs.getInt("quantity"));
	        oi.foodItem.setUnitPrice(rs.getInt("foodPrice"));
	        return oi;
	    }




	@Override
	public Object[] toRowTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
