package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

public interface GenericPaginatedJoinHolderDao<T extends JoinHolder> 
extends GenericJoinHolderDao<T>, GenericPaginatedDao<T>{

	<U> List<T> getPreviousJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageFirstRowNum, int pageSize);	
	
	<U> List<T> getNextJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageLastRowNum, int pageSize);
	
}
