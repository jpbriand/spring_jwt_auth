package com.jp.jwtspringauth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jp.jwtspringauth.server.dao.UserDao;
import com.jp.jwtspringauth.server.domain.User;
import com.jp.jwtspringauth.server.web.rest.dto.ManagedUserDTO;

@Component
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    
	@Autowired
    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
    }

	public User register(final ManagedUserDTO managedUser) {
		User user = new User();
		user.setMail(managedUser.getMail());
		user.setPassword(passwordEncoder.encode(managedUser.getPassword()));
		userDao.createUser(user);
		return user;
	}
}
