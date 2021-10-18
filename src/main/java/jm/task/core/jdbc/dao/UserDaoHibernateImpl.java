package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                "id int auto_increment," +
                "name varchar(45) not null," +
                "lastname varchar(45) not null," +
                "age int not null,constraint users_pk primary key (id));")
                .executeUpdate();
        session.getTransaction()
                .commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users")
                .executeUpdate();
        session.getTransaction()
                .commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction()
                .commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction()
                .commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery(session.getCriteriaBuilder().createQuery(User.class)).getResultList();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE * FROM `users`")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
