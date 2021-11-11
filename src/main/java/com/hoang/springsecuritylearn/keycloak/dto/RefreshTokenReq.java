package com.hoang.springsecuritylearn.keycloak.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hoang.springsecuritylearn.core.validator.ValidRequireCustom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshTokenReq {

    @ValidRequireCustom
    private String refreshToken;
}
