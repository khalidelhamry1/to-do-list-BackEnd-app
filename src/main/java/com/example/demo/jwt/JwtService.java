package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SecretKey = "6A576E5A7234753778214125442A472D4B6150645367566B5870327335763879";
    private static final long EXPIRATION_DURATION = 100*60*60*24;

    public String extractEmail(String jwt) {
         return extractClaim(jwt , Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String Token , Function<Claims , T> ClaimResolver){
        final Claims claims = extractAllClaims(Token);
        return ClaimResolver.apply(claims);
    }
    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails user){
        return generateToken(new HashMap<>(), user);
    }

    public String generateToken(Map<String , Object> extraClaims ,
                                UserDetails user){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DURATION))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token , UserDetails user){
        final String  email = extractEmail(token);
        return ( email.equals(user.getUsername()) ) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        //Date Expiration = extractClaim(token , Claims::getExpiration);
        //return Expiration.before(new Date(System.currentTimeMillis()));
         return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
