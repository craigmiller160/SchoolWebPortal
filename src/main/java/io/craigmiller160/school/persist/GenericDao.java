package io.craigmiller160.school.persist;

import java.util.List;

/**
 * Generic interface for performing basic CRUD operations and
 * other database interactions. Can be used with ORM frameworks
 * or vanilla JDBC code, depending on the implementation.
 * 
 * @author craig
 * @version 1.0
 * @param <T> the type of entity this DAO will persist.
 */
public interface GenericDao<T> {

	/**
	 * Insert a new entity into the database. The entity
	 * parameter cannot have the same unique ID as an
	 * entity already persisted in the database.
	 * 
	 * @param entity the entity to insert into the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void insertEntity(T entity);
	
	/**
	 * Update an entity already persisted in the database.
	 * The entity parameter must have already been inserted
	 * previously. 
	 * 
	 * @param entity the entity to update in the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void updateEntity(T entity);
	
	/**
	 * Delete an entity from the database. The entity
	 * parameter must have already been persisted previously.
	 * 
	 * @param entity the entity to delete from the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void deleteEntity(T entity);
	
	/**
	 * Get an entity from the database by its ID. If the specified
	 * entity does not exist in the database, this operation will fail.
	 * 
	 * @param entityId the unique ID of the entity to retrieve.
	 * @return the specified entity from the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	T getEntityById(int entityId);
	
	/**
	 * Get a count of all entities of this type in the database.
	 * 
	 * @return a count of all entities of this type in the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	long getEntityCount();
	
	/**
	 * Get a list of all entities of this type persisted in the
	 * database.
	 * 
	 * @return a list of all entities of this type persisted in
	 * the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<T> getAllEntities();
	
}
