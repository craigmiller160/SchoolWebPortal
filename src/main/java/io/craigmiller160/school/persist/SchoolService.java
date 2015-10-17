package io.craigmiller160.school.persist;

import java.util.List;

/**
 * Interface for the service layer for the Class Schedule program. It
 * performs additional operations on top of the basic
 * CRUD operations of the DAOs. 
 * 
 * @author craig
 * @version 1.0
 */
public interface SchoolService {

	/**
	 * Save changes to an existing entity in the 
	 * database.
	 * 
	 * @param entity the entity to update in the database.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> void saveEntity(T entity);
	
	/**
	 * Convenience class for creating new instances of
	 * entities and inserting them into the database.
	 * This method will return an entity of the specified
	 * type, with values set to whatever fields are provided
	 * in the varargs params argument.
	 * <p>
	 * <b>NOTE:</b> For the varargs params argument, the order
	 * of the arguments must match a constructor of the 
	 * entity to be created. Not specifying all values is
	 * permitted, but the order must be maintained with null
	 * placeholder values.
	 * 
	 * @param entityType the type of entity to create.
	 * @param params the values to set to the entity's fields.
	 * @return the created entity, having already been inserted
	 * into the database.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> T createEntity(Class<T> entityType, Object...params);
	
	/**
	 * Insert the entity into the database. This is to 
	 * be used with an entity that has not already been
	 * added, and an exception may be thrown if called on
	 * an entity that already exists.
	 * 
	 * @param entity the entity to insert into the database.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 * @throws RuntimeException a subclass of this exception is
	 * thrown if the entity already exists in the database.
	 */
	<T> void insertEntity(T entity);
	
	/**
	 * Get a list of all persisted entities of the
	 * specified type.
	 * 
	 * @param entityType the type of entity to get all records of.
	 * @return a list of all persisted entities of the specified type.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> List<T> getAllEntities(Class<T> entityType);
	
	/**
	 * Get a list of all persisted entities of the 
	 * specified type, within the range of indexes
	 * provided. These indexes are ROW NUMBERS, they
	 * are NOT primary key/id values.
	 * <p>
	 * This method is a convenience method to help
	 * with pagination in the view.
	 * 
	 * @param entityType the type of entity to get records of.
	 * @param startIndex the first row to retrieve a record from.
	 * @param endIndex the last row to retrieve a record from.
	 * @return a list of all persisted entities of the 
	 * specified type, within the range of indexes
	 * provided.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> List<T> getEntitiesInRange(Class<T> entityType, 
		long startIndex, long endIndex);
	
	/**
	 * Get an entity of the specified type from the 
	 * database matching the provided id.
	 * 
	 * @param entityType the type of entity to retrieve.
	 * @param entityId the id of the entity to retrieve.
	 * @return the entity of the specified type matching the 
	 * provided id, or null if it doesn't exist.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> T getEntity(Class<T> entityType, int entityId);
	
	/**
	 * Get a count of the total number of entities
	 * of the specified type that have been persisted
	 * in the database.
	 * 
	 * @param entityType the type of entity to get a count of.
	 * @return a count of the total number of entities
	 * of the specified type.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> long getEntityCount(Class<T> entityType);
	
}
