package jdbc;

import jdbc.dao.UsersDAO;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class DBService {
    private static DBService instance;
    private UsersDAO usersDAO;

    private DBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
    }

    public static DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
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
    public void setConnection(Connection connection){
        usersDAO = new UsersDAO(connection);
    }
}