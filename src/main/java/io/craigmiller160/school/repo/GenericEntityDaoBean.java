package io.craigmiller160.school.repo;

/**
 * Helper interface for use with the Spring IOC container.
 * Classes that need to implement both the <tt>GenericEntityDao</tt>
 * and <tt>GenericPaginatedEntityDao</tt> interfaces should
 * implement this instead. This will solve the Spring proxy
 * issue when depending on the Spring IOC container to inject
 * an implementation of these interfaces into a class.
 * <p>
 * Spring's proxy mechanism implements the interfaces of a class
 * being proxied, instead of extending the class itself. This
 * causes type matching exceptions when trying to use a class
 * as the declared type of a proxied bean being retrieved from
 * the container.
 * <p>
 * This interface unites its parent interfaces to provide a single,
 * polymorphic type that can be declared when retrieving an 
 * implementation from the Spring container. If the parent interfaces
 * are implemented directly by a DAO class, any classes that need
 * the implementation to be injected by the Spring container will
 * be forced to limit themselves to just one of the parent interfaces
 * for the declared type, thus restricting the available methods or
 * forcing the use of complex casting. This interface eliminates the
 * need for all of that, providing a single type that can be declared
 * with access to all methods.
 * 
 * @author craig
 * @version 1.0
 * @param <T> the type of entity this DAO will persist.
 */
public interface GenericEntityDaoBean<T> 
extends GenericEntityDao<T>, GenericPaginatedEntityDao<T>{}
