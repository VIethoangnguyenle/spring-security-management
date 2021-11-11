package com.hoang.springsecuritylearn.user;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.core.dto.RestResponse;
import com.hoang.springsecuritylearn.core.utils.Constants;
import com.hoang.springsecuritylearn.exception.RestBadRequestException;
import com.hoang.springsecuritylearn.user.dto.PasswordDto;
import com.hoang.springsecuritylearn.user.dto.UserProfileDto;
import com.hoang.springsecuritylearn.user.dto.UserRegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

@RestController
@Validated
@Slf4j
@RequestMapping(value = Constants.API_ROOT + "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRegisterController {

    @PostMapping("phone-and-email")
    public RestResponse<Object>registerByPhoneAndEmail(@Valid @RequestBody UserRegisterDto userRegisterDto, HttpServletRequest context) {

        context.getSession().invalidate();
        context.getSession(true).setAttribute(UserRegisterDto.class.getName(), userRegisterDto);
        context.getSession().setMaxInactiveInterval(900);
        MDC.put("sessionId", context.getSession().getId());

        JSONObject logObject = new JSONObject();
        logObject.put("phoneNumber", userRegisterDto.getMobile());
        logObject.put("email", userRegisterDto.getEmail());
        logObject.put("message", "api_called");
        log.info(logObject.toString());

        return new RestResponse<>().success();
    }

    @PostMapping("profile")
    public RestResponse<Object> userProfile(@Valid @RequestBody UserProfileDto userProfileDto, HttpServletRequest context) {

        if (context.getSession().isNew()) {
            context.getSession().invalidate();
            log.error("Not in progress");
            throw new RestBadRequestException(RestError.newBuilder().addMessage("Not in progress").build());
        }

        context.getSession(true).setAttribute(UserProfileDto.class.getName(), userProfileDto);
        log.info("success");
        return new RestResponse<>().success();
    }

    @PostMapping("set-password")
    public RestResponse<Object> password(@Valid @RequestBody PasswordDto passwordDto, HttpServletRequest context) {

        if (context.getSession().isNew()) {
            context.getSession().invalidate();
            log.error("Not in progress");
            throw new RestBadRequestException(RestError.newBuilder().addMessage("Not in progress").build());
        }

        UserProfileDto userProfile = (UserProfileDto) context.getSession().getAttribute(UserProfileDto.class.getName());
        UserRegisterDto userRegister = (UserRegisterDto) context.getSession().getAttribute(UserRegisterDto.class.getName());

        if (userRegister == null || userProfile == null) {
            JSONObject logObject = new JSONObject();
            logObject.put("userProfile", userProfile !=null);
            logObject.put("userRegister", userRegister != null);
            log.error(logObject.toString());
            throw new RestBadRequestException(RestError.newBuilder().addMessage("not in progress").build());
        }

        context.getSession().setAttribute(PasswordDto.class.getName(), passwordDto);


        log.info("success");
        return new RestResponse<>().success();
    }


}
