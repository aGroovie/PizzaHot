package pizza.hot.dao;

import pizza.hot.model.Pizza;
import pizza.hot.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    User findByUsername(String username);

    User getUserById(Long id);

}
