package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="address_student")
public class AddressStudent 
extends Address implements Comparable<AddressStudent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658590941067029101L;
	
	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public AddressStudent(){
		super();
	}
	
	public AddressStudent(AddressType addressType, String address1, 
			String address2, String city, 
			State state, String zip){
		super(addressType, address1, address2, city, state, zip);
	}
	
	public AddressStudent(AddressType addressType, String address1, 
			String city, State state, String zip){
		super(addressType, address1, city, state, zip);
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Override
	public int hashCode(){
		String s = "STUDENT "
				+ toString();
		return s.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AddressStudent){
			String s1 = "STUDENT " + toString();
			String s2 = "STUDENT " + obj.toString();
			return s1.equals(s2);
		}
		return false;
	}
	
	@Override
	public int compareTo(AddressStudent address){
		String s1 = "STUDENT " + toString();
		String s2 = "STUDENT " + address.toString();
		return s1.compareTo(s2);
	}
	
}
