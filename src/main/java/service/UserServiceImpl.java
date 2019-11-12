package service;

import dao.DBException;
import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserDAO usersDAO;

    private UserServiceImpl() {
        usersDAO = UserDaoFactory.createUserDAO();
        try {
            usersDAO.createTable();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public void updateUser(String id, String login, String password, String role) {
        try {
            usersDAO.updateUser(id, login, password, role);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String id) {
        User user = null;
        try {
            user = usersDAO.getUser(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(String id) {
        try {
            usersDAO.deleteUser(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String name, String login, String password, String role) {
        try {
            usersDAO.insertUser(name, login, password, role);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            return usersDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean isUserExist(String login, String password) {
        boolean result = false;
        try {
            result = usersDAO.isUserExist(login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        User result = null;
        try {
            result = usersDAO.getUserByLoginAndPassword(login, password);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }
}