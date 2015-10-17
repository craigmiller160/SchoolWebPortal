package io.craigmiller160.school.persist;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.Course;

/**
 * An implementation of <tt>CourseDao</tt> using the
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
@Component ("courseDao")
public class HibernateCourseDao implements CourseDao {

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
	@Autowired
	public HibernateCourseDao(SessionFactory sessionFactory){
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
	public void insertCourse(Course course) {
		sessionFactory.getCurrentSession().save(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void updateCourse(Course course) {
		sessionFactory.getCurrentSession().update(course);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public Course getCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		return (Course) session.createCriteria(Course.class)
					.setFetchMode("students", FetchMode.JOIN)
					.add(Restrictions.naturalId().set("courseId", courseId))
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
	public List<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Course.class)
				.list();
	}
	
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<Course> getCoursesInRange(long startIndex, long endIndex){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("coursesByIndexRangeProcedure")
				.setLong("startIndex", startIndex)
				.setLong("endIndex", endIndex);
		return query.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void deleteCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
	}
	
	/**
	 * Close the <tt>SessionFactory</tt> when this class's work
	 * is complete.
	 * 
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	public void closeSessionFactory(){
		sessionFactory.close();
	}

	@Override
	public long getCourseCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count(*) from Course").uniqueResult();
	}

}
