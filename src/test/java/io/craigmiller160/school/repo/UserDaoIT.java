package io.craigmiller160.school.repo;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.Administrator;
import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.PersonHolder;
import io.craigmiller160.school.entity.Role;
import io.craigmiller160.school.entity.SchoolUser;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.entity.UserRole;
import io.craigmiller160.school.util.HibernateTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class UserDaoIT {

	//TODO use trigger to give a better message back when violating
	//unique constraint on username than "constraint violation".
	
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
	
	/**
	 * DAO for <tt>SchoolUser</tt> entities, which is being tested here.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<SchoolUser> userDao;

	/**
	 * Get the DAO for <tt>SchoolUser</tt> entities.
	 * 
	 * @return the DAO for <tt>SchoolUser</tt> entities.
	 */
	public GenericEntityDaoBean<SchoolUser> getCourseDao() {
		return userDao;
	}

	/**
	 * Set the DAO for <tt>SchoolUser</tt> entities.
	 * 
	 * @param userDao the DAO for <tt>SchoolUser</tt> entities.
	 */
	public void setCourseDao(GenericEntityDaoBean<SchoolUser> userDao) {
		this.userDao = userDao;
	}
	
	private void setUser1(SchoolUser user){
		user.setUsername("hsolo2");
		user.setPassword("pass");
		user.setEnabled(true);
	}
	
	private void setUser2(SchoolUser user){
		user.setUsername("hsolo3");
		user.setPassword("pass2");
		user.setEnabled(false);
	}
	
	/**
	 * Test CRUD operations in the DAO.
	 */
	@Transactional
	@Test
	public void testCRUD(){
		//Create and insert test entity
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userDao.insertEntity(user);
		Long userId = user.getUserId();
		
		//Get entity and test for successful insert
		user = userDao.getEntityById(userId);
		assertNotNull(INSERT_FAIL, user);
		assertEquals(INSERT_FAIL, user.getUsername(), "hsolo2");
		assertEquals(INSERT_FAIL, user.getPassword(), "pass");
		assertEquals(INSERT_FAIL, user.isEnabled(), true);
		
		//Change content and update
		setUser2(user);
		userDao.updateEntity(user);
		
		//Get entity and test for successful update
		user = userDao.getEntityById(userId);
		assertNotNull(UPDATE_FAIL, user);
		assertEquals(UPDATE_FAIL, user.getUsername(), "hsolo3");
		assertEquals(UPDATE_FAIL, user.getPassword(), "pass2");
		assertEquals(UPDATE_FAIL, user.isEnabled(), false);
		
		//Delete entity
		userDao.deleteEntity(user);
		
		//Try to get entity and test for delete
		user = userDao.getEntityById(userId);
		assertNull(DELETE_FAIL, user);
	}
	
	@Transactional
	@Test
	public void testCount(){
		//Create and insert test data
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userDao.insertEntity(user);
		
		user = new SchoolUser();
		setUser2(user);
		userDao.insertEntity(user);
		
		//Get entity count and test accuracy
		long count = userDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert test data
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userDao.insertEntity(user);
		
		user = new SchoolUser();
		setUser2(user);
		userDao.insertEntity(user);
		
		//Get list and check for content
		List<SchoolUser> users = userDao.getAllEntities();
		assertNotNull(users);
		assertTrue(users.size() >= 2);
	}
	
	/**
	 * Test pagination method of DAO.
	 */
	@Transactional
	@Test
	public void testPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			SchoolUser user = new SchoolUser();
			user.setUsername("page" + i);
			user.setPassword("password");
			userDao.insertEntity(user);
		}
		
		//Get page and test for content
		List<SchoolUser> users1 = userDao.getEntitiesByPage(10, 5);
		assertNotNull("Users list is null", users1);
		assertTrue("List is wrong size", users1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<SchoolUser> users2 = userDao.getEntitiesByPage(5, 6);
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
	public void testGetByUserName(){
		//Create and insert test data
		SchoolUser user = new SchoolUser();
		setUser1(user);
		userDao.insertEntity(user);
		
		//Attempt to get user by UserName
		user = ((HibernateUserDao) userDao).getUserByUsername("hsolo2");
		assertNotNull("Is null", user);
		assertEquals("Values don't match", user.getPassword(), "pass");
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
		
		userDao.insertEntity(user);
		Long userId = user.getUserId();
		
		//Get entity and test values
		user = userDao.getEntityById(userId);
		Collection<UserRole> roles = user.getAuthorities();
		assertNotNull("Is Null", roles);
		assertEquals("Roles List Wrong Size", roles.size(), 2);
		for(UserRole r : roles){
			assertThat("Invalid Role", r, 
					anyOf(
						equalTo(new UserRole(Role.ROLE_USER)), 
						equalTo(new UserRole(Role.ROLE_ADMIN))));
		}
		
		//Alter/remove data and update databse
		user.getAuthorities().clear();
		user.addRole(new UserRole(Role.ROLE_MASTER));
		userDao.updateEntity(user);
		
		//Get Entity and test data
		user = userDao.getEntityById(userId);
		roles = user.getAuthorities();
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
	public void testPersistPersonHolderStudent(){
		//Create Student to insert into PersonHolder
		Student student = new Student();
		setStudent1(student);
		
		//Create PersonHolder
		PersonHolder personHolder = new PersonHolder();
		personHolder.setStudent(student);
		
		//Create User to persist
		SchoolUser user = new SchoolUser();
		setUser1(user);
		user.setPersonHolder(personHolder);
		
		//Insert User
		userDao.insertEntity(user);
		Long userId = user.getUserId();
		Long studentId = student.getStudentId();
		
		//Retrieve User and test PersonHolder
		user = userDao.getEntityById(userId);
		personHolder = user.getPersonHolder();
		assertNotNull("PersonHolder is null", personHolder);
		assertEquals("PersonHolder isn't holding Student", 
				personHolder.getPersonType(), Student.class);
		assertEquals("PersonHolder isn't holding correct Student",
				personHolder.getPersonId(), studentId);
	}
	
	@Transactional
	@Test
	public void testPersistHolderAdmin(){
		//Create Admin to insert into PersonHolder
		Administrator admin = new Administrator();
		setAdmin1(admin);
		
		//Create PersonHolder
		PersonHolder personHolder = new PersonHolder();
		personHolder.setAdmin(admin);
		
		//Create User to persist
		SchoolUser user = new SchoolUser();
		setUser1(user);
		user.setPersonHolder(personHolder);
		
		//Insert User
		userDao.insertEntity(user);
		Long userId = user.getUserId();
		Long adminId = admin.getAdminId();
		
		//Retrieve User and test PersonHolder
		user = userDao.getEntityById(userId);
		personHolder = user.getPersonHolder();
		assertNotNull("PersonHolder is null", personHolder);
		assertEquals("PersonHolder isn't holding Administrator", 
				personHolder.getPersonType(), Administrator.class);
		assertEquals("PersonHolder isn't holding correct Administrator",
				personHolder.getPersonId(), adminId);
	}
	
	@Transactional
	@Test
	public void testPersistPersonHolderAdmin(){
		
	}
	
	/**
	 * Set the fields of the <tt>Student</tt> object
	 * to the first set of values.
	 * 
	 * @param student the <tt>Student</tt> object to set.
	 */
	private void setStudent1(Student student){
		student.setFirstName("First");
		student.setLastName("Last");
		student.setBirthDate(LocalDate.of(1900, 1, 1));
		student.setGender(Gender.UNKNOWN);
		student.setGrade(1);
	}
	
	/**
	 * Set the fields of the <tt>Administrator</tt> object
	 * to the first set of values.
	 * 
	 * @param admin the <tt>Administrator</tt> object to set.
	 */
	private void setAdmin1(Administrator admin){
		admin.setFirstName("First");
		admin.setLastName("Last");
		admin.setBirthDate(LocalDate.of(1900, 1, 1));
		admin.setGender(Gender.MALE);
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
		testUtil.resetUserStudentAdminAutoIncrement();
		testUtil.resetStudentAutoIncrement();
		testUtil.resetAdminAutoIncrement();
	}
	
}
