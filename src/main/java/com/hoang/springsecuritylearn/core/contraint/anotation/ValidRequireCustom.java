package com.hoang.springsecuritylearn.core.contraint.anotation;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.contraint.validator.ValidRequiredCustomValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidRequiredCustomValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRequireCustom {

    String message() default RestError.INVALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
