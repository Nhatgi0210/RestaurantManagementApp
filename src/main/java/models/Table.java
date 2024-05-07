package models;

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
