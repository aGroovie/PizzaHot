package pizza.hot.service;

import pizza.hot.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    User findByUsername(String username);

    boolean isAlreadyInUse(String username);

    String getUsernameFromSession(Object principal);

}
