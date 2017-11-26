package com.jp.jwtspringauth.server.configuration;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jp.jwtspringauth.server.dao.UserDao;
import com.jp.jwtspringauth.server.domain.User;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserDao userDao;
    
    @Autowired
    public DomainUserDetailsService(final UserDao userDao) {
    	this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(final String mail) {
        log.debug("Authenticating {}", mail);
        Optional<User> userFromDatabase = userDao.findOneByMail(mail);
        return userFromDatabase.map(user -> {
            return new org.springframework.security.core.userdetails.User(mail, user.getPassword(), new ArrayList<>());
        }).orElseThrow(() -> 
        	new UsernameNotFoundException("User not found"));
    }
}

