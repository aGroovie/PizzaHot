/*
package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizza.hot.dao.UserDao;
import pizza.hot.model.User;

import java.util.HashSet;
import java.util.Set;
//@Service("userDetailsService")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
        grantedAuthoritySet.add(new SimpleGrantedAuthority("CUSTOMER_ROLE"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), grantedAuthoritySet);
    }
}
*/
