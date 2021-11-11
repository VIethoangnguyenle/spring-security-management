package com.hoang.springsecuritylearn.core.validator;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneInfoValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestError.EMPTY)
                    .addConstraintViolation();
            return false;
        }

        if (userService.getUserIdByMobile(value) != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestError.USED)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
