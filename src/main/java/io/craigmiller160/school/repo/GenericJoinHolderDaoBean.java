package io.craigmiller160.school.repo;

import io.craigmiller160.school.entity.JoinHolder;

/**
 * Helper interface for use with the Spring IOC container and
 * DAO beans that handle <tt>JoinHolder</tt> entities.
 * Classes that need to implement all the generic DAO interfaces
 * (<tt>GenericEntityDao</tt>, <tt>GenericPaginatedEntityDao</tt>,
 * <tt>GenericJoinHolderDao</tt>, <tt>GenericPaginatedJoinHolderDao</tt>)
 * should implement this instead. This will solve the Spring proxy
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
 * @param <T> an entity that implements the <tt>JoinHolder</tt> interface.
 */
public interface GenericJoinHolderDaoBean<T extends JoinHolder>
extends GenericEntityDao<T>, GenericPaginatedEntityDao<T>,
GenericJoinHolderDao<T>, GenericPaginatedJoinHolderDao<T>{}
