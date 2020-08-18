package pizza.hot.dao.impl;

import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.OrderDao;
import pizza.hot.model.Order;
import pizza.hot.model.Payment;

import java.util.List;

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

        Query query = session.createNativeQuery("SELECT * FROM orders").addEntity(Order.class);
        List<Order> orderList = query.list();

        session.close();

        return orderList;
    }

    @Override
    public void deleteOrderById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM orders where order_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }
}
