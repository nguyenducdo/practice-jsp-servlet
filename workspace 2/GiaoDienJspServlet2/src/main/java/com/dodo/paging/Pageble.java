package com.dodo.paging;

import com.dodo.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getLimit();
	Integer getTotalPages(int count);
	Integer getOffset();
	Sorter getSorter();
}
