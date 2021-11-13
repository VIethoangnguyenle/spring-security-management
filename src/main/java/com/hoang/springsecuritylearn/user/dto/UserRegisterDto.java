package com.hoang.springsecuritylearn.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hoang.springsecuritylearn.core.contraint.anotation.ValidPhoneNumber;
import com.hoang.springsecuritylearn.core.contraint.anotation.ValidRequireCustom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterDto {

    @ValidRequireCustom
    @ValidPhoneNumber
    private String mobile;

    @ValidRequireCustom
    private String email;

    @ValidRequireCustom
    private String province;
}
