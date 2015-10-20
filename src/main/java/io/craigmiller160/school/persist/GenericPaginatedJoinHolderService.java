package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

public interface GenericPaginatedJoinHolderService 
extends GenericJoinHolderService, GenericPaginatedEntityService{

	<T extends JoinHolder,U> List<T> getPreviousJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId, int lastPageFirstRowNum, int pageSize);
	
	<T extends JoinHolder,U> List<T> getNextJoinsFor(Class<T> joinHolderType,
			Class<U> joinedEntityType, int entityId, int lastPageLastRowNum, int pageSize);
	
}
