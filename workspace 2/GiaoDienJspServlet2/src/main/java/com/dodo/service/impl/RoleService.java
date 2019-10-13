package com.dodo.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import com.dodo.dao.IRoleDAO;
import com.dodo.model.Role;
import com.dodo.service.IRoleService;

@ManagedBean
public class RoleService implements IRoleService{
	
//	private IRoleDAO roleDAO;
//	
//	public RoleService() {
//		roleDAO = new RoleDAO();
//	}
	
	@Inject
	private IRoleDAO roleDAO;
	
	@Override	
	public List<Role> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public List<Role> findByCode(String code) {
		return roleDAO.findByCode(code);
	}

	@Override
	public Long save(Role role) {
		return roleDAO.insert(role);
	}

	@Override
	public void delete(long id) {
		roleDAO.delete(id);
	}

	@Override
	public void update(Role role) {
		roleDAO.update(role);
	}

}
