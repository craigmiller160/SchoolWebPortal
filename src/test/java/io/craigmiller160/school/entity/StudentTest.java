package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

/**
 * <tt>JUnit</tt> test case for the <tt>Student</tt>
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
 * for comparisons between <tt>Student</tt> objects.
 * <p>
 * <b>CompareTo:</b> Test the overriden compareTo() method
 * and more comparisons between <tt>Student</tt> objects.
 * 
 * @author craig
 * @version 1.0
 */
public class StudentTest{

	/**
	 * Test both constructors in this class,
	 * and ensure that both produce non-null objects and that
	 * any arguments are set to the appropriate fields.
	 */
	@Test
	public void testConstructorArgs(){
		Student student = new Student();
		assertNotNull(student);
		
		student = new Student("FirstName", "LastName", 
				LocalDate.of(1900, 1, 1), Gender.UNKNOWN, 1);
		assertNotNull(student);
		assertEquals("First Name is wrong", student.getFirstName(), "FirstName");
		assertEquals("Last Name is wrong", student.getLastName(), "LastName");
		assertEquals("Birth Date is wrong", student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals("Gender is wrong", student.getGender(), Gender.UNKNOWN);
		assertEquals("Grade is wrong", student.getGrade(), 1);
	}
	
	/**
	 * Test all setters to ensure that they
	 * all assign their values to the appropriate fields.
	 */
	@Test
	public void testFields(){
		Student student = new Student();
		assertNotNull(student);
		student.setStudentId(1);
		student.setFirstName("Joe");
		student.setLastName("Dirt");
		student.setBirthDate(LocalDate.of(1988, 10, 26));
		student.setGender(Gender.UNKNOWN);
		student.setGrade(5);
		
		assertEquals("ID is wrong", student.getStudentId(), 1);
		assertEquals("First Name is wrong", student.getFirstName(), "Joe");
		assertEquals("Last Name is wrong", student.getLastName(), "Dirt");
		assertEquals("Birth Date is wrong", student.getBirthDate(), 
				LocalDate.of(1988, 10, 26));
		assertEquals("Gender is wrong", student.getGender(), Gender.UNKNOWN);
		assertEquals("Grade is wrong", student.getGrade(), 5);
		assertEquals("Age is wrong", student.getAge(), 27);
	}
	
	/**
	 * Test the overriden equals() method
	 * for comparisons between <tt>Student</tt> objects.
	 */
	@Test
	public void testEquals(){
		Object o = new Object();
		
		Student student1 = new Student();
		student1.setStudentId(1);
		
		Student student2 = new Student();
		student2.setStudentId(2);
		
		Student student3 = new Student();
		student3.setStudentId(1);
		
		assertFalse("ID mismatch", student1.equals(student2));
		assertFalse("Type mismatch", student1.equals(o));
		assertTrue("Perfect match", student1.equals(student3));
	}
	
	/**
	 * Test the overriden compareTo() method
	 * and more comparisons between <tt>Student</tt> objects.
	 */
	@Test
	public void testCompareTo(){
		Student student1 = new Student();
		student1.setStudentId(1);
		
		Student student2 = new Student();
		student2.setStudentId(2);
		
		Student student3 = new Student();
		student3.setStudentId(1);
		
		assertEquals("Less than test", student1.compareTo(student2), -1);
		assertEquals("Greater than test", student2.compareTo(student1), 1);
		assertEquals("Equals test", student1.compareTo(student3), 0);
	}
	
}
