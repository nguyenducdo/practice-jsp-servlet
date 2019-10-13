package com.dodo.dao.impl;

import java.util.List;

import com.dodo.dao.ICategoryDAO;
import com.dodo.mapper.impl.CategoryMapper;
import com.dodo.model.Category;

public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO{

	@Override
	public List<Category> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

}
