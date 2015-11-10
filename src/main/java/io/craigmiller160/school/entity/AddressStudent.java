package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="address_student")
public class AddressStudent extends Address {

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
			if(getAddressType()
					.equals(((AddressStudent) obj)
							.getAddressType())
					&& getAddress1()
					.equals(((AddressStudent) obj)
							.getAddress1())
					&& getAddress2()
					.equals(((AddressStudent) obj)
							.getAddress2())
					&& getCity()
					.equals(((AddressStudent) obj)
							.getCity())
					&& getState()
					.equals(((AddressStudent) obj)
							.getState())
					&& getZip()
					.equals(((AddressStudent) obj)
							.getZip())){
				return true;
			}
		}
		return false;
	}
	
}
