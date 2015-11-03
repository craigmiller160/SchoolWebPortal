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
	 * Get a "page" of of entities from the database, essentially
	 * a sub-list of records from the database. The size
	 * of a page is defined by the last parameter (pageRowCount).
	 * The records to select are calculated by the pageNumber parameter,
	 * which is multiplied by the pageRowCount to find the section
	 * in the table to retrieve records for.
	 * 
	 * @param entityType the type of entity to retrieve records of.
	 * @param pageNumber the page to retrieve, based on breaking up
	 * the number of records in the table into "pages" with the
	 * number of rows defined in pageRowCount.
	 * @param pageRowCount the number of rows on each page.
	 * @return a sub-list of entities from the table that is a "page"
	 * of data, based on the supplied parameters.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 * @param <T> the type of entity to get by page.
	 */
	<T> List<T> getEntitiesByPage(Class<T> entityType, int pageNumber, 
			int pageRowCount);
	
	/**
	 * A convenience method to determine if the last available
	 * "page" of an entity has been displayed. This is
	 * determined based on the provided pageNumber and pageRowCount
	 * arguments, which are used to calculate if a "next page"
	 * operation would return any additional records.
	 * 
	 * @param entityType the type of entity to check if pages remain.
	 * @param pageNumber the current page number.
	 * @param pageRowCount the number of rows per page.
	 * @return true if a "next page" operation would return 
	 * additional records.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 * @param <T> the type of entity to get by page.
	 */
	<T> boolean hasPagesRemaining(Class<T> entityType, int pageNumber, 
			int pageRowCount);
	
}
