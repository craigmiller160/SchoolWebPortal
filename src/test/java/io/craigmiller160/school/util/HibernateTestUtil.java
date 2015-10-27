package io.craigmiller160.school.util;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility class for performing unit testing on the database
 * connection managed by the <tt>Hibernate</tt> framework.
 * It provides the means to reset the auto-increment counter
 * after running test database operations. This prevents unit
 * testing from using up tons of available values in that count.
 * <p>
 * This class is intended only for use by <tt>JUnit</tt> test
 * cases. It also manages its own transactions internally, because
 * the transactions performed by this class need to be committed,
 * unlike the transactions of a test case which should be rolled back.
 * Any use of this class in the test case should avoid causing the
 * transactions of this class to be propagated as a part of the 
 * transactions.
 * <p>
 * <b>THREAD SAFETY:</b> This class is thread-safe and has no
 * mutable state.
 * 
 * @author craig
 * @version 1.0
 */
@Component ("hibernateTestUtil")
public class HibernateTestUtil {

	/**
	 * The <tt>SessionFactory</tt> to create database sessions for this class.
	 */
	private final SessionFactory sessionFactory;

	/**
	 * Create a new <tt>HiberanteTestUtil</tt> with the necessary
	 * <tt>SessionFactory</tt>. If the factory parameter is set to 
	 * null, this class will not be able to function.
	 * 
	 * @param sessionFactory the <tt>SessionFactory</tt> this class requires.
	 */
	@Autowired
	public HibernateTestUtil(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Reset the auto-increment counter on the student table. If
	 * the <tt>SessionFactory</tt> was set to null, an exception
	 * will be thrown.
	 * 
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Transactional
	public void resetStudentAutoIncrement(){
		Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getDialect();
		if(dialect instanceof MySQLDialect){
			sessionFactory.getCurrentSession()
			.createSQLQuery("alter table student auto_increment = 1")
			.executeUpdate();
		}
		else{
			throw new UnsupportedOperationException(
					"Method is only compatible with MySQL database");
		}
	}
	
	/**
	 * Reset the auto-increment counter on the course table. If
	 * the <tt>SessionFactory</tt> was set to null, an exception
	 * will be thrown.
	 * 
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Transactional
	public void resetCourseAutoIncrement(){
		Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getDialect();
		if(dialect instanceof MySQLDialect){
			sessionFactory.getCurrentSession()
			.createSQLQuery("alter table course auto_increment = 1")
			.executeUpdate();
		}
		else{
			throw new UnsupportedOperationException(
					"Method is only compatible with MySQL database");
		}
	}
	
	/**
	 * Reset the auto-increment counter on the student_course table.
	 * If the <tt>SessionFactory</tt> was set to null, an exception
	 * will be thrown.
	 * 
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Transactional
	public void resetStudentCourseAutoIncrement(){
		Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getDialect();
		if(dialect instanceof MySQLDialect){
			sessionFactory.getCurrentSession()
			.createSQLQuery("alter table student_course auto_increment = 1")
			.executeUpdate();
		}
		else{
			throw new UnsupportedOperationException(
					"Method is only compatible with MySQL database");
		}
	}
	
}
