package io.craigmiller160.school.persistold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.persist.EntityService;
import io.craigmiller160.school.persist.HibernateTestUtil;

/**
 * <tt>JUnit</tt> test case for the <tt>SchoolService</tt>
 * class. This test cas tests all the operations for this
 * service. It uses <tt>Spring's</tt> <tt>SpringJUnit4ClassRunning</tt>
 * class to ensure that all transactions are rolled back upon completion,
 * thus ensuring the integrity of the underlying database. It performs 
 * six tests to accomplish this:
 * <p>
 * <b>Create Entity:</b> Test the convenience <tt>createEntity()</tt> method
 * for both <tt>Student</tt> and <tt>Course</tt> entities.
 * <p>
 * <b>Insert Entity:</b> Test <tt>insertEntity()</tt> and <tt>getEntity()</tt> methods
 * for both <tt>Student</tt> and <tt>Course</tt>
 * entities.
 * <p>
 * <b>Save Entity:</b> Test <tt>saveEntity()</tt> and <tt>getEntity()</tt> methods
 * for both <tt>Student</tt> and <tt>Course</tt>
 * entities. Depends on the <tt>insertEntity()</tt> method
 * to be working, so if that test fails, so does this one.
 * <p>
 * <b>Get All Entities:</b> Test the <tt>getAllEntities()</tt> method
 * for both <tt>Student</tt> and <tt>Course</tt>
 * entities. Depends on <tt>insertEntity()</tt>
 * method to work, so if that test fails,
 * so does this one.
 * <p>
 * <b>Get entity Count:</b> Test the <tt>getEntityCount()</tt> method
 * for both <tt>Student</tt> and <tt>Course</tt>
 * entities. Depends on <tt>insertEntity()</tt>
 * method to work, so if that test fails,
 * so does this one.
 * <p>
 * <b>Get Previous Entities:</b> Tests the <tt>getPreviousEntities()</tt> operation, to
 * conveniently retrieve a "previous page" of entities
 * from the database.
 * <p>
 * <b>Get Next Entities:</b> Tests the <tt>getNextEntities()</tt> operation, to
 * conveniently retrieve a "next page" of entities
 * from the database.
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
public class SchoolServiceTest {

	/**
	 * The <tt>SchoolService</tt> class that's being
	 * tested.
	 */
	@Autowired
	private EntityService schoolService;
	
	/**
	 * Test the convenience <tt>createEntity()</tt> method
	 * for both <tt>Student</tt> and <tt>Course</tt> entities.
	 */
	@Transactional
	@Test
	public void testCreateEntity(){
		Course course = schoolService.createEntity(Course.class, 
				"Name", "Subject", "Teacher", 1);
		assertNotNull("Course is null", course);
		assertTrue("CourseID not set", course.getCourseId() > 0);
		
		Student student = schoolService.createEntity(Student.class, 
				"FirstName", "LastName", LocalDate.of(1988, 10, 26), 'M', 10);
		assertNotNull("Student is null", student);
		assertTrue("StudentID not set", student.getStudentId() > 0);
	}
	
	/**
	 * Test <tt>insertEntity()</tt> and <tt>getEntity()</tt> methods
	 * for both <tt>Student</tt> and <tt>Course</tt>
	 * entities.
	 */
	@Transactional
	@Test
	public void testInsertEntity(){
		Course course = new Course();
		course.setCourseName("Name");
		schoolService.insertEntity(course);
		int courseId = course.getCourseId();
		
		course = schoolService.getEntity(Course.class, courseId);
		assertNotNull("Course not inserted", course);
		assertEquals("Course returned doesn't match insert", course.getCourseName(), "Name");
		
		Student student = new Student();
		student.setFirstName("FirstName");
		schoolService.insertEntity(student);
		int studentId = student.getStudentId();
		
		student = schoolService.getEntity(Student.class, studentId);
		assertNotNull("Student not inserted", student);
		assertEquals("Student returned doesn't match insert", student.getFirstName(), "FirstName");
	}
	
	/**
	 * Test <tt>saveEntity()</tt> and <tt>getEntity()</tt> methods
	 * for both <tt>Student</tt> and <tt>Course</tt>
	 * entities. Depends on the <tt>insertEntity()</tt> method
	 * to be working, so if that test fails, so does this one.
	 */
	@Transactional
	@Test
	public void testSaveEntity(){
		Course course = new Course();
		course.setCourseName("Name");
		schoolService.insertEntity(course);
		int courseId = course.getCourseId();
		
		Student student = new Student();
		student.setFirstName("FirstName");
		schoolService.insertEntity(student);
		int studentId = student.getStudentId();
		
		course.setCourseName("Name2");
		schoolService.updateEntity(course);
		
		course = schoolService.getEntity(Course.class, courseId);
		assertNotNull("Course not inserted", course);
		assertEquals("Course save failed", course.getCourseName(), "Name2");
		
		student.setFirstName("FirstName2");
		schoolService.updateEntity(student);
		
		student = schoolService.getEntity(Student.class, studentId);
		assertNotNull("Student not inserted", student);
		assertEquals("Student save failed", student.getFirstName(), "FirstName2");
	}
	
	/**
	 * Test the <tt>getAllEntities()</tt> method
	 * for both <tt>Student</tt> and <tt>Course</tt>
	 * entities. Depends on <tt>insertEntity()</tt>
	 * method to work, so if that test fails,
	 * so does this one.
	 */
	@Transactional
	@Test
	public void testGetAllEntities(){
		Course course = new Course();
		course.setCourseName("Name");
		schoolService.insertEntity(course);
		
		Student student = new Student();
		student.setFirstName("FirstName");
		schoolService.insertEntity(student);
		
		List<Course> courses = schoolService.getAllEntities(Course.class);
		assertNotNull("Courses list is null", courses);
		assertTrue("List of all courses not retrieved", courses.size() > 0);
		
		List<Student> students = schoolService.getAllEntities(Student.class);
		assertNotNull("Students list is null", students);
		assertTrue("List of all students not retrieved", students.size() > 0);
	}
	
	/**
	 * Test the <tt>getEntityCount()</tt> method
	 * for both <tt>Student</tt> and <tt>Course</tt>
	 * entities. Depends on <tt>insertEntity()</tt>
	 * method to work, so if that test fails,
	 * so does this one.
	 */
	@Transactional
	@Test
	public void testGetEntityCount(){
		Course course = new Course();
		course.setCourseName("Name");
		schoolService.insertEntity(course);
		
		Student student = new Student();
		student.setFirstName("FirstName");
		schoolService.insertEntity(student);
		
		long courseCount = schoolService.getEntityCount(Course.class);
		assertTrue("Course count failed", courseCount > 0);
		
		long studentCount = schoolService.getEntityCount(Student.class);
		assertTrue("Student count failed", studentCount > 0);
	}
	
	/**
	 * Tests the <tt>getPreviousEntities()</tt> operation, to
	 * conveniently retrieve a "previous page" of entities
	 * from the database.
	 */
	@Transactional
	@Test
	public void testPreviousEntities(){
		int studentId = 0;
		int courseId = 0;
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			course.setCourseName("Name");
			schoolService.insertEntity(course);
			courseId = i == 0 ? course.getCourseId() : courseId;
			
			Student student = new Student();
			student.setFirstName("FirstName");
			schoolService.insertEntity(student);
			studentId = i == 0 ? student.getStudentId() : studentId;
		}
		
		List<Course> previousCourses = schoolService.getPreviousEntities(Course.class, courseId + 12, 10);
		assertNotNull("Previous courses list is null", previousCourses);
		assertTrue("Previous courses list not correct size", previousCourses.size() == 10);
		
		List<Student> previousStudents = schoolService.getPreviousEntities(Student.class, studentId + 12, 10);
		assertNotNull("Previous students list is null", previousStudents);
		assertTrue("Previous students list not correct size", previousStudents.size() == 10);
	}
	
	/**
	 * Tests the <tt>getNextEntities()</tt> operation, to
	 * conveniently retrieve a "next page" of entities
	 * from the database.
	 */
	@Transactional
	@Test
	public void testNextEntities(){
		int studentId = 0;
		int courseId = 0;
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			course.setCourseName("Name");
			schoolService.insertEntity(course);
			courseId = i == 0 ? course.getCourseId() : courseId;
			
			Student student = new Student();
			student.setFirstName("FirstName");
			schoolService.insertEntity(student);
			studentId = i == 0 ? student.getStudentId() : studentId;
		}
		
		List<Course> nextCourses = schoolService.getNextEntities(Course.class, courseId, 10);
		assertNotNull("Next courses list is null", nextCourses);
		assertTrue("Next courses list not correct size", nextCourses.size() == 10);
		
		List<Student> nextStudents = schoolService.getNextEntities(Student.class, studentId, 10);
		assertNotNull("Next students list is null", nextStudents);
		assertTrue("Next students list not correct size", nextStudents.size() == 10);
	}
	
	/**
	 * Reset the auto-increment counter of the tables being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetCourseAutoIncrement();
		testUtil.resetStudentAutoIncrement();
	}
}
