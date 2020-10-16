package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.config.HibernateConf;
import pizza.hot.dao.PizzaDao;
import pizza.hot.model.Pizza;

import java.util.List;

@Repository
@Transactional

public class PizzaDaoImpl implements PizzaDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPizza(Pizza pizza) {
        Session session = this.sessionFactory.getCurrentSession();

        session.saveOrUpdate(pizza);


    }

    @Override
    public List<Pizza> getAllPizzas() {
        return sessionFactory.getCurrentSession().createQuery("FROM Pizza").getResultList();


    }

    @Override
    public void deletePizzaById(Long id) {

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("DELETE FROM base_pizzas where pizza_id= :id").setParameter("id", id);
        query.executeUpdate();


    }

    @Override
    public void updatePizzaById(Long id, Pizza newPizza) {

        Pizza oldPizza;


        Session session = this.sessionFactory.getCurrentSession();
        oldPizza = (Pizza) session.get(Pizza.class, id);

        oldPizza.setPrice(newPizza.getPrice());
        oldPizza.setName(newPizza.getName());
        oldPizza.setDescription(newPizza.getDescription());
        oldPizza.setProducts(newPizza.getProducts());

        session.update(oldPizza);


    }

    @Override
    public Pizza getPizzaById(Long id) {
        Pizza pizza;
        Session session = this.sessionFactory.getCurrentSession();
        pizza = session.get(Pizza.class, id);
        session.evict(pizza);

        return pizza;
    }
}
