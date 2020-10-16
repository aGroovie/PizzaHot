package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.config.HibernateConf;
import pizza.hot.dao.AddressDao;
import pizza.hot.model.Address;

import java.util.List;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveAddress(Address address) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);

    }

    @Override
    public List<Address> findAll() {
        return  sessionFactory.getCurrentSession().createQuery("FROM Address ").getResultList();
    }

    @Override
    public void deleteAddressById(Long id) {

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("DELETE FROM user_address where address_id = :id").setParameter("id", id);
      query.executeUpdate();

    }

    @Override
    public Address getAddressById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Address.class, id);
    }

}
