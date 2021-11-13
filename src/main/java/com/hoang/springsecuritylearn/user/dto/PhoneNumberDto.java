package com.hoang.springsecuritylearn.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@NoArgsConstructor
public class PhoneNumberDto {
    private int countryCode;

    private long nationalNumber;

    @JsonIgnore
    public String getInternationalNumber() {
        return "+" + countryCode + nationalNumber;
    }
}
