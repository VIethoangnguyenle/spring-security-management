package com.hoang.springsecuritylearn.core.contraint.anotation;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.contraint.validator.PhoneInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneInfoValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default RestError.INVALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
