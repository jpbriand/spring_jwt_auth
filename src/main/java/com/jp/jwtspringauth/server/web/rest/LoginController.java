package com.jp.jwtspringauth.server.web.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.jwtspringauth.server.service.JwtService;
import com.jp.jwtspringauth.server.web.rest.dto.ManagedUserDTO;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(path = "", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody ManagedUserDTO managedUser, HttpServletResponse response) {
    	
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(managedUser.getMail(), managedUser.getPassword());
        
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.tokenFor(managedUser.getMail());
            response.setHeader("Token", jwt);
            return ResponseEntity.ok().body(null);
        } catch (Exception ae) {
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
