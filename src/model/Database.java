package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Database database;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
    public Connection getConnection() {
        return connection;
    }
}