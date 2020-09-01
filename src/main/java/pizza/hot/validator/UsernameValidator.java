package pizza.hot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pizza.hot.dao.UserDao;
import pizza.hot.model.User;

import javax.persistence.NoResultException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UsernameValidator implements ConstraintValidator<UserNameConstraint, String> {
    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
  if(userDao.findByUsername(username) != null){
      return false;
  }
  return true;

    }
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {

    }
}
