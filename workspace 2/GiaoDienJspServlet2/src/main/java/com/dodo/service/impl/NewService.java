package com.dodo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.dodo.dao.INewDAO;
import com.dodo.model.New;
import com.dodo.paging.PageRequest;
import com.dodo.paging.Pageble;
import com.dodo.service.INewService;
import com.dodo.sort.Sorter;

public class NewService implements INewService{

	@Inject
	private INewDAO newDAO;
	
	@Override
	public List<New> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public New findOne(long id) {
		return newDAO.findOne(id);
	}

	@Override
	public List<New> findByCategoryId(long id) {
		return newDAO.findByCategoryId(id);
	}

	@Override
	public Long insert(New newObj) {
		newObj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newObj.setCreatedBy("");
		return newDAO.insert(newObj);
	}

	

	@Override
	public void update(New newObj) {
		New oldObj = findOne(newObj.getId());
		if(oldObj==null) {
			System.out.println("Khong tim thay id: "+newObj.getId());
			return;
		}
		newObj.setCreatedBy(oldObj.getCreatedBy());
		newObj.setCreatedDate(oldObj.getCreatedDate());
		newObj.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		newObj.setModifiedBy("");
		newDAO.update(newObj);
	}

	@Override
	public void delete(long[] ids) {
//		System.out.println(ids.getClass());
		for(long id : ids) {
			newDAO.delete(id);
		}
	}

	@Override
	public Integer countTotalItems() {
		return newDAO.countTotalItems();
	}

	public Pageble getPagebleForList(String pageStr, String limitStr, String sortName, String sortBy) {
		int page=1;
		if(pageStr!=null && !pageStr.isEmpty()) {
			try {
				page = Integer.parseInt(pageStr);
			}catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		int limit=5;
		if(limitStr!=null && !limitStr.isEmpty()) {
			try {
				limit = Integer.parseInt(limitStr);
			}catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		Sorter sorter = null;
		if(sortName!=null && !sortName.isEmpty() && sortBy!=null && !sortBy.isEmpty()) {
			sorter = new Sorter(sortName, sortBy);
		}
		Pageble pageble = new PageRequest(page, limit, sorter);
		return pageble;
	}

	@Override
	public Pageble getPagebleForList(String pageStr, String limitStr) {
		return getPagebleForList(pageStr, limitStr, null, null);
	}

}
