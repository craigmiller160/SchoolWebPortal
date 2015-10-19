package io.craigmiller160.school.persist;

import java.util.List;


public interface JoinHolderDao<T> extends GenericPaginatedDao<T>{

	<U> List<T> getAllJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> List<T> getPreviousJoinsFor(Class<U> joinedEntityType, 
			int entityId, long firstId, int numRecords);	
	
	<U> List<T> getNextJoinsFor(Class<U> joinedEntityType, 
			int entityId, long lastId, int numRecords);
	
	<U> List<T> removeJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> long getJoinCountFor(Class<U> joinedEntityType, int entityId);
	
}
