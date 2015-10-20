package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

public interface GenericJoinHolderService extends GenericEntityService{
	
	<T extends JoinHolder> void joinEntities(Class<T> joinHolderType, Object...entitiesToJoin);

	<T extends JoinHolder,U> List<T> getAllJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId);
	
	<T extends JoinHolder,U> long getJoinCountFor(Class<T> joinHolderType, Class<U> joinedEntityType, int entityId);
	
}
