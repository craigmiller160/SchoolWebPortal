package io.craigmiller160.school.repo;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.Administrator;

@Component ("adminDao")
public class HibernateAdminDao implements GenericEntityDaoBean<Administrator> {

	/**
	 * The <tt>SessionFactory</tt> that this class uses
	 * for connecting to the database.
	 */
	private final SessionFactory sessionFactory;
	
	@Autowired (required=true)
	public HibernateAdminDao(SessionFactory sessionFactory){
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
	
	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void insertEntity(Administrator admin) {
		sessionFactory.getCurrentSession().save(admin);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void updateEntity(Administrator admin) {
		sessionFactory.getCurrentSession().update(admin);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void deleteEntity(Administrator admin) {
		sessionFactory.getCurrentSession().delete(admin);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public Administrator getEntityById(int adminId) {
		return (Administrator) sessionFactory.getCurrentSession()
				.get(Administrator.class, adminId);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public long getEntityCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from "
				+ Administrator.class.getName())
				.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Criteria.list() doesn't support generics
	@Override
	public List<Administrator> getAllEntities() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Administrator.class)
				.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<Administrator> getEntitiesByPage(int startPageAfterRow, int pageRowCount) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Administrator.class)
				.setFirstResult(startPageAfterRow)
				.setMaxResults(pageRowCount)
				.list();
		
		//TODO the previous methods, what happens if firstResult < 0???
	}

}
