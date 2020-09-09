package pizza.hot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pizza.hot.model.User;
import pizza.hot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthProviderImpl implements AuthenticationProvider {


    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
     User user =  userService.findByUsername(name);
     if(user == null){
         throw  new UsernameNotFoundException("User not found");
     }
     String password = authentication.getCredentials().toString();
     if(!password.equals(user.getPassword())){
         throw new BadCredentialsException("bad info");
     }
        String role = user.getUserRole();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
        grantedAuthorities.add(new SimpleGrantedAuthority("CUSTOMER_ROLE"));
        user.setUserRole(User.ROLE_CUSTOMER);
        return  new UsernamePasswordAuthenticationToken(user, role, grantedAuthorities );
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
