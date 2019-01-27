package dao;

import dao.impl.UserDaoHibernateImpl;
import dao.impl.UserDaoJDBCImpl;
import utils.UtilProperty;

public class UserDaoFactory {
    public static UserDAO createUserDAO() {
        String propertyValue = UtilProperty.getPropertyValue("DAO");
        switch (propertyValue) {
            case "JDBC":
                return new UserDaoJDBCImpl();
            case "HIBERNATE":
                return new UserDaoHibernateImpl();
            default:
                throw new IllegalArgumentException("unknown " + propertyValue);
        }
    }
}