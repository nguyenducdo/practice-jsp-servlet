package com.dodo.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dodo.dao.IUserDAO;
import com.dodo.model.User;
import com.dodo.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	IUserDAO userDAO;
	
	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public List<User> findByUsername(String userName) {
		return userDAO.findByUsername(userName);
	}

	@Override
	public User findOne(String userName, String password, Integer status) {
		return userDAO.findOne(userName, password, status);
	}

	@Override
	public User findOne(long id) {
		return userDAO.findOne(id);
	}

}
