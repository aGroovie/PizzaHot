package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pizza.hot.dao.UserDao;
import pizza.hot.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userDao.findByUsername(username);
           if(user ==null){
               throw new UsernameNotFoundException("User" + username + "does not exists!");

           }
           String role = user.getUserRole();
        List<GrantedAuthority> grantlist = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(role);

       grantlist.add(authority);

        UserDetails userDetails = (UserDetails) new User();  //here might be a problem


        return userDetails;
    }
}