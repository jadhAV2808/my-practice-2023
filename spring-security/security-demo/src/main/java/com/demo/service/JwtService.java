package com.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {

    String SECRET ="ee40ca7bc90df844d2f5b5667b27361a2350fad99352d8a6ce061c69e41e5d32";

    public String generateToken(String username){
        Map<String, Object> claims =new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims , String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(){
       byte[] keyBytes = Decoders.BASE64.decode(SECRET);
       return Keys.hmacShaKeyFor(keyBytes);
    }


    //method to extract token
    public String extractUsername(String token){
        return  extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims:: getExpiration);
    }

    public<T> T  extractClaim(String token, Function<Claims, T> claimsResolver) {

        final  Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token){
        return  Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                //.parseClaimsJwt(token)
                .parseClaimsJws(token)
                .getBody();

    }
    private  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
