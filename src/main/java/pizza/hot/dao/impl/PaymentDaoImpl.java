package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.PaymentDao;
import pizza.hot.model.Payment;

import java.util.List;
@Repository
public class PaymentDaoImpl implements PaymentDao {
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
}
