package com.cms.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalCodeValidator implements ConstraintValidator<ValidNationalCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || !value.matches("\\d{10}")) {
            return false;
        }

        if (value.matches("(\\d)\\1{9}")) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += (value.charAt(i) - '0') * (10 - i);
        }

        int remainder = sum % 11;
        int checkDigit = value.charAt(9) - '0';

        if (remainder < 2) {
            return checkDigit == remainder;
        }

        return checkDigit == 11 - remainder;
    }
}
