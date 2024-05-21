package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Employee;
import others.EmployeePermission;

public class EmployeeDAO implements DAOinterface<Employee>{
	public static EmployeeDAO getDAO() {
		return new EmployeeDAO();
	}
	public int updateIdRestaurant(int id, int idRestauran) {
		String query = "UPDATE `employee` SET `idRestaurant`=? WHERE id = ?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, idRestauran);
			statement.setInt(2, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public ArrayList<Employee> getAll() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<>();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM employee";
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()){
			Employee employee = Employee.getFromResultSet(rs);
			employees.add(employee);
		}
		return employees;
	}

	public ArrayList<Employee> getByIdRestaurant(int id) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<>();
		Statement statement = conn.createStatement();
		String query = "SELECT * FROM employee WHERE idRestaurant = "+id;
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()){
			Employee employee = Employee.getFromResultSet(rs);
			employees.add(employee);
		}
		return employees;
	}
	public ArrayList<Employee> getByPos(int id, String permission) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM employee WHERE idRestaurant = ? and permissionName = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		statement.setNString(2, permission);
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			Employee employee = Employee.getFromResultSet(rs);
			employees.add(employee);
		}
		return employees;
	}
	public ArrayList<Employee> getByWord(int id, String keyword) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM employee WHERE idRestaurant = ? and name LIKE N'%"+keyword+"%'";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			Employee employee = Employee.getFromResultSet(rs);
			employees.add(employee);
		}
		return employees;
	}
	@Override
	public Employee get(int id) throws SQLException {
		 Statement statement = conn.createStatement();
	        String query = "SELECT * FROM employee WHERE employee.id = " + id;
	        ResultSet rs = statement.executeQuery(query);
	        if (rs.next()) {
	            Employee employee = Employee.getFromResultSet(rs);
	            return employee;
	        }
	        return null;
	}

	@Override
	public int add(Employee t){
		 	int row = 0;
		 	
	        String query = "INSERT INTO `employee` (`username`, `password`, `name`, `phoneNumber`, `startDate`, `permissionName`,idRestaurant)"
	                + " VALUES (?, ?, ?, ?, current_timestamp(), ?,?)";
	        PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(query);
				stmt.setNString(1, t.getUsername());
				stmt.setNString(2, t.getPassword());
				stmt.setNString(3, t.getName());
				stmt.setNString(4, t.getPhoneNumber());
				stmt.setNString(5, t.getPermission().getName());
				stmt.setInt(6, t.getIdRestaurant());
				row = stmt.executeUpdate();
			} catch (SQLException e) {
				
				System.out.println("Lỗi CSDL");
				return 0;
			}
			return row;
	        
	       
	}

	@Override
	public void update(Employee t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Employee rong");
	        }
	        String query = "UPDATE `employee` SET `username` = ?, `password` = ?, `name` = ?, `phoneNumber` = ?, `permission` = ?, `salary` = ? WHERE `id` = ?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setNString(1, t.getUsername());
	        stmt.setNString(2, t.getPassword());
	        stmt.setNString(3, t.getName());
	        stmt.setNString(4, t.getPhoneNumber());
	        stmt.setNString(5, t.getPermission().getId());
//	        stmt.setInt(6, t.getSalary());
	        stmt.setInt(7, t.getId());
	        stmt.executeUpdate();
	}

	@Override
	public void delete(Employee t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `employee` WHERE `employee`.`id` = ?");
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();

	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `employee` WHERE `employee`.`id` = ?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}
	public void deleteByUsername(String username) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("DELETE FROM `employee` WHERE `employee`.`username` = ?");
			stmt.setString(1, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Employee login(String userName, String password) {
        try {
			String query = "SELECT * FROM `employee` WHERE username = ? and password = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setNString(1, userName);
			statement.setNString(2, password);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
			    Employee employee = Employee.getFromResultSet(rs);
			 
			    return employee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	public Employee findByUsername(String userName) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM employee WHERE employee.username = '" + userName + "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Employee employee = Employee.getFromResultSet(rs);
            return employee;
        }
        return null;
    }
}
