package com.dodo.dao;

import java.util.List;

import com.dodo.model.New;
import com.dodo.paging.Pageble;

public interface INewDAO {
	List<New> findAll(Pageble pageble);
	Integer countTotalItems();
	New findOne(long id);
	List<New> findByCategoryId(long id);
	Long insert(New newObj);
	void delete(long id);
	void update(New newObj);
}
