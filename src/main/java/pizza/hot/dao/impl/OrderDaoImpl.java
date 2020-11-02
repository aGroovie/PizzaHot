package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.dao.OrderDao;
import pizza.hot.model.Order;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional

public class OrderDaoImpl implements OrderDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);


    }

    @Override
    public List<Order> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Order ").getResultList();
    }

    @Override
    public void deleteOrderById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();


        Query query = session.createNativeQuery("DELETE FROM orders where order_id= :id").setParameter("id", id);
        query.executeUpdate();


    }

    @Override
    public Float getTotalByDate(String date) {
        Session session = this.sessionFactory.getCurrentSession();
        BigDecimal bigTotal;
        Float total;
        try {
            Query query = session.createNativeQuery("SELECT SUM(order_total) FROM orders WHERE order_date =:date").setParameter("date", date);
            bigTotal = (BigDecimal) query.getSingleResult();
            total = bigTotal.floatValue();

        }
        catch (NullPointerException npe){
            total = null;
        }

        return total;


    }

    @Override
    public Order getOrderById(Long id) {
        Order order;
        Session session = this.sessionFactory.getCurrentSession();
        order = session.get(Order.class, id);


        return order;
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        List<Order> orders;

        try {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createNativeQuery("SELECT * FROM orders WHERE order_date =:date").setParameter("date", date).addEntity(Order.class);
            orders = query.getResultList();
        } catch (NullPointerException npe ) {

            orders = null;
        }
        return orders;


    }
}
