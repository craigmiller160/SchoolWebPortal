package io.craigmiller160.school.persist;

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
import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;

//TODO document how this is a BIG test class
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class SchoolDataServiceIT {

	//TODO when all methods are done, add additional tests
	//to ensure the right types are being returned.
	
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
	
	private static final String CREATE_FAIL = "Create Failed";
	
	@Autowired (required=true)
	private GenericPaginatedJoinHolderService schoolDataService;

	public GenericPaginatedJoinHolderService getSchoolDataService() {
		return schoolDataService;
	}

	public void setSchoolDataService(GenericPaginatedJoinHolderService schoolDataService) {
		this.schoolDataService = schoolDataService;
	}
	
	@Transactional
	@Test
	public void testStudentCRUD(){
		//Create and insert test entity
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		//Get entity and test for successful insert
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
		assertEquals(INSERT_FAIL, student.getLastName(), "Last");
		assertEquals(INSERT_FAIL, student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, student.getGender(), 'U');
		assertEquals(INSERT_FAIL, student.getGrade(), 1);
		
		//Change content and update
		setStudent2(student);
		schoolDataService.updateEntity(student);
		
		//Get entity and test for successful update
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(UPDATE_FAIL, student.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, student.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, student.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, student.getGender(), 'M');
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		//Delete entity
		schoolDataService.deleteEntity(student);
		
		//Try to get entity and test for delete
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	@Transactional
	@Test
	public void testCourseCRUD(){
		//Create and insert test entity
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		//Get entity and test for successful insert
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(INSERT_FAIL, course.getCourseName(), "Name");
		assertEquals(INSERT_FAIL, course.getSubject(), "Subject");
		assertEquals(INSERT_FAIL, course.getTeacherLastName(), "LastName");
		assertEquals(INSERT_FAIL, course.getPeriod(), 1);
		
		//Change content and update
		setCourse2(course);
		schoolDataService.updateEntity(course);
		
		//Get entity and test for successful update
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(UPDATE_FAIL, course.getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, course.getSubject(), "Subject2");
		assertEquals(UPDATE_FAIL, course.getTeacherLastName(), "LastName2");
		assertEquals(UPDATE_FAIL, course.getPeriod(), 2);
		
		//Delete entity
		schoolDataService.deleteEntity(course);
		
		//Try to get entity and test for delete
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNull(DELETE_FAIL, course);
	}
	
	@Transactional
	@Test
	public void testScJoinHolderCRUD(){
		//Create and insert test entities
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		//Create first joinHolder and insert
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		int joinHolderId = joinHolder.getScId();
		
		//Get joinHolder and test values
		joinHolder = schoolDataService.getEntityById(ScJoinHolder.class, joinHolderId);
		assertNotNull(INSERT_FAIL, joinHolder);
		assertEquals(INSERT_FAIL, joinHolder.getCourse().getCourseName(), "Name");
		assertEquals(INSERT_FAIL, joinHolder.getStudent().getFirstName(), "First");
		
		//Change content and update
		setStudent2(student);
		setCourse2(course);
		joinHolder.setStudent(student);
		joinHolder.setCourse(course);
		schoolDataService.updateEntity(joinHolder);
		
		//Get entity and test for successful update
		joinHolder = schoolDataService.getEntityById(ScJoinHolder.class, joinHolderId);
		assertNotNull(INSERT_FAIL, joinHolder);
		assertEquals(UPDATE_FAIL, joinHolder.getCourse().getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, joinHolder.getStudent().getFirstName(), "First2");
		
		//Delete entity
		schoolDataService.deleteEntity(joinHolder);
		
		//Try to get entity and test for delete
		joinHolder = schoolDataService.getEntityById(ScJoinHolder.class, joinHolderId);
		assertNull(DELETE_FAIL, joinHolder);
		
		//Test to ensure that student/course entities haven't been deleted
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull("Student was deleted with JoinHolder", student);
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNotNull("Course was deleted with JoinHolder", course);
	}
	
	@Transactional
	@Test
	public void testCreateStudent(){
		//Create entity and test content.
		Student student = schoolDataService.createEntity(Student.class,
				"First", "Last", LocalDate.of(1988, 10, 26), 'M', 10);
		assertEquals(CREATE_FAIL, student.getFirstName(), "First");
		assertEquals(CREATE_FAIL, student.getLastName(), "Last");
		assertEquals(CREATE_FAIL, student.getBirthDate(), LocalDate.of(1988, 10, 26));
		assertEquals(CREATE_FAIL, student.getGender(), 'M');
		assertEquals(CREATE_FAIL, student.getGrade(), 10);
		int studentId = student.getStudentId();
		
		//Test if it was inserted properly into the database.
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
	}
	
	@Transactional
	@Test
	public void testCreateCourse(){
		//Create entity and test content.
		Course course = schoolDataService.createEntity(Course.class, 
				"Name", "Subject", "LastName", 1);
		assertNotNull(CREATE_FAIL, course);
		assertEquals(CREATE_FAIL, course.getCourseName(), "Name");
		assertEquals(CREATE_FAIL, course.getSubject(), "Subject");
		assertEquals(CREATE_FAIL, course.getTeacherLastName(), "LastName");
		assertEquals(CREATE_FAIL, course.getPeriod(), 1);
		int courseId = course.getCourseId();
		
		//Test if it was inserted properly into the database.
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNotNull(INSERT_FAIL, course);
		assertEquals(INSERT_FAIL, course.getCourseName(), "Name");
	}
	
	@Transactional
	@Test
	public void testStudentCount(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get count and test its value
		long count = schoolDataService.getEntityCount(Student.class);
		assertTrue(count >= 3);
	}
	
	@Transactional
	@Test
	public void testCourseCount(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get count and test its value
		long count = schoolDataService.getEntityCount(Course.class);
		assertTrue(count >= 3);
	}
	
	@Transactional
	@Test
	public void testScJoinHolderCount(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Course course = new Course();
			setCourse1(course);
			Student student = new Student();
			setStudent1(student);
			
			schoolDataService.insertEntity(student);
			schoolDataService.insertEntity(course);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get count and test its vaue
		long count = schoolDataService.getEntityCount(ScJoinHolder.class);
		assertTrue(count >= 3);
	}
	
	@Transactional
	@Test
	public void testGetAllStudents(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get list and check for content
		List<Student> students = schoolDataService.getAllEntities(Student.class);
		assertNotNull(students);
		assertTrue(students.size() >= 3);
	}
	
	@Transactional
	@Test
	public void testGetAllCourses(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get list and check for content
		List<Course> courses = schoolDataService.getAllEntities(Course.class);
		assertNotNull(courses);
		assertTrue(courses.size() >= 3);
	}
	
	@Transactional
	@Test
	public void testGetAllScJoinHolders(){
		//Create dummy data
		for(int i = 0; i < 3; i++){
			Course course = new Course();
			setCourse1(course);
			Student student = new Student();
			setStudent1(student);
			
			schoolDataService.insertEntity(student);
			schoolDataService.insertEntity(course);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get list and check for content
		List<ScJoinHolder> joinHolders = schoolDataService.getAllEntities(ScJoinHolder.class);
		assertNotNull(joinHolders);
		assertTrue(joinHolders.size() >= 3);
	}
	
	@Transactional
	@Test
	public void testGetPreviousStudents(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get previous page and test for content
		List<Student> students1 = schoolDataService.getPreviousEntities(Student.class, 16, 5);
		assertNotNull("Students list is null", students1);
		assertTrue("List is wrong size", students1.size() == 5);
		
		//Get another page and compare the two
		List<Student> students2 = schoolDataService.getPreviousEntities(Student.class, 11, 5);
		assertNotNull("Students list is null", students2);
		assertTrue("List is wrong size", students2.size() == 5);
		for(Student s : students2){
			assertFalse("Overlap between pages", students1.contains(s));
		}
	}
	
	@Transactional
	@Test
	public void testGetPreviousCourses(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get previous page and test for content
		List<Course> courses1 = schoolDataService.getPreviousEntities(Course.class, 11, 5);
		assertNotNull("Courses list is null", courses1);
		assertTrue("List is wrong size", courses1.size() == 5);
		
		//Get another page and compare the two
		List<Course> courses2 = schoolDataService.getPreviousEntities(Course.class, 6, 5);
		assertNotNull("Courses list is null", courses2);
		assertTrue("List is wrong size", courses2.size() == 5);
		for(Course c : courses2){
			assertFalse("Overlap between pages", courses1.contains(c));
		}
	}
	
	@Transactional
	@Test
	public void testGetPreviousScJoinHolders(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			Student student = new Student();
			setStudent1(student);
			
			schoolDataService.insertEntity(student);
			schoolDataService.insertEntity(course);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get previous page and test for content
		List<ScJoinHolder> joinHolders1 = schoolDataService.getPreviousEntities(ScJoinHolder.class, 11, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		List<ScJoinHolder> joinHolders2 = schoolDataService.getPreviousEntities(ScJoinHolder.class, 6, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testGetNextStudents(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get next page and test for content
		List<Student> students1 = schoolDataService.getNextEntities(Student.class, 5, 5);
		assertNotNull("Students list is null", students1);
		assertTrue("List is wrong size", students1.size() == 5);
		
		//Get another page and compare the two
		List<Student> students2 = schoolDataService.getNextEntities(Student.class, 10, 5);
		assertNotNull("Students list is null", students2);
		assertTrue("List is wrong size", students2.size() == 5);
		for(Student s : students2){
			assertFalse("Overlap between pages", students1.contains(s));
		}
	}
	
	@Transactional
	@Test
	public void testGetNextCourses(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get next page and test for content
		List<Course> courses1 = schoolDataService.getNextEntities(Course.class, 5, 5);
		assertNotNull("Courses list is null", courses1);
		assertTrue("List is wrong size", courses1.size() == 5);
		
		//Get another page and compare the two
		List<Course> courses2 = schoolDataService.getNextEntities(Course.class, 10, 5);
		assertNotNull("Courses list is null", courses2);
		assertTrue("List is wrong size", courses2.size() == 5);
		for(Course c : courses2){
			assertFalse("Overlap between pages", courses1.contains(c));
		}
	}
	
	@Transactional
	@Test
	public void testGetNextScJoinHolders(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			Student student = new Student();
			setStudent1(student);
			
			schoolDataService.insertEntity(student);
			schoolDataService.insertEntity(course);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get next page and test for content
		List<ScJoinHolder> joinHolders1 = schoolDataService.getNextEntities(ScJoinHolder.class, 5, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		List<ScJoinHolder> joinHolders2 = schoolDataService.getNextEntities(ScJoinHolder.class, 10, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
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
	 * 	 * @param course the <tt>Course</tt> object to set.
	 */
	private void setCourse2(Course course){
		course.setCourseName("Name2");
		course.setSubject("Subject2");
		course.setTeacherLastName("LastName2");
		course.setPeriod(2);
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
		testUtil.resetStudentCourseAutoIncrement();
	}
	
}
