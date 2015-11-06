package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="student_address")
public class StudentAddress extends Address {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658590941067029101L;
	
	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public StudentAddress(){
		super();
	}
	
	public StudentAddress(AddressType addressType, String address1, 
			String address2, String city, 
			State state, String zip){
		super(addressType, address1, address2, city, state, zip);
	}
	
	public StudentAddress(AddressType addressType, String address1, 
			String city, State state, String zip){
		super(addressType, address1, city, state, zip);
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
