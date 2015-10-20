package io.craigmiller160.school.persist;

import java.util.List;


public interface GenericJoinHolderDao<T> extends GenericDao<T>{

	<U> List<T> getAllJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> void removeJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> long getJoinCountFor(Class<U> joinedEntityType, int entityId);
	
}
