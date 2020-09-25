package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.PaymentDao;
import pizza.hot.model.Payment;
import pizza.hot.model.Pizza;
import pizza.hot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
@Repository
public class PaymentDaoImpl implements PaymentDao {


    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void savePayment(Payment payment) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(payment);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<Payment> findAll() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM payment_info").addEntity(Payment.class);
        List<Payment> paymentList = query.list();

        session.close();

        return paymentList;
    }

    @Override
    public void deletePaymentById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM payment_info where payment_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }
    @Override
    public Payment getPaymentById(Long id) {
        Session session;
        Payment payment;
        session = HibernateUtils.getSessionFactory().openSession();
        payment = session.get(Payment.class, id);
        Hibernate.initialize(payment);
        session.close();
        return payment;
    }

    @Override
    public Payment getPaymentByIdAndCvv(Long id, String payment_cvv) {
        Payment payment;
        try {
            payment = (Payment) entityManager.createQuery("from Payment u where u.id = :id and u.ccCVV= :payment_cvv")
                    .setParameter("id",id).setParameter("payment_cvv",payment_cvv)
                    .getSingleResult();
        } catch (NoResultException e) {
            payment = null;
        }

        return payment;
    }


    }
