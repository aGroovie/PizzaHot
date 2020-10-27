package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.ModPizzaDao;
import pizza.hot.model.ModifiedPizza;
import pizza.hot.model.Pizza;
import pizza.hot.service.ModPizzaService;

import java.util.List;

@Service
public class ModPizzaServiceImpl implements ModPizzaService {

    ModPizzaDao modPizzaDao;

    @Autowired
    public ModPizzaServiceImpl setModPizzaDao(ModPizzaDao modPizzaDao) {
        this.modPizzaDao = modPizzaDao;
        return this;
    }


    @Override
    public void savePizza(ModifiedPizza pizza) {
        modPizzaDao.savePizza(pizza);

    }

    @Override
    public List<ModifiedPizza> getAllPizzas() {
        return modPizzaDao.getAllPizzas();
    }

    @Override
    public void deletePizzaById(Long id) {
        modPizzaDao.deletePizzaById(id);
    }



    @Override
    public ModifiedPizza getPizzaById(Long id) {
        return modPizzaDao.getPizzaById(id);
    }

    @Override
    public ModifiedPizza clonePizza(Pizza pizza) {
        ModifiedPizza modifiedPizza = new ModifiedPizza();
        String name = pizza.getName();
        String description = pizza.getDescription();
        String pictureLink = pizza.getPictureLink();
        int size = pizza.getSize();
        float price = pizza.getPrice();
        Long id = pizza.getId();
        modifiedPizza.setName(name);
        modifiedPizza.setDescription(description);
        modifiedPizza.setPictureLink(pictureLink);
        modifiedPizza.setSize(size);
        modifiedPizza.setPrice(price);
        modifiedPizza.setPizza(pizza);
        modifiedPizza.setId(id);
        return modifiedPizza;
    }
}
