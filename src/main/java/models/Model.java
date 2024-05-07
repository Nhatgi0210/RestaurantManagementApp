package models;

import java.sql.ResultSet;

public interface Model <T> {
	public String toStringvn();

    public Object[] toRowTable();
 
}
