package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * <tt>JUnit</tt> test case for the <tt>Course</tt>
 * entity. It runs four tests on the integrity of
 * the class.
 * <p>
 * <b>Constructor:</b> Test both constructors in this class,
 * and ensure that both produce non-null objects and that
 * any arguments are set to the appropriate fields.
 * <p>
 * <b>Fields:</b> Test all setters to ensure that they
 * all assign their values to the appropriate fields.
 * <p>
 * <b>Equals:</b> Test the overriden equals() method
 * for comparisons between <tt>Course</tt> objects.
 * <p>
 * <b>CompareTo:</b> Test the overriden compareTo() method
 * and more comparisons between <tt>Course</tt> objects.
 * 
 * @author craig
 * @version 1.0
 */
public class CourseTest{

	/**
	 * Test both constructors in this class,
	 * and ensure that both produce non-null objects and that
	 * any arguments are set to the appropriate fields.
	 */
	@Test
	public void testConstructorArgs(){
		//Create test data
		Course course = new Course();
		assertNotNull(course);
		
		//Test the values
		course = new Course("Name", "Teacher", "Subject", 1);
		assertNotNull(course);
		assertEquals("Name Fail", course.getCourseName(), "Name");
		assertEquals("Subject Fail", course.getSubject(), "Subject");
		assertEquals("Teacher Fail", course.getTeacherLastName(), "Teacher");
		assertEquals("Period Fail", course.getPeriod(), 1);
	}
	
	/**
	 * Test all setters to ensure that they
	 * all assign their values to the appropriate fields.
	 */
	@Test
	public void testFields(){
		//Create test data
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("Algebra");
		course.setSubject("Math");
		course.setTeacherLastName("Flubber");
		course.setPeriod(5);
		
		//Test the values
		assertEquals("ID fail", course.getCourseId(), 1);
		assertEquals("Name Fail", course.getCourseName(), "Algebra");
		assertEquals("Subject Fail", course.getSubject(), "Math");
		assertEquals("Teacher Fail", course.getTeacherLastName(), "Flubber");
		assertEquals("Period Fail", course.getPeriod(), 5);
	}
	
	/**
	 * Test the overriden equals() method
	 * for comparisons between <tt>Course</tt> objects.
	 */
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		Course course1 = new Course();
		course1.setCourseId(1);
		
		//Same type, doesn't match
		Course course2 = new Course();
		course2.setCourseId(2);
		
		//Same type, should match
		Course course3 = new Course();
		course3.setCourseId(1);
		
		//Test for accurate comparison
		assertFalse("ID mismatch", course1.equals(course2));
		assertFalse("Type mismatch", course1.equals(o));
		assertTrue("Perfect match", course1.equals(course3));
	}
	
	/**
	 * Test the overriden compareTo() method
	 * and more comparisons between <tt>Course</tt> objects.
	 */
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Course course1 = new Course();
		course1.setCourseId(1);
		
		//Same type, doesn't match
		Course course2 = new Course();
		course2.setCourseId(2);
		
		//Same type, should match
		Course course3 = new Course();
		course3.setCourseId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", course1.compareTo(course2), -1);
		assertEquals("Greater than test", course2.compareTo(course1), 1);
		assertEquals("Equals test", course1.compareTo(course3), 0);
	}
	
}
