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
public interface GenericPaginatedDao<T>{

	
	
	
	
	
	/**
	 * Get a "previous page" of entities of the type persisted
	 * by this DAO. The definition of what is a page is determined
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
	 * @param lastPageFirstRowNum the first row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of entities to be displayed on a
	 * page.
	 * @return a "previous page" of entities persisted by this DAO, defined
	 * by the parameters.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<T> getPreviousEntities(int lastPageFirstRowNum, int pageSize);
	
	/**
	 * Get a "next page" of entities of the type persisted
	 * by this DAO. The definition of what is a page is determined
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
	 * @param lastPageLastRowNum the last row number (NOT the primary key) on the currently
	 * displayed page.
	 * @param pageSize the number of entities to be displayed on a
	 * page.
	 * @return a "next page" of entities persisted by this DAO, defined
	 * by the parameters.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<T> getNextEntities(int lastPageLastRowNum, int pageSize);
	
}
