package com.jp.jwtspringauth.server.service;

import static java.time.ZoneOffset.UTC;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jp.jwtspringauth.server.configuration.Properties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {

	private Properties properties;
	
    @Autowired
    public JwtService(final Properties properties) {
    	this.properties = properties;
    }

    public String tokenFor(String username) throws IOException, URISyntaxException {
        String secretKey = this.properties.jwtSecret;
        Date expiration = Date.from(LocalDateTime.now().plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
