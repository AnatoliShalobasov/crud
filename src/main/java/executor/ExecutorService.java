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
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(update);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                /*NOP*/
            }

        }
    }

    public <T> List<T> get(String query, ResultHandler<T> resultHandler) throws SQLException {
        Statement stmt = null;
        List<T> result;
        try {
            stmt = connection.createStatement();
            stmt.execute(query);
            result = resultHandler.handle(stmt.getResultSet());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                /*NOP*/
            }
        }
        return result;
    }
}