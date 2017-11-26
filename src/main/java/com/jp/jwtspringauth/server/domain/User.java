package com.jp.jwtspringauth.server.domain;


import lombok.Data;

@Data
public class User {
	private String mail;
    private String password;
}
