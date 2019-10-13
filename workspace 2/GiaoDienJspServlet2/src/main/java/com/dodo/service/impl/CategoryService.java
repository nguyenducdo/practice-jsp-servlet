package com.dodo.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dodo.dao.ICategoryDAO;
import com.dodo.model.Category;
import com.dodo.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

}
