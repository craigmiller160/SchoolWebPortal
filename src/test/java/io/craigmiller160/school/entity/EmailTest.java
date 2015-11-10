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
				"bob@gmail.com"){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		
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
		Email email = new Email(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		email.setEmailId(1);
		email.setEmailType(EmailType.PERSONAL);
		email.setEmailAddress("bob@gmail.com");
		
		//Test the values
		assertNotNull(email);
		assertEquals("ID Fail", email.getEmailId(), 1);
		assertEquals("Type Fail", email.getEmailType(), EmailType.PERSONAL);
		assertEquals("Email Fail", email.getEmailAddress(), "bob@gmail.com");
	}
	
	@Test
	public void testAdminCompareTo(){
		//The base object to be compared
		EmailAdmin email1 = new EmailAdmin();
		setEmail1(email1);
		email1.setEmailId(1);
		
		//Same type, doesn't match
		EmailAdmin email2 = new EmailAdmin();
		setEmail2(email2);
		email2.setEmailId(2);
		
		//Same type, should match
		EmailAdmin email3 = new EmailAdmin();
		setEmail1(email3);
		email3.setEmailId(1);
		
		//Test for accurate comparison
		assertTrue("Less than test", email1.compareTo(email2) < 0);
		assertTrue("Greater than test", email2.compareTo(email1) > 0);
		assertEquals("Equals test", email1.compareTo(email3), 0);
	}
	
	@Test
	public void testStudentCompareTo(){
		//The base object to be compared
		EmailStudent email1 = new EmailStudent();
		setEmail1(email1);
		email1.setEmailId(1);
		
		//Same type, doesn't match
		EmailStudent email2 = new EmailStudent();
		setEmail2(email2);
		email2.setEmailId(2);
		
		//Same type, should match
		EmailStudent email3 = new EmailStudent();
		setEmail1(email3);
		email3.setEmailId(1);
		
		//Test for accurate comparison
		assertTrue("Less than test", email1.compareTo(email2) < 0);
		assertTrue("Greater than test", email2.compareTo(email1) > 0);
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
	
	@Test
	public void testAdminEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		EmailAdmin email1 = new EmailAdmin();
		setEmail1(email1);
		email1.setEmailId(1);
		
		//Same type, won't match
		EmailAdmin email2 = new EmailAdmin();
		setEmail2(email2);
		email2.setEmailId(2);
		
		//Same type, should match
		EmailAdmin email3 = new EmailAdmin();
		setEmail1(email3);
		email3.setEmailId(1);
		
		//Different Email Type, same content, won't match
		EmailStudent email4 = new EmailStudent();
		setEmail1(email4);
		email4.setEmailId(1);
		
		//Test for accurate comparison
		assertFalse("Content mismatch", email1.equals(email2));
		assertFalse("Type mismatch", email1.equals(o));
		assertFalse("Email Class mismatch", email1.equals(email4));
		assertTrue("Perfect match", email1.equals(email3));
	}
	
	@Test
	public void testStudentEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		EmailStudent email1 = new EmailStudent();
		setEmail1(email1);
		email1.setEmailId(1);
		
		//Same type, won't match
		EmailStudent email2 = new EmailStudent();
		setEmail2(email2);
		email2.setEmailId(2);
		
		//Same type, should match
		EmailStudent email3 = new EmailStudent();
		setEmail1(email3);
		email3.setEmailId(1);
		
		//Different Email Type, same content, won't match
		EmailAdmin email4 = new EmailAdmin();
		setEmail1(email4);
		email4.setEmailId(1);
		
		//Test for accurate comparison
		assertFalse("Content mismatch", email1.equals(email2));
		assertFalse("Type mismatch", email1.equals(o));
		assertFalse("Email Class mismatch", email1.equals(email4));
		assertTrue("Perfect match", email1.equals(email3));
	}
	
	private void setEmail1(Email email){
		email.setEmailType(EmailType.PERSONAL);
		email.setEmailAddress("craig@gmail.com");
	}
	
	private void setEmail2(Email email){
		email.setEmailType(EmailType.WORK);
		email.setEmailAddress("bob@aol.com");
	}
	
}
