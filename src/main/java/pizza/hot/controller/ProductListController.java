package pizza.hot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pizza.hot.model.Product;
import pizza.hot.service.ProductService;

import java.util.List;

@Controller
public class ProductListController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/saveProduct")
    public String save(@ModelAttribute("product")Product product){
        productService.saveProduct(product);
        return "redirect:/product-list";
    }

   @GetMapping("/product-list")
    public List<Product> getAllProducts(){
        return productService.findAllProducts();
   }

   @GetMapping(value = "deleteProductById/{id}")
    String deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);

        return "redirect:/product-list";
   }



}
