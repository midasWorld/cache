package com.midas.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.midas.cache.domain.User;
import com.midas.cache.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Cacheable(value = "users", key = "#id")
	public User findUser(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Could not found User with id=" + id));
	}

	@CachePut(value = "users", key = "#id")
	public User changePassword(Long id, String password) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Could not found User with id=" + id));
		user.changePassword(password);
		return user;
	}

	@CacheEvict(value = "users", key = "#id")
	public void delete(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Could not found User with id=" + id));
		userRepository.delete(user);
	}
}
