package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SchoolUserTest {

	@Test
	public void testConstructorArgs(){
		//Create test data
		SchoolUser user = new SchoolUser("hsolo", "pass", true);
		
		//Test the values
		assertNotNull("Is null", user);
		assertEquals("UserName Fail", user.getUsername(), "hsolo");
		assertEquals("Password Fail", user.getPassword(), "pass");
		assertEquals("Enabled Fail", user.isEnabled(), true);
	}
	
	@Test
	public void testFields(){
		//Create test data
		SchoolUser user = new SchoolUser();
		user.setUserId(new Long(1));
		user.setUsername("hsolo");
		user.setPassword("pass");
		user.setEnabled(true);
		
		//Test the values
		assertNotNull("Is null", user);
		assertEquals("ID Fail", user.getUserId(), new Long(1));
		assertEquals("UserName Fail", user.getUsername(), "hsolo");
		assertEquals("Password Fail", user.getPassword(), "pass");
		assertEquals("Enabled fail", user.isEnabled(), true);
	}
	
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		SchoolUser user1 = new SchoolUser();
		setUser1(user1);
		user1.setUserId(new Long(1));
		
		//Same type, won't match
		SchoolUser user2 = new SchoolUser();
		setUser2(user2);
		user2.setUserId(new Long(2));
		
		//Same type, should match
		SchoolUser user3 = new SchoolUser();
		setUser1(user3);
		user3.setUserId(new Long(1));
		
		//Test for accurate comparison.
		assertFalse("Content mismatch", user1.equals(user2));
		assertFalse("Type mismatch", user1.equals(o));
		assertTrue("Perfect match", user1.equals(user3));
	}
	
	@Test
	public void testCompareTo(){
		//The base object to be compared
		SchoolUser user1 = new SchoolUser();
		setUser1(user1);
		user1.setUserId(new Long(1));
		
		//Same type, won't match
		SchoolUser user2 = new SchoolUser();
		setUser2(user2);
		user2.setUserId(new Long(2));
		
		//Same type, should match
		SchoolUser user3 = new SchoolUser();
		setUser1(user3);
		user3.setUserId(new Long(1));
		
		//Test for accurate comparison
		assertTrue("Less than test", user1.compareTo(user2) < 0);
		assertTrue("Greater than test", user2.compareTo(user1) > 0);
		assertEquals("Equals test", user1.compareTo(user3), 0);
	}
	
	private void setUser1(SchoolUser user){
		user.setUsername("hsolo");
		user.setPassword("pass");
		user.setEnabled(true);
	}
	
	private void setUser2(SchoolUser user){
		user.setUsername("hsolo2");
		user.setPassword("pass2");
		user.setEnabled(false);
	}
	
}
