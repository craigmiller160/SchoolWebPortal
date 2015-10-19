package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericPaginatedDao<T> extends GenericDao<T>{

	List<T> getPreviousEntities(int firstId, int numRecords);
	
	List<T> getNextEntities(int lastId, int numRecords);
	
}
