package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UsersHibernate {
    private Session session;

    public UsersHibernate() {
        this.session = UtilsHibernate.getConnection();
    }

    public List<User> getAll() throws DBException {
        session.beginTransaction();
        Query query = session.createQuery("FROM model.User");
        ArrayList<User> users = (ArrayList<User>) query.list();
        session.getTransaction().commit();
        return users;
    }

    public void updateUser(String id, String login, String password) throws DBException {
        session.beginTransaction();
        final User user = session.get(User.class, Long.valueOf(id));
        user.setLogin(login);
        user.setPassword(password);
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

    public void insertUser(String name, String login, String password) throws DBException {
        session.beginTransaction();
        User user = new User(name, login, password);
        session.save(user);
        session.getTransaction().commit();
    }

    public void createTable() throws DBException {
    }
}