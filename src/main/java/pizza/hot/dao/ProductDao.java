package pizza.hot.dao;

import pizza.hot.model.Product;

import java.util.List;

public interface ProductDao {

    void saveProduct(Product product);

    List<Product> findAllProducts();

    void deleteProductById(Long id);

    Product getProductById(Long id);
}
