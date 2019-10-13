package com.dodo.service;

import java.util.List;

import com.dodo.model.Role;

public interface IRoleService {
	List<Role> findAll();
	List<Role> findByCode(String code);
	Long save(Role role);
	void delete(long id);
	void update(Role role);
}
