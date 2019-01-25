package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultHandler<T> {
    List<T> handle(ResultSet resultSet) throws SQLException;
}