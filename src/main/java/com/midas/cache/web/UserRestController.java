package com.midas.cache.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.cache.domain.User;
import com.midas.cache.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserRestController {

	private final UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		long startTime = System.currentTimeMillis();
		User user = userService.findUser(id);
		log.info("캐시 적용 전/후: {}ms", System.currentTimeMillis() - startTime);

		return new ResponseEntity<>(
			user,
			HttpStatus.OK
		);
	}
}
