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

import io.craigmiller160.school.entity.Student;

/**
 * An implementation of <tt>StudentDao</tt> using the
 * <tt>Hibernate</tt> framework. This class depends
 * on a <tt>Hibernate SessionFactory</tt> to generate
 * the database sessions & connections. This class
 * does NOT manage its own transactions, it will
 * depend on a service layer class to handle that 
 * functionality.
 * <p>
 * <b>THREAD SAFETY:</b> This class is thread-safe.
 * It has no mutable state that could cause issues
 * with multiple threads.
 * 
 * @author craig
 * @version 1.0
 */
@Component ("studentDao")
public class HibernateStudentDao implements GenericPaginatedDao<Student> {

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
	public HibernateStudentDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get the <tt>SessionFactory</tt> used by this class
	 * for database sessions.
	 * 
	 * @return the <tt>SessionFactory used by this class.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void insertEntity(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void updateEntity(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public Student getEntityById(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		return (Student) session.createCriteria(Student.class)
					.setFetchMode("courses", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("studentId", studentId))
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
	public List<Student> getAllEntities() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Student.class)
				.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void deleteEntity(Student student) {
		sessionFactory.getCurrentSession().delete(student);
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
	public long getEntityCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from Course").uniqueResult();
		
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<Student> getPreviousEntities(int lastPageFirstRowNum, int pageSize) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Student.class)
				.setFirstResult(lastPageFirstRowNum - 1 - pageSize)
				.setMaxResults(pageSize)
				.list();
		//TODO the previous methods, what happens if firstResult < 0???
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<Student> getNextEntities(int lastPageLastRowNum, int pageSize) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Student.class)
				.setFirstResult(lastPageLastRowNum)
				.setMaxResults(pageSize)
				.list();
	}

}
