package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCode {
    public static boolean saveCustomer(Customer c) {
        // Save customer to database
        try {
            boolean isSaved = CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)", c.getId(), c.getName(), c.getAddress(), c.getSalary());
            return isSaved;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean updateCustomer(Customer c) {
        // Update customer to database
        try {
            boolean isUpdated = CrudUtil.execute("UPDATE customer SET id=?,name=?,address=?,salary=? WHERE id=?", c.getId(), c.getName(), c.getAddress(), c.getSalary(), c.getId());
            return isUpdated;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteCustomer(String id) {
        // Delete customer from database
        try {
            boolean isDeleted = CrudUtil.execute("DELETE FROM customer WHERE id = ?", id);
            return isDeleted;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static List<Customer> loadTable() {
        // Load table from database
        try {
            ResultSet set = CrudUtil.execute("SELECT * FROM customer");
            List<Customer> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Customer(set.getString(1), set.getString(2), set.getString(3), set.getDouble(4)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
