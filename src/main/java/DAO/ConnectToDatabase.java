package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectToDatabase {
	private static ConnectToDatabase instance = null;
	private Connection connection = null;
	
	
	public ConnectToDatabase() {
		 try {
	            String connectProperty = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	            String host = "127.0.0.1",
	                   user = "root",
	                   password = "",
	                   name = "restaurant";
	            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	            String url = "jdbc:mysql://127.0.0.1/" + name;	           
	            this.connection = DriverManager.getConnection(url, user, password);
	            System.out.println("Kết nối cơ sở dữ liệu thành công!");
	        } catch (SQLException e) {
	            System.out.println("Kết nối cơ sở dữ liệu thất bại:");
	            System.out.println(e.toString());
	            System.exit(0);
	        }

	}

	public Connection getConnection() {
		return connection;
	}

	public static ConnectToDatabase getInstance() {
		 if (instance == null) {
	            instance = new ConnectToDatabase();
	        }
	     return instance;
	}
	public static void main(String[] args) {
		new ConnectToDatabase();
	}
}
