package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import others.OrderStatus;



public class Order implements Model {

	private int id, idEmployee, idTable;
    private OrderStatus status;
    private Timestamp orderDate, payDate;
    private int totalAmount;
    private Employee employee;
    private Table table;
    
    public Order() {
        status = OrderStatus.UNPAID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

   

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.idEmployee = employee.getId();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
        this.idTable = table.getId();
    }

    public int getFinalAmount() {
        return totalAmount;
    }

    public static Order getFromResultSet(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setIdEmployee(rs.getInt("idEmployee"));
        o.setIdTable(rs.getInt("idTable"));
        o.setStatus(OrderStatus.getById(rs.getNString("status")));
        o.setOrderDate(rs.getTimestamp("orderDate"));
        o.setPayDate(rs.getTimestamp("payDate"));
        o.setTotalAmount(rs.getInt("totalAmount"));
        return o;
    }


	@Override
	public Object[] toRowTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
