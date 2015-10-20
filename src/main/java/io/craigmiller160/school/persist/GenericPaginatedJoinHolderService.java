package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericPaginatedJoinHolderService 
extends GenericJoinHolderService, GenericPaginatedEntityService{

	<T,U> List<T> getPreviousJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId, int lastPageFirstRowNum, int pageSize);
	
	<T,U> List<T> getNextJoinsFor(Class<T> joinHolderType,
			Class<U> joinedEntityType, int entityId, int lastPageLastRowNum, int pageSize);
	
}
