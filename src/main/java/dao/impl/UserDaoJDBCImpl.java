package dao.impl;

import dao.DBException;
import dao.UserDAO;
import executor.ExecutorService;
import executor.ResultHandler;
import model.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {
    private Connection connection;
    private ExecutorService executorService;
    private ResultHandler<User> resultHandler;

    public UserDaoJDBCImpl() {
        this.resultHandler = new Result();
    }

    public List<User> getAll() throws DBException {
        ArrayList<User> users;
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String selectAllQuery = "SELECT * FROM users";
            users = (ArrayList<User>) executorService.get(selectAllQuery, resultHandler);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }


    public void updateUser(String id, String login, String password, String role) throws DBException {
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String updateQuery = "UPDATE users " +
                    "SET user_login = '" + login + "'," +
                    "user_password = '" + password + "', " +
                    "user_role = '" + role + "' " +
                    "WHERE id = '" + Integer.valueOf(id) + "'";

            executorService.execUpdate(updateQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(String id) throws DBException {
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String deleteQuery = "DELETE FROM users WHERE id = '" + Integer.valueOf(id) + "'";
            executorService.execUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUser(String id) throws DBException {
        User user;
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String getQuery = "SELECT * FROM users WHERE id = '" + Integer.valueOf(id) + "'";
            user = executorService.get(getQuery, resultHandler).get(0);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean isUserExist(String login, String password) throws DBException {
        ArrayList<User> users;
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String getQuery = "SELECT * FROM users WHERE user_login = '" + login + "' AND user_password = '" + password + "' ";
            users = (ArrayList<User>) executorService.get(getQuery, resultHandler);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return !users.isEmpty();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        List<User> users;
        User result = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String getQuery = "SELECT * FROM users WHERE user_login = '" + login + "' AND user_password = '" + password + "' ";
            users = executorService.get(getQuery, resultHandler);
            if (users.size() > 0){
                result = users.get(0);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void insertUser(String name, String login, String password, String role) throws DBException {
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String insertQuery = "INSERT INTO users (user_name, user_login, user_password, user_role) " +
                    "VALUES ('" + name + "', '" + login + "', '" + password + "', '" + role + "')";
            executorService.execUpdate(insertQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createTable() throws DBException {
        try {
            connection = DBHelper.getInstance().getConnection();
            executorService = new ExecutorService(connection);
            String createQuery = "CREATE TABLE if NOT EXISTS" +
                    " users (id bigint auto_increment ," +
                    " user_name VARCHAR (50) NOT NULL," +
                    " user_login VARCHAR (50) NOT NULL," +
                    " user_password VARCHAR (50) NOT NULL," +
                    " user_role VARCHAR (10) NOT NULL DEFAULT 'user'," +
                    " PRIMARY KEY (id))";
            executorService.execUpdate(createQuery);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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