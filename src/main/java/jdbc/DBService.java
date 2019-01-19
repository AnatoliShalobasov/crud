package jdbc;

import model.User;

import java.sql.*;

public class DBService {
    public static void main(String[] argv) throws SQLException {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stmt.execute("CREATE TABLE if NOT EXISTS users (id bigint auto_increment , user_name VARCHAR (50) NOT NULL, user_login VARCHAR (50) NOT NULL, user_password VARCHAR (50) NOT NULL, PRIMARY KEY (id))");
        System.out.println("TABLE CREATE");
        stmt.execute("INSERT INTO users (user_name, user_login, user_password) VALUES ('PASHA', 'pasha', '123pasha')");
        stmt.execute("insert into users (user_name, user_login, user_password) values ('KOLIA', 'kolia', '123kolia')");
        stmt.execute("insert into users (user_name, user_login, user_password) values ('ALBERT', 'albert', '123albert')");
        stmt.execute("DELETE FROM users WHERE user_name = 'KOLIA'");
        stmt.execute("UPDATE users SET user_login = 'PAPA',user_password = '55555' WHERE user_name = 'PASHA'");
        ResultSet set=stmt.executeQuery("SELECT * FROM users");
        while (set.next()){
            String name = set.getString("user_name");
            System.out.print(name + " name ");
            String login = set.getString("user_login");
            System.out.print(" " + login + " login ");
            String password = set.getString("user_password");
            System.out.print(" "  + password+ " password ");
            System.out.println();
        }
        //stmt.execute("drop table users");
        stmt.close();
    }
}
