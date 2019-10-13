package com.dodo.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dodo.mapper.IRowMapper;
import com.dodo.model.Role;
import com.dodo.model.User;

public class UserMapper implements IRowMapper<User>{

	@Override
	public User mapRow(ResultSet rs) {
		User user = null;
		try {
			user = new User();
			user.setId(rs.getLong("user.id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("fullname"));
			user.setStatus(rs.getInt("status"));
			Role role = new Role();
			role.setId(rs.getLong("role.id"));
			role.setCode(rs.getString("code"));
			role.setName(rs.getString("name"));
			user.setRole(role);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
