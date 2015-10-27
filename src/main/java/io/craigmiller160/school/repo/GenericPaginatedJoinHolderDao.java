package io.craigmiller160.school.repo;

import java.util.List;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * A generic DAO interface containing additional methods for
 * accessing <tt>JoinHolders</tt> and the persisted entities
 * they join in a paginated way.
 * 
 * @author craig
 * @version 1.0
 * @param <T> an entity that implements the <tt>JoinHolder</tt> interface.
 */
public interface GenericPaginatedJoinHolderDao<T extends JoinHolder> {

	/**
	 * Get a "previous page" of <tt>JoinHolder</tt> entities that are
	 * joined with the entity whose type and unique ID are
	 * specified in the parameters. The definition of what is a page is determined
	 * by the <tt>pageSize</tt> parameter.
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
	 * @param joinedEntityType the type of the entity to get the joins for.
	 * @param entityId the ID of the entity to get the joins for.
	 * @param lastPageFirstRowNumthe first row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of entities to be displayed on a
	 * page.
	 * @return a "previous page" of entities joined to the specified entity.
	 * @throws IllegalArgumentException if the type of entity provided
	 * is not a valid type for this class's <tt>JoinHolder</tt>.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	<U> List<T> getPreviousJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageFirstRowNum, int pageSize);	
	
	/**
	 * Get a "next page" of <tt>JoinHolder</tt> entities that are
	 * joined with the entity whose type and unique ID are
	 * specified in the parameters. The definition of what is a page is determined
	 * by the <tt>pageSize</tt> parameter.
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
	 * @param joinedEntityType the type of the entity to get the joins for.
	 * @param entityId the ID of the entity to get the joins for.
	 * @param lastPageLastRowNum the last row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of entities to be displayed on a
	 * page.
	 * @return a "next page" of entities joined to the specified entity.
	 * @throws IllegalArgumentException if the type of entity provided
	 * is not a valid type for this class's <tt>JoinHolder</tt>.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	<U> List<T> getNextJoinsFor(Class<U> joinedEntityType, 
			int entityId, int lastPageLastRowNum, int pageSize);
	
}
