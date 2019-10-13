package com.dodo.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dodo.mapper.IRowMapper;
import com.dodo.model.Category;

public class CategoryMapper implements IRowMapper<Category>{

	@Override
	public Category mapRow(ResultSet rs) {
		Category category = new Category();
		try {
			category.setId(rs.getLong("id"));
			category.setCode(rs.getString("code"));
			category.setName(rs.getString("name"));
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
