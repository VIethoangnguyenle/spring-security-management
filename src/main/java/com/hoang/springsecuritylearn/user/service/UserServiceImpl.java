package com.hoang.springsecuritylearn.user.service;

import com.hoang.springsecuritylearn.keycloak.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    KeycloakService keycloakService;

    @Override
    public String getUserIdByMobile(String mobile) {
        return keycloakService.getUserIdByMobilePhone(mobile);
    }
}
