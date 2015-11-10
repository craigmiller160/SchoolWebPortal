package io.craigmiller160.school.repo;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * A generic DAO interface containing additional methods for
 * accessing <tt>JoinHolders</tt> and the persisted entities
 * they join in a paginated way. It should be be used to expand
 * the capabilities of an existing <tt>JoinHolder</tt> DAO 
 * implementation. It can be used with basic JDBC or with ORM 
 * frameworks.
 * 
 * @author craig
 * @version 1.0
 * @param <T> an entity that implements the <tt>JoinHolder</tt> interface.
 */
public interface GenericPaginatedJoinHolderDao<T extends JoinHolder> {

	/**
	 * Get a "page" of <tt>JoinHolder</tt> entities from the
	 * database that joined with the entity whose type and unique
	 * ID are specified in the parameters. A page is essentially
	 * a sub-list of records from the database. The size
	 * of a page is defined by the fourth parameter (pageRowCount).
	 * The offset is determined by the third parameter (startPageAfterRow).
	 * 
	 * @param joinedEntityType the type of entity to retrieve a 
	 * "page" of its joined entities for.
	 * @param entityId the unique ID of the entity to retrieve a
	 * "page" of its joined entities for. 
	 * @param startPageAfterRow the row in the table to start retrieving records
	 * after (eg. if this argument is 10, this method will retrieve records starting
	 * with row 11).
	 * @param pageRowCount the total number of rows for the page (the total
	 * number of records to be retrieved).
	 * @return a sub-list of entities from the table that is a "page"
	 * of data, based on the supplied parameters.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 * @param <U> the type of entity that is joined.
	 */
	<U> List<T> getEntitiesByPageFor(Class<U> joinedEntityType, Long entityId,
			int startPageAfterRow, int pageRowCount);
	
}
