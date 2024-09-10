package com.emazon.api_user.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponseConstants {
    USER_ALREADY_EXISTS("There is already an user with that email"),
    USER_EMAIL_CORRECT("The email does not have the correct structure"),
    USER_PHONE_CORRECT("Invalid phone format"),
    MINOR_INVALID("The user must not be a minor"),
    FIELD_INVALID("The request arrived with an invalid format, please validate the request and try again.");

    private final String message;

    ExceptionResponseConstants(String message) {
        this.message = message;
    }

}
