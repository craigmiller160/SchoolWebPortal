package io.craigmiller160.school.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//TODO document how this is only testing Address fields,
//and as such it instantiates the abstract class.
//The DAO tests for Student/Admin will test the subclasses.
public class AddressTest {

	private void setAddress1(Address address){
		address.setAddressType(AddressType.HOME);
		address.setAddress1("12 Freehold Way");
		address.setAddress2("Apt 23");
		address.setCity("Hamilton");
		address.setState(State.AL);
		address.setZip("12345");
	}
	
	private void setAddress2(Address address){
		address.setAddressType(AddressType.WORK);
		address.setAddress1("500 Paradise Rd");
		address.setAddress2("Apt 46");
		address.setCity("Henderson");
		address.setState(State.NV);
		address.setZip("08852");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testConstructorArgs(){
		//Create test data
		Address address = new Address(AddressType.HOME,
				"12 Freehold Way", "Apt 23", "Hamilton",
				State.AL, "12345"){
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
		assertNotNull("Is null", address);
		assertEquals("Type Fail", address.getAddressType(), AddressType.HOME);
		assertEquals("Address1 Fail", address.getAddress1(), "12 Freehold Way");
		assertEquals("Address2 Fail", address.getAddress2(), "Apt 23");
		assertEquals("City Fail", address.getCity(), "Hamilton");
		assertEquals("State Fail", address.getState(), State.AL);
		assertEquals("Zip Fail", address.getZip(), "12345");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testFields(){
		//Create test data
		Address address = new Address(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		address.setAddressId(1);
		setAddress1(address);
		
		//Test the values
		assertNotNull("Is null", address);
		assertEquals("ID fail", address.getAddressId(), 1);
		assertEquals("Type Fail", address.getAddressType(), AddressType.HOME);
		assertEquals("Address1 Fail", address.getAddress1(), "12 Freehold Way");
		assertEquals("Address2 Fail", address.getAddress2(), "Apt 23");
		assertEquals("City Fail", address.getCity(), "Hamilton");
		assertEquals("State Fail", address.getState(), State.AL);
		assertEquals("Zip Fail", address.getZip(), "12345");
	}
	
	//Anonymous instantiation of abstract class, needs @SuppressWarnings
	@SuppressWarnings("serial")
	@Test
	public void testCompareTo(){
		//The base object to be compared
		Address address1 = new Address(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		address1.setAddressId(1);
		
		//Same type, doesn't match
		Address address2 = new Address(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		address2.setAddressId(2);
		
		//Same type, should match
		Address address3 = new Address(){
			//Impliment these methods with dummy values
			//They aren't needed for this test anyway.
			public int hashCode(){
				return 1;
			}
			public boolean equals(Object obj){
				return false;
			}
		};
		address3.setAddressId(1);
		
		//Test for accurate comparison
		assertEquals("Less than test", address1.compareTo(address2), -1);
		assertEquals("Greater than test", address2.compareTo(address1), 1);
		assertEquals("Equals test", address1.compareTo(address3), 0);
	}
	
	//Testing for each of the two subclasses is here
	@Test
	public void testStudent(){
		//Create dummy data
		Student student = new Student();
		AddressStudent address = new AddressStudent();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				address.getStudent());
		
		//Set student in address
		address.setStudent(student);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				address.getStudent());
	}
	
	@Test
	public void testAdmin(){
		//Create dummy data
		Administrator admin = new Administrator();
		AddressAdmin address = new AddressAdmin();
		
		//Test that student field is null
		assertNull("Pre-Test should be Null", 
				address.getAdministrator());
		
		//Set student in address
		address.setAdministrator(admin);
		
		//Test if it worked
		assertNotNull("Post-Test should not be null", 
				address.getAdministrator());
	}
	
	@Test
	public void testAdminEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		AddressAdmin address1 = new AddressAdmin();
		setAddress1(address1);
		address1.setAddressId(1);
		
		//Same type, won't match
		AddressAdmin address2 = new AddressAdmin();
		setAddress2(address2);
		address2.setAddressId(2);
		
		//Same type, should match
		AddressAdmin address3 = new AddressAdmin();
		setAddress1(address3);
		address3.setAddressId(1);
		
		//Different Address type, same content, won't match
		AddressStudent address4 = new AddressStudent();
		setAddress1(address4);
		address4.setAddressId(1);
		
		//Test for accurate comparison
		assertFalse("Content mismatch", address1.equals(address2));
		assertFalse("Type mismatch", address1.equals(o));
		assertFalse("Address Type mismatch", address1.equals(address4));
		assertTrue("Perfect match", address1.equals(address3));
	}
	
	@Test
	public void testStudentEquals(){
		//An invalid type object
		Object o = new Object();
		
		//The base object to be compared
		AddressStudent address1 = new AddressStudent();
		setAddress1(address1);
		address1.setAddressId(1);
		
		//Same type, won't match
		AddressStudent address2 = new AddressStudent();
		setAddress2(address2);
		address2.setAddressId(2);
		
		//Same type, should match
		AddressStudent address3 = new AddressStudent();
		setAddress1(address3);
		address3.setAddressId(1);
		
		//The base object to be compared
		AddressAdmin address4 = new AddressAdmin();
		setAddress1(address1);
		address1.setAddressId(1);
		
		//Test for accurate comparison
		assertFalse("Content mismatch", address1.equals(address2));
		assertFalse("Type mismatch", address1.equals(o));
		assertFalse("Address Type mismatch", address1.equals(address4));
		assertTrue("Perfect match", address1.equals(address3));
	}
	
}
