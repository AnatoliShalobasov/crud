package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll() throws DBException;

    void updateUser(String id, String login, String password, String role) throws DBException;

    void deleteUser(String id) throws DBException;

    User getUser(String id) throws DBException;

    boolean isUserExist(String login, String password) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    void insertUser(String name, String login, String password, String role) throws DBException;

    void createTable() throws DBException;
}