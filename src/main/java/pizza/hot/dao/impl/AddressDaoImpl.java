package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.AddressDao;
import pizza.hot.model.Address;
import pizza.hot.model.Product;

import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {
    @Override
    public void saveAddress(Address address) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(address);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<Address> findAll() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("SELECT * FROM user_address").addEntity(Address.class);
        List<Address> addressList = query.list();

        session.close();

        return addressList;
    }

    @Override
    public void deleteAddressById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createNativeQuery("DELETE FROM user_address where address_id = :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }

    @Override
    public Address getAddressById(Long id) {
        Address address;


        Session session = HibernateUtils.getSessionFactory().openSession();
        address = session.get(Address.class, id);
        Hibernate.initialize(address);

        session.close();
        return address;
    }

}
