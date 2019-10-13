package com.dodo.dao;

import java.util.List;

import com.dodo.mapper.IRowMapper;

public interface GenericDAO<T> {
	List<T> query(String sql, IRowMapper<T> mapper, Object... params);
	Long save(String sql, Object... params);
	void modify(String sql, Object... params);
	Integer count(String sql, Object... params);
}
