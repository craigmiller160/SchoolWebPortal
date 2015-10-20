package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericPaginatedJoinHolderDao<T> 
extends GenericJoinHolderDao<T>, GenericPaginatedDao<T>{

	<U> List<T> getPreviousJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageFirstRowNum, int pageSize);	
	
	<U> List<T> getNextJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageLastRowNum, int pageSize);
	
}
