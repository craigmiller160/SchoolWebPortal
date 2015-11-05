package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhoneTest {

	@Test
	public void testConstructorArgs(){
		//Create test data
		Phone phone = new Phone(PhoneType.HOME,
				"732", "613", "1234", "5678");
		
		//Test the values
		assertNotNull("Is null", phone);
		assertEquals("Type Fail", phone.getPhoneType(), PhoneType.HOME);
		assertEquals("Area Code Fail", phone.getAreaCode(), "732");
		assertEquals("Prefix Fail", phone.getPrefix(), "613");
		assertEquals("Suffix Fail", phone.getSuffix(), "1234");
		assertEquals("Extension Fail", phone.getExtension(), "5678");
	}
	
	@Test
	public void testFields(){
		//Create test data
		Phone phone = new Phone();
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
	
	@Test
	public void testEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		Phone phone1 = new Phone();
		phone1.setPhoneId(1);
		
		//Same type, doesn't match
		Phone phone2 = new Phone();
		phone2.setPhoneId(2);
		
		//Same type, should match
		Phone phone3 = new Phone();
		phone3.setPhoneId(1);
		
		//Test for accurate comparison
		assertFalse("ID mismatch", phone1.equals(phone2));
		assertFalse("Type mismatch", phone1.equals(o));
		assertTrue("Perfect match", phone1.equals(phone3));
	}
	
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Phone phone1 = new Phone();
		phone1.setPhoneId(1);
		
		//Same type, doesn't match
		Phone phone2 = new Phone();
		phone2.setPhoneId(2);
		
		//Same type, should match
		Phone phone3 = new Phone();
		phone3.setPhoneId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", phone1.compareTo(phone2), -1);
		assertEquals("Greater than test", phone2.compareTo(phone1), 1);
		assertEquals("Equals test", phone1.compareTo(phone3), 0);
	}
	
}
