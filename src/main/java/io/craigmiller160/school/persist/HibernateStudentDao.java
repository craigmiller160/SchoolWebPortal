package io.craigmiller160.school.persist;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
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
public class HibernateStudentDao implements StudentDao {

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
	public void insertStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public Student getStudent(int studentId) {
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
	public List<Student> getAllStudents() {
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
	public void deleteStudent(Student student) {
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

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<Student> getStudentsInRange(long startIndex, long endIndex) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("studentsByIndexRangeProcedure")
				.setLong("startIndex", startIndex)
				.setLong("endIndex", endIndex);
		return query.list();
	}

	@Override
	public long getStudentCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from Course").uniqueResult();
		
	}

}
