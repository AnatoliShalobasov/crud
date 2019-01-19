package jdbc.dao;

import model.User;
import jdbc.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void updateUser(String name, String login, String password) throws SQLException {
        executor.execUpdate("UPDATE users SET user_login = '" + login + "',user_password = '" + password + "' WHERE users_name = '" + name + "'");
    }
    public void deleteUser(String name) throws SQLException {
        executor.execUpdate("DELETE FROM users WHERE user_name = '" + name + "'");
    }

    public void insertUser(String name, String login, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users (user_name, user_login, user_password) VALUES ('" + name + "', '" + login + "', '" + password + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT NOT NULL, user_name VARCHAR(50) NOT NULL, user_login VARCHAR(50) NOT NULL, user_password VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }
}