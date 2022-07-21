package com.midas.cache.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateRequest {
	private String password;

	public UserUpdateRequest(String password) {
		this.password = password;
	}
}
