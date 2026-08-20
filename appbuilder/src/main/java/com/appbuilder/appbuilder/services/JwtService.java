package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.entity.UserEntity;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {

    public String generateToken(UserEntity user);
    public String extractUserId(String token);
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

}
