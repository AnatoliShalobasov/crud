package utils;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper instance;

    private DBHelper() {
    }

    private Configuration configuration;

    public static DBHelper getInstance() {
        if (instance == null) {
            return instance = new DBHelper();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName(UtilProperty.getPropertyValue("JDBC_DRIVER"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager
                .getConnection(UtilProperty.getPropertyValue("BASE"),
                        UtilProperty.getPropertyValue("BASE_USER"),
                        UtilProperty.getPropertyValue("BASE_PASSWORD"));
        return connection;
    }

    public Session getSession() {
        SessionFactory sessionFactory = createSessionFactory(getMySqlConfiguration());
        return sessionFactory.openSession();
    }

    public Configuration getConfiguration() {
        configuration = DBHelper.getMySqlConfiguration();
        return configuration;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", UtilProperty.getPropertyValue("DIALECT"));
        configuration.setProperty("hibernate.connection.driver_class", UtilProperty.getPropertyValue("JDBC_DRIVER_HIBERNATE"));
        configuration.setProperty("hibernate.connection.url", UtilProperty.getPropertyValue("BASE"));
        configuration.setProperty("hibernate.connection.username", UtilProperty.getPropertyValue("BASE_USER"));
        configuration.setProperty("hibernate.connection.password", UtilProperty.getPropertyValue("BASE_PASSWORD"));
        configuration.setProperty("hibernate.show_sql", UtilProperty.getPropertyValue("SHOW_SQL"));
        configuration.setProperty("hibernate.hbm2ddl.auto", UtilProperty.getPropertyValue("hbm2ddl_auto"));

        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}