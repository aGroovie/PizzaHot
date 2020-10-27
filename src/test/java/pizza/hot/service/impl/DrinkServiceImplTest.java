package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.DrinkDao;
import pizza.hot.dao.UserDao;
import pizza.hot.dao.impl.DrinkDaoImpl;
import pizza.hot.model.Drink;
import pizza.hot.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrinkServiceImplTest {


    @InjectMocks
    DrinkDaoImpl drinkDao;

    @Mock
    DrinkServiceImpl drinkService;

    Drink drink;

    Drink updatedDrink;

    List<Drink> drinks;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        drink = new Drink();
        drink.setId(15L);
        drink.setDescription("cool dr pepper");
        drink.setName("Dr.Pepper");
        drink.setPrice(4.0f);
        drink.setSize(2);

        updatedDrink = new Drink();
        updatedDrink.setId(16L);
        updatedDrink.setDescription("Cool sweet cola");
        updatedDrink.setName("Cola");
        updatedDrink.setPrice(3.0f);
        updatedDrink.setSize(2);


        drinks = new ArrayList<>();
        drinks.add(drink);
        drinks.add(updatedDrink);
    }


    @Test
    void setDrinkDao() {
    }

    @Test
    void saveDrink() {
    }

    @Test
    void findAll_ReturnsCorrect() {
        Mockito.when(drinkService.findAll()).thenReturn(drinks);
        Assert.assertEquals(2, drinkService.findAll().size());
    }

    @Test
    void deleteDrinkById_ThrowsNpe() {

    }

    @Test
    void getDrinkById_CorrectReturn() {
        Mockito.when(drinkService.getDrinkById(15L)).thenReturn(drink);
        Assert.assertEquals(drinkService.getDrinkById(15L), drink);
    }


    @Test
    void getDrinkById_ThrowsIAC() {
        Mockito.when(drinkService.getDrinkById(0L)).thenThrow(new IllegalArgumentException("id cannot be zero"));
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            drinkService.getDrinkById(0L);
        });
        assertEquals("id cannot be zero", exception.getMessage());
    }

    @Test
    void updateDrinkById() {

    }
}