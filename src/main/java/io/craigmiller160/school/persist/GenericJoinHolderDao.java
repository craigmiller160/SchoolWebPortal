package io.craigmiller160.school.persist;

import java.util.List;


public interface GenericJoinHolderDao<T> extends GenericDao<T>{

	<U> List<T> getAllJoinsFor(Class<U> joinedEntityType, int entityId);
	
	<U> void removeJoinsFor(Class<U> joinedEntityType, int entityId);
	
	//TODO restore this only if it's actuall necessary
	/*<U,V> void removeJoinFor(Class<U> firstJoinedEntityType, int firstEntityId, 
			Class<V> secondJoinedEntityType, int secondEntityId);*/
	
	<U> long getJoinCountFor(Class<U> joinedEntityType, int entityId);
	
}
