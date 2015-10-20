package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericJoinHolderService extends GenericEntityService{
	
	<T> void joinEntities(Class<T> joinHolderType, Object...entitiesToJoin);

	<T,U> List<T> getAllJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId);
	
	<T,U> long getJoinCountFor(Class<T> joinHolderType, Class<U> joinedEntityType, int entityId);
	
}
