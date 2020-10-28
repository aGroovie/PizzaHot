package pizza.hot.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateSearchValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateSearchConstraint {

    String message() default "There are no orders  for this date!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}

