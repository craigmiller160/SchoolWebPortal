package io.craigmiller160.school.repo;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * A generic interface for DAO objects handling <tt>JoinHolder</tt>
 * interfaces. It provides special operations for interacting with the 
 * entities of a database via a join table. If operations are needed
 * to interact with the <tt>JoinHolders</tt> directly, this interface
 * can be used alongside an implementation of standard DAO methods. 
 * It can be implemented with basic JDBC implementations or ORM frameworks.
 * 
 * @author craig
 * @version 1.0
 * @param <T> an entity that implements the <tt>JoinHolder</tt> interface.
 */
public interface GenericJoinHolderDao<T extends JoinHolder>{

	/**
	 * Get a list of <tt>JoinHolder</tt> entities that are
	 * joined with the entity whose type and unique ID are
	 * specified in the parameters.
	 * 
	 * @param joinedEntityType the type of the entity to get the joins for.
	 * @param entityId the ID of the entity to get the joins for.
	 * @return a list of <tt>JoinHolder</tt> entities that are
	 * joined with the specified entity.
	 * @throws IllegalArgumentException if the type of entity provided
	 * is not a valid type for this class's <tt>JoinHolder</tt>.
	 * @param <U> the type of entity that is joined.
	 */
	<U> List<T> getAllJoinsFor(Class<U> joinedEntityType, int entityId);
	
	/**
	 * Remove all <tt>JoinHolder</tt> entities that are
	 * joined with the entity whose type and unique ID
	 * are specified in the parameters.
	 * 
	 * @param joinedEntityType the type of the entity to remove the joins for.
	 * @param entityId the ID of the entity to remove the joins for.
	 * @throws IllegalArgumentException if the type of entity provided
	 * is not a valid type for this class's <tt>JoinHolder</tt>.
	 * @param <U> the type of entity that is joined.
	 */
	<U> void removeJoinsFor(Class<U> joinedEntityType, int entityId);
	
	//TODO restore this only if it's actuall necessary
	/*<U,V> void removeJoinFor(Class<U> firstJoinedEntityType, int firstEntityId, 
			Class<V> secondJoinedEntityType, int secondEntityId);*/
	
	/**
	 * Get a count of the number of <tt>JoinHolder</tt> entities
	 * that are joined with the entity whose type and unique ID
	 * are specified in the parameters.
	 * 
	 * @param joinedEntityType the type of the entity to count the joins for.
	 * @param entityId the ID of the entity to count the joins for.
	 * @return the count of the number of <tt>JoinHolder</tt> entities
	 * that are joined with the entity.
	 * @throws IllegalArgumentException if the type of entity provided
	 * is not a valid type for this class's <tt>JoinHolder</tt>.
	 * @param <U> the type of entity that is joined.
	 */
	<U> long getJoinCountFor(Class<U> joinedEntityType, int entityId);
	
}
