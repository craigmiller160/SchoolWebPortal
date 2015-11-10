package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//TODO document how this is only testing Phone fields,
//and as such it instantiates the abstract class.
//The DAO tests for Student/Admin will test the subclasses.
public class PhoneTest {

	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testConstructorArgs(){
		//Create test data
		Phone phone = new Phone(PhoneType.HOME,
				"732", "613", "1234", "5678"){
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
		assertNotNull("Is null", phone);
		assertEquals("Type Fail", phone.getPhoneType(), PhoneType.HOME);
		assertEquals("Area Code Fail", phone.getAreaCode(), "732");
		assertEquals("Prefix Fail", phone.getPrefix(), "613");
		assertEquals("Suffix Fail", phone.getSuffix(), "1234");
		assertEquals("Extension Fail", phone.getExtension(), "5678");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testFields(){
		//Create test data
		Phone phone = new Phone(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		phone.setPhoneId(1);
		phone.setPhoneType(PhoneType.HOME);
		phone.setAreaCode("732");
		phone.setPrefix("613");
		phone.setSuffix("1234");
		phone.setExtension("5678");
		
		//Test the values
		assertNotNull("Is null", phone);
		assertEquals("ID Fail", phone.getPhoneId(), 1);
		assertEquals("Type Fail", phone.getPhoneType(), PhoneType.HOME);
		assertEquals("Area Code Fail", phone.getAreaCode(), "732");
		assertEquals("Prefix Fail", phone.getPrefix(), "613");
		assertEquals("Suffix Fail", phone.getSuffix(), "1234");
		assertEquals("Extension Fail", phone.getExtension(), "5678");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Phone phone1 = new Phone(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		phone1.setPhoneId(1);
		
		//Same type, doesn't match
		Phone phone2 = new Phone(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		phone2.setPhoneId(2);
		
		//Same type, should match
		Phone phone3 = new Phone(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		phone3.setPhoneId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", phone1.compareTo(phone2), -1);
		assertEquals("Greater than test", phone2.compareTo(phone1), 1);
		assertEquals("Equals test", phone1.compareTo(phone3), 0);
	}
	
	//Testing for each of the two subclasses is here
	@Test
	public void testStudent(){
		//Create dummy data
		Student student = new Student();
		PhoneStudent phone = new PhoneStudent();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				phone.getStudent());
		
		//Set student in address
		phone.setStudent(student);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				phone.getStudent());
	}
	
	@Test
	public void testAdmin(){
		//Create dummy data
		Administrator admin = new Administrator();
		PhoneAdmin phone = new PhoneAdmin();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				phone.getAdministrator());
		
		//Set student in address
		phone.setAdministrator(admin);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				phone.getAdministrator());
	}
	
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		PhoneAdmin phone1 = new PhoneAdmin();
		setPhone1(phone1);
		phone1.setPhoneId(1);
		
		//Same type, won't match
		PhoneAdmin phone2 = new PhoneAdmin();
		setPhone2(phone2);
		phone2.setPhoneId(2);
		
		//Same type, should match
		PhoneAdmin phone3 = new PhoneAdmin();
		setPhone1(phone3);
		phone3.setPhoneId(1);
		
		//Different phone type, same content, won't match
		PhoneStudent phone4 = new PhoneStudent();
		setPhone1(phone4);
		phone4.setPhoneId(1);
		
		//Test for accurate comparison
		assertFalse("ID mismatch", phone1.equals(phone2));
		assertFalse("Type mismatch", phone1.equals(o));
		assertFalse("Phone Class mismatch", phone1.equals(phone4));
		assertTrue("Perfect match", phone1.equals(phone3));
	}
	
	private void setPhone1(Phone phone){
		phone.setPhoneType(PhoneType.HOME);
		phone.setAreaCode("732");
		phone.setPrefix("555");
		phone.setSuffix("6789");
		phone.setExtension("x1234");
	}
	
	private void setPhone2(Phone phone){
		phone.setPhoneType(PhoneType.WORK);
		phone.setAreaCode("908");
		phone.setPrefix("666");
		phone.setSuffix("1234");
		phone.setExtension(null);
	}
	
}
