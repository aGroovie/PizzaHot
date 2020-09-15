package pizza.hot.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizza.hot.model.Pizza;
import pizza.hot.service.PizzaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;

@Controller
@RequestMapping("/admin")
public class ImageController {

    PizzaService pizzaService;

    @Autowired
    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @RequestMapping(value = "/productImage", method = RequestMethod.GET)
    public void productimage(@RequestParam("id")Long id, Model model,
                             HttpServletRequest request, HttpServletResponse response) throws IOException{

        Pizza pizza  = pizzaService.getPizzaById(id);
        response.setContentType("images/jpeg, images/jpg, images/png, images/gif");
        response.getOutputStream().write(pizza.getImage());
        response.getOutputStream().close();

    }




}
