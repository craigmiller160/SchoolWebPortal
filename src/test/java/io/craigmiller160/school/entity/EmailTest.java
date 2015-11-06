package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//TODO document how this is only testing Email fields,
//and as such it instantiates the abstract class.
//The DAO tests for Student/Admin will test the subclasses.
public class EmailTest {

	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testConstructorArgs(){
		//Create test data
		Email email = new Email(EmailType.PERSONAL, 
				"bob@gmail.com"){};
		
		//Test the values
		assertNotNull(email);
		assertEquals("Type Fail", email.getEmailType(), EmailType.PERSONAL);
		assertEquals("Email Fail", email.getEmailAddress(), "bob@gmail.com");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testFields(){
		//Create test data
		Email email = new Email(){};
		email.setEmailId(1);
		email.setEmailType(EmailType.PERSONAL);
		email.setEmailAddress("bob@gmail.com");
		
		//Test the values
		assertNotNull(email);
		assertEquals("ID Fail", email.getEmailId(), 1);
		assertEquals("Type Fail", email.getEmailType(), EmailType.PERSONAL);
		assertEquals("Email Fail", email.getEmailAddress(), "bob@gmail.com");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		Email email1 = new Email(){};
		email1.setEmailId(1);
		
		//Same type, doesn't match
		Email email2 = new Email(){};
		email2.setEmailId(2);
		
		//Same type, should match
		Email email3 = new Email(){};
		email3.setEmailId(1);
		
		//Test for accurate comparison
		assertFalse("ID mismatch", email1.equals(email2));
		assertFalse("Type mismatch", email1.equals(o));
		assertTrue("Perfect match", email1.equals(email3));
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Email email1 = new Email(){};
		email1.setEmailId(1);
		
		//Same type, doesn't match
		Email email2 = new Email(){};
		email2.setEmailId(2);
		
		//Same type, should match
		Email email3 = new Email(){};
		email3.setEmailId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", email1.compareTo(email2), -1);
		assertEquals("Greater than test", email2.compareTo(email1), 1);
		assertEquals("Equals test", email1.compareTo(email3), 0);
	}
	
	//Testing for each of the two subclasses is here
	@Test
	public void testStudent(){
		//Create dummy data
		Student student = new Student();
		EmailStudent email = new EmailStudent();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				email.getStudent());
		
		//Set student in email
		email.setStudent(student);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				email.getStudent());
	}
	
	@Test
	public void testAdmin(){
		//Create dummy data
		Administrator admin = new Administrator();
		EmailAdmin email = new EmailAdmin();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				email.getAdministrator());
		
		//Set student in address
		email.setAdministrator(admin);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				email.getAdministrator());
	}
	
}
