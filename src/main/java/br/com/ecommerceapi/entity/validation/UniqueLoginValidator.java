package br.com.ecommerceapi.entity.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecommerceapi.service.UserService;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return Objects.isNull(login) || !userService.existsByLogin(login);
    }
}
