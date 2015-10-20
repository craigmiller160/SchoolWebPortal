package io.craigmiller160.school.persist;

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

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class CourseDaoIT {

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
	
	@Autowired (required=true)
	private GenericPaginatedDao<Course> courseDao;

	public GenericPaginatedDao<Course> getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(GenericPaginatedDao<Course> courseDao) {
		this.courseDao = courseDao;
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
	
	@Transactional
	@Test
	public void testCRUD(){
		//Create and insert test entity
		Course course = new Course();
		setCourse1(course);
		courseDao.insertEntity(course);
		int courseId = course.getCourseId();
		
		//Get entity and test for successful insert
		course = courseDao.getEntityById(courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(INSERT_FAIL, course.getCourseName(), "Name");
		assertEquals(INSERT_FAIL, course.getSubject(), "Subject");
		assertEquals(INSERT_FAIL, course.getTeacherLastName(), "LastName");
		assertEquals(INSERT_FAIL, course.getPeriod(), 1);
		
		//Change content and update
		setCourse2(course);
		courseDao.updateEntity(course);
		
		//Get entity and test for successful update
		course = courseDao.getEntityById(courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(UPDATE_FAIL, course.getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, course.getSubject(), "Subject2");
		assertEquals(UPDATE_FAIL, course.getTeacherLastName(), "LastName2");
		assertEquals(UPDATE_FAIL, course.getPeriod(), 2);
		
		//Delete entity
		courseDao.deleteEntity(course);
		
		//Try to get entity and test for delete
		course = courseDao.getEntityById(courseId);
		assertNull(DELETE_FAIL, course);
	}
	
	@Transactional
	@Test
	public void testCount(){
		//Create and insert test data
		Course course = new Course();
		setCourse1(course);
		courseDao.insertEntity(course);
		course = new Course();
		setCourse2(course);
		courseDao.insertEntity(course);
		
		//Get entity count and test accuracy
		long count = courseDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert test data
		Course course = new Course();
		setCourse1(course);
		courseDao.insertEntity(course);
		course = new Course();
		setCourse2(course);
		courseDao.insertEntity(course);
		
		//Get list and check for content
		List<Course> courses = courseDao.getAllEntities();
		assertNotNull(courses);
		assertTrue(courses.size() >= 2);
	}
	
	@Transactional
	@Test
	public void testPreviousPage(){
		int courseId = 0;
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			courseDao.insertEntity(course);
			System.out.println("LoopId " + course.getCourseId());
			courseId = i == 0 ? course.getCourseId() : courseId;
		}
		
		//Get previous page and test for content
		List<Course> courses = courseDao.getPreviousEntities(courseId + 13, 10);
		assertNotNull("Courses list is null", courses);
		assertTrue("List is wrong size", courses.size() == 10);
		System.out.println(courseId);
		for(Course c : courses){
			System.out.println(c.getCourseId());
		}
		assertTrue("Course ID match failed", courses.get(courses.size() - 1).getCourseId() == courseId + 12);
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
