package pizza.hot.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.businesslogic.HibernateUtils;
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
}
