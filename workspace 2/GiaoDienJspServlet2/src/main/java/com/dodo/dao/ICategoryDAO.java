package com.dodo.dao;

import java.util.List;

import com.dodo.model.Category;

public interface ICategoryDAO {
	List<Category> findAll();
}
