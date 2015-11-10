package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScJoinHolderTest {

	@Test
	public void testConstructorArgs(){
		//Create test data
		ScJoinHolder joinHolder = new ScJoinHolder();
		assertNotNull(joinHolder);
		
		//Test the values
		joinHolder = new ScJoinHolder(new Student(), new Course());
		assertNotNull(joinHolder);
		assertNotNull(joinHolder.getStudent());
		assertNotNull(joinHolder.getCourse());
	}
	
	@Test
	public void testFields(){
		//Create test data
		ScJoinHolder joinHolder = new ScJoinHolder();
		Student student = new Student();
		student.setStudentId(new Long(1));
		Course course = new Course();
		course.setCourseId(new Long(2));
		
		joinHolder.setStudent(student);
		joinHolder.setCourse(course);
		
		//Test the values
		assertNotNull(joinHolder.getStudent());
		assertEquals(joinHolder.getStudent().getStudentId(), new Long(1));
		assertNotNull(joinHolder.getCourse());
		assertEquals(joinHolder.getCourse().getCourseId(), new Long(2));
	}
	
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		ScJoinHolder jh1 = new ScJoinHolder();
		jh1.setScId(new Long(1));
		
		//Same type, doesn't match
		ScJoinHolder jh2 = new ScJoinHolder();
		jh2.setScId(new Long(2));
		
		//Same type, should match
		ScJoinHolder jh3 = new ScJoinHolder();
		jh3.setScId(new Long(1));
		
		//Test for accurate comparison
		assertFalse("ID mismatch", jh1.equals(jh2));
		assertFalse("Type mismatch", jh1.equals(o));
		assertTrue("Perfect match", jh1.equals(jh3));
	}
	
	@Test
	public void testCompareTo(){
		//The base object to be compared
		ScJoinHolder jh1 = new ScJoinHolder();
		jh1.setScId(new Long(1));
		
		//Same type, doesn't match
		ScJoinHolder jh2 = new ScJoinHolder();
		jh2.setScId(new Long(2));
		
		//Same type, should match
		ScJoinHolder jh3 = new ScJoinHolder();
		jh3.setScId(new Long(1));
		
		//Test for accurate comparison
		assertEquals("Less than test", jh1.compareTo(jh2), -1);
		assertEquals("Greater than test", jh2.compareTo(jh1), 1);
		assertEquals("Equals test", jh1.compareTo(jh3), 0);
	}
	
	
}
