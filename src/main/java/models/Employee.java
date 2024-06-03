package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import others.EmployeePermission;

public class Employee implements Model{

	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", phoneNumber=" + phoneNumber  + ", permissionId=" + permissionId
				+ ", permission=" + permission + ", startDate=" + startDate ;
	}

	private int id,idRestaurant;
    private String username, password, name, phoneNumber;
    private int permissionId;
    private EmployeePermission permission;
    private Date startDate;
    
    
    

	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public Employee(String username, String password, String name, String phoneNumber,
			EmployeePermission permission) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.permission = permission;
	}


	public Employee() {
    }

	
    

	public int getIdRestaurant() {
		return idRestaurant;
	}


	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
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
        e.setPermission(EmployeePermission.getByName(rs.getNString("permissionName")));
        e.setIdRestaurant(rs.getInt("idRestaurant"));
//        e.setSalary(rs.getInt("salary"));
        return e;
    }

    @Override
    public Object[] toRowTable() {
        return new Object[]{
            id, name, username, password,
            phoneNumber, startDate, permission.getName(),
        };
    }
	
}	
