package dao.impl;

import dao.DBException;
import dao.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll() throws DBException {
        ArrayList<User> users = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            users = (ArrayList<User>) query.list();
            transaction.commit();
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /**/
            }
        }
        /**/
        return users;
    }

    @Override
    public void updateUser(String id, String login, String password, String role) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            final User user = session.get(User.class, Long.valueOf(id));
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /**/
            }

        }
        /**/

    }

    public void deleteUser(String id) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            final User user = session.get(User.class, Long.valueOf(id));
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /**/
            }
        }
        /**/
    }

    public User getUser(String id) throws DBException {
        User result ;
        try (Session session = sessionFactory.openSession()) {
            result = session.get(User.class, Long.valueOf(id));
        }

        return result != null ? result : new User();
    }

    @Override
    public boolean isUserExist(String login, String password) throws DBException {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("From User u WHERE u.login =:user_login AND u.password =:user_password ");
            query.setParameter("user_login", login);
            query.setParameter("user_password", password);

            users = query.list();
        }
        /**/
        return users.size() > 0;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("From User u WHERE u.login =:user_login AND u.password =:user_password ");
            query.setParameter("user_login", login);
            query.setParameter("user_password", password);
            users = (ArrayList<User>) query.list();
        }
        /**/
        return users.size() > 0 ? users.get(0) : null;
    }

    public void insertUser(String name, String login, String password, String role) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, login, password, role);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception ex) {
                /**/
            }
        }
        /**/
    }

    @Override
    public void createTable() throws DBException {
    }
}