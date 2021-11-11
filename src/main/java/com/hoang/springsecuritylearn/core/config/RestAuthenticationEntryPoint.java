package com.hoang.springsecuritylearn.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoang.springsecuritylearn.core.dto.RestResponse;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class RestAuthenticationEntryPoint extends KeycloakAuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper();

    public RestAuthenticationEntryPoint(AdapterDeploymentContext adapterDeploymentContext) {
        super(adapterDeploymentContext);
    }

    @Override
    protected void commenceUnauthorizedResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RestResponse<Object> restResponse = new RestResponse<>().unauthorized();
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().print(mapper.writeValueAsString(restResponse));
    }
}
