package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.PizzaDao;
import pizza.hot.model.Pizza;

import java.util.List;

@Repository
public class PizzaDaoImpl implements PizzaDao {
    @Override
    public void addPizza(Pizza pizza) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(pizza);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<Pizza> getAllPizzas() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM base_pizzas").addEntity(Pizza.class);
        List<Pizza> pizzaList = query.list();

        session.close();

        return pizzaList;
    }

    @Override
    public void deletePizzaById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM base_pizzas where pizza_id= :id").setParameter("id", id);
        query.executeUpdate();

        session.close();

    }

    @Override
    public void updatePizzaById(Long id, Pizza newPizza) {
        Pizza oldPizza;
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        oldPizza = session.get(Pizza.class, id);
        oldPizza = newPizza;
        session.update(oldPizza);
        session.getTransaction().commit();
        session.close();

    }
}
