package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import others.EmployeePermission;

public class Employee implements Model{

	public int id;
    public String username, password, name, phoneNumber;
    public int permissionId;
    public EmployeePermission permission;
    public Date startDate;
    public int salary;
    
    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public EmployeePermission getPermission() {
        return permission;
    }

    public void setPermission(EmployeePermission permission) {
        this.permission = permission;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.max(0, salary);
    }

	@Override
	public String toStringvn() {
		// TODO Auto-generated method stub
		return name;
	}
	public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static Employee getFromResultSet(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setUsername(rs.getNString("username"));
        e.setPassword(rs.getNString("password"));
        e.setName(rs.getNString("name"));
        e.setPhoneNumber(rs.getNString("phoneNumber"));
        e.setStartDate(rs.getDate("startDate"));
        e.setPermission(EmployeePermission.getById(rs.getNString("permissionID")));
//        e.setSalary(rs.getInt("salary"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            id, name, username, password,
            phoneNumber, startDate, permission.getName(),
            salary
        };
    }
	
}	
