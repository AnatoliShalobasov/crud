package jdbc.dao;

import model.User;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDAO {
    Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> getAll() {
        String selectAllQuery = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllQuery)) {
            connection.setAutoCommit(false);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_password")));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Get All EXCEPTION!");
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
        return users;
    }

    public void updateUser(String id, String login, String password) throws SQLException {
        String updateQuery = "UPDATE users SET user_login = '" + login + "',user_password = '" + password + "' WHERE id = '" + Integer.valueOf(id) + "'";
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateQuery);
        }
    }

    public void deleteUser(String id) throws SQLException {
        String deleteQuery = "DELETE FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        try (Statement statement = connection.createStatement()) {
            statement.execute(deleteQuery);
        }
    }

    public User getUser(String id) {
        String getQuery = "SELECT * FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        User user = null;
        try (Statement statement = connection.createStatement();
             ResultSet userResultSet = statement.executeQuery(getQuery)) {
            while (userResultSet.next()) {
                user = new User(userResultSet.getInt("id"),
                        userResultSet.getString("user_name"),
                        userResultSet.getString("user_login"),
                        userResultSet.getString("user_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insertUser(String name, String login, String password) {
        String insertQuery = "INSERT INTO users (user_name, user_login, user_password) VALUES ('" + name + "', '" + login + "', '" + password + "')";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(insertQuery);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void createTable() throws SQLException {
        String createQuery = "CREATE TABLE if NOT EXISTS users (id bigint auto_increment , user_name VARCHAR (50) NOT NULL, user_login VARCHAR (50) NOT NULL, user_password VARCHAR (50) NOT NULL, PRIMARY KEY (id))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createQuery);
        }
    }

    public void dropTable() throws SQLException {
        String dropQuery = "DROP TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropQuery);
        }
    }
}