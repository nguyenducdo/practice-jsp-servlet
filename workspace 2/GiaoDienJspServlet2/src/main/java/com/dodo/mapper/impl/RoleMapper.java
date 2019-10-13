package com.dodo.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dodo.mapper.IRowMapper;
import com.dodo.model.Role;

public class RoleMapper implements IRowMapper<Role>{

	@Override
	public Role mapRow(ResultSet rs) {
		try {
			Role role = new Role();
			role.setId(rs.getLong("id"));
			role.setName(rs.getString("name"));
			role.setCode(rs.getString("code"));
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
