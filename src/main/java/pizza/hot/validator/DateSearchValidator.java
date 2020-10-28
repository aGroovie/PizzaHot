package pizza.hot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.service.OrderService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class DateSearchValidator implements ConstraintValidator<DateSearchConstraint, String> {


    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void initialize(DateSearchConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {

        return date !=null && !orderService.isThereAnyOrders(date);

    }
}
