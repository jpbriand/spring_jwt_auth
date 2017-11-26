package com.jp.jwtspringauth.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jp.jwtspringauth.server.domain.User;

@Component
public class UserDao {
	
	private static final List<User> listUser = new ArrayList<>();

	public User createUser(User user) {
		listUser.add(user);
		return user;
	}

	public Optional<User> findOneByMail(String mail) {
		return listUser.stream().filter(user -> mail.equals(user.getMail())).findFirst();
	}
}
