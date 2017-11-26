package com.jp.jwtspringauth.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	
	@Value("${jwt.secret}")
	public String jwtSecret;
}
