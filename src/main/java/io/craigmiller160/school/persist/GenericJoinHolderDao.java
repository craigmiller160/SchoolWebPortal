package io.craigmiller160.school.persist;

import java.util.List;


public interface GenericJoinHolderDao<T> extends GenericPaginatedDao<T>{

	<U> List<T> getAllJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> List<T> getPreviousJoinsFor(Class<U> joinedEntityType, 
			int entityId, int firstId, int numRecords);	
	
	<U> List<T> getNextJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastId, int numRecords);
	
	<U> void removeJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> long getJoinCountFor(Class<U> joinedEntityType, int entityId);
	
}
