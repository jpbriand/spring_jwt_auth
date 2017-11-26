package com.jp.jwtspringauth.server.web.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.jwtspringauth.server.domain.User;
import com.jp.jwtspringauth.server.service.UserService;
import com.jp.jwtspringauth.server.web.rest.dto.ManagedUserDTO;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    	this.userService = userService;
    }

    @RequestMapping(path = "/register", method = POST, produces = APPLICATION_JSON_VALUE)
    public User register(@RequestBody ManagedUserDTO managedUser) {
        return userService.register(managedUser);
    }
}
