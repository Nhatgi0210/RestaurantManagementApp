package others;

public enum EmployeePermission {
    MANAGER("manager", "Quản Lý"),
    CASHIER("cashier","Thu Ngân"),
    ORDER("order","Order"),
    CHEF("chef","Đầu Bếp");
    private String id, name;
  

    EmployeePermission(String id, String name) {
        this.id = id;
        this.name = name;
        
    }
    
    public static EmployeePermission getById(String id) {
        for (EmployeePermission e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
		return null;
       
    }

    public static EmployeePermission getByName(String name) {
        for (EmployeePermission e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    

}

