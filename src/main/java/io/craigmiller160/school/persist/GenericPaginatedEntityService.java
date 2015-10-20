package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericPaginatedEntityService extends GenericEntityService{

	/**
	 * A convenience method to get the previous page of records of
	 * an entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param entityType the type of entity to retrieve records of.
	 * @param firstId the first ID of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the previous page of entities.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database. 
	 */
	<T> List<T> getPreviousEntities(Class<T> entityType, int firstId, int numRecords);
	
	/**
	 * A convenience method to get the next page of records of
	 * an entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param entityType the type of entity to retrieve records of.
	 * @param lastId the last ID of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the next page of entities. 
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> List<T> getNextEntities(Class<T> entityType, int lastId, int numRecords);
	
}
