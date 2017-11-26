package com.jp.jwtspringauth.server.web.rest.dto;

import lombok.Data;

@Data
public class ManagedUserDTO {
    private String mail;
    private String password;
}
