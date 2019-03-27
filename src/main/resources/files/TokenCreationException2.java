package com.yevhenii.to_do_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TokenCreationException extends Exception {
    public TokenCreationException(String str, com.auth0.jwt.exceptions.JWTCreationException e) {
    }
}
