package com.dodo.service;

import java.util.List;

import com.dodo.model.New;
import com.dodo.paging.Pageble;

public interface INewService {
	List<New> findAll(Pageble pageble);
	New findOne(long id);
	Integer countTotalItems();
	List<New> findByCategoryId(long id);
	Long insert(New newObj);
	void delete(long[] ids);
	void update(New newObj);
	Pageble getPagebleForList(String pageStr, String limitStr);
	Pageble getPagebleForList(String pageStr, String limitStr, String sortName, String sortBy);
}
