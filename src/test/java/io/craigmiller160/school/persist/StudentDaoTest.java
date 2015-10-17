package io.craigmiller160.school.persist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Student;

/**
 * <tt>JUnit</tt> test case for the <tt>StudentDao</tt> class.
 * This test case tests all the persistence operations of the 
 * DAO. It uses <tt>Spring's</tt> <tt>SpringJUnit4ClassRunning</tt>
 * class to ensure that all transactions are rolled back upon completion,
 * thus ensuring the integrity of the underlying database. It performs 
 * four tests to accomplish this:
 * <p>
 * <b>Course Operations:</b> Test the basic CRUD operations on 
 * the student table of the database. Ensures <tt>Hibernate</tt> 
 * can make the connection, modify and access the content, and
 * that the mapping has been done accurately.
 * <p>
 * <b>List Operations:</b> Tests the ability to pull a list of
 * all entities from the student table of the database.
 * <p>
 * <b>List Range Operations:</b> Tests the abiltiy to pull a
 * list of all entities from the student table within a certain
 * range (limit on the number of records).
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
@ContextConfiguration ({"classpath:/test-context.xml"})
public class StudentDaoTest{

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
	 * The DAO object for the <tt>Student</tt> class.
	 */
	@Autowired
	private StudentDao studentDao;

	/**
	 * Get the DAO object for the <tt>Student</tt> class.
	 * 
	 * @return the DAO object for the <tt>Student</tt> class.
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * Set the DAO object for the <tt>Student</tt> class.
	 * 
	 * @param courseDao the DAO object for the <tt>Student</tt> class.
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	/**
	 * Test the basic CRUD operations on 
	 * the student table of the database. Ensures <tt>Hibernate</tt> 
	 * can make the connection, modify and access the content, and
	 * that the mapping has been done accurately.
	 */
	@Transactional
	@Test
	public void testStudentOperations(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		int studentId = student.getStudentId();
		
		student = studentDao.getStudent(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
		assertEquals(INSERT_FAIL, student.getLastName(), "Last");
		assertEquals(INSERT_FAIL, student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, student.getGender(), 'U');
		assertEquals(INSERT_FAIL, student.getGrade(), 1);
		
		setStudent2(student);
		studentDao.updateStudent(student);
		
		student = studentDao.getStudent(studentId);
		assertNotNull(UPDATE_FAIL, student);
		assertEquals(UPDATE_FAIL, student.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, student.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, student.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, student.getGender(), 'M');
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		studentDao.deleteStudent(student);
		
		student = studentDao.getStudent(studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	/**
	 * Set the fields of the <tt>Student</tt> object
	 * to the first set of values.
	 * 
	 * @param course the <tt>Student</tt> object to set.
	 */
	private void setStudent1(Student student){
		student.setFirstName("First");
		student.setLastName("Last");
		student.setBirthDate(LocalDate.of(1900, 1, 1));
		student.setGender('U');
		student.setGrade(1);
	}
	
	/**
	 * Set the fields of the <tt>Student</tt> object
	 * to the second set of values.
	 * 
	 * @param course the <tt>Student</tt> object to set.
	 */
	private void setStudent2(Student student){
		student.setFirstName("First2");
		student.setLastName("Last2");
		student.setBirthDate(LocalDate.of(1950, 1, 1));
		student.setGender('M');
		student.setGrade(2);
	}
	
	/**
	 * Tests the ability to pull a list of
	 * all entities from the student table of the database. 
	 */
	@Transactional
	@Test
	public void testListOperation(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		
		List<Student> students = studentDao.getAllStudents();
		assertNotNull("Students list is null", students);
		assertTrue("Students list less than 1", students.size() >= 1);
		assertTrue("Students list doesn't contain student", students.contains(student));
	}
	
	/**
	 * Tests the abiltiy to pull a
	 * list of all entities from the student table within a certain
	 * range (limit on the number of records).
	 */
	@Test
	@Transactional
	public void testListRangeOperation(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		
		List<Student> students = studentDao.getStudentsInRange(1, 8);
		assertNotNull("Students list is null", students);
		assertTrue("Students list less than startIndex", students.size() >= 1);
		assertTrue("Students list greater than endIndex", students.size() <= 8);
	}
	
	/**
	 * Tests the ability to get the count
	 * of the number of records in the table.
	 */
	@Test
	@Transactional
	public void testCountOperation(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertStudent(student);
		
		long count = studentDao.getStudentCount();
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
		testUtil.resetStudentAutoIncrement();
	}
}
