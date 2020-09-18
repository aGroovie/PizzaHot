package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.DrinkDao;
import pizza.hot.model.Drink;
import pizza.hot.service.DrinkService;

import java.util.List;
@Service
public class DrinkServiceImpl implements DrinkService {


   private DrinkDao drinkDao;

    @Autowired
    public void setDrinkDao(DrinkDao drinkDao) {
        this.drinkDao = drinkDao;
    }

    @Override
    public void saveDrink(Drink drink) {
        drinkDao.saveDrink(drink);
    }

    @Override
    public List<Drink> findAll() {
        return drinkDao.findAll();
    }

    @Override
    public void deleteDrinkById(Long id) {

        drinkDao.deleteDrinkById(id);
    }

    @Override
    public Drink getDrinkById(Long id) {
        return drinkDao.getDrinkById(id);
    }

    @Override
    public void updateDrinkById(Long id, Drink newDrink) {
        drinkDao.updateDrinkById(id,newDrink);
    }
}
