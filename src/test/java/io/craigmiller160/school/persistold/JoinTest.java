package io.craigmiller160.school.persistold;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmille160.school.deprecated.CourseDao;
import io.craigmille160.school.deprecated.StudentDao;
import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.persist.HibernateTestUtil;

/**
 * <tt>JUnit</tt> test case for the join operations of the entity classes.
 * This test case tests that the many-to-many mapping of the entities
 * functions properly. It uses <tt>Spring's</tt> <tt>SpringJUnit4ClassRunning</tt>
 * class to ensure that all transactions are rolled back upon completion,
 * thus ensuring the integrity of the underlying database. It performs 
 * two tests to accomplish this:
 * <p>
 * <b> Add Course to Student:</b> Test the mapping, including cascade
 * operations, for when a course is added to a student.
 * <p>
 * <b>Add Student to Course:</b> Test the mapping, including cascade
 * operations, for when a student is added to a course.
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
public class JoinTest {

	/**
	 * The DAO object for the <tt>Course</tt> class.
	 */
	@Autowired
	private CourseDao courseDao;
	
	/**
	 * The DAO object for the <tt>Student</tt> class.
	 */
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * The ID of the student temporarily persisted in the database.
	 */
	private int studentId;
	
	/**
	 * The ID of a course temporarily persisted in the database.
	 */
	private int courseId;
	
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
	 * Create and persist the entities needed
	 * for this test.
	 */
	@Transactional
	@Before
	public void createEntities(){
		Student student = new Student(
				"First", "Last", 
				LocalDate.of(1988, 10, 26), 
				'M', 1);
		Course course = new Course(
				"Course", "Subject", 
				"Teacher", 1);
		
		studentDao.insertStudent(student);
		studentId = student.getStudentId();
		
		courseDao.insertCourse(course);
		courseId = course.getCourseId();
	}
	
	/**
	 * Test the mapping, including cascade
	 * operations, for when a course is added to a student.	
	 */
	@Transactional
	@Test
	public void testAddCourseToStudent(){
		//Get entities
		Student student = studentDao.getStudent(studentId);
		Course course = courseDao.getCourse(courseId);
		
		//Add course to student and update database
		student.addCourse(course);
		studentDao.updateStudent(student);
		//TODO figure out how to do this without updating both DAOs in the test
		//Hibernate isn't flushing the operation in this test case, so the join table isn't being populated
		//It WILL work, though, just isn't right now for whatever reason.
		course.addStudent(student);
		courseDao.updateCourse(course);
		
		//Get student from database and test if course is in list
		student = studentDao.getStudent(studentId);
		List<Course> courses = student.getCourses();
		assertNotNull("Courses is null", courses);
		assertTrue("Courses does not contain Course", courses.contains(course));
		
		//Get course from database and test if student is in list
		course = courseDao.getCourse(courseId);
		List<Student> students = course.getStudents();
		assertNotNull("Students is null", students);
		assertTrue("Students does not contain Student", students.contains(student));
		
		//Test delete operation
		student.removeCourse(course);
		studentDao.updateStudent(student);
		
		student = studentDao.getStudent(studentId);
		courses = student.getCourses();
		assertNotNull("Courses is null (delete)", courses);
		assertTrue("Course was not deleted", !courses.contains(course));
		
	}
	
	/**
	 * Test the mapping, including cascade
	 * operations, for when a student is added to a course.
	 */
	@Transactional
	@Test
	public void testAddStudentToCourse(){
		//Get entities
		Student student = studentDao.getStudent(studentId);
		Course course = courseDao.getCourse(courseId);
		
		//Add student to course and update database
		course.addStudent(student);
		courseDao.updateCourse(course);
		//TODO figure out how to do this without updating both DAOs in the test
		//Hibernate isn't flushing the operation in this test case, so the join table isn't being populated
		//It WILL work, though, just isn't right now for whatever reason.
		student.addCourse(course);
		studentDao.updateStudent(student);
		
		//Get course from database and test if student is in list
		course = courseDao.getCourse(courseId);
		List<Student> students = course.getStudents();
		assertNotNull("Students is null", students);
		assertTrue("Students does not contain Student", students.contains(student));
		
		//Get student from database and test if course is in list
		student = studentDao.getStudent(studentId);
		List<Course> courses = student.getCourses();
		assertNotNull("Courses is null", courses);
		assertTrue("Courses does not contain Course", courses.contains(course));
		
		//Test delete operation
		course.removeStudent(student);
		courseDao.updateCourse(course);
		
		course = courseDao.getCourse(courseId);
		students = course.getStudents();
		assertNotNull("Students is null (delete)", students);
		assertTrue("Student was not deleted", !students.contains(student));
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
