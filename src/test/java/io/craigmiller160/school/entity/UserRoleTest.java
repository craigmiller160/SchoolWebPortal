package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserRoleTest {

	//TODO at some point, rename TestFields method
	//to test setters in all test classes
	
	@Test
	public void testConstructorArgs(){
		//Create dummy data
		UserRole role = new UserRole(Role.ROLE_USER);
		
		//Test values
		assertNotNull("Is null", role);
		assertEquals("Role Fail", role.getRole(), Role.ROLE_USER);
	}
	
	@Test
	public void testFields(){
		//Create dummy data
		UserRole role = new UserRole();
		role.setRole(Role.ROLE_USER);
		
		//Test values
		assertNotNull("Is null", role);
		assertEquals("Role Fail", role.getRole(), Role.ROLE_USER);
	}
	
	@Test
	public void testEquals(){
		//An invalid type of object
		Object o = new Object();
		
		//The base object to be compared
		UserRole role1 = new UserRole();
		role1.setRole(Role.ROLE_USER);
		role1.setUserRoleId(new Long(1));
		
		//Same type, won't match
		UserRole role2 = new UserRole();
		role2.setRole(Role.ROLE_ADMIN);
		role2.setUserRoleId(new Long(2));
		
		//Same type, should match
		UserRole role3 = new UserRole();
		role3.setRole(Role.ROLE_USER);
		role3.setUserRoleId(new Long(1));
		
		//Test for accurate comparison
		assertFalse("Content mismatch", role1.equals(role2));
		assertFalse("Type mismatch", role1.equals(o));
		assertTrue("Perfect match", role1.equals(role3));
	}
	
	@Test
	public void testCompareTo(){
		//The base object to be compared
		UserRole role1 = new UserRole();
		role1.setRole(Role.ROLE_USER);
		role1.setUserRoleId(new Long(1));
		
		//Same type, won't match
		UserRole role2 = new UserRole();
		role2.setRole(Role.ROLE_ADMIN);
		role2.setUserRoleId(new Long(2));
		
		//Same type, should match
		UserRole role3 = new UserRole();
		role3.setRole(Role.ROLE_USER);
		role3.setUserRoleId(new Long(1));
		
		//Test for accurate comparison
		assertTrue("Less than test", role2.compareTo(role1) < 0);
		assertTrue("Greater than test", role1.compareTo(role2) > 0);
		assertEquals("Equals test", role1.compareTo(role3), 0);
	}
	
}
