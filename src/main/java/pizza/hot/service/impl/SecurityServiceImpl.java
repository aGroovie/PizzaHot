/*
package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pizza.hot.service.SecurityService;

import java.io.ObjectInputStream;
@Service
public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String findLoggedInUserName() {
        Object userdetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userdetails instanceof UserDetails){
        return     ((UserDetails) userdetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

authenticationManager.authenticate(authenticationToken);
if(authenticationToken.isAuthenticated()){
  SecurityContextHolder.getContext().setAuthentication(authenticationToken);


}
    }
}
*/