package com.dodo.model;

import java.sql.Timestamp;
import java.util.List;

public abstract class AbstractModel<T> {
	private long id;
	private Timestamp createdDate;
	private Timestamp ModifiedDate;
	private String createdBy;
	private String modifiedBy;
	
	private long[] ids;
	private List<T> list;
	private int totalPages;
	private int startPage;
	private int maxItemInPage;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public long[] getIds() {
		return ids;
	}
	public void setIds(long[] ids) {
		this.ids = ids;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getMaxItemInPage() {
		return maxItemInPage;
	}
	public void setMaxItemInPage(int maxItemInPage) {
		this.maxItemInPage = maxItemInPage;
	}
	
}
