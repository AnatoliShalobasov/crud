package dao;

import model.User;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection connection;
    private ExecutorService ExecutorService;
    private ResultHandler<User> resultHandler;

    public UsersDAO() {
        try {
            this.connection = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.ExecutorService = new ExecutorService(connection);
        this.resultHandler = new Result();
    }

    public List<User> getAll() throws DBException {
        String selectAllQuery = "SELECT * FROM users";
        ArrayList<User> users;
        try {
            users = (ArrayList<User>) ExecutorService.get(selectAllQuery, resultHandler);
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
            throw new DBException(e);
        }
    }

    public void deleteUser(String id) throws DBException {
        String deleteQuery = "DELETE FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        try {
            ExecutorService.execUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUser(String id) throws DBException {
        String getQuery = "SELECT * FROM users WHERE id = '" + Integer.valueOf(id) + "'";
        User user;
        try {
            user = ExecutorService.get(getQuery, resultHandler).get(0);
        } catch (SQLException e) {
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
            throw new DBException(e);
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
            throw new DBException(e);
        }
    }

    private static class Result implements ResultHandler<User> {
        @Override
        public ArrayList<User> handle(ResultSet result) throws SQLException {
            ArrayList<User> users = new ArrayList<>();
            while (result.next()) {
                users.add(new User(result.getInt("id"),
                        result.getString("user_name"),
                        result.getString("user_login"),
                        result.getString("user_password")));
            }
            return users;
        }
    }
}