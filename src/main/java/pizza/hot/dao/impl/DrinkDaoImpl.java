package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.DrinkDao;
import pizza.hot.model.Drink;

import java.util.List;

@Repository
public class DrinkDaoImpl implements DrinkDao {
    @Override
    public void saveDrink(Drink drink) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(drink);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Drink> findAll() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM drinks").addEntity(Drink.class);
        List<Drink> drinkList = query.list();

        session.close();

        return drinkList;

    }

    @Override
    public void deleteDrinkById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM drinks where drink_id = :id").setParameter("id", id);
        query.executeUpdate();

        session.close();


    }

    @Override
    public Drink getDrinkById(Long id) {
        Session session;
        Drink drink;
        session = HibernateUtils.getSessionFactory().openSession();
        drink = session.get(Drink.class, id);
        Hibernate.initialize(drink);
        session.close();
        return drink;
    }

    @Override
    public void updateDrinkById(Long id, Drink newDrink) {

        Drink oldDrink;

        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        oldDrink =  session.get(Drink.class, id);

        oldDrink.setPrice(newDrink.getPrice());
        oldDrink.setName(newDrink.getName());
        oldDrink.setSize(newDrink.getSize());
        oldDrink.setDescription(newDrink.getDescription());


        session.update(oldDrink);
        tx.commit();

        session.close();

    }
}
