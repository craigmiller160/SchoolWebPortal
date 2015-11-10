package io.craigmiller160.school.repo;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.SchoolUser;

@Component ("userDao")
public class HibernateUserDao implements 
GenericEntityDaoBean<SchoolUser> {

	/**
	 * The <tt>SessionFactory</tt> that this class uses
	 * for connecting to the database.
	 */
	private final SessionFactory sessionFactory;
	
	/**
	 * Create this DAO with the mandatory <tt>SessionFactory</tt>
	 * that it requires. Passing null as this parameter will
	 * cause this class to not be able to function.
	 * 
	 * @param sessionFactory the <tt>SessionFactory</tt> this class
	 * needs to create database sessions.
	 */
	@Autowired (required=true)
	public HibernateUserDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get the <tt>SessionFactory</tt> used by this class
	 * for database sessions.
	 * 
	 * @return the <tt>SessionFactory</tt> used by this class.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public void insertEntity(SchoolUser user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateEntity(SchoolUser user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteEntity(SchoolUser user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public SchoolUser getEntityById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		return (SchoolUser) session.createCriteria(SchoolUser.class)
					.setFetchMode("courses", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("userId", userId))
					.uniqueResult();
	}

	@Override
	public long getEntityCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from "
				+ SchoolUser.class.getName())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked") //Criteria.list() doesn't support generics
	@Override
	public List<SchoolUser> getAllEntities() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(SchoolUser.class)
				.list();
	}

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<SchoolUser> getEntitiesByPage(int startPageAfterRow, int pageRowCount) {
		return sessionFactory.getCurrentSession()
				.createCriteria(SchoolUser.class)
				.setFirstResult(startPageAfterRow)
				.setMaxResults(pageRowCount)
				.list();
	}
	
	/**
	 * Close the <tt>SessionFactory</tt> when this class's work
	 * is complete.
	 * 
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@PreDestroy
	public void closeSessionFactory(){
		sessionFactory.close();
	}

}
