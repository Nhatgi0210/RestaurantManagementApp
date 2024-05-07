package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOinterface <T> {
	Connection conn =  ConnectToDatabase.getInstance().getConnection();
	
	public  ArrayList<T> getAll() throws SQLException;

    public  T get(int id) throws SQLException;

    public  void add(T t) throws SQLException;

    public  void update(T t) throws SQLException;

    public  void delete(T t) throws SQLException;

    public  void deleteById(int id) throws SQLException;
}
