package com.dodo.service;

import com.dodo.authentication.AuthenticationForLogin;

public interface ILoginService {
	AuthenticationForLogin authenticate(String username, String password);
}
