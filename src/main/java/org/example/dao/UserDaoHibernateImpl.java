package org.example.dao;

import org.example.model.User;
import org.example.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getHibernateConnection();

    @Override
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT(3) )";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS user";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
            System.out.println("Пользователь " + name + " " + lastName + " добавлен в базу");
        } catch (HibernateException e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void clearUserTable() {
        String sql = "TRUNCATE TABLE user";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
