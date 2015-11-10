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
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.AddressAdmin;
import io.craigmiller160.school.entity.AddressType;
import io.craigmiller160.school.entity.Administrator;
import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.State;
import io.craigmiller160.school.util.HibernateTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class AdminDaoIT {

	//TODO all entities need their IDs to be changed to
	//longs
	
	//TODO both DAO tests need tests for the contact entities
	//as well
	
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
	 * DAO for <tt>Administrator</tt> entities, which is being tested here.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Administrator> adminDao;
	
	/**
	 * Get the DAO for <tt>Administrator</tt> entities.
	 * 
	 * @return the DAO for <tt>Administrator</tt> entities.
	 */
	public GenericEntityDaoBean<Administrator> getAdminDao(){
		return adminDao;
	}
	
	/**
	 * Set the DAO for <tt>Administrator</tt> entities.
	 * 
	 * @param studentDao the DAO for <tt>Administrator</tt> entities.
	 */
	public void setAdminDao(GenericEntityDaoBean<Administrator> adminDao) {
		this.adminDao = adminDao;
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
	 * Set the fields of the <tt>Administrator</tt> object
	 * to the second set of values.
	 * 
	 * @param admin the <tt>Administrator</tt> object to set.
	 */
	private void setAdmin2(Administrator admin){
		admin.setFirstName("First2");
		admin.setLastName("Last2");
		admin.setBirthDate(LocalDate.of(1950, 1, 1));
		admin.setGender(Gender.FEMALE);
	}
	
	/**
	 * Test CRUD operations in the DAO.
	 */
	@Transactional
	@Test
	public void testCRUD(){
		//Create and insert test entity
		Administrator admin = new Administrator();
		setAdmin1(admin);
		adminDao.insertEntity(admin);
		int adminId = admin.getAdminId();
		
		//Get entity and test for successful insert.
		admin = adminDao.getEntityById(adminId);
		assertNotNull(INSERT_FAIL, admin);
		assertEquals(INSERT_FAIL, admin.getFirstName(), "First");
		assertEquals(INSERT_FAIL, admin.getLastName(), "Last");
		assertEquals(INSERT_FAIL, admin.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, admin.getGender(), Gender.MALE);
		
		//Change content and update
		setAdmin2(admin);
		adminDao.updateEntity(admin);
		
		//Get entity and test for successful update
		admin = adminDao.getEntityById(adminId);
		assertNotNull(INSERT_FAIL, admin);
		assertEquals(UPDATE_FAIL, admin.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, admin.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, admin.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, admin.getGender(), Gender.FEMALE);
		
		//Delete entity
		adminDao.deleteEntity(admin);
		
		//Try to get entity and test for delete
		admin = adminDao.getEntityById(adminId);
		assertNull(DELETE_FAIL, admin);
	}
	
	/**
	 * Test count operation in the DAO.
	 */
	@Transactional
	@Test
	public void testCount(){
		//Create and insert test data
		Administrator admin = new Administrator();
		setAdmin1(admin);
		adminDao.insertEntity(admin);
		
		admin = new Administrator();
		setAdmin2(admin);
		adminDao.insertEntity(admin);
		
		//Get entity count and test accuracy
		long count = adminDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	/**
	 * Test get all operation in DAO.
	 */
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert data
		Administrator admin = new Administrator();
		setAdmin1(admin);
		adminDao.insertEntity(admin);
		
		admin = new Administrator();
		setAdmin2(admin);
		adminDao.insertEntity(admin);
		
		//Get list and check for content
		List<Administrator> admins = adminDao.getAllEntities();
		assertNotNull(admins);
		assertTrue(admins.size() >= 2);
	}
	
	/**
	 * Test pagination method of DAO.
	 */
	@Transactional
	@Test
	public void testPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Administrator admin = new Administrator();
			setAdmin1(admin);
			adminDao.insertEntity(admin);
		}
		
		//Get next page and test for content
		List<Administrator> admins1 = adminDao.getEntitiesByPage(10, 5);
		assertNotNull("Admins list is null", admins1);
		assertTrue("List is wrong size", admins1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Administrator> admins2 = adminDao.getEntitiesByPage(5, 6);
		assertNotNull("Admins list is null", admins2);
		assertTrue("List is wrong size", admins2.size() == 6);
		
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				admins1.get(0), admins2.get(admins2.size() - 1));
		
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < admins1.size(); i++){
			assertFalse("Overlap between pages", 
					admins2.contains(admins1.get(i)));
		}
	}
	
	/**
	 * Test CRUD operations with added Addresses to this
	 * entity.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Test
	public void testAddressCRUD(){
		//Create dummy Admin
		Administrator admin = new Administrator();
		setAdmin1(admin);
		
		//Add addresses
		AddressAdmin address = new AddressAdmin();
		setAddress1(address);
		admin.addAddress(address);
		
		address = new AddressAdmin();
		setAddress2(address);
		admin.addAddress(address);
		
		//Insert entity with addresses;
		adminDao.insertEntity(admin);
		int adminId = admin.getAdminId();
		
		//Retrieve entity and test the addresses
		admin = adminDao.getEntityById(adminId);
		Set<AddressAdmin> addresses = admin.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 2);
		for(AddressAdmin a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("East Brunswick"), 
					equalTo("Fords")));
		}
		
		
		address = new AddressAdmin();
		setAddress1(address);
		
		//Change value and update
		for(AddressAdmin a : addresses){
			if(a.equals(address)){
				setAddress3(a);
			}
		}
		adminDao.updateEntity(admin);
		
		//Retrieve and test for update
		admin = adminDao.getEntityById(adminId);
		addresses = admin.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 2);
		for(AddressAdmin a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("Henderson"), 
					equalTo("Fords")));
			assertThat(a.getCity(), anyOf(not(equalTo("East Brunswick"))));
		}
		
		//Create comparison address
		address = new AddressAdmin();
		setAddress2(address);
		
		//Remove address and update entity
		for(AddressAdmin a : addresses){
			if(a.equals(address)){
				addresses.remove(a);
				break;
			}
		}
		adminDao.updateEntity(admin);
		
		//Test for propper removal
		admin = adminDao.getEntityById(adminId);
		addresses = admin.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 1);
		for(AddressAdmin a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("Henderson")));
			assertThat(a.getCity(), anyOf(not(equalTo("Fords"))));
		}
	}
	
	/**
	 * Set the fields of the <tt>Address</tt> object
	 * to the first set of values.
	 * 
	 * @param address the <tt>Address</tt> object to set.
	 */
	private void setAddress1(AddressAdmin address){
		address.setAddressType(AddressType.HOME);
		address.setAddress1("3 Brookside Ct");
		address.setCity("East Brunswick");
		address.setState(State.NJ);
		address.setZip("08816");
	}
	
	/**
	 * Set the fields of the <tt>Address</tt> object
	 * to the second set of values.
	 * 
	 * @param address the <tt>Address</tt> object to set.
	 */
	private void setAddress2(AddressAdmin address){
		address.setAddressType(AddressType.WORK);
		address.setAddress1("22 Denman Drive");
		address.setCity("Fords");
		address.setState(State.NJ);
		address.setZip("08892");
	}
	
	/**
	 * Set the fields of the <tt>Address</tt> object
	 * to the second set of values.
	 * 
	 * @param address the <tt>Address</tt> object to set.
	 */
	private void setAddress3(AddressAdmin address){
		address.setAddressType(AddressType.MAILING);
		address.setAddress1("500 Paradise Rd");
		address.setCity("Henderson");
		address.setState(State.NV);
		address.setZip("53321");
	}
	
	/**
	 * Reset the auto-increment counter of the table being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(
				HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetAdminAutoIncrement();
		testUtil.resetAddressAdminAutoIncrement();
		testUtil.resetPhoneAdminAutoIncrement();
		testUtil.resetEmailAdminAutoIncrement();
	}
	
}
