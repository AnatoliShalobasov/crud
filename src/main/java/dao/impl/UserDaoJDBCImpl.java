package dao.impl;

import dao.DBException;
import dao.UserDAO;
import utils.DBHelper;
import executor.ExecutorService;
import executor.ResultHandler;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {
    private Connection connection;
    private ExecutorService ExecutorService;
    private ResultHandler<User> resultHandler;

    public UserDaoJDBCImpl() {
        try {
            this.connection = DBHelper.getInstance().getConnection();
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


    public void updateUser(String id, String login, String password, String role) throws DBException {
        String updateQuery = "UPDATE users " +
                "SET user_login = '" + login + "'," +
                "user_password = '" + password + "', " +
                "user_role = '" + role + "' " +
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
        User user = null;
        try {
            user = ExecutorService.get(getQuery, resultHandler).get(0);
        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public boolean isUserExist(String login, String password) throws DBException {
        String getQuery = "SELECT * FROM users WHERE user_login = '" + login + "' AND user_password = '" + password + "' ";
        ArrayList<User> users;
        try {
            users = (ArrayList<User>) ExecutorService.get(getQuery, resultHandler);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return !users.isEmpty();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        String getQuery = "SELECT * FROM users WHERE user_login = '" + login + "' AND user_password = '" + password + "' ";
        User result;
        try {
            result = ExecutorService.get(getQuery, resultHandler).get(0);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return result;
    }

    public void insertUser(String name, String login, String password, String role) throws DBException {
        String insertQuery = "INSERT INTO users (user_name, user_login, user_password, user_role) " +
                "VALUES ('" + name + "', '" + login + "', '" + password + "', '" + role + "')";
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
                " user_role VARCHAR (10) NOT NULL DEFAULT 'user'," +
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
                        result.getString("user_password"),
                        result.getString("user_role")));
            }
            return users;
        }
    }
}