package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Pizza;
import pizza.hot.model.Product;
import pizza.hot.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ProductListController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "saveProduct")
    public String save(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:admin/product-list";
    }


    @GetMapping(value = "deleteProductById/{id}")
    String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);

        return "redirect:admin/product-list";
    }

    @GetMapping("product-list")
    public String productList(Model model) {
        model.addAttribute("products", productService.findAllProducts());

        return "product-list";
    }

    @GetMapping("add-product")
    public String getAddProduct(@ModelAttribute("product") Product product) {
        return "/add-product";
    }

    @PostMapping(value = "add-product")
    public String addPizza(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:admin/product-list";
    }

}
