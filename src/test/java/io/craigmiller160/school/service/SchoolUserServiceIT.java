package io.craigmiller160.school.service;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Role;
import io.craigmiller160.school.entity.SchoolUser;
import io.craigmiller160.school.entity.UserRole;
import io.craigmiller160.school.util.HibernateTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class SchoolUserServiceIT {

	//TODO this test depends on their not being a username matching the one 
	//that is being inserted. That should be resolved in the future.
	
	/**
	 * Output message for the insert operation failing.
	 */
	private static final String INSERT_FAIL = "Insert Failed";
	
	/**
	 * Output message for the update operation failing.
	 */
	private static final String UPDATE_FAIL = "Update Failed";
	
	/**
	 * Output message for the delete operation failing.
	 */
	private static final String DELETE_FAIL = "Delete Failed";
	
	private static final String CREATE_FAIL = "Create Failed";
	
	@Autowired (required=true)
	private SchoolUserService userService;
	
	/**
	 * Get the service class of this application, to be tested here.
	 * 
	 * @return the service class of this application, to be tested here.
	 */
	public SchoolUserService getSchoolDataService() {
		return userService;
	}

	/**
	 * Set the service class of this application, to be tested here.
	 * 
	 * @param userService the service class of this application, 
	 * to be tested here.
	 */
	public void setSchoolDataService(SchoolUserService userService) {
		this.userService = userService;
	}
	
	@Transactional
	@Test
	public void testBasicCRUD(){
		//Create and insert test entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userService.insertUser(user);
		Long userId = user.getUserId();
		
		//Get entity and test for successful insert
		user = userService.getUserById(userId);
		assertNotNull(INSERT_FAIL, user);
		assertEquals(INSERT_FAIL, user.getUserName(), "hsolo2");
		assertEquals(INSERT_FAIL, user.getPassword(), "pass");
		assertEquals(INSERT_FAIL, user.isEnabled(), true);
		
		//Change content and update
		setUser2(user);
		userService.updateUser(user);
		
		//Get entity and test for successful update
		user = userService.getUserById(userId);
		assertNotNull(INSERT_FAIL, user);
		assertEquals(UPDATE_FAIL, user.getUserName(), "hsolo3");
		assertEquals(UPDATE_FAIL, user.getPassword(), "pass2");
		assertEquals(UPDATE_FAIL, user.isEnabled(), false);
		
		//Delete entity
		userService.deleteUser(user);
		
		//Try to get entity and test for delete
		user = userService.getUserById(userId);
		assertNull(DELETE_FAIL, user);
	}
	
	//TODO this test depends on UserRole persistence
	@Transactional
	@Test
	public void testSpecialCRUD(){
		//Create User
		userService.createUser("hsolo2", "pass", 
				new UserRole(Role.ROLE_USER));
		
		//Get By Name
		SchoolUser user = userService.getByUserName("hsolo2");
		assertNotNull(CREATE_FAIL, user);
		assertEquals(CREATE_FAIL, user.getUserName(), "hsolo2");
		assertEquals(CREATE_FAIL, user.getPassword(), "pass");
		assertEquals(CREATE_FAIL, user.isEnabled(), true);
		
		//Delete By Name
		userService.deleteByUserName("hsolo2");
		
		//Try to get entity and test for delete
		boolean userNotFound = false;
		try{
			userService.getByUserName("hsolo2");
		}
		catch(UsernameNotFoundException ex){
			userNotFound = true;
		}
		assertTrue("User Was Found", userNotFound);
	}
	
	@Transactional
	@Test
	public void testExists(){
		//Insert Test Entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		
		userService.insertUser(user);
		
		//Test if user exists
		boolean exists = userService.userExists("hsolo2");
		assertTrue("User should exist", exists);
		
		//Delete user and test for not existing
		userService.deleteUser(user);
		
		exists = userService.userExists("hsolo2");
		assertFalse("User should not exist", exists);
	}
	
	@Transactional
	@Test
	public void testDeleteById(){
		//Insert test entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		
		userService.insertUser(user);
		Long userId = user.getUserId();
		
		//Delete entity
		userService.deleteUserById(userId);
		
		//Test for successful deletion
		user = userService.getUserById(userId);
		assertNull("User not deleted", user);
	}
	
	@Transactional
	@Test
	public void testGetAll(){
		//Insert test entities
		SchoolUser user1 = new SchoolUser();
		setUser1(user1);
		userService.insertUser(user1);
		
		SchoolUser user2 = new SchoolUser();
		setUser2(user2);
		userService.insertUser(user2);
		
		//Get list and test values
		List<SchoolUser> users = userService.getAllUsers();
		assertTrue("User List Wrong Size", users.size() >= 2);
		assertTrue("User List missing values", users.contains(user1));
		assertTrue("User List missing values", users.contains(user2));
	}
	
	@Transactional
	@Test
	public void testCount(){
		//Insert test entities
		SchoolUser user1 = new SchoolUser();
		setUser1(user1);
		userService.insertUser(user1);
		
		SchoolUser user2 = new SchoolUser();
		setUser2(user2);
		userService.insertUser(user2);
		
		//Get and test count
		long count = userService.getUserCount();
		assertTrue("Count incorrect", count >= 2);
	}
	
	@Transactional
	@Test
	public void testLoadUserDetails(){
		//Insert test entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userService.insertUser(user);
		
		//Get and test UserDetails
		UserDetails userDetails = userService.loadUserByUsername("hsolo2");
		assertNotNull("Is null", userDetails);
		assertEquals("Username doesn't match", 
				userDetails.getUsername(), "hsolo2");
		assertEquals("Password doesn't match", 
				userDetails.getPassword(), "pass");
		assertEquals("Enabled doesn't match", userDetails.isEnabled(), true);
	}
	
	@Transactional
	@Test
	public void testChangePassword(){
		//Insert test entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userService.insertUser(user);
		Long userId = user.getUserId();
		
		//Attempt to Change password
		boolean success = false;
		try{
			success = userService.changePassword(
					"hsolo2", "pass", "pass123");
		}
		catch(UsernameNotFoundException ex){
			success = false;
		}
		
		//Test success or fail
		assertTrue("Password Change Failed", success);
		
		//Retrieve user and test password value
		user = userService.getUserById(userId);
		assertEquals("Password value incorrect", 
				user.getPassword(), "pass123");
	}
	
	@Transactional
	@Test
	public void testPersistRoles(){
		//Create and insert test data
		SchoolUser user = new SchoolUser();
		setUser1(user);
		
		UserRole role1 = new UserRole(Role.ROLE_USER);
		UserRole role2 = new UserRole(Role.ROLE_ADMIN);
		user.addRole(role1);
		user.addRole(role2);
		
		userService.insertUser(user);
		Long userId = user.getUserId();
		
		//Get entity and test values
		user = userService.getUserById(userId);
		Set<UserRole> roles = user.getRoles();
		assertNotNull("Is Null", roles);
		assertEquals("Roles List Wrong Size", roles.size(), 2);
		for(UserRole r : roles){
			assertThat("Invalid Role", r, 
					anyOf(
						equalTo(new UserRole(Role.ROLE_USER)), 
						equalTo(new UserRole(Role.ROLE_ADMIN))));
		}
		
		//Alter/remove data and update databse
		user.getRoles().clear();
		user.addRole(new UserRole(Role.ROLE_MASTER));
		userService.updateUser(user);
		
		//Get Entity and test data
		user = userService.getUserById(userId);
		roles = user.getRoles();
		assertNotNull("Is Null", roles);
		assertEquals("Roles List Wrong Size", roles.size(), 1);
		for(UserRole r : roles){
			assertThat("Invalid Role", r, 
					equalTo(new UserRole(Role.ROLE_MASTER)));
			assertThat("Invalid Role", r,
					not(anyOf(equalTo(new UserRole(Role.ROLE_USER)),
							equalTo(new UserRole(Role.ROLE_ADMIN)))));
		}
	}
	
	@Transactional
	@Test
	public void testGetUsersPaginated(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			SchoolUser user = new SchoolUser();
			user.setUserName("hsolo2" + i);
			user.setPassword("pass");
			user.setEnabled(true);
			userService.insertUser(user);
		}
		
		//Get page and test for content
		List<SchoolUser> users1 = userService.getUsersByPage(2, 5);
		assertNotNull("Users list is null", users1);
		assertTrue("List is wrong size", users1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<SchoolUser> users2 = userService.getUsersByPage(1, 6);
		assertNotNull("Users list is null", users2);
		assertTrue("List is wrong size", users2.size() == 6);
		
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				users1.get(0), users2.get(users2.size() - 1));
		
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < users1.size(); i++){
			assertFalse("Overlap between pages", 
					users2.contains(users1.get(i)));
		}
	}
	
	@Transactional
	@Test
	public void testHasPagesRemaining(){
		//Create dummy data
		for(int i = 0; i < 20; i++){
			SchoolUser user = new SchoolUser();
			user.setUserName("hsolo2" + i);
			user.setPassword("pass");
			user.setEnabled(true);
			userService.insertUser(user);
		}
		
		//Get the current count to set up the comparison
		long actualCount = userService.getUserCount();
		int pageCount = (int) actualCount / 10; //Works because of small data sets, might not work in larger application.
		
		//Test with a value that should result in true
		assertTrue("Should have pages remaining", 
				userService.hasPagesRemaining(1, 5));
		//Test with a value that should result in false
		assertFalse("Should not have pages remaining", 
				userService.hasPagesRemaining(pageCount + 1, 10));
	}
	
	private void setUser1(SchoolUser user){
		user.setUserName("hsolo2");
		user.setPassword("pass");
		user.setEnabled(true);
	}
	
	private void setUser2(SchoolUser user){
		user.setUserName("hsolo3");
		user.setPassword("pass2");
		user.setEnabled(false);
	}
	
	/**
	 * Reset the auto-increment counter of the table being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetUserAutoIncrement();
		testUtil.resetUserRoleAutoIncrement();
	}
	
}
