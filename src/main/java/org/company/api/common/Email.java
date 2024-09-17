package org.company.api.common;


import org.apache.commons.validator.routines.EmailValidator;

public record Email(String email) {
    private static final EmailValidator VALIDATOR = EmailValidator.getInstance(true, true);

    public Email {
        if (!VALIDATOR.isValid(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }
}
