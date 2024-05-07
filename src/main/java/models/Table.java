package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import others.TableStatus;

public class Table implements Model{
	private int id;
    private String name;
    private TableStatus status;
    
    
	public Table() {
		
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

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}
	public static Table getFromResultSet(ResultSet rs) throws SQLException {
        Table t = new Table();
        t.setId(rs.getInt("id"));
        t.setName(rs.getNString("name"));
        t.setStatus(TableStatus.getById(rs.getNString("status")));
        return t;
    }
	@Override
	public String toStringvn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toRowTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
