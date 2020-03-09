package br.com.ecommerceapi.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = UniqueLoginValidator.class)

public @interface UniqueLogin {
    String message() default "Login already exists";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
