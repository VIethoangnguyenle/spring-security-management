package com.hoang.springsecuritylearn.keycloak;

import com.hoang.springsecuritylearn.core.dto.RestError;
import com.hoang.springsecuritylearn.exception.RestBadRequestException;
import com.hoang.springsecuritylearn.exception.RestException;
import com.hoang.springsecuritylearn.keycloak.dto.RefreshTokenReq;
import com.hoang.springsecuritylearn.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.TokenVerifier;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
    @Value("#{'${keycloak.credentials.secret}'.trim()}")
    String secretKey;

    @Value("#{'${keycloak.resource}'.trim()}")
    String clientId;

    @Value("#{'${keycloak.auth-server-url}'.trim()}")
    String authUrl;

    @Value("#{'${keycloak.realm}'.trim()}")
    String realm;

    @Value("#{'${keycloak-admin.username}'.trim()}")
    String username;

    @Value("#{'${keycloak-admin.password}'.trim()}")
    String password;

    Keycloak keycloakAdmin;

    @Bean
    private void initKeycloak() {
        keycloakAdmin = KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .realm(realm)
                .clientId("admin-cli")
                .password(password)
                .username(username)
                .resteasyClient(
                        new ResteasyClientBuilder().connectionPoolSize(10).build()
                )
                .build();
    }

    @Override
    public String createUser(String userName, String password, String email, String name) {
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(name);
        user.setEmail(email);
        user.setEnabled(true);
        user.setUsername(userName);
        user.setEmailVerified(false);

        // Get realm
        RealmResource realmResource = keycloakAdmin.realm(realm);
        UsersResource usersResource = realmResource.users();

        try (Response response = usersResource.create(user)) {
            if (response.getStatus() == 200) {
                String userId = CreatedResponseUtil.getCreatedId(response);

                // Define password credential
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(password);

                UserResource userResource = usersResource.get(userId);
                userResource.resetPassword(passwordCred);
                return userId;
            }
            else if (response.getStatus() == 409){
                throw new RestBadRequestException(RestError.newBuilder().addUsedField("phoneNumber").build());
            }
            else {
                throw new RestBadRequestException(RestError.newBuilder().addMessage(response.getStatusInfo().getReasonPhrase()).build());
            }
        } catch (RestException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RestBadRequestException(RestError.newBuilder().addMessage(e.getMessage()).build());
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        try {
            keycloakAdmin.realm(realm).users().delete(userId);
            return true;
        } catch (Exception e) {
            log.error("error");
        }
        return false;
    }

    @Override
    public boolean isEmailVerified(String userId) {
        UserResource user = keycloakAdmin.realm(realm).users().get(userId);
        return user.toRepresentation().isEmailVerified();
    }

    @Override
    public String getUserNameByUserId(String userId) {
        return keycloakAdmin.realm(realm).users().get(userId).toRepresentation().getUsername();
    }

    @Override
    public AccessTokenRespCustom getUserJWT(String userId) throws VerificationException {
        Keycloak keycloakUser = KeycloakBuilder.builder()
                .username(username)
                .password(password)
                .serverUrl(authUrl)
                .grantType("password")
                .clientId(clientId)
                .clientSecret(secretKey)
                .build();
        AccessTokenResponse accessTokenResponse = keycloakUser.tokenManager().getAccessToken();
        AccessToken token = TokenVerifier.create(accessTokenResponse.getIdToken(), AccessToken.class).getToken();

        return new AccessTokenRespCustom(accessTokenResponse);
    }

    @Override
    public void invalidateToken(RefreshTokenReq refreshTokenReq) {

    }

    @Override
    public String getUserIdByMobilePhone(String mobile) {
        UsersResource usersResource = keycloakAdmin.realm(realm).users();
        List<UserRepresentation> users = usersResource.search(mobile, true);
        UserRepresentation userRepresentation = users.stream().filter(item-> item.getUsername().equals(mobile)).findFirst().orElse(null);
        return userRepresentation != null ? userRepresentation.getId() : null;
    }
}
