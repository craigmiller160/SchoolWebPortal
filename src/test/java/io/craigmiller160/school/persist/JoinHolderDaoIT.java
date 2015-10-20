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

//TODO document how this is a BIG test class
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
		
		//Get joinHolder and test values
		joinHolder = scJoinHolderDao.getEntityById(joinHolderId);
		assertNotNull(INSERT_FAIL, joinHolder);
		assertEquals(INSERT_FAIL, joinHolder.getCourse().getCourseName(), "Name");
		assertEquals(INSERT_FAIL, joinHolder.getStudent().getFirstName(), "First");
		
		//Change content and update
		student = studentDao.getEntityById(studentId2);
		course = courseDao.getEntityById(courseId2);
		joinHolder.setStudent(student);
		joinHolder.setCourse(course);
		scJoinHolderDao.updateEntity(joinHolder);
		
		//Get entity and test for successful update
		joinHolder = scJoinHolderDao.getEntityById(joinHolderId);
		assertNotNull(INSERT_FAIL, joinHolder);
		assertEquals(UPDATE_FAIL, joinHolder.getCourse().getCourseName(), "Name2");
		assertEquals(UPDATE_FAIL, joinHolder.getStudent().getFirstName(), "First2");
		
		//Delete entity
		scJoinHolderDao.deleteEntity(joinHolder);
		
		//Try to get entity and test for delete
		joinHolder = scJoinHolderDao.getEntityById(joinHolderId);
		assertNull(DELETE_FAIL, joinHolder);
		
		//Test to ensure that student/course entities haven't been deleted
		student = studentDao.getEntityById(studentId2);
		assertNotNull("Student was deleted with JoinHolder", student);
		course = courseDao.getEntityById(courseId2);
		assertNotNull("Course was deleted with JoinHolder", course);
	}
	
	@Transactional
	@Test
	public void testCountAll(){
		//Create and insert test data
		Course course = courseDao.getEntityById(courseId1);
		Student student = studentDao.getEntityById(studentId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		course = courseDao.getEntityById(courseId2);
		student = studentDao.getEntityById(studentId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Get entity count and test accuracy
		long count = scJoinHolderDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert test data
		Course course = courseDao.getEntityById(courseId1);
		Student student = studentDao.getEntityById(studentId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		course = courseDao.getEntityById(courseId2);
		student = studentDao.getEntityById(studentId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Get list and check for content
		List<ScJoinHolder> joinHolders = scJoinHolderDao.getAllEntities();
		assertNotNull(joinHolders);
		assertTrue(joinHolders.size() >= 2);
	}
	
	@Transactional
	@Test
	public void testPreviousPage(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Course course = courseDao.getEntityById(courseId1);
			Student student = studentDao.getEntityById(studentId1);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get previous page and test for content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getPreviousEntities(11, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getPreviousEntities(6, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testNextPage(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Course course = courseDao.getEntityById(courseId1);
			Student student = studentDao.getEntityById(studentId1);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get next page and test for content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getNextEntities(5, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getNextEntities(10, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testCountForStudent(){
		//Create entities and insert. Same student, different course.
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		course = courseDao.getEntityById(courseId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Get count based on student
		long count = scJoinHolderDao.getJoinCountFor(Student.class, studentId1);
		assertEquals(count, 2);
	}
	
	@Transactional
	@Test
	public void testCountForCourse(){
		//Create entities and insert. Same course, different student.
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		student = studentDao.getEntityById(studentId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Get count based on course
		long count = scJoinHolderDao.getJoinCountFor(Course.class, courseId1);
		assertEquals(count, 2);
	}
	
	@Transactional
	@Test
	public void testGetAllForStudent(){
		//Create entities and insert. Same student, different course.
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		course = courseDao.getEntityById(courseId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Retrieve list based on student and test
		List<ScJoinHolder> joinHolders = scJoinHolderDao.getAllJoinsFor(Student.class, studentId1);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals(joinHolders.size(), 2);
	}
	
	@Transactional
	@Test
	public void testGetAllForCourse(){
		//Create entities and insert. Same course, different student.
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		student = studentDao.getEntityById(studentId2);
		joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Retrieve list based on course and test
		List<ScJoinHolder> joinHolders = scJoinHolderDao.getAllJoinsFor(Course.class, courseId1);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals(joinHolders.size(), 2);
	}
	
	@Transactional
	@Test
	public void testPreviousForStudent(){
		//Create large list of dummy data
		Student student = studentDao.getEntityById(studentId1);
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			courseDao.insertEntity(course);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getPreviousJoinsFor(Student.class, studentId1, 11, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId1);
		}
		
		//Get another page and compare
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getPreviousJoinsFor(Student.class, studentId1, 6, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId1);
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testPreviousForCourse(){
		//Create large list of dummy data
		Course course = courseDao.getEntityById(courseId1);
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			studentDao.insertEntity(student);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getPreviousJoinsFor(Course.class, courseId1, 11, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId1);
		}
		
		//Get another page and compare
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getPreviousJoinsFor(Course.class, courseId1, 6, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId1);
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testNextForStudent(){
		//Create large list of dummy data
		Student student = studentDao.getEntityById(studentId1);
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			courseDao.insertEntity(course);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getNextJoinsFor(Student.class, studentId1, 5, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId1);
		}
		
		//Get another page and compare
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getNextJoinsFor(Student.class, studentId1, 10, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId1);
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testNextforCourse(){
		//Create large list of dummy data
		Course course = courseDao.getEntityById(courseId1);
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			studentDao.insertEntity(student);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getNextJoinsFor(Course.class, courseId1, 5, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId1);
		}
		
		//Get another page and compare the two
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getNextJoinsFor(Course.class, courseId1, 10, 5);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 5);
		for(ScJoinHolder jh : joinHolders2){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId1);
			assertFalse("Overlap between pages", joinHolders1.contains(jh));
		}
	}
	
	@Transactional
	@Test
	public void testRemoveForStudent(){
		//Create dummy data
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Removed join with the student and test result
		scJoinHolderDao.removeJoinsFor(Student.class, studentId1);
		List<ScJoinHolder> joinHolders = scJoinHolderDao.getAllJoinsFor(Student.class, studentId1);
		assertNotNull("JoinHolders list is null", joinHolders);
		assertEquals("JoinHolders is not empty", joinHolders.size(), 0);
	}
	
	@Transactional
	@Test
	public void testRemoveForCourse(){
		//Create dummy data
		Student student = studentDao.getEntityById(studentId1);
		Course course = courseDao.getEntityById(courseId1);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		scJoinHolderDao.insertEntity(joinHolder);
		
		//Remove join with the course and test result
		scJoinHolderDao.removeJoinsFor(Course.class, courseId1);
		List<ScJoinHolder> joinHolders = scJoinHolderDao.getAllJoinsFor(Course.class, courseId1);
		assertNotNull("JoinHolders list is null", joinHolders);
		assertEquals("JoinHolders is not empty", joinHolders.size(), 0);
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
		testUtil.resetStudentCourseAutoIncrement();
	}
}
