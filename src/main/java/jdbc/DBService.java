package jdbc;

import jdbc.dao.UsersDAO;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class DBService {
    private static DBService instance;
    private UsersDAO usersDAO;

    private DBService(Connection connection) {
        usersDAO = new UsersDAO(connection);
    }

    public static DBService getInstance() throws SQLException {
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();
            }
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/root?serverTimezone=UTC", "root", "root");
            instance = new DBService(connection);
        }
        return instance;
    }

    public void updateUser(String id, String login, String password) throws SQLException {
        usersDAO.updateUser(id, login, password);
    }

    public User getUser(String id) {
        return usersDAO.getUser(id);
    }

    public void deleteUser(String id) throws SQLException {
        usersDAO.deleteUser(id);
    }

    public void insertUser(String name, String login, String password) throws SQLException {
        usersDAO.insertUser(name, login, password);
    }

    public ArrayList<User> getAllUsers() {
        return usersDAO.getAll();
    }

    public void createTable() throws SQLException {
        usersDAO.createTable();
    }
}