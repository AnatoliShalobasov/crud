package dao.impl;

import dao.DBException;
import dao.UserDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import utils.DBHelper;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {
    private Configuration configuration = DBHelper.getInstance().getConfiguration();
    private Session session;

    public UserDaoHibernateImpl() {
        this.session = createSessionFactory(configuration).openSession();
    }

    public List<User> getAll() throws DBException {
        session.beginTransaction();
        Query query = session.createQuery("FROM User");
        ArrayList<User> users = (ArrayList<User>) query.list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void updateUser(String id, String login, String password, String role) throws DBException {
        session.beginTransaction();
        final User user = session.get(User.class, Long.valueOf(id));
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        session.update(user);
        session.getTransaction().commit();
    }

    public void deleteUser(String id) throws DBException {
        session.beginTransaction();
        final User user = session.get(User.class, Long.valueOf(id));
        session.delete(user);
        session.getTransaction().commit();
    }

    public User getUser(String id) throws DBException {
        final User result = session.get(User.class, Long.valueOf(id));
        return result != null ? result : new User();
    }

    @Override
    public boolean isUserExist(String login, String password) throws DBException {
        Query query = session.createQuery("From User u WHERE u.login =:user_login AND u.password =:user_password ");
        query.setParameter("user_login", login);
        query.setParameter("user_password", password);

        List list = query.list();
        return list.size() > 0;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        Query query = session.createQuery("From User u WHERE u.login =:user_login AND u.password =:user_password ");
        query.setParameter("user_login", login);
        query.setParameter("user_password", password);
        ArrayList<User> list = (ArrayList<User>) query.list();

        return list.get(0);
    }

    public void insertUser(String name, String login, String password, String role) throws DBException {
        session.beginTransaction();
        User user = new User(name, login, password, role);
        session.save(user);
        session.getTransaction().commit();
    }

    public void createTable() throws DBException {
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}