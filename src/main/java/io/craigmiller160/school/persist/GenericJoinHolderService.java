package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericJoinHolderService {
	
	<T> void joinEntities(Class<T> joinHolderType, Object...entitiesToJoin);

	<T,U> List<T> getAllJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId);
	
	<T,U> List<T> getPreviousJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId, int firstId, int numRecords);
	
	<T,U> List<T> getNextJoinsFor(Class<T> joinHolderType,
			Class<U> joinedEntityType, int entityId, int lastId, int numRecords);
	
	<T,U> long getJoinCountFor(Class<T> joinHolderType, Class<U> joinedEntityType, int entityId);
	
}
