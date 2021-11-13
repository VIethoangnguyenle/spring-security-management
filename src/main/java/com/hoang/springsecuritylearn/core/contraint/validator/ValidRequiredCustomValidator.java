package com.hoang.springsecuritylearn.core.contraint.validator;

import com.hoang.springsecuritylearn.core.contraint.anotation.ValidRequireCustom;
import com.hoang.springsecuritylearn.core.dto.RestError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRequiredCustomValidator implements ConstraintValidator<ValidRequireCustom, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value == null ||  (value.getClass().equals(String.class) && value.equals(""))) {
            context.buildConstraintViolationWithTemplate(RestError.EMPTY)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
