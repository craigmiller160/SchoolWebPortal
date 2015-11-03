package io.craigmiller160.school.service;

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
import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.util.HibernateTestUtil;

/**
 * An integration test for the service layer
 * of this application. Because the service
 * class depends on the DAO classes to successfully 
 * perform its operations, if the tests for the DAO
 * classes fail, then the tests for this will fail.
 * <p>
 * This is also a LARGE test, as the service class
 * joins together operations from all DAOs and as 
 * such all need to be tested.
 * 
 * @author craig
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class SchoolDataServiceIT {

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
	
	/**
	 * The service class of this application, to be tested here.
	 */
	@Autowired (required=true)
	private GenericEntityServiceBean schoolDataService;

	/**
	 * Get the service class of this application, to be tested here.
	 * 
	 * @return the service class of this application, to be tested here.
	 */
	public GenericEntityServiceBean getSchoolDataService() {
		return schoolDataService;
	}

	/**
	 * Set the service class of this application, to be tested here.
	 * 
	 * @param schoolDataService the service class of this application, 
	 * to be tested here.
	 */
	public void setSchoolDataService(GenericEntityServiceBean schoolDataService) {
		this.schoolDataService = schoolDataService;
	}
	
	/**
	 * Test CRURD operations with <tt>Student</tt> entities.
	 */
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
		assertEquals(INSERT_FAIL, student.getGender(), Gender.UNKNOWN);
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
		assertEquals(UPDATE_FAIL, student.getGender(), Gender.MALE);
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		//Delete entity
		schoolDataService.deleteEntity(student);
		
		//Try to get entity and test for delete
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	/**
	 * Test CRURD operations with <tt>Course</tt> entities.
	 */
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
	
	/**
	 * Test CRURD operations with <tt>JoinHolder</tt> entities.
	 */
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
	
	/**
	 * Test create operation for <tt>Student</tt> entities.
	 */
	@Transactional
	@Test
	public void testCreateStudent(){
		//Create entity and test content.
		Student student = schoolDataService.createEntity(Student.class,
				"First", "Last", LocalDate.of(1988, 10, 26), Gender.MALE, 10);
		assertEquals(CREATE_FAIL, student.getFirstName(), "First");
		assertEquals(CREATE_FAIL, student.getLastName(), "Last");
		assertEquals(CREATE_FAIL, student.getBirthDate(), LocalDate.of(1988, 10, 26));
		assertEquals(CREATE_FAIL, student.getGender(), Gender.MALE);
		assertEquals(CREATE_FAIL, student.getGrade(), 10);
		int studentId = student.getStudentId();
		
		//Test if it was inserted properly into the database.
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
	}
	
	/**
	 * Test create operation for <tt>Course</tt> entities.
	 */
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
	
	/**
	 * Test count operation for <tt>Student</tt> entities.
	 */
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
	
	/**
	 * Test count operation for <tt>Course</tt> entities.
	 */
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
	
	/**
	 * Test count operation for <tt>JoinHolder</tt> entities.
	 */
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
	
	/**
	 * Test get all operation for <tt>Student</tt> entities.
	 */
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
	
	/**
	 * Test get all operation for <tt>Course</tt> entities.
	 */
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
	
	/**
	 * Test get all operation for <tt>JoinHolder</tt> entities.
	 */
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
	
	/**
	 * Test getting <tt>Student</tt> entities in a
	 * paginated way.
	 */
	@Transactional
	@Test
	public void testGetStudentsPaginated(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get page and test for content
		List<Student> students1 = schoolDataService.getEntitiesByPage(
				Student.class, 2, 5);
		assertNotNull("Students list is null", students1);
		assertTrue("List is wrong size", students1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Student> students2 = schoolDataService.getEntitiesByPage(
				Student.class, 1, 6);
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
	 * Test getting <tt>Course</tt> entities in a
	 * paginated way.
	 */
	@Transactional
	@Test
	public void testGetCoursesPaginated(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get page and test for content
		List<Course> courses1 = schoolDataService.getEntitiesByPage(
				Course.class, 2, 5);
		assertNotNull("Courses list is null", courses1);
		assertTrue("List is wrong size", courses1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Course> courses2 = schoolDataService.getEntitiesByPage(
				Course.class, 1, 6);
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
			assertFalse("Overlap between pages", 
					courses2.contains(courses1.get(i)));
		}
	}
	
	/**
	 * Test getting <tt>JoinHolder</tt> entities in a
	 * paginated way.
	 */
	@Transactional
	@Test
	public void testGetScJoinHoldersPaginated(){
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
		
		//Get page and test for content
		List<ScJoinHolder> joinHolders1 = schoolDataService.getEntitiesByPage(
				ScJoinHolder.class, 2, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = schoolDataService.getEntitiesByPage(
				ScJoinHolder.class, 1, 6);
		assertNotNull("JoinHolders list is null", joinHolders2);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
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
	 * Test get all joined entities with
	 * specified <tt>Student</tt>.
	 */
	@Transactional
	@Test
	public void testGetAllForStudent(){
		//Create entities and insert.
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		course = new Course();
		setCourse2(course);
		schoolDataService.insertEntity(course);
		joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Retrieve list based on student and test
		List<ScJoinHolder> joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Student.class, studentId);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals(joinHolders.size(), 2);
	}
	
	/**
	 * Test get all joined entities with
	 * specified <tt>Course</tt>.
	 */
	@Transactional
	@Test
	public void testGetAllForCourse(){
		//Create entities and insert.
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		student = new Student();
		setStudent2(student);
		schoolDataService.insertEntity(student);
		joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Retrieve list based on course and test
		List<ScJoinHolder> joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Course.class, courseId);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals(joinHolders.size(), 2);
	}
	
	/**
	 * Get entities joined with specified
	 * <tt>Student</tt> in a paginated way.
	 */
	@Transactional
	@Test
	public void testPaginatedForStudent(){
		//Create large list of dummy data
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		for(int i = 0; i < 20; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = schoolDataService.getEntitiesByPageFor(
				ScJoinHolder.class, Student.class, studentId, 
				2, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		//assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong studentId", jh.getStudent().getStudentId(), studentId);
		}
		
		//Get another page and compare
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = schoolDataService.getEntitiesByPageFor(
				ScJoinHolder.class, Student.class, studentId, 
				1, 6);
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
					joinHolders2.get(i).getStudent().getStudentId(), studentId);
			//Don't do this for the last entry in the list
			if(i != joinHolders2.size() - 1){
				assertFalse("Overlap between pages", 
						joinHolders1.contains(joinHolders2.get(i)));
			}
		}
	}
	
	/**
	 * Get entities joined with specified
	 * <tt>Course</tt> in a paginated way.
	 */
	@Transactional
	@Test
	public void testPaginatedForCourse(){
		//Create large list of dummy data
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get page and test content
		List<ScJoinHolder> joinHolders1 = schoolDataService.getEntitiesByPageFor(
				ScJoinHolder.class, Course.class, courseId, 
				2, 5);
		assertNotNull("JoinHolders list is null", joinHolders1);
		assertTrue("List is wrong size", joinHolders1.size() == 5);
		for(ScJoinHolder jh : joinHolders1){
			assertEquals("Wrong courseID", jh.getCourse().getCourseId(), courseId);
		}
		
		//Get another page and compare
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<ScJoinHolder> joinHolders2 = schoolDataService.getEntitiesByPageFor(
				ScJoinHolder.class, Course.class, courseId, 
				1, 6);
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
					joinHolders2.get(i).getCourse().getCourseId(), courseId);
			//Don't do this for the last entry in the list
			if(i != joinHolders2.size() - 1){
				assertFalse("Overlap between pages", 
						joinHolders1.contains(joinHolders2.get(i)));
			}
		}
	}
	
	/**
	 * Test getting count of all entities joined
	 * with specified <tt>Student</tt>.
	 */
	@Transactional
	@Test
	public void testGetCountForStudent(){
		//Create entities and insert.
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		course = new Course();
		setCourse2(course);
		schoolDataService.insertEntity(course);
		
		joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Get count based on student
		long count = schoolDataService.getJoinCountFor(ScJoinHolder.class, 
				Student.class, studentId);
		assertEquals(count, 2);
	}
	
	/**
	 * Test getting count of all entities joined
	 * with specified <tt>Course</tt>.
	 */
	@Transactional
	@Test
	public void testGetCountForCourse(){
		//Create entities and insert.
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		student = new Student();
		setStudent2(student);
		schoolDataService.insertEntity(student);
		
		joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Get count based on course
		long count = schoolDataService.getJoinCountFor(ScJoinHolder.class, 
				Course.class, courseId);
		assertEquals(count, 2);
	}
	
	/**
	 * Test operation to create a <tt>JoinHolder</tt>
	 * by specifying the entities to be joined.
	 * This test depends on the <tt>getJoinsFor</tt>
	 * operation to be working, which is tested
	 * separately here. If that test fails, this
	 * one will too. 
	 */
	@Transactional
	@Test
	public void testJoinEntities(){
		//Create entities and insert.
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		//Use convenience method to join entities and insert
		schoolDataService.joinEntities(ScJoinHolder.class, student, course);
		
		//Retrieve joins for student and test
		List<ScJoinHolder> joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Student.class, studentId);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals("JoinHolders wrong size", joinHolders.size(), 1);
		assertEquals("Wrong JoinHolder retrieved", 
				joinHolders.get(0).getCourse().getCourseId(), courseId);
		
		//Retrieve joins for course and test
		joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Course.class, courseId);
		assertNotNull("JoinHolders is null", joinHolders);
		assertEquals("JoinHolders wrong size", joinHolders.size(), 1);
		assertEquals("Wrong JoinHolder retrieved", 
				joinHolders.get(0).getStudent().getStudentId(), studentId);
	}
	
	/**
	 * Test removing all entities joined with
	 * a specified student.
	 */
	@Transactional
	@Test
	public void testRemoveForStudent(){
		//Create dummy data
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Remove join via student
		schoolDataService.removeJoinsFor(ScJoinHolder.class, Student.class, 
				studentId);
		
		//Retrieve JoinHolder by student and test it
		List<ScJoinHolder> joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Student.class, studentId);
		assertNotNull("JoinHolders list is null", joinHolders);
		assertEquals("Wrong size", joinHolders.size(), 0);
	}
	
	/**
	 * Test remove all entities joined with
	 * a specified <tt>Course</tt>.
	 */
	@Transactional
	@Test
	public void testRemoveForCourse(){
		//Create dummy data
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		ScJoinHolder joinHolder = new ScJoinHolder(student, course);
		schoolDataService.insertEntity(joinHolder);
		
		//Remove join via course
		schoolDataService.removeJoinsFor(ScJoinHolder.class, Course.class, 
				courseId);
		
		//Retreive JoinHolder by course and test it
		List<ScJoinHolder> joinHolders = schoolDataService.getAllJoinsFor(
				ScJoinHolder.class, Course.class, courseId);
		assertNotNull("JoinHolders list is null", joinHolders);
		assertEquals("Wrong size", joinHolders.size(), 0);
	}
	
	/**
	 * Test delete by ID for <tt>Student</tt> entities.
	 * This test depends on the basic CRUD operations 
	 * to be working, if they do not then this test will fail too.
	 */
	@Transactional
	@Test
	public void testStudentDeleteById(){
		//Create dummy data
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		
		//Test to ensure successful insert
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNotNull("Student insert failed", student);
		
		//Delete student
		schoolDataService.deleteEntityById(Student.class, studentId);
		
		//Attempt to retrieve student to test for deletion
		student = schoolDataService.getEntityById(Student.class, studentId);
		assertNull("Student not deleted", student);
	}
	
	/**
	 * Test delete by ID for <tt>Course</tt> entities.
	 * This test depends on the basic CRUD operations 
	 * to be working, if they do not then this test will fail too.
	 */
	@Transactional
	@Test
	public void testCourseDeleteById(){
		//Create dummy data
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		
		//Test to ensure successful insert
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNotNull("Course insert failed", course);
		
		//Delete course
		schoolDataService.deleteEntityById(Course.class, courseId);
		
		//Attempt to retrieve course to test for deletion
		course = schoolDataService.getEntityById(Course.class, courseId);
		assertNull("Course not deleted", course);
	}
	
	/**
	 * Test delete by ID for <tt>JoinHolder</tt> entities.
	 * This test depends on the basic CRUD operations 
	 * to be working, if they do not then this test will fail too.
	 */
	@Transactional
	@Test
	public void testJoinHolderDeleteById(){
		//Create dummy data
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		
		ScJoinHolder joinHolder = new ScJoinHolder();
		joinHolder.setCourse(course);
		joinHolder.setStudent(student);
		schoolDataService.insertEntity(joinHolder);
		int jhId = joinHolder.getScId();
		
		//Test to ensure successful insert
		joinHolder = schoolDataService.getEntityById(ScJoinHolder.class, jhId);
		assertNotNull("JoinHolder insert failed", joinHolder);
		
		//Delete JoinHolder
		schoolDataService.deleteEntityById(ScJoinHolder.class, jhId);
		
		//Attempt to retrieve JoinHolder to test for deletion
		joinHolder = schoolDataService.getEntityById(ScJoinHolder.class, jhId);
		assertNull("JoinHolder not deleted", joinHolder);
	}
	
	/**
	 * Test the hasPagesRemaining method in the service
	 * class for <tt>Student</tt> entities. This is dependent 
	 * on the count methods, and
	 * if they don't work, this won't
	 * work either.
	 */
	@Transactional
	@Test
	public void testStudentHasPagesRemaining(){
		//Create dummy data
		for(int i = 0; i < 10; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
		}
		
		//Get the current count to set up the comparison
		long actualCount = schoolDataService.getEntityCount(Student.class);
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", schoolDataService.hasPagesRemaining(
				Student.class, 1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", schoolDataService.hasPagesRemaining(
				Student.class, pageCount + 1, 10));
	}
	
	/**
	 * Test the hasPagesRemaining method in the service
	 * class for <tt>Course</tt> entities. This is dependent 
	 * on the count methods, and
	 * if they don't work, this won't
	 * work either.
	 */
	@Transactional
	@Test
	public void testCourseHasPagesRemaining(){
		//Create dummy data
		for(int i = 0; i < 10; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
		}
		
		//Get the current count to set up the comparison
		long actualCount = schoolDataService.getEntityCount(Course.class);
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", schoolDataService.hasPagesRemaining(
				Course.class, 1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", schoolDataService.hasPagesRemaining(
				Course.class, pageCount + 1, 10));
	}
	
	/**
	 * Test the hasPagesRemaining method in the service
	 * class for <tt>ScJoinHolder</tt> entities. This is dependent 
	 * on the count methods, and
	 * if they don't work, this won't
	 * work either.
	 */
	@Transactional
	@Test
	public void testJoinHolderHasPagesRemaining(){
		//Create dummy data
		for(int i = 0; i < 10; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
			
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get the current count to set up the comparison
		long actualCount = schoolDataService.getEntityCount(ScJoinHolder.class);
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", schoolDataService.hasPagesRemaining(
				ScJoinHolder.class, 1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", schoolDataService.hasPagesRemaining(
				ScJoinHolder.class, pageCount + 1, 10));
	}
	
	/**
	 * Test hasPagesRemainingFor method for
	 * joins from a <tt>Student</tt> entity.
	 * This is dependent 
	 * on the count methods, and
	 * if they don't work, this won't
	 * work either.
	 */
	@Transactional
	@Test
	public void testHasPagesRemainingForStudent(){
		//Create dummy data
		Student student = new Student();
		setStudent1(student);
		schoolDataService.insertEntity(student);
		int studentId = student.getStudentId();
		for(int i = 0; i < 10; i++){
			Course course = new Course();
			setCourse1(course);
			schoolDataService.insertEntity(course);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get the current count to set up the comparison
		long actualCount = schoolDataService.getJoinCountFor(
				ScJoinHolder.class, Student.class, studentId);
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", schoolDataService.hasPagesRemainingFor(
				ScJoinHolder.class, Student.class, studentId, 1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", schoolDataService.hasPagesRemainingFor(
				ScJoinHolder.class, Student.class, studentId, pageCount + 1, 10));
	}
	
	/**
	 * Test hasPagesRemainingFor method for
	 * joins from a <tt>Student</tt> entity.
	 * This is dependent 
	 * on the count methods, and
	 * if they don't work, this won't
	 * work either.
	 */
	@Transactional
	@Test
	public void testHasPagesRemainingForCourse(){
		//Create dummy data
		Course course = new Course();
		setCourse1(course);
		schoolDataService.insertEntity(course);
		int courseId = course.getCourseId();
		for(int i = 0; i < 10; i++){
			Student student = new Student();
			setStudent1(student);
			schoolDataService.insertEntity(student);
			
			ScJoinHolder joinHolder = new ScJoinHolder(student, course);
			schoolDataService.insertEntity(joinHolder);
		}
		
		//Get the current count to set up the comparison
		long actualCount = schoolDataService.getJoinCountFor(
				ScJoinHolder.class, Course.class, courseId);
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", schoolDataService.hasPagesRemainingFor(
				ScJoinHolder.class, Course.class, courseId, 1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", schoolDataService.hasPagesRemainingFor(
				ScJoinHolder.class, Course.class, courseId, pageCount + 1, 10));
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
		student.setGender(Gender.UNKNOWN);
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
		student.setGender(Gender.MALE);
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
