package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBUtils {
    static Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
        return connection;
    }
}