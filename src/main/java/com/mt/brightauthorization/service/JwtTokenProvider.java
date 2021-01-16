package com.mt.brightauthorization.service;


import com.mt.brightauthorization.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getSubject(String token) {
        return Jwts.parser().
                setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(Users user){
        Claims claims = Jwts.claims().setSubject(user.getPhone());
        claims.put("userId", user.getId() + "");
        return doGenerateToken(claims);
    }

    private String doGenerateToken(Claims claims){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY)
                .compact();
    }

}
