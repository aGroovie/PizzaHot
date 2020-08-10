package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.UserDao;
import pizza.hot.model.Pizza;
import pizza.hot.model.User;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser(User user) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(user);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM users").addEntity(User.class);
        List<User> userlist = query.list();

        session.close();

        return userlist;
    }

    @Override
    public void deleteUserById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM user where user_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }

    @Override
    public User findByUsername(String username) {
        User user;
        Session session = HibernateUtils.getSessionFactory().openSession();

        user = session.get(User.class, username);
        Hibernate.initialize(user);
        session.close();
        return user;
    }
}
