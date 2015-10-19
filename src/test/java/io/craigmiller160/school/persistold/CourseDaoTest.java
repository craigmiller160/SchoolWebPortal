package io.craigmiller160.school.persistold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmille160.school.deprecated.CourseDao;
import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Course;

/**
 * <tt>JUnit</tt> test case for the <tt>CourseDao</tt> class.
 * This test case tests all the persistence operations of the 
 * DAO. It uses <tt>Spring's</tt> <tt>SpringJUnit4ClassRunning</tt>
 * class to ensure that all transactions are rolled back upon completion,
 * thus ensuring the integrity of the underlying database. It performs 
 * four tests to accomplish this:
 * <p>
 * <b>Course Operations:</b> Test the basic CRUD operations on 
 * the course table of the database. Ensures <tt>Hibernate</tt> 
 * can make the connection, modify and access the content, and
 * that the mapping has been done accurately.
 * <p>
 * <b>List Operations:</b> Tests the ability to pull a list of
 * all entities from the course table of the database. 
 * <p>
 * <b>Previous Page Operation:</b> Tests the <tt>getPreviousCourses()</tt> operation, to
 * conveniently retrieve a "previous page" of courses
 * from the database.
 * <p>
 * <b>Next Page Operation:</b> Tests the <tt>getPreviousCourses()</tt> operation, to
 * conveniently retrieve a "previous page" of courses
 * from the database.
 * <p>
 * <b>Count Operation:</b> Tests the ability to get the count
 * of the number of records in the table.
 * <p>
 * After the completion of its operations, a method is run to 
 * reset the auto-increment counter on the underlying database.
 * This prevents the tests from using up large numbers of values
 * from the counter.
 * 
 * @author craig
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class CourseDaoTest{
	
	/**
	 * Output message for the insert operation failing.
	 */
	private static final String INSERT_FAIL = "Insert Failed";
	
	/**
	 * Output message for the update operation failing.
	 */
	private static final String UPDATE_FAIL = "Update Failed";
	
	/**
	 * Output message for the delete operation failing.
	 */
	private static final String DELETE_FAIL = "Delete Failed";
	
	/**
	 * The DAO object for the <tt>Course</tt> class.
	 */
	@Autowired
	private CourseDao courseDao;

	/**
	 * Get the DAO object for the <tt>Course</tt> class.
	 * 
	 * @return the DAO object for the <tt>Course</tt> class.
	 */
	public CourseDao getCourseDao() {
		return courseDao;
	}

	/**
	 * Set the DAO object for the <tt>Course</tt> class.
	 * 
	 * @param courseDao the DAO object for the <tt>Course</tt> class.
	 */
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	/**
	 * Test the basic CRUD operations on 
	 * the course table of the database. Ensures <tt>Hibernate</tt> 
	 * can make the connection, modify and access the content, and
	 * that the mapping has been done accurately.
	 */
	@Test
	@Transactional
	public void testCourseOperations(){
		Course course = new Course();
		setCourse1(course);
		courseDao.insertCourse(course);
		int courseId = course.getCourseId();
		
		course = courseDao.getCourse(courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(INSERT_FAIL, course.getCourseName(), "Name");
		assertEquals(INSERT_FAIL, course.getSubject(), "Subject");
		assertEquals(INSERT_FAIL, course.getTeacherLastName(), "LastName");
		assertEquals(INSERT_FAIL, course.getPeriod(), 1);
		
		setCourse2(course);
		courseDao.updateCourse(course);
		
		course = courseDao.getCourse(courseId);
		assertNotNull(UPDATE_FAIL, course);
		assertEquals(UPDATE_FAIL, course.getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, course.getSubject(), "Subject2");
		assertEquals(UPDATE_FAIL, course.getTeacherLastName(), "LastName2");
		assertEquals(UPDATE_FAIL, course.getPeriod(), 2);
		
		courseDao.deleteCourse(course);
		
		course = courseDao.getCourse(courseId);
		assertNull(DELETE_FAIL, course);
	}
	
	/**
	 * Set the fields of the <tt>Course</tt> object
	 * to the first set of values.
	 * 
	 * @param course the <tt>Course</tt> object to set.
	 */
	private void setCourse1(Course course){
		course.setCourseName("Name");
		course.setSubject("Subject");
		course.setTeacherLastName("LastName");
		course.setPeriod(1);
	}
	
	/**
	 * Set the fields of the <tt>Course</tt> object
	 * to the second set of values.
	 * 
	 * @param course the <tt>Course</tt> object to set.
	 */
	private void setCourse2(Course course){
		course.setCourseName("Name2");
		course.setSubject("Subject2");
		course.setTeacherLastName("LastName2");
		course.setPeriod(2);
	}
	
	/**
	 * Tests the ability to pull a list of
	 * all entities from the course table of the database. 
	 */
	@Test
	@Transactional
	public void testListOperation(){
		Course course = new Course();
		setCourse1(course);
		courseDao.insertCourse(course);
		
		List<Course> courses = courseDao.getAllCourses();
		assertNotNull("Courses list is null", courses);
		assertTrue("Courses list less than 1", courses.size() >= 1);
		assertTrue("Courses list doesn't contain course", courses.contains(course));
	}
	
	/**
	 * Tests the <tt>getPreviousCourses()</tt> operation, to
	 * conveniently retrieve a "previous page" of courses
	 * from the database.
	 */
	@Test
	@Transactional
	public void testPreviousCoursesOperation(){
		int courseId = 0;
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			course.setCourseName("Name");
			courseDao.insertCourse(course);
			courseId = i == 0 ? course.getCourseId() : courseId;
		}
		
		List<Course> courses = courseDao.getPreviousCourses(courseId + 12, 10);
		assertNotNull("Courses list is null", courses);
		assertTrue("Courses list not correct size", courses.size() == 10);
	}
	
	/**
	 * Tests the <tt>getNextCourses()</tt> operation, to
	 * conveniently retrieve a "next page" of courses
	 * from the database.
	 */
	@Test
	@Transactional
	public void testNextCoursesOperation(){
		int courseId = 0;
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			course.setCourseName("Name");
			courseDao.insertCourse(course);
			courseId = i == 0 ? course.getCourseId() : courseId;
		}
		
		List<Course> courses = courseDao.getNextCourses(courseId, 10);
		assertNotNull("Courses list is null", courses);
		assertTrue("Courses list not correct size", courses.size() == 10);
	}
	
	/**
	 * Tests the ability to get the count
	 * of the number of records in the table.
	 */
	@Test
	@Transactional
	public void testCountOperation(){
		Course course = new Course();
		setCourse1(course);
		courseDao.insertCourse(course);
		
		long count = courseDao.getCourseCount();
		assertTrue(count > 0);
	}
	
	/**
	 * Reset the auto-increment counter of the table being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetCourseAutoIncrement();
	}
	
}
