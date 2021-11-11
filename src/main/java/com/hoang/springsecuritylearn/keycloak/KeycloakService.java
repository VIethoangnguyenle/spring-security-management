package com.hoang.springsecuritylearn.keycloak;

import com.hoang.springsecuritylearn.keycloak.dto.RefreshTokenReq;
import org.keycloak.common.VerificationException;

public interface KeycloakService {
    String createUser(String userName, String password, String email, String name);
    boolean deleteUser(String userId);
    boolean isEmailVerified(String userId);
    String getUserNameByUserId(String userId);
    AccessTokenRespCustom getUserJWT(String userId) throws VerificationException;
    void invalidateToken(RefreshTokenReq refreshTokenReq);
}
