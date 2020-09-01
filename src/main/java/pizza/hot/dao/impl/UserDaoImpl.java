package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.UserDao;
import pizza.hot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

        Query query = session.createNativeQuery("DELETE FROM users where user_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }

    @Override
    public User findByUsername(String username) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        User user = new User();
        try {
            user = (User) entityManager.createNativeQuery(
                    "SELECT * from users WHERE username = :username", User.class).
                    setParameter("username", username).getSingleResult();

        }
        catch (NoResultException nre){

    }
        finally {
            session.close();
        }
        return user;
        }

}
