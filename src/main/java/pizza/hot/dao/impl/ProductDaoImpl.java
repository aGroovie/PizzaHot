package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.config.HibernateConf;
import pizza.hot.dao.ProductDao;
import pizza.hot.model.Product;

import java.util.List;
@Repository
@Transactional

public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void saveProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);

    }

    @Override
    public List<Product> findAllProducts() {
      return  sessionFactory.getCurrentSession().createQuery("FROM Product ").getResultList();
    }

    @Override
    public void deleteProductById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();


        Query query = session.createNativeQuery("DELETE  FROM products where product_id = :id").setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public Product getProductById(Long id) {
        Product product;
        Session session = this.sessionFactory.getCurrentSession();
        product = session.get(Product.class, id);
      session.evict(product);


        return product;
    }
}
