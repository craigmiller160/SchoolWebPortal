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
import io.craigmiller160.school.entity.Address;
import io.craigmiller160.school.entity.AddressStudent;
import io.craigmiller160.school.entity.AddressType;
import io.craigmiller160.school.entity.Email;
import io.craigmiller160.school.entity.EmailStudent;
import io.craigmiller160.school.entity.EmailType;
import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.Phone;
import io.craigmiller160.school.entity.PhoneStudent;
import io.craigmiller160.school.entity.PhoneType;
import io.craigmiller160.school.entity.State;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.util.HibernateTestUtil;

/**
 * JUnit Integration test for the DAO class that
 * handles <tt>Student</tt> entities.
 * 
 * @author craig
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-context.xml"})
public class StudentDaoIT {

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
	 * DAO for <tt>Student</tt> entities, which is being tested here.
	 */
	@Autowired (required=true)
	private GenericEntityDaoBean<Student> studentDao;

	/**
	 * Get the DAO for <tt>Student</tt> entities.
	 * 
	 * @return the DAO for <tt>Student</tt> entities.
	 */
	public GenericEntityDaoBean<Student> getStudentDao() {
		return studentDao;
	}

	/**
	 * Set the DAO for <tt>Student</tt> entities.
	 * 
	 * @param studentDao the DAO for <tt>Student</tt> entities.
	 */
	public void setStudentDao(GenericEntityDaoBean<Student> studentDao) {
		this.studentDao = studentDao;
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
	 * Set the fields of the <tt>Student</tt> object
	 * to the second set of values.
	 * 
	 * @param student the <tt>Student</tt> object to set.
	 */
	private void setStudent2(Student student){
		student.setFirstName("First2");
		student.setLastName("Last2");
		student.setBirthDate(LocalDate.of(1950, 1, 1));
		student.setGender(Gender.MALE);
		student.setGrade(2);
	}
	
	/**
	 * Test CRUD operations in the DAO.
	 */
	@Transactional
	@Test
	public void testCRUD(){
		//Create and insert test entity
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		Long studentId = student.getStudentId();
		
		//Get entity and test for successful insert.
		student = studentDao.getEntityById(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(INSERT_FAIL, student.getFirstName(), "First");
		assertEquals(INSERT_FAIL, student.getLastName(), "Last");
		assertEquals(INSERT_FAIL, student.getBirthDate(), LocalDate.of(1900, 1, 1));
		assertEquals(INSERT_FAIL, student.getGender(), Gender.UNKNOWN);
		assertEquals(INSERT_FAIL, student.getGrade(), 1);
		
		//Change content and update
		setStudent2(student);
		studentDao.updateEntity(student);
		
		//Get entity and test for successful update
		student = studentDao.getEntityById(studentId);
		assertNotNull(INSERT_FAIL, student);
		assertEquals(UPDATE_FAIL, student.getFirstName(), "First2");
		assertEquals(UPDATE_FAIL, student.getLastName(), "Last2");
		assertEquals(UPDATE_FAIL, student.getBirthDate(), LocalDate.of(1950, 1, 1));
		assertEquals(UPDATE_FAIL, student.getGender(), Gender.MALE);
		assertEquals(UPDATE_FAIL, student.getGrade(), 2);
		
		//Delete entity
		studentDao.deleteEntity(student);
		
		//Try to get entity and test for delete
		student = studentDao.getEntityById(studentId);
		assertNull(DELETE_FAIL, student);
	}
	
	/**
	 * Test count operation in the DAO.
	 */
	@Transactional
	@Test
	public void testCount(){
		//Create and insert test data
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		student = new Student();
		setStudent2(student);
		studentDao.insertEntity(student);
		
		//Get entity count and test accuracy
		long count = studentDao.getEntityCount();
		assertTrue(count >= 2);
	}
	
	/**
	 * Test get all operation in DAO.
	 */
	@Transactional
	@Test
	public void testGetAll(){
		//Create and insert data
		Student student = new Student();
		setStudent1(student);
		studentDao.insertEntity(student);
		student = new Student();
		setStudent2(student);
		studentDao.insertEntity(student);
		
		//Get list and check for content
		List<Student> students = studentDao.getAllEntities();
		assertNotNull(students);
		assertTrue(students.size() >= 2);
	}
	
	/**
	 * Test pagination method of DAO.
	 */
	@Transactional
	@Test
	public void testPagination(){
		//Create big list of temporary data
		for(int i = 0; i < 20; i++){
			Student student = new Student();
			setStudent1(student);
			studentDao.insertEntity(student);
		}
		
		//Get next page and test for content
		List<Student> students1 = studentDao.getEntitiesByPage(10, 5);
		assertNotNull("Students list is null", students1);
		assertTrue("List is wrong size", students1.size() == 5);
		
		//Get another page and compare the two
		//This list is deliberately one entity larger than the first one
		//This allows testing to ensure that the pages are retrieving entities
		//in the right order.
		List<Student> students2 = studentDao.getEntitiesByPage(5, 6);
		assertNotNull("Students list is null", students2);
		assertTrue("List is wrong size", students2.size() == 6);
		//The uneven sizes are meant for the following test: If this is true,
		//then the last entity in the second list matches the first in the first.
		//That would prove that pages are being retrieved in order.
		assertEquals("First entity in first list doesn't equal last entity in second", 
				students1.get(0), students2.get(students2.size() - 1));
		//Test for overlap while skipping the first record in list one because
		//that one should match, but the others should not.
		for(int i = 1; i < students1.size(); i++){
			assertFalse("Overlap between pages", 
					students2.contains(students1.get(i)));
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
		//Create dummy Student
		Student student = new Student();
		setStudent1(student);
		
		//Add addresses
		AddressStudent address = new AddressStudent();
		setAddress1(address);
		student.addAddress(address);
		
		address = new AddressStudent();
		setAddress2(address);
		student.addAddress(address);
		
		//Insert entity with addresses;
		studentDao.insertEntity(student);
		Long studentId = student.getStudentId();
		
		//Retrieve entity and test the addresses
		student = studentDao.getEntityById(studentId);
		Set<AddressStudent> addresses = student.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 2);
		for(AddressStudent a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("East Brunswick"), 
					equalTo("Fords")));
		}
		
		//Address to find and change in list
		address = new AddressStudent();
		setAddress1(address);
		
		//Change value and update
		for(AddressStudent a : addresses){
			if(a.equals(address)){
				setAddress3(a);
			}
		}
		studentDao.updateEntity(student);
		
		//Retrieve and test for update
		student = studentDao.getEntityById(studentId);
		addresses = student.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 2);
		for(AddressStudent a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("Henderson"), 
					equalTo("Fords")));
			assertThat(a.getCity(), anyOf(not(equalTo("East Brunswick"))));
		}
		
		//Create comparison address
		address = new AddressStudent();
		setAddress2(address);
		
		//Remove address and update entity
		for(AddressStudent a : addresses){
			if(a.equals(address)){
				addresses.remove(a);
				break;
			}
		}
		studentDao.updateEntity(student);
		
		//Test for propper removal
		student = studentDao.getEntityById(studentId);
		addresses = student.getAddresses();
		assertNotNull("Addresses are null", addresses);
		assertEquals("Addresses size wrong", addresses.size(), 1);
		for(AddressStudent a : addresses){
			assertThat(a.getCity(), anyOf(equalTo("Henderson")));
			assertThat(a.getCity(), anyOf(not(equalTo("Fords"))));
		}
	}
	
	/**
	 * Test CRUD operations with added Phones to this
	 * entity.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Test
	public void testPhoneCRUD(){
		//Create dummy Student
		Student student = new Student();
		setStudent1(student);
		
		//Add phones
		PhoneStudent phone = new PhoneStudent();
		setPhone1(phone);
		student.addPhone(phone);
		
		phone = new PhoneStudent();
		setPhone2(phone);
		student.addPhone(phone);
		
		//Insert Student with phones
		studentDao.insertEntity(student);
		Long studentId = student.getStudentId();
		
		//Retrieve Student and test phones
		student = studentDao.getEntityById(studentId);
		Set<PhoneStudent> phones = student.getPhones();
		assertNotNull("Phones are null", phones);
		assertEquals("Phones size wrong", phones.size(), 2);
		for(PhoneStudent p : phones){
			assertThat(p.getAreaCode(), anyOf(equalTo("732"), 
					equalTo("908")));
		}
		
		//Phone to find and change in list
		phone = new PhoneStudent();
		setPhone1(phone);
		
		//Change phone value and update
		for(PhoneStudent p : phones){
			if(p.equals(phone)){
				setPhone3(p);
			}
		}
		studentDao.updateEntity(student);
		
		//Retrieve and test for update
		student = studentDao.getEntityById(studentId);
		phones = student.getPhones();
		assertNotNull("Phones are null", phones);
		assertEquals("Phones size wrong", phones.size(), 2);
		for(PhoneStudent p : phones){
			assertThat(p.getAreaCode(), anyOf(equalTo("908"), 
					equalTo("800")));
			assertThat(p.getAreaCode(), anyOf(not(equalTo("732"))));
		}
		
		//Create comparison phone
		phone = new PhoneStudent();
		setPhone2(phone);
		
		//Remove phone and update entity
		for(PhoneStudent p : phones){
			if(p.equals(phone)){
				phones.remove(p);
				break;
			}
		}
		studentDao.updateEntity(student);
		
		//Test for propper removal
		student = studentDao.getEntityById(studentId);
		phones = student.getPhones();
		assertNotNull("Phones are null", phones);
		assertEquals("Phones size wrong", phones.size(), 1);
		for(PhoneStudent p : phones){
			assertThat(p.getAreaCode(), anyOf(equalTo("800")));
			assertThat(p.getAreaCode(), anyOf(not(equalTo("908"))));
		}
	}
	
	/**
	 * Test CRUD operations with added Emails to this
	 * entity.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Test
	public void testEmailCRUD(){
		//Create dummy Student
		Student student = new Student();
		setStudent1(student);
		
		//Add emails
		EmailStudent email = new EmailStudent();
		setEmail1(email);
		student.addEmail(email);
		
		email = new EmailStudent();
		setEmail2(email);
		student.addEmail(email);
		
		//Insert Student with emails
		studentDao.insertEntity(student);
		Long studentId = student.getStudentId();
		
		//Retrieve Student and test emails
		student = studentDao.getEntityById(studentId);
		Set<EmailStudent> emails = student.getEmails();
		assertNotNull("Emails are null", emails);
		assertEquals("Emails size wrong", emails.size(), 2);
		for(EmailStudent e : emails){
			assertThat(e.getEmailAddress(), anyOf(equalTo("craig@gmail.com"), 
					equalTo("bob@aol.com")));
		}
		
		//Email to find and change in list
		email = new EmailStudent();
		setEmail1(email);
		
		//Change email value and update
		for(EmailStudent e : emails){
			if(e.equals(email)){
				setEmail3(e);
			}
		}
		studentDao.updateEntity(student);
		
		//Retrieve Student and test for update
		student = studentDao.getEntityById(studentId);
		emails = student.getEmails();
		assertNotNull("Emails are null", emails);
		assertEquals("Emails size wrong", emails.size(), 2);
		for(EmailStudent e : emails){
			assertThat(e.getEmailAddress(), anyOf(equalTo("joe@hotmail.com"), 
					equalTo("bob@aol.com")));
		}
		
		//Create comparison email
		email = new EmailStudent();
		setEmail2(email);
		
		//Remove email and update entity
		for(EmailStudent e : emails){
			if(e.equals(email)){
				emails.remove(e);
				break;
			}
		}
		studentDao.updateEntity(student);
		
		//Test for propper removal
		student = studentDao.getEntityById(studentId);
		emails = student.getEmails();
		assertNotNull("Emails are null", emails);
		assertEquals("Emails size wrong", emails.size(), 1);
		for(EmailStudent e : emails){
			assertThat(e.getEmailAddress(), anyOf(equalTo("joe@hotmail.com")));
			assertThat(e.getEmailAddress(), anyOf(not(equalTo("bob@aol.com"))));
		}
	}
	
	/**
	 * Set the fields of the <tt>Address</tt> object
	 * to the first set of values.
	 * 
	 * @param address the <tt>Address</tt> object to set.
	 */
	private void setAddress1(Address address){
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
	private void setAddress2(Address address){
		address.setAddressType(AddressType.WORK);
		address.setAddress1("22 Denman Drive");
		address.setCity("Fords");
		address.setState(State.NJ);
		address.setZip("08892");
	}
	
	/**
	 * Set the fields of the <tt>Address</tt> object
	 * to the third set of values.
	 * 
	 * @param address the <tt>Address</tt> object to set.
	 */
	private void setAddress3(Address address){
		address.setAddressType(AddressType.MAILING);
		address.setAddress1("500 Paradise Rd");
		address.setCity("Henderson");
		address.setState(State.NV);
		address.setZip("53321");
	}
	
	/**
	 * Set the fields of the <tt>Phone</tt> object
	 * to the first set of values.
	 * 
	 * @param phone the <tt>Phone</tt> object to set.
	 */
	private void setPhone1(Phone phone){
		phone.setPhoneType(PhoneType.HOME);
		phone.setAreaCode("732");
		phone.setPrefix("555");
		phone.setSuffix("6789");
		phone.setExtension("x1234");
	}
	
	/**
	 * Set the fields of the <tt>Phone</tt> object
	 * to the second set of values.
	 * 
	 * @param phone the <tt>Phone</tt> object to set.
	 */
	private void setPhone2(Phone phone){
		phone.setPhoneType(PhoneType.WORK);
		phone.setAreaCode("908");
		phone.setPrefix("666");
		phone.setSuffix("1234");
		phone.setExtension(null);
	}
	
	/**
	 * Set the fields of the <tt>Phone</tt> object
	 * to the third set of values.
	 * 
	 * @param phone the <tt>Phone</tt> object to set.
	 */
	private void setPhone3(Phone phone){
		phone.setPhoneType(PhoneType.FAX);
		phone.setAreaCode("800");
		phone.setPrefix("123");
		phone.setSuffix("4567");
		phone.setExtension(null);
	}
	
	/**
	 * Set the fields of the <tt>Email</tt> object
	 * to the first set of values.
	 * 
	 * @param email the <tt>Email</tt> object to set.
	 */
	private void setEmail1(Email email){
		email.setEmailType(EmailType.PERSONAL);
		email.setEmailAddress("craig@gmail.com");
	}
	
	/**
	 * Set the fields of the <tt>Email</tt> object
	 * to the second set of values.
	 * 
	 * @param email the <tt>Email</tt> object to set.
	 */
	private void setEmail2(Email email){
		email.setEmailType(EmailType.WORK);
		email.setEmailAddress("bob@aol.com");
	}
	
	/**
	 * Set the fields of the <tt>Email</tt> object
	 * to the third set of values.
	 * 
	 * @param email the <tt>Email</tt> object to set.
	 */
	private void setEmail3(Email email){
		email.setEmailType(EmailType.OTHER);
		email.setEmailAddress("joe@hotmail.com");
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
		testUtil.resetStudentAutoIncrement();
		testUtil.resetAddressStudentAutoIncrement();
		testUtil.resetPhoneStudentAutoIncrement();
		testUtil.resetEmailStudentAutoIncrement();
	}
	
}
