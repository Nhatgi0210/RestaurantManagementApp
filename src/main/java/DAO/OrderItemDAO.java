package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.FoodItemDAO;
import models.OrderItem;

public class OrderItemDAO implements DAOinterface<OrderItem> {

	public static OrderItemDAO getDAO() {
		return new OrderItemDAO();
	}
	 FoodItemDAO foodItemDao = new FoodItemDAO();
	public ArrayList<OrderItem> getAll() throws SQLException {
		 ArrayList<OrderItem> orderItems = new ArrayList<>();
	        Statement statement = conn.createStatement();
	        String query = "SELECT * FROM `order_item` ORDER BY `order_item`.`idOrder` DESC, `order_item`.`quantity` DESC";
	        ResultSet rs = statement.executeQuery(query);
	        while (rs.next()) {
	            OrderItem orderItem = OrderItem.getFromResultSet(rs);
	            orderItem.setFoodItem(foodItemDao.get(orderItem.getIdFoodItem()));
	            orderItems.add(orderItem);
	        }
	        return orderItems;
	}

	@Override
	public OrderItem get(int id) throws SQLException {
		return null;
		
	}

	@Override
	public int add(OrderItem t) {
		int row = 0;
        try {
			String query = "INSERT INTO `order_item` (`idOrder`, `idFoodItem`, `quantity`, `unitPrice`) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, t.getIdOrder());
			stmt.setInt(2, t.getIdFoodItem());
//        stmt.setInt(3, t.getIdTopping());
			stmt.setInt(3, t.getQuantity());
			stmt.setInt(4, t.getFoodPrice());
//        stmt.setInt(6, t.getToppingPrice());
//        stmt.setNString(7, t.getNote());
//			stmt.setInt(8, t.getQuantity());
			row = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return row;
	}

	@Override
	public int update(OrderItem t) throws SQLException {
		int row = 0;
		if (t == null) {
            throw new SQLException("Order Item rỗng");
        }
        String query = "UPDATE `order_item` SET  `quantity` = ?, `foodPrice` = ?, `toppingPrice` = ?, `note` = ? WHERE `idOrder` = ? AND `idFoodItem` = ? AND `idTopping` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getQuantity());
        stmt.setInt(2, t.getFoodPrice());
//        stmt.setInt(3, t.getToppingPrice());
//        stmt.setNString(4, t.getNote());
        stmt.setInt(5, t.getIdOrder());
        stmt.setInt(6, t.getIdFoodItem());
//        stmt.setInt(7, t.getIdTopping());
        row = stmt.executeUpdate();
        return row;
	}

	@Override
	public void delete(OrderItem t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Order Item rỗng");
	        }
	        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order_item` WHERE `idOrder` = ? AND `idFoodItem` = ? AND `idTopping` = ?");
	        stmt.setInt(1, t.getIdOrder());
	        stmt.setInt(2, t.getIdFoodItem());
//	        stmt.setInt(3, t.getIdTopping());
	        stmt.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<OrderItem> getAll(int idRestaurant) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public int addOrderItems(ArrayList<OrderItem> orderItems) {
		for(OrderItem orderItem : orderItems) {
			add(orderItem);
		}
		return 1;
	}

}
