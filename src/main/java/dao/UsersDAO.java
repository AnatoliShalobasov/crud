package dao;

import model.User;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDAO {
    Connection connection;
    ExecutorService ExecutorService;

    public UsersDAO() {
        try {
            this.connection = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.ExecutorService = new ExecutorService(connection);

    }

    public ArrayList<User> getAll() throws DBException {
        String selectAllQuery = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        try {
            users = ExecutorService.get(selectAllQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return users;
    }

    public void updateUser(String id, String login, String password) throws DBException {
        String updateQuery = "UPDATE users " +
                "SET user_login = '" + login + "'," +
                "user_password = '" + password + "' " +
                "WHERE id = '" + Integer.valueOf(id) + "'";
        try {
            ExecutorService.execUpdate(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public void deleteUser(String id) throws DBException {
        String deleteQuery = "DELETE FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        try {
            ExecutorService.execUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public User getUser(String id) throws DBException {
        String getQuery = "SELECT * FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        User user = null;
        try {
            user = ExecutorService.get(getQuery).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return user;
    }

    public void insertUser(String name, String login, String password) throws DBException {
        String insertQuery = "INSERT INTO users (user_name, user_login, user_password) " +
                "VALUES ('" + name + "', '" + login + "', '" + password + "')";
        try {
            ExecutorService.execUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws DBException {
        String createQuery = "CREATE TABLE if NOT EXISTS" +
                " users (id bigint auto_increment ," +
                " user_name VARCHAR (50) NOT NULL," +
                " user_login VARCHAR (50) NOT NULL," +
                " user_password VARCHAR (50) NOT NULL," +
                " PRIMARY KEY (id))";
        try {
            ExecutorService.execUpdate(createQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public void dropTable() throws DBException {
        String dropQuery = "DROP TABLE users";
        try {
            ExecutorService.execUpdate(dropQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    private static class ExecutorService {
        private final Connection connection;

        public ExecutorService(Connection connection) {
            this.connection = connection;
        }

        public void execUpdate(String update) throws SQLException {
            Statement stmt = connection.createStatement();
            stmt.execute(update);
            stmt.close();
        }

        public ArrayList<User> get(String query) throws SQLException {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            ArrayList<User> users = new ArrayList<>();
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                users.add(new User(result.getInt("id"),
                        result.getString("user_name"),
                        result.getString("user_login"),
                        result.getString("user_password")));
            }

            result.close();
            stmt.close();
            return users;
        }
    }
}