package com.dodo.dao;

import java.util.List;

import com.dodo.model.Role;

public interface IRoleDAO{
	List<Role> findAll();
	List<Role> findByCode(String code);
	Long insert(Role role);
	void delete(long id);
	void update(Role role);
}
