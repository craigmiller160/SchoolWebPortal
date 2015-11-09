package io.craigmiller160.school.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
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
import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.util.HibernateTestUtil;

/**
 * JUnit Integration test for the DAO class that
 * handles <tt>Student</tt> entities.
 * 
 * @author craig
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class StudentDaoIT {

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
	 * DAO for <tt>Student</tt> entities, which is being tested here.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Student> studentDao;

	/**
	 * Get the DAO for <tt>Student</tt> entities.
	 * 
	 * @return the DAO for <tt>Student</tt> entities.
	 */
	public GenericEntityDaoBean<Student> getStudentDao() {
		return studentDao;
	}

	/**
	 * Set the DAO for <tt>Student</tt> entities.
	 * 
	 * @param studentDao the DAO for <tt>Student</tt> entities.
	 */
	public void setStudentDao(GenericEntityDaoBean<Student> studentDao) {
		this.studentDao = studentDao;
	}
	
	/**
	 * Set the fields of the <tt>Student</tt> object
	 * to the first set of values.
	 * 
	 * @param student the <tt>Student</tt> object to set.
	 */
	private void setStudent1(Student student){
		student.setFirstName("First");
		student.setLastName("Last");
		student.setBirthDate(LocalDate.of(1900, 1, 1));
		student.setGender(Gender.UNKNOWN);
		student.setGrade(1);
	}
	
	/**
	 * Set the fields of the <tt>Student</tt> object
	 * to the second set of values.
	 * 
	 * @param student the <tt>Student</tt> object to set.
	 */
	private void setStudent2(Student student){
		student.setFirstName("First2");
		student.setLastName("Last2");
		student.setBirthDate(LocalDate.of(1950, 1, 1));
		student.setGender(Gender.MALE);
		student.setGrade(2);
	}
	
	/**
	 * Test CRUD operations in the DAO.
	 */
	@Transactional
	@Test
	public void testCRUD(){
		//Create and insert test entity
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		int studentId = student.getStudentId();
		
		//Get entity and test for successful insert.
		student = studentDao.getEntityById(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
		assertEquals(INSERT_FAIL, student.getLastName(), "Last");
		assertEquals(INSERT_FAIL, student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, student.getGender(), Gender.UNKNOWN);
		assertEquals(INSERT_FAIL, student.getGrade(), 1);
		
		//Change content and update
		setStudent2(student);
		studentDao.updateEntity(student);
		
		//Get entity and test for successful update
		student = studentDao.getEntityById(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(UPDATE_FAIL, student.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, student.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, student.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, student.getGender(), Gender.MALE);
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		//Delete entity
		studentDao.deleteEntity(student);
		
		//Try to get entity and test for delete
		student = studentDao.getEntityById(studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	/**
	 * Test count operation in the DAO.
	 */
	@Transactional
	@Test
	public void testCount(){
		//Create and insert test data
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		student = new Student();
		setStudent2(student);
		studentDao.insertEntity(student);
		
		//Get entity count and test accuracy
		long count = studentDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	/**
	 * Test get all operation in DAO.
	 */
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert data
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		student = new Student();
		setStudent2(student);
		studentDao.insertEntity(student);
		
		//Get list and check for content
		List<Student> students = studentDao.getAllEntities();
		assertNotNull(students);
		assertTrue(students.size() >= 2);
	}
	
	/**
	 * Test pagination method of DAO.
	 */
	@Transactional
	@Test
	public void testPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			studentDao.insertEntity(student);
		}
		
		//Get next page and test for content
		List<Student> students1 = studentDao.getEntitiesByPage(10, 5);
		assertNotNull("Students list is null", students1);
		assertTrue("List is wrong size", students1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Student> students2 = studentDao.getEntitiesByPage(5, 6);
		assertNotNull("Students list is null", students2);
		assertTrue("List is wrong size", students2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				students1.get(0), students2.get(students2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < students1.size(); i++){
			assertFalse("Overlap between pages", 
					students2.contains(students1.get(i)));
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
		testUtil.resetStudentAutoIncrement();
		testUtil.resetAddressStudentAutoIncrement();
		testUtil.resetPhoneStudentAutoIncrement();
		testUtil.resetEmailStudentAutoIncrement();
	}
	
}
