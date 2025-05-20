package com.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRECT_KEY = "4pi_pizz4";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRECT_KEY);

    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("api-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

}
