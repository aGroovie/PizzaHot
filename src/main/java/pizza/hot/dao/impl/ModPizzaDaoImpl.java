package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.dao.ModPizzaDao;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;

import java.util.List;
@Repository
@Transactional
public class ModPizzaDaoImpl implements ModPizzaDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void savePizza(ModifiedPizza pizza) {
        Session session = this.sessionFactory.getCurrentSession();

        session.saveOrUpdate(pizza);


    }

    @Override
    public List<ModifiedPizza> getAllPizzas() {
        return sessionFactory.getCurrentSession().createQuery("FROM ModifiedPizza").getResultList();


    }

    @Override
    public void deletePizzaById(Long id) {

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("DELETE FROM modified_pizzas where pizza_id= :id").setParameter("id", id);
        query.executeUpdate();


    }


    @Override
    public ModifiedPizza getPizzaById(Long id) {
        ModifiedPizza pizza;
        Session session = this.sessionFactory.getCurrentSession();
        pizza = session.get(ModifiedPizza.class, id);


        return pizza;
    }


}
