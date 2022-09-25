package com.likelion.bliond.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;
    private final String AUTHORITIES_KEY = "auth";
    private final String BEARER_TYPE = "bearer";
    private final Long ACCESS_TOKEN_EXPIRE_TIME = (1000L * 60L * 30L * 24L * 30L);
    private SecretKey key;



    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public JwtDto generateToken(MemberContext memberContext) {
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        List<GrantedAuthority> authorities = memberContext.getAuthorities().stream().toList();
        String accessToken = Jwts.builder()
            .claim("username", memberContext.getUsername())
            .claim("id",memberContext.getId().toString())
            .claim(AUTHORITIES_KEY, authorities.get(0).getAuthority())
            .setExpiration(accessTokenExpiresIn) // payload "exp": 1516239022 (예시)
            .signWith(key, SignatureAlgorithm.HS512) // header "alg": "HS512"
            .compact();

        log.info("accessToken: " + accessToken);

//        String refreshToken = Jwts.builder()
//            .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
//            .signWith(key, SignatureAlgorithm.HS512)
//            .compact();

        return JwtDto.builder()
            .accessToken(accessToken)
//            .refreshToken(refreshToken)
            .bearer_type(BEARER_TYPE)
            .expiresIn(ACCESS_TOKEN_EXPIRE_TIME)
            .build();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            log.info("claims: " + claims);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token");
            log.error("Invalid JWT token trace: " + e.getMessage());
        }

        return false;
    }

    public String getId(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .deserializeJsonWith(new JacksonDeserializer<>())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("id", String.class);
    }

    public String getAuthorities(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get(AUTHORITIES_KEY, String.class);
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("username", String.class);
    }

    public String getNickname(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("nickname", String.class);
    }

    public JwtDto generateTokenTest(String username, String id, String auth, String nickname) {
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
            .claim("username", username)
            .claim("id", id)
            .claim("nickname", nickname)
            .claim(AUTHORITIES_KEY, auth)
            .setExpiration(accessTokenExpiresIn) // payload "exp": 1516239022 (예시)
            .signWith(key, SignatureAlgorithm.HS512) // header "alg": "HS512"
            .compact();

        log.info("accessToken: " + accessToken);

//        String refreshToken = Jwts.builder()
//            .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
//            .signWith(key, SignatureAlgorithm.HS512)
//            .compact();

        return JwtDto.builder()
            .accessToken(accessToken)
//            .refreshToken(refreshToken)
            .bearer_type(BEARER_TYPE)
            .expiresIn(ACCESS_TOKEN_EXPIRE_TIME)
            .build();
    }
}
