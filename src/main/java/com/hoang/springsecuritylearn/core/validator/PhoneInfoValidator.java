package com.hoang.springsecuritylearn.core.validator;

import com.hoang.springsecuritylearn.core.dto.RestError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneInfoValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestError.EMPTY)
                    .addConstraintViolation();
            return false;
        }

        if ()
    }
}
