package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private static ConnectionDAO instance;

    private ConnectionDAO() {
    }

    public static ConnectionDAO getInstance() {
        if (instance == null) instance = new ConnectionDAO();
        return instance;
    }

    private String jdbcURL = "jdbc:mysql://localhost:3306/product_manager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Toiphaithanhcong.152";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
