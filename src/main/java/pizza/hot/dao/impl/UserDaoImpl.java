package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.config.HibernateConf;
import pizza.hot.dao.UserDao;
import pizza.hot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional

public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);


    }

    @Override
    public List<User> getAllUsers() {
     return  sessionFactory.getCurrentSession().createQuery("FROM User ").getResultList();
    }

    @Override
    public void deleteUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();


        Query query = session.createNativeQuery("DELETE FROM users where user_id= :id").setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public User findByUsername(String username) {
        User user;
        try {
            user = (User) entityManager.createQuery("from User u where u.username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user;
        Session session = this.sessionFactory.getCurrentSession();
        user = session.get(User.class, id);


        return user;
    }
}
