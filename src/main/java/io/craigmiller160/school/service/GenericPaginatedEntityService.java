package io.craigmiller160.school.service;

import java.util.List;

/**
 * An expansion of the <tt>GenericEntityService</tt> interface with additional
 * methods to facilitate paginated access to data. Like other service classes, implementations
 * of this class can work with multiple DAOs and should provide transactional
 * support.
 * 
 * @author craig
 * @version 1.0
 */
public interface GenericPaginatedEntityService{

	
	
	/**
	 * A convenience method to get the previous page of records of
	 * an entity from the database. Used to facilitate pagination
	 * behavior. 
	 * <p>
	 * To use this method properly, the application should track
	 * the first row number (NOT the primary key) on the currently
	 * displayed page, to pass as the <tt>lastPageFirstRowNum</tt>
	 * parameter. In addition, the <tt>pageSize</tt> value should
	 * stay consistent between calls, unless it is specifically
	 * changed by the client. These guidelines will minimize
	 * inconsistencies with pagination.
	 * <p>
	 * <b>NOTE:</b> Ongoing CRUD operations on the underlying
	 * database will likely effect pagination, so implementations
	 * should take this into account in trying to be as flexible as
	 * possible.
	 * 
	 * @param entityType the type of entity to retrieve records of.
	 * @param lastPageFirstRowNum the first row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of records to return for the page.
	 * @return a list of the previous page of entities.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database. 
	 */
	<T> List<T> getPreviousEntities(Class<T> entityType, int lastPageFirstRowNum, int pageSize);
	
	/**
	 * A convenience method to get the next page of records of
	 * an entity from the database. Used to facilitate pagination
	 * behavior.
	 * <p>
	 * To use this method properly, the application should track
	 * the last row number (NOT the primary key) on the currently
	 * displayed page, to pass as the <tt>lastPageLastRowNum</tt>
	 * parameter. In addition, the <tt>pageSize</tt> value should
	 * stay consistent between calls, unless it is specifically
	 * changed by the client. These guidelines will minimize
	 * inconsistencies with pagination.
	 * <p>
	 * <b>NOTE:</b> Ongoing CRUD operations on the underlying
	 * database will likely effect pagination, so implementations
	 * should take this into account in trying to be as flexible as
	 * possible.
	 * 
	 * @param entityType the type of entity to retrieve records of.
	 * @param lastPageLastRowNum the last row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of records to return for the page.
	 * @return a list of the next page of entities. 
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 */
	<T> List<T> getNextEntities(Class<T> entityType, int lastPageLastRowNum, int pageSize);
	
}
