package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScJoinHolderTest {

	@Test
	public void testConstructorArgs(){
		ScJoinHolder joinHolder = new ScJoinHolder();
		assertNotNull(joinHolder);
		
		joinHolder = new ScJoinHolder(new Student(), new Course());
		assertNotNull(joinHolder);
		assertNotNull(joinHolder.getStudent());
		assertNotNull(joinHolder.getCourse());
	}
	
	@Test
	public void testFields(){
		ScJoinHolder joinHolder = new ScJoinHolder();
		Student student = new Student();
		student.setStudentId(1);
		Course course = new Course();
		course.setCourseId(2);
		
		joinHolder.setStudent(student);
		joinHolder.setCourse(course);
		
		assertNotNull(joinHolder.getStudent());
		assertEquals(joinHolder.getStudent().getStudentId(), 1);
		assertNotNull(joinHolder.getCourse());
		assertEquals(joinHolder.getCourse().getCourseId(), 2);
	}
	
	@Test
	public void testEquals(){
		Object o = new Object();
		ScJoinHolder jh1 = new ScJoinHolder();
		jh1.setScId(1);
		ScJoinHolder jh2 = new ScJoinHolder();
		jh2.setScId(2);
		ScJoinHolder jh3 = new ScJoinHolder();
		jh3.setScId(1);
		
		assertFalse("ID mismatch", jh1.equals(jh2));
		assertFalse("Type mismatch", jh1.equals(o));
		assertTrue("Perfect match", jh1.equals(jh3));
	}
	
	@Test
	public void testCompareTo(){
		ScJoinHolder jh1 = new ScJoinHolder();
		jh1.setScId(1);
		ScJoinHolder jh2 = new ScJoinHolder();
		jh2.setScId(2);
		ScJoinHolder jh3 = new ScJoinHolder();
		jh3.setScId(1);
		
		assertEquals("Less than test", jh1.compareTo(jh2), -1);
		assertEquals("Greater than test", jh2.compareTo(jh1), 1);
		assertEquals("Equals test", jh1.compareTo(jh3), 0);
	}
	
	
}
