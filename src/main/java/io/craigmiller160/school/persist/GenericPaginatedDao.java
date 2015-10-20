package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericPaginatedDao<T> extends GenericDao<T>{

	List<T> getPreviousEntities(int lastPageFirstRowNum, int pageSize);
	
	List<T> getNextEntities(int lastPageLastRowNum, int pageSize);
	
}
