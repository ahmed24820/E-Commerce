package com.my_project.e_commerce.Security.CustomFilter;

import com.my_project.e_commerce.Exceptions.JWTExpiration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    @Value("${Secret-key}")
    private String secret_key;
     @Value("${access-token-expiration}")
    private long access_expiration;
    @Value("${refresh-token-expiration}")
    private long refresh_expiration;

    public String GenerateToken(UserDetails userDetails){
        return Create_Access_Token(new HashMap<>(),userDetails);
    }
    public String Create_Access_Token(
            Map<String,Object> My_Claims,
            UserDetails userDetails)
    {
        return CreateToken(My_Claims,userDetails,access_expiration);
    }
    public String Create_Refresh_Token(
            UserDetails userDetails)
    {
        return CreateToken(new HashMap<>(),userDetails,refresh_expiration);
    }

    public String CreateToken(
            Map<String,Object> My_Claims,
            UserDetails userDetails,
            long expiration)
    {
        return Jwts.
                builder()
                .setClaims(My_Claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignkey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String ExtractUsername(String token){
        return ExtractItem(token,Claims::getSubject) ;
    }

    public boolean valid(String token, UserDetails userDetails){
          String username = ExtractUsername(token);
        return (username.equals(userDetails.getUsername()) && !CheckExpiration(token));
    }
    public boolean CheckExpiration(String token){
        return ExtractItem(token,Claims::getExpiration).before(new Date());
    }

    private Claims ExtractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private <T> T ExtractItem(String token, Function<Claims,T> claimsTFunction){
        Claims claims=ExtractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    private Key getSignkey() {
        byte[] key = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(key);
 }

}
