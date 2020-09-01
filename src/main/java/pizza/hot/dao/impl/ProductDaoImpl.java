package pizza.hot.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pizza.hot.config.HibernateUtils;
import pizza.hot.dao.ProductDao;
import pizza.hot.model.Product;

import java.util.List;
@Repository
public class ProductDaoImpl implements ProductDao {
    @Override
    public void saveProduct(Product product) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();
        session.saveOrUpdate(product);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<Product> findAllProducts() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createNativeQuery("SELECT * FROM products").addEntity(Product.class);
        List<Product> productList = query.list();

        session.close();
        return productList;
    }

    @Override
    public void deleteProductById(Long id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createNativeQuery("DELETE  FROM products where product_id = :id").setParameter("id", id);
        query.executeUpdate();

        session.close();
    }

    @Override
    public Product getProductById(Long id) {
        Product product;

        Session session = HibernateUtils.getSessionFactory().openSession();
        product = session.get(Product.class, id);
        Hibernate.initialize(product);

        session.close();
        return product;
    }
}
