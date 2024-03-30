package com.had.his.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    //generate key
    private SecretKey key(){
        byte[] decodedSecret = Decoders.BASE64URL.decode(jwtSecret);
        return Keys.hmacShaKeyFor(decodedSecret);
    }


    //generate JWT Token
    public String generateToken(UserDetails userDetails) {
        System.out.println("Token Generation started");

        String user1=userDetails.getUsername();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        System.out.println("hello");

        String token=Jwts.builder().subject(user1).issuedAt(currentDate).expiration(expireDate).signWith(key()).compact();

        System.out.println("Token Generated"+ token);
        return token;
    }

    //get email from jwtToken
    public String getuserfromtoken(String token)
    {

           Claims claims=Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
           return claims.getSubject();

    }

    private Date extractExpiration(String token)
    {

        Claims claims=Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
        return claims.getExpiration();

    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    //validate Jwt Token
    public  boolean validateToken(String token, UserDetails userDetails){
        try {
           Claims claims= Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
            return (claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch (MalformedJwtException malformedJwtException){
            throw new RuntimeException("Invalid Jwt Token",malformedJwtException);
        }
        catch (ExpiredJwtException expiredJwtException){
            throw new RuntimeException("token expired",expiredJwtException);
        }
        catch (UnsupportedJwtException unsupportedJwtException){
            throw new RuntimeException("Unsupported Jwt Token",unsupportedJwtException);
        }
        catch (IllegalArgumentException illegalArgumentException){
            throw new RuntimeException("Jwt claim String empty",illegalArgumentException);
        }

    }


}
