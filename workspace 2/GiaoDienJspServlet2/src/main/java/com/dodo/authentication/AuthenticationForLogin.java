package com.dodo.authentication;

import com.dodo.model.User;

public class AuthenticationForLogin {
	private User user;
	private String errorMessage;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
