package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

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
	public void testConstructor(){
		Course course = new Course();
		assertNotNull(course);
		
		course = new Course("Name", "Teacher", "Subject", 1);
		assertNotNull(course);
		assertEquals(course.getCourseName(), "Name");
		assertEquals(course.getSubject(), "Subject");
		assertEquals(course.getTeacherLastName(), "Teacher");
		assertEquals(course.getPeriod(), 1);
	}
	
	/**
	 * Test all setters to ensure that they
	 * all assign their values to the appropriate fields.
	 */
	public void testFields(){
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("Algebra");
		course.setSubject("Math");
		course.setTeacherLastName("Flubber");
		course.setPeriod(5);
		
		assertEquals(course.getCourseId(), 1);
		assertEquals(course.getCourseName(), "Algebra");
		assertEquals(course.getSubject(), "Math");
		assertEquals(course.getTeacherLastName(), "Flubber");
		assertEquals(course.getPeriod(), 5);
	}
	
	/**
	 * Test the overriden equals() method
	 * for comparisons between <tt>Course</tt> objects.
	 */
	public void testEquals(){
		Object o = new Object();
		
		Course course1 = new Course();
		course1.setCourseId(1);
		
		Course course2 = new Course();
		course2.setCourseId(2);
		
		Course course3 = new Course();
		course3.setCourseId(1);
		
		assertFalse(course1.equals(course2));
		assertFalse(course1.equals(o));
		assertTrue(course1.equals(course3));
	}
	
	/**
	 * Test the overriden compareTo() method
	 * and more comparisons between <tt>Course</tt> objects.
	 */
	public void testCompareTo(){
		Course course1 = new Course();
		course1.setCourseId(1);
		
		Course course2 = new Course();
		course2.setCourseId(2);
		
		Course course3 = new Course();
		course3.setCourseId(3);
		
		TreeSet<Course> set = new TreeSet<>();
		set.add(course2);
		set.add(course1);
		set.add(course3);
		
		assertEquals(set.size(), 3);
		assertEquals(set.pollFirst(), course1);
		assertEquals(set.pollFirst(), course2);
		assertEquals(set.pollFirst(), course3);
	}
	
}
