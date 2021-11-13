package com.hoang.springsecuritylearn.core.utils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.hoang.springsecuritylearn.user.dto.PhoneNumberDto;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FnCommon {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static final SecureRandom secureRandom = new SecureRandom();

    public static Calendar today() {
        return Calendar.getInstance();
    }

    public static String todayStr(String pattern) {
        pattern = pattern == null ? "yyyy-MM-dd'T'HH:mm:ssZ" : pattern;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return dateFormat.format(today().getTime());
    }

    public static Date dateFromString(String dateString) throws ParseException {
        dateString = dateString.replaceAll("-", "/");
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        return formatter.parse(dateString);
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern == null ? DATE_PATTERN : pattern);
        return dateFormat.format(date);
    }

    public static boolean isValidPhoneNumber(PhoneNumberDto phoneNumberDto) {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        phoneNumber.setNationalNumber(phoneNumberDto.getNationalNumber())
                .setCountryCode(phoneNumberDto.getCountryCode());
        return phoneNumberUtil.isValidNumber(phoneNumber);
    }
}
