package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Table;

public class TableDAO implements DAOinterface<Table>{

	@Override
	public ArrayList<Table> getAll() throws SQLException {
		ArrayList<Table> tables = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Table table = Table.getFromResultSet(rs);
            tables.add(table);
        }
        return tables;
	}

	@Override
	public Table get(int id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM `table` WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Table table = Table.getFromResultSet(rs);
            return table;
        }
        return null;
	}

	@Override
	public void add(Table t) throws SQLException {
		if (t == null) {
            throw new SQLException("Table rỗng");
        }
        String query = "INSERT INTO `table` (`name`, `status`) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getName());
        stmt.setNString(2, t.getStatus().getId());
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(Table t) throws SQLException {
		 if (t == null) {
	            throw new SQLException("Table rỗng");
	        }
	        String query = "UPDATE `table` SET `name` = ? , `status` = ? WHERE `table`.`id` = ?";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setNString(1, t.getName());
	        stmt.setNString(2, t.getStatus().getId());
	        stmt.setInt(3, t.getId());
	        int row = stmt.executeUpdate();
	}

	@Override
	public void delete(Table t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`id` = ?");
		stmt.setInt(1, t.getId());
		stmt.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM `table` WHERE `table`.`id` = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}

}
