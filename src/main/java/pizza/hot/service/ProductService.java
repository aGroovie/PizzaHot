package pizza.hot.service;

import pizza.hot.model.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    List<Product> findAllProducts();

    void deleteProductById(Long id);

    Product getProductById(Long id);
}
