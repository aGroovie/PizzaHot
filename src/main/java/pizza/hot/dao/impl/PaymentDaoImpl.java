package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.config.HibernateConf;
import pizza.hot.dao.PaymentDao;
import pizza.hot.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
@Repository
@Transactional

public class PaymentDaoImpl implements PaymentDao {


    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void savePayment(Payment payment) {
        Session session = this.sessionFactory.getCurrentSession();

        session.saveOrUpdate(payment);

    }

    @Override
    public List<Payment> findAll() {
        return  sessionFactory.getCurrentSession().createQuery("FROM Payment ").getResultList();
    }

    @Override
    public void deletePaymentById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createNativeQuery("DELETE FROM payment_info where payment_id= :id").setParameter("id", id);
        query.executeUpdate();

    }
    @Override
    public Payment getPaymentById(Long id) {

        Payment payment;
        Session session = this.sessionFactory.getCurrentSession();

        payment = session.get(Payment.class, id);

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
