package com.dodo.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.dodo.dao.INewDAO;
import com.dodo.mapper.impl.NewMapper;
import com.dodo.model.New;
import com.dodo.paging.Pageble;
import com.dodo.sort.Sorter;

public class NewDAO extends AbstractDAO<New> implements INewDAO{
	
	@Override
	public List<New> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM news,category WHERE categoryid=category.id");
		NewMapper newMapper = new NewMapper();
		Sorter sorter = pageble.getSorter();
		if(sorter!=null) {
			sql.append(" ORDER BY " + sorter.getSortName() + " " + sorter.getSortBy());
		}
		
		if(pageble.getOffset()!=null) {
			sql.append(" LIMIT ?,?");
			return query(sql.toString(),newMapper,pageble.getOffset(),pageble.getLimit());
		}
		return query(sql.toString(),newMapper);
	}

	@Override
	public List<New> findByCategoryId(long id) {
		String sql = "SELECT * FROM news,category WHERE categoryid=category.id AND categoryid=?";
		NewMapper newMapper = new NewMapper();
		return query(sql, newMapper, id);
	}

	@Override
	public Long insert(New newObj) {
		String sql = "INSERT INTO news(title,thumbnail,shortdescription,content,categoryid) VALUES(?,?,?,?,?)";
		String title = newObj.getTitle();
		String thumbnail = newObj.getThumbnail();
		String shortdescription = newObj.getShortDescription();
		String content = newObj.getContent();
		Long categoryid = newObj.getCategoryId();
		return save(sql, title,thumbnail,shortdescription,content,categoryid);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id=?";
		modify(sql, id);
	}

	@Override
	public void update(New newObj) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE news SET ");
		sql.append("title=?, thumbnail=?, shortdescription=?, ");
		sql.append("content=?, categoryid=?, ");
		sql.append("createddate=?, modifieddate=?, createdby=?, modifiedby=? ");
		sql.append("WHERE id=?");
		String title = newObj.getTitle();
		String thumbnail = newObj.getThumbnail();
		String shortdescription = newObj.getShortDescription();
		String content = newObj.getContent();
		Long categoryid = newObj.getCategoryId();
		Timestamp createddate = newObj.getCreatedDate();
		Timestamp modifieddate = newObj.getModifiedDate();
		String createdby = newObj.getCreatedBy();
		String modifiedby = newObj.getModifiedBy();
		Long id = newObj.getId();
		modify(sql.toString(),
				title, thumbnail, shortdescription,
				content, categoryid,
				createddate, modifieddate, createdby, modifiedby, id);
	}

	@Override
	public New findOne(long id) {
		String sql = "SELECT * FROM news,category WHERE categoryid=category.id AND news.id=?";
		NewMapper newMapper = new NewMapper();
		List<New> listNews = query(sql, newMapper, id);
		if(listNews==null || listNews.isEmpty()) return null;
		return listNews.get(0);
	}

	@Override
	public Integer countTotalItems() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

}
