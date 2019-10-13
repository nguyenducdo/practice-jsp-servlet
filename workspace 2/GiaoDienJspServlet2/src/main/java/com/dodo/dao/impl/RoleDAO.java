package com.dodo.dao.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import com.dodo.dao.IRoleDAO;
import com.dodo.mapper.impl.RoleMapper;
import com.dodo.model.Role;

@ManagedBean
public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO{
	
	@Override
	public List<Role> findAll() {
		String sql = "SELECT * FROM role";
		RoleMapper roleMapper = new RoleMapper();
		return query(sql, roleMapper);
	}

	@Override
	public List<Role> findByCode(String code) {
		String sql = "SELECT * FROM role WHERE code=?";
		RoleMapper roleMapper = new RoleMapper();
		return query(sql, roleMapper, code);
	}

	@Override
	public Long insert(Role role) {
		String sql = "INSERT INTO role(name,code) VALUES(?,?)";
		return save(sql, role.getName(),role.getCode());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM role WHERE id=?";
		modify(sql, id);
	}

	@Override
	public void update(Role role) {
		String sql = "UPDATE role SET name=?, code=? WHERE id=?";
		modify(sql, role.getName(), role.getCode(), role.getId());
	}
	
	

}
