package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.UserDao;
import pizza.hot.enums.Role;
import pizza.hot.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {


    User user;


    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();

        user.setId(33L);
        user.setEmail("mgsBadGame@gmail.com");
        user.setUsername("customerZoom");
        user.setPassword("1337228");
    }

    @Test
    void saveUser_setsRole() {
        userService.saveUser(user);
        Assert.assertEquals(Role.CUSTOMER,user.getUserRole());
    }

    @Test
    void getUsernameFromSession() {
    }



    @Test
    void isAlreadyInUse_returnsTrue(){
        Mockito.when(userDao.findByUsername("customerZoom")).thenReturn(user);
        Assert.assertEquals(true,userService.isAlreadyInUse("customerZoom"));

    }
    @Test
    void isAlreadyInUser_returnsFalse(){
        Mockito.when(userDao.findByUsername("customerZ")).thenReturn(null);
        Assert.assertEquals(false,userService.isAlreadyInUse("customerZ"));
    }
}