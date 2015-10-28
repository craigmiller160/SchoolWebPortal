package io.craigmiller160.school.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import io.craigmiller160.school.util.HibernateTestUtil;

/**
 * JUnit Integration test for the DAO class
 * that handles <tt>Course</tt> entities.
 * 
 * @author craig
 * @version 1.0
 */
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
	
	/**
	 * DAO for <tt>Course</tt> entities, which is being tested here.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Course> courseDao;

	/**
	 * Get the DAO for <tt>Course</tt> entities.
	 * 
	 * @return the DAO for <tt>Course</tt> entities.
	 */
	public GenericEntityDaoBean<Course> getCourseDao() {
		return courseDao;
	}

	/**
	 * Set the DAO for <tt>Course</tt> entities.
	 * 
	 * @param courseDao the DAO for <tt>Course</tt> entities.
	 */
	public void setCourseDao(GenericEntityDaoBean<Course> courseDao) {
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
	
	/**
	 * Test CRUD operations in the DAO.
	 */
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
	
	/**
	 * Test count operation in the DAO.
	 */
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
	
	/**
	 * Test get all operation in DAO.
	 */
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
	
	/**
	 * Test pagination method of DAO.
	 */
	@Transactional
	@Test
	public void testPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			courseDao.insertEntity(course);
		}
		
		//Get previous page and test for content
		List<Course> courses1 = courseDao.getEntitiesByPage(10, 5);
		assertNotNull("Courses list is null", courses1);
		assertTrue("List is wrong size", courses1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Course> courses2 = courseDao.getEntitiesByPage(5, 6);
		assertNotNull("Courses list is null", courses2);
		assertTrue("List is wrong size", courses2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				courses1.get(0), courses2.get(courses2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < courses1.size(); i++){
			assertFalse("Overlap between pages", courses2.contains(courses1.get(i)));
		}
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
