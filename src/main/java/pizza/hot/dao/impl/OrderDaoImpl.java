package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.OrderDao;
import pizza.hot.utils.Order;

import java.util.List;
@Repository
public class OrderDaoImpl implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(order);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<Order> findAll() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM buyer_order").addEntity(Order.class);
        List<Order> orderList = query.list();

        session.close();

        return orderList;
    }

    @Override
    public void deleteOrderById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM buyer_order where buyer_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }
}
