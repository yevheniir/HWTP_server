package com.yevheniir.hwtp.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class AuthService {

    private String secretKey = "nanachi_say_naaa";
    private int timeExpire = 3600000;

    public boolean validToken(String token) {
        String[] parsedToken = token.split("\\.");

        String decodedString = new String(Base64.getDecoder().decode(parsedToken[0]));
        LocalDateTime tokenTime = LocalDateTime.parse(new String(Base64.getDecoder().decode(parsedToken[1])));

        return decodedString.equals(secretKey) && tokenTime.isBefore(LocalDateTime.now()) && ChronoUnit.MILLIS.between(LocalDateTime.now(), tokenTime) < timeExpire;
    }

    public String createToken() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes()) + "."
                        + Base64.getEncoder().encodeToString(LocalDateTime.now().toString().getBytes());
    }
}
