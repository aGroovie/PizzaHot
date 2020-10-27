package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pizza.hot.config.EncrytedPasswordUtils;
import pizza.hot.dao.UserDao;
import pizza.hot.enums.Role;
import pizza.hot.model.User;
import pizza.hot.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        String encryptedPassword = EncrytedPasswordUtils.encrytePassword(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setUserRole(Role.CUSTOMER);
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean isAlreadyInUse(String username) {
        if (userDao.findByUsername(username) != null) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String getUsernameFromSession(Object principal) {
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
