package com.dodo.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dodo.mapper.IRowMapper;
import com.dodo.model.Category;
import com.dodo.model.New;

public class NewMapper implements IRowMapper<New>{

	@Override
	public New mapRow(ResultSet rs) {
		try {
			New result = new New();
			result.setId(rs.getLong("id"));
			result.setTitle(rs.getString("title"));
			result.setThumbnail(rs.getString("thumbnail"));
			result.setShortDescription(rs.getString("shortdescription"));
			result.setContent(rs.getString("content"));
			result.setCategoryId(rs.getLong("categoryid"));
			Category category = new Category();
			category.setId(rs.getLong("categoryid"));
			category.setCode(rs.getString("code"));
			category.setName(rs.getString("name"));
			result.setCategory(category);
			result.setCreatedBy(rs.getString("createdby"));
			result.setCreatedDate(rs.getTimestamp("createddate"));
			result.setModifiedBy(rs.getString("modifiedby"));
			result.setModifiedDate(rs.getTimestamp("modifieddate"));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
