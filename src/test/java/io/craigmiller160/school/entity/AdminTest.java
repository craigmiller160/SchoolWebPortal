package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class AdminTest {

	@Test
	public void testConstructorArgs(){
		//Create test data
		Administrator admin = new Administrator(
				"Bob", "Saget", LocalDate.of(1988, 1, 1),
				Gender.MALE);
		
		//Test the values
		assertNotNull("Is Null", admin);
		assertEquals("First Name Fail", admin.getFirstName(), "Bob");
		assertEquals("Last Name Fail", admin.getLastName(), "Saget");
		assertEquals("Birth Date Fail", admin.getBirthDate(), 
				LocalDate.of(1988, 1, 1));
		assertEquals("Gender Fail", admin.getGender(), Gender.MALE);
	}
	
	@Test
	public void testFields(){
		//Create test data
		Administrator admin = new Administrator();
		admin.setAdminId(1);
		admin.setFirstName("Bob");
		admin.setLastName("Saget");
		admin.setBirthDate(LocalDate.of(1988, 1, 1));
		admin.setGender(Gender.MALE);
		
		//Test the values
		assertNotNull("Is Null", admin);
		assertEquals("ID Fail", admin.getAdminId(), 1);
		assertEquals("First Name Fail", admin.getFirstName(), "Bob");
		assertEquals("Last Name Fail", admin.getLastName(), "Saget");
		assertEquals("Birth Date Fail", admin.getBirthDate(), 
				LocalDate.of(1988, 1, 1));
		assertEquals("Gender Fail", admin.getGender(), Gender.MALE);
	}
	
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		Administrator admin1 = new Administrator();
		admin1.setAdminId(1);
		
		//Same type, doesn't match
		Administrator admin2 = new Administrator();
		admin2.setAdminId(2);
		
		//Same type, should match
		Administrator admin3 = new Administrator();
		admin3.setAdminId(1);
		
		//Test for accurate comparison
		assertFalse("ID mismatch", admin1.equals(admin2));
		assertFalse("Type mismatch", admin1.equals(o));
		assertTrue("Perfect match", admin1.equals(admin3));
	}
	
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Administrator admin1 = new Administrator();
		admin1.setAdminId(1);
		
		//Same type, doesn't match
		Administrator admin2 = new Administrator();
		admin2.setAdminId(2);
		
		//Same type, should match
		Administrator admin3 = new Administrator();
		admin3.setAdminId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", admin1.compareTo(admin2), -1);
		assertEquals("Greater than test", admin2.compareTo(admin1), 1);
		assertEquals("Equals test", admin1.compareTo(admin3), 0);
	}
	
}
