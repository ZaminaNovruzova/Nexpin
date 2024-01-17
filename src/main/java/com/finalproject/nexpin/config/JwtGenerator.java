package com.finalproject.nexpin.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //JSON Veb Açar Dəsti (JWKS) tokenlərin, xüsusən də JWT-lərin həqiqiliyini və bütövlüyünü yoxlamaq üçün istifadə edilən ictimai kriptoqrafik açarlar toplusudur.
//hs 512 imzali alqoritmden istifade etmek ucun acar klasinin obyektine bu alqoritmi menimsedirik
    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expireDate).signWith(key, SignatureAlgorithm.HS512).compact();
        System.out.println("New Token: ");
        System.out.println(token);
        return token;
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("Jwt token expired", exception.fillInStackTrace());
        }
    }
}
