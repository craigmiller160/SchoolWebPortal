package io.craigmiller160.school.service;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * Expanded version of the <tt>GenericPaginatedEntityService</tt> and
 * the <tt>GenericJoinHolderService</tt> interfaces, bringing bringing paginated
 * operations and join operations together. The methods this
 * interface provides allow for the retrieval of joined entities
 * in paginated fashion. Like other service classes, implementations
 * of this class can work with multiple DAOs and should provide transactional
 * support.
 * 
 * @author craig
 * @version 1.0
 */
public interface GenericPaginatedJoinHolderService {

	/**
	 * Get a "page" of <tt>JoinHolder</tt> entities that are
	 * joined with the entity whose type and unique ID are
	 * specified in the parameters. The size
	 * of a page is defined by the last parameter (pageRowCount).
	 * The records to select are calculated by the pageNumber parameter,
	 * which is multiplied by the pageRowCount to find the section
	 * in the table to retrieve records for.
	 * 
	 * @param joinHolderType the type of <tt>JoinHolder</tt> the specified
	 * entity is linked to.
	 * @param entityId the unique ID of the entity to get the joins for. 
	 * @param joinedEntityType the type of the entity to get the joins for.
	 * @param pageNumber the page to retrieve, based on breaking up
	 * the number of records in the table into "pages" with the
	 * number of rows defined in pageRowCount.
	 * @param pageRowCount the number of rows on each page.
	 * @return a sub-list of entities from the table that is a "page"
	 * of data, based on the supplied parameters.
	 * @throws IllegalArgumentException if the type of entity
	 * provided is not a supported entity by the database.
	 * @param <T> the type of <tt>JoinHolder</tt>  to use.
	 * @param <U> the type of entity that is joined.
	 */
	<T extends JoinHolder,U> List<T> getEntitiesByPageFor(Class<T> joinHolderType,
			Class<U> joinedEntityType, int entityId, int pageNumber, int pageRowCount);
	
}