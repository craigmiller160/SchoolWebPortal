package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * A generic interface for service class methods handling
 * <tt>JoinHolder</tt> entities. These build on the basic
 * CRUD operations inherited from <tt>GenericEntityService</tt>
 * by providing operations for manipulating <tt>JoinHolders</tt>
 * via the entities they contain. 
 * 
 * @author craig
 * @version 1.0
 */
public interface GenericJoinHolderService extends GenericEntityService{
	
	/**
	 * Join the provided entities together using the type
	 * of <tt>JoinHolder</tt> specified. If the specified
	 * <tt>JoinHolder</tt> type isn't appropriate for the
	 * provided entities, an exception is thrown.
	 * 
	 * @param joinHolderType the type of <tt>JoinHolder</tt> to use to join the entities.
	 * @param entitiesToJoin any number of entities to join using the <tt>JoinHolder</tt>
	 * @throws IllegalArgumentException if the <tt>JoinHolder</tt> type object is not
	 * a supported type of <tt>JoinHolder</tt>, or if the entities provided
	 * are not supported by the <tt>JoinHolder</tt> type.
	 */
	<T extends JoinHolder> void joinEntities(Class<T> joinHolderType, Object...entitiesToJoin);

	/**
	 * Get all <tt>JoinHolder</tt> entities that hold the entity
	 * specified by the provided type and unique ID. If the specified
	 * <tt>JoinHolder</tt> type isn't appropriate for the
	 * provided entities, an exception is thrown.
	 * 
	 * @param joinHolderType the type of <tt>JoinHolder</tt> the entity is joined with.
	 * @param joinedEntityType the type of entity to get joins for.
	 * @param entityId the unique ID of the entity to get joins for.
	 * @return all <tt>JoinHolder</tt> entities that hold the entity
	 * specified.
	 * @throws IllegalArgumentException if the <tt>JoinHolder</tt> type object is not
	 * a supported type of <tt>JoinHolder</tt>, or if the entities provided
	 * are not supported by the <tt>JoinHolder</tt> type.
	 */
	<T extends JoinHolder,U> List<T> getAllJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId);
	
	/**
	 * Get a count of the number of <tt>JoinHolder</tt> entities that hold the entity
	 * specified by the provided type and unique ID. If the specified
	 * <tt>JoinHolder</tt> type isn't appropriate for the
	 * provided entities, an exception is thrown.
	 * 
	 * @param joinHolderType the type of <tt>JoinHolder</tt> the entity is joined with.
	 * @param joinedEntityType the type of entity to get joins for.
	 * @param entityId the unique ID of the entity to get joins for.
	 * @return a count of the number of <tt>JoinHolder</tt> entities that hold the entity
	 * specified.
	 * @throws IllegalArgumentException if the <tt>JoinHolder</tt> type object is not
	 * a supported type of <tt>JoinHolder</tt>, or if the entities provided
	 * are not supported by the <tt>JoinHolder</tt> type.
	 */
	<T extends JoinHolder,U> long getJoinCountFor(Class<T> joinHolderType, Class<U> joinedEntityType, int entityId);
	
}
