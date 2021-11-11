package com.hoang.springsecuritylearn.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hoang.springsecuritylearn.core.validator.ValidRequireCustom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordDto implements Serializable {

    @ValidRequireCustom
    private String password;

}
