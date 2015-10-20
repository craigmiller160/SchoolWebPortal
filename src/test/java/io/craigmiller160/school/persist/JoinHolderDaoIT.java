package io.craigmiller160.school.persist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class JoinHolderDaoIT {

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
	private GenericPaginatedJoinHolderDao<ScJoinHolder> scJoinHolderDao;
	
	@Autowired (required=true)
	private GenericPaginatedDao<Student> studentDao;
	
	@Autowired (required=true)
	private GenericPaginatedDao<Course> courseDao;
	
	private int studentId1;
	
	private int courseId1;
	
	private int studentId2;
	
	private int courseId2;
	
	public GenericPaginatedDao<Student> getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(GenericPaginatedDao<Student> studentDao) {
		this.studentDao = studentDao;
	}

	public GenericPaginatedDao<Course> getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(GenericPaginatedDao<Course> courseDao) {
		this.courseDao = courseDao;
	}

	public GenericPaginatedJoinHolderDao<ScJoinHolder> getScJoinHolderDao() {
		return scJoinHolderDao;
	}

	public void setScJoinHolderDao(GenericPaginatedJoinHolderDao<ScJoinHolder> scJoinHolderDao) {
		this.scJoinHolderDao = scJoinHolderDao;
	}
	
	@Transactional
	@Before
	public void before(){
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		studentId1 = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		courseDao.insertEntity(course);
		courseId1 = course.getCourseId();
		
		student = new Student();
		setStudent2(student);
		studentDao.insertEntity(student);
		studentId2 = student.getStudentId();
		
		course = new Course();
		setCourse2(course);
		courseDao.insertEntity(course);
		courseId2 = course.getCourseId();
	}
	
	
	
	@Transactional
	@Test
	public void testCRUD(){
		//Get first entities
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		
		//Create first joinHolder and insert
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		int joinHolderId = joinHolder.getScId();
		System.out.println(joinHolderId);
		
		//Get joinHolder and test values
		joinHolder = scJoinHolderDao.getEntityById(joinHolderId);
		assertNotNull(INSERT_FAIL, joinHolder);
		assertTrue(INSERT_FAIL, joinHolder.getCourse().getCourseName().equals("Name"));
		assertTrue(INSERT_FAIL, joinHolder.getStudent().getFirstName().equals("First"));
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
	 * Reset the auto-increment counter of the table being tested
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
