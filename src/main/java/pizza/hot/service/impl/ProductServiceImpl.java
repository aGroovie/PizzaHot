package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.ProductDao;
import pizza.hot.model.Product;
import pizza.hot.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void saveProduct(Product product) {
          productDao.saveProduct(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return  productDao.findAllProducts();

    }

    @Override
    public void deleteProductById(Long id) {
           productDao.deleteProductById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }


}
