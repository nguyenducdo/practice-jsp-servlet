package com.dodo.dao.impl;

import java.util.List;

import com.dodo.dao.IUserDAO;
import com.dodo.mapper.impl.UserMapper;
import com.dodo.model.User;

public class UserDAO extends AbstractDAO<User> implements IUserDAO{

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		return query(sql, new UserMapper());
	}

	@Override
	public List<User> findByUsername(String userName) {
		String sql = "SELECT * FROM user WHERE username=?";
		return query(sql, new UserMapper(),userName);
	}

	@Override
	public User findOne(String userName, String password, Integer status) {
		List<User> list = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user,role WHERE user.roleid=role.id AND username=? AND password=?");
		if(status!=null) {
			sql.append(" AND status=?");
			list = query(sql.toString(), new UserMapper(), userName, password, status);
		}else {
			list = query(sql.toString(), new UserMapper(), userName, password);
		}
		
		if(list==null || list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}

	@Override
	public User findOne(long id) {
		List<User> list = null;
		String sql = "SELECT * FROM user WHERE id=?";
		list = query(sql, new UserMapper(),id);
		if(list==null || list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}

}
