package dao;

import dao.impl.UserDaoHibernateImpl;
import dao.impl.UserDaoJDBCImpl;
import utils.DBHelper;
import utils.UtilProperty;

import java.io.InputStream;

public class UserDaoFactory {
    public static UserDAO createUserDAO() {
        String propertyValue = UtilProperty.getPropertyValue("DAO");
        switch (propertyValue) {
            case "JDBC":
                return new UserDaoJDBCImpl();
            case "HIBERNATE":
                return new UserDaoHibernateImpl(DBHelper.createSessionFactory(DBHelper.getMySqlConfiguration()));
            default:
                throw new IllegalArgumentException("unknown " + propertyValue);
        }
    }
}