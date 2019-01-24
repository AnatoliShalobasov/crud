package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExecutorService {
    private final Connection connection;

    public ExecutorService(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
    }

    public <T> ArrayList<T> get(String query, ResultHandler<T> resultHandler) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ArrayList<T> users = resultHandler.handle(stmt.getResultSet());
        stmt.close();
        return users;
    }
}