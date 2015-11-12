package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PersonHolderTest {

	@Test
	public void testId(){
		//Create entity to test
		PersonHolder personHolder = new PersonHolder();
		
		//Test if null
		assertNotNull("Is null", personHolder);
		
		//Set ID
		personHolder.setPersonHolderId(new Long(1));
		
		//Test ID value
		assertEquals("ID Fail",
				personHolder.getPersonHolderId(), new Long(1));
	}
	
	@Test
	public void testHolder(){
		//Create entities for the holder
		Administrator admin = new Administrator();
		admin.setFirstName("Admin");
		admin.setAdminId(new Long(22));
		
		Student student = new Student();
		student.setFirstName("Student");
		student.setStudentId(new Long(11));
		
		SchoolUser user = new SchoolUser();
		user.setUsername("User");
		
		//Create Holder
		PersonHolder personHolder = new PersonHolder();
		
		//Test if null
		assertNotNull("Is null", personHolder);
		
		//Set User and Admin
		personHolder.setUser(user);
		personHolder.setAdmin(admin);
		
		//Test user and Admin
		assertNotNull("User is null", personHolder.getUser());
		assertEquals("User value fail", user.getUsername(), "User");
		assertNotNull("PersonType is null", personHolder.getPersonType());
		assertEquals("PersonType value fail", personHolder.getPersonType(),
				Administrator.class);
		assertEquals("PersonId value fail", personHolder.getPersonId(),
				new Long(22));
		
		//Change to Student
		personHolder.setStudent(student);
		
		//Test user and Student
		assertNotNull("User is null", personHolder.getUser());
		assertEquals("User value fail", user.getUsername(), "User");
		assertNotNull("PersonType is null", personHolder.getPersonType());
		assertEquals("PersonType value fail", personHolder.getPersonType(),
				Student.class);
		assertEquals("PersonId value fail", personHolder.getPersonId(),
				new Long(11));
	}
	
}
