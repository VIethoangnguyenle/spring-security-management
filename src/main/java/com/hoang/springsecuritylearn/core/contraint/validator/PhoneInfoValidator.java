package com.hoang.springsecuritylearn.core.contraint.validator;

import com.hoang.springsecuritylearn.core.contraint.anotation.ValidPhoneNumber;
import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.utils.FnCommon;
import com.hoang.springsecuritylearn.user.dto.PhoneNumberDto;
import com.hoang.springsecuritylearn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneInfoValidator implements ConstraintValidator<ValidPhoneNumber, PhoneNumberDto> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(PhoneNumberDto phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestError.EMPTY)
                    .addConstraintViolation();
            return false;
        }

        if (userService.getUserIdByMobile(phoneNumber.getInternationalNumber()) != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestError.USED)
                    .addConstraintViolation();
            return false;
        }
        return FnCommon.isValidPhoneNumber(phoneNumber);
    }
}
