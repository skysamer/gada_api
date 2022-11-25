package com.smartmobility.gada_api.config;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.domain.Role;
import com.smartmobility.gada_api.member.service.OauthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Log log = LogFactory.getLog(getClass());

    @Value("${spring.jwt.secret-key}")
    private String secretKey;
    private final OauthService service;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createTokenLogin(String sub, Role role) {
        Claims claims = Jwts.claims().setSubject(sub);
        claims.put("role", role);
        Date now = new Date();

        long tokenValidTime = 24 * 60 * 60 * 1000L;

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createLongTermTokenLogin(String sub, String role) {
        Claims claims = Jwts.claims().setSubject(sub);
        claims.put("role", role);
        Date now = new Date();

        long tokenValidTime = ((24 * 60 * 60 * 1000L) * 30) * 3;

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Member member = (Member) service.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(member, "", Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())));
    }

    public String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(jwtToken);
            log.info(claims.getBody().getExpiration());
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
}
