package dao;

import model.User;

import java.util.List;

public interface DAO {
    List<User> getAll() throws DBException;

    void updateUser(String id, String login, String password) throws DBException;

    void deleteUser(String id) throws DBException;

    User getUser(String id) throws DBException;

    void insertUser(String name, String login, String password) throws DBException;

    void createTable() throws DBException;
}