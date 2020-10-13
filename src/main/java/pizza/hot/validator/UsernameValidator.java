package pizza.hot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Service
public class UsernameValidator implements ConstraintValidator<UserNameConstraint, String> {

    UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override

    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username != null && !userService.isAlreadyInUse(username);

    }

    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {

    }
}
