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
import io.craigmiller160.school.util.HibernateTestUtil;

/**
 * An integration test for the <tt>JoinHolder</tt>
 * entity DAO used by this application. Because 
 * <tt>JoinHolders</tt> operations must be tested
 * using the entities being joined to ensure all
 * the DAO methods are working properly, this is
 * a LARGE test class, with many tests in it.
 * <p>
 * Because <tt>JoinHolder</tt> entities depend
 * on the entities they are being joined with,
 * this test cannot succeed if the integration
 * tests for the DAOs for the entities being
 * joined do not succeed as well. If the other
 * DAOs do not work, then join operations cannot
 * be successfully tested.
 * 
 * @author craig
 * @version 1.0
 */
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
	
	/**
	 * The DAO for the <tt>JoinHolder</tt> entity being tested.
	 */
	@Autowired (required=true)
	private GenericJoinHolderDaoBean<ScJoinHolder> scJoinHolderDao;
	
	/**
	 * The DAO for <tt>Student</tt> entities, needed to test
	 * the <tt>JoinHolder</tt> DAO.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Student> studentDao;
	
	/**
	 * The DAO for <tt>Course</tt> entities, needed to test
	 * the <tt>JoinHolder</tt> DAO.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Course> courseDao;
	
	/**
	 * The ID of the first <tt>Student</tt> entity used
	 * in the <tt>JoinHolder</tt> tests.
	 */
	private int studentId1;
	
	/**
	 * The ID of the first <tt>Course</tt> entity used
	 * in the <tt>JoinHolder</tt> tests.
	 */
	private int courseId1;
	
	/**
	 * The ID of the second <tt>Student</tt> entity used
	 * in the <tt>JoinHolder</tt> tests.
	 */
	private int studentId2;
	
	/**
	 * The ID of the second <tt>Course</tt> entity used
	 * in the <tt>JoinHolder</tt> tests.
	 */
	private int courseId2;
	
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
	 * Get the DAO for <tt>JoinHolder</tt> entities.
	 * 
	 * @return the DAO for <tt>JoinHolder</tt> entities.
	 */
	public GenericJoinHolderDaoBean<ScJoinHolder> getScJoinHolderDao() {
		return scJoinHolderDao;
	}

	/**
	 * Set the DAO for <tt>JoinHolder</tt> entities.
	 * 
	 * @param scJoinHolderDao the DAO for <tt>JoinHolder</tt> entities.
	 */
	public void setScJoinHolderDao(GenericJoinHolderDaoBean<ScJoinHolder> scJoinHolderDao) {
		this.scJoinHolderDao = scJoinHolderDao;
	}
	
	/**
	 * Before each test, run this operation to prepare
	 * entities to be joined.
	 */
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
	
	/**
	 * Test CRUD operations for the DAO.
	 */
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
	
	/**
	 * Test count operation for all <tt>JoinHolder</tt>
	 * entities in the DAO.
	 */
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
	
	/**
	 * Test get operation for all <tt>JoinHolder</tt>
	 * entities in the DAO.
	 */
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
	
	/**
	 * Test pagination method for all <tt>JoinHolders</tt>
	 * in the DAO.
	 */
	@Transactional
	@Test
	public void testJoinHolderPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Course course = courseDao.getEntityById(courseId1);
			Student student = studentDao.getEntityById(studentId1);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			scJoinHolderDao.insertEntity(joinHolder);
		}
		
		//Get page and test for content
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getEntitiesByPage(10, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getEntitiesByPage(5, 6);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				joinHolders1.get(0), joinHolders2.get(joinHolders2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < joinHolders1.size(); i++){
			assertFalse("Overlap between pages", 
					joinHolders2.contains(joinHolders1.get(i)));
		}
	}
	
	/**
	 * Test count operation for entities joined with
	 * a specified <tt>Student</tt> entity.
	 */
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
	
	/**
	 * Test count operation for entities joined with
	 * a specified <tt>Course</tt> entity.
	 */
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
	
	/**
	 * Test getting all entities joined with specified
	 * <tt>Student</tt>.
	 */
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
	
	/**
	 * Test getting all entities joined with specified
	 * <tt>Course</tt>.
	 */
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
	
	/**
	 * Test retrieving entities joined with the specified
	 * <tt>Student</tt> in paginated fashion.
	 */
	@Transactional
	@Test
	public void testPaginationForStudent(){
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
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getEntitiesByPageFor(
				Student.class, studentId1, 10, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId1);
		}
		
		//Get another page and compare
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getEntitiesByPageFor(
				Student.class, studentId1, 5, 6);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				joinHolders1.get(0), joinHolders2.get(joinHolders2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 0; i < joinHolders2.size(); i++){
			assertEquals("Wrong studentId", 
					joinHolders2.get(i).getStudent().getStudentId(), studentId1);
			//Don't do this for the last entry in the list
			if(i != joinHolders2.size() - 1){
				assertFalse("Overlap between pages", 
						joinHolders1.contains(joinHolders2.get(i)));
			}
		}
	}
	
	/**
	 * Test retrieving entities joined with the specified
	 * <tt>Course</tt> in paginated fashion.
	 */
	@Transactional
	@Test
	public void testPaginationForCourse(){
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
		List<ScJoinHolder> joinHolders1 = scJoinHolderDao.getEntitiesByPageFor(
				Course.class, courseId1, 10, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId1);
		}
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = scJoinHolderDao.getEntitiesByPageFor(
				Course.class, courseId1, 5, 6);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				joinHolders1.get(0), joinHolders2.get(joinHolders2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 0; i < joinHolders2.size(); i++){
			assertEquals("Wrong courseID", 
					joinHolders2.get(i).getCourse().getCourseId(), courseId1);
			//Don't do this for the last entry in the list
			if(i != joinHolders2.size() - 1){
				assertFalse("Overlap between pages", 
						joinHolders1.contains(joinHolders2.get(i)));
			}
		}
	}
	
	/**
	 * Test remove joined entities for
	 * specified <tt>Student</tt>.
	 */
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
	
	/**
	 * Test remove joined entities for
	 * specified <tt>Course</tt>.
	 */
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
