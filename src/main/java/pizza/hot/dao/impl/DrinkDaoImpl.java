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
import pizza.hot.dao.DrinkDao;
import pizza.hot.model.Drink;

import java.util.List;

@Repository
@Transactional
public class DrinkDaoImpl implements DrinkDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveDrink(Drink drink) {

        Session session = this.sessionFactory.getCurrentSession();


        session.saveOrUpdate(drink);


    }

    @Override
    public List<Drink> findAll() {
        return  sessionFactory.getCurrentSession().createQuery("FROM Drink ").getResultList();

    }

    @Override
    public void deleteDrinkById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createNativeQuery("DELETE FROM drinks where drink_id = :id").setParameter("id", id);
        query.executeUpdate();



    }

    @Override
    public Drink getDrinkById(Long id) {
        Drink drink;
        Session session = this.sessionFactory.getCurrentSession();
        drink = session.get(Drink.class, id);

        return drink;
    }

    @Override
    public void updateDrinkById(Long id, Drink newDrink) {

        Drink oldDrink;

        Session session = this.sessionFactory.getCurrentSession();
        oldDrink = session.get(Drink.class, id);

        oldDrink.setPrice(newDrink.getPrice());
        oldDrink.setName(newDrink.getName());
        oldDrink.setSize(newDrink.getSize());
        oldDrink.setDescription(newDrink.getDescription());


        session.update(oldDrink);



    }
}
