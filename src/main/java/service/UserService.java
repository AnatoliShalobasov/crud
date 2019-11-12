package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void updateUser(String id, String login, String password, String role);

    User getUser(String id);

    void deleteUser(String id);

    void insertUser(String name, String login, String password, String role);

    boolean isUserExist(String login, String password);

    User getUserByLoginAndPassword(String login, String password);
}