package executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    public <T> List<T> get(String query, ResultHandler<T> resultHandler) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        List<T> result = resultHandler.handle(stmt.getResultSet());
        stmt.close();
        return result;
    }
}