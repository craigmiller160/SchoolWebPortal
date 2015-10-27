package io.craigmiller160.school.context;

import org.springframework.context.ApplicationContext;

/**
 * <tt>Spring</tt> utility class for accessing the <tt>ApplicationContext</tt>
 * in a static, shared way. Intended for use in <tt>JUnit</tt> test cases that
 * need to reuse the same context, but can't use the <tt>ApplicationContextAware</tt>
 * interface (ie, they need to access the context in a static way).
 * <p>
 * The main example of a use of this class is in the <tt>@AfterClass</tt> methods
 * of <tt>CourseDaoTest</tt> and <tt>StudentDaoTest</tt>. This class allows for
 * them to run their test transaction cleanup operations without needed to re-create
 * a new, duplicate context object each time. This significantly reduces the overhead
 * of testing. 
 * 
 * @author craig
 * @version 1.0
 * @see io.craigmiller160.school.repo.CourseDaoIT.persist.CourseDaoTest CourseDaoTest
 * @see io.craigmiller160.schedule.persist.StudentDaoTest StudentDaoTest
 */
public class AppContext {

	/**
	 * The shared <tt>ApplicationContext</tt> object.
	 */
	private static ApplicationContext context;
	
	/**
	 * Set the shared <tt>ApplicationContext</tt> object.
	 * 
	 * @param ctx the <tt>ApplicationContext</tt> object to be shared.
	 */
	public static void setApplicationContext(ApplicationContext ctx){
		context = ctx;
	}
	
	/**
	 * Get the shared <tt>ApplicationContext</tt> object.
	 * 
	 * @return the shared <tt>ApplicationContext</tt> object.
	 */
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
}
