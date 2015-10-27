package io.craigmiller160.school.repo;

import java.util.List;

/**
 * A generic DAO interface that contains additional methods
 * for accessing data in a paginated way. It should be used
 * to expand the capabilities of an existing DAO implementation. 
 * It can be implemented with basic JDBC implementations or ORM 
 * frameworks.
 * 
 * @author craig
 * @version 1.0
 * @param <T> the type of entity this DAO persists.
 */
public interface GenericPaginatedEntityDao<T>{

	/**
	 * Get a "page" of of entities from the database, essentially
	 * a sub-list of records from the database. The size
	 * of a page is defined by the second parameter (pageRowCount).
	 * The offset is determined by the first parameter (startPageAfterRow).
	 * 
	 * @param startPageAfterRow the row in the table to start retrieving records
	 * after (eg. if this argument is 10, this method will retrieve records starting
	 * with row 11).
	 * @param pageRowCount the total number of rows for the page (the total
	 * number of records to be retrieved).
	 * @return a sub-list of entities from the table that is a "page"
	 * of data, based on the supplied parameters.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<T> getEntitiesByPage(int startPageAfterRow, int pageRowCount);
	
}
