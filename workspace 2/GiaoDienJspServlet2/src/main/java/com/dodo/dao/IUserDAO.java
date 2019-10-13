package com.dodo.dao;

import java.util.List;

import com.dodo.model.User;

public interface IUserDAO {
	List<User> findAll();
	List<User> findByUsername(String userName);
	User findOne(String userName, String password, Integer status);
	User findOne(long id);
}
