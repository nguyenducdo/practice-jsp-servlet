package com.dodo.service.impl;

import java.util.ResourceBundle;

import javax.inject.Inject;

import com.dodo.authentication.AuthenticationForLogin;
import com.dodo.model.User;
import com.dodo.service.ILoginService;
import com.dodo.service.IUserService;

public class LoginService implements ILoginService{
	
	@Inject
	IUserService userSerivce;
	
	private ResourceBundle rBundle = ResourceBundle.getBundle("ErrorMessage");
	
	@Override
	public AuthenticationForLogin authenticate(String username, String password) {
		AuthenticationForLogin authen = new AuthenticationForLogin();
		String errorMessage = null;
		User user = null;
		if(username==null || username.isEmpty() || password == null || password.isEmpty()) {
			errorMessage = rBundle.getString("empty_field");
		}else {
			user = userSerivce.findOne(username, password, User.STATUS_ACTIVE);
			if(user==null) {
				errorMessage = rBundle.getString("username_password_invalid");
			}
		}
		authen.setErrorMessage(errorMessage);
		authen.setUser(user);
		return authen;
	}

}
