package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="address_admin")
public class AddressAdmin extends Address {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7444091189326827312L;
	
	@ManyToOne
	@JoinColumn (name="admin_id")
	private Administrator admin;
	
	public AddressAdmin(){
		super();
	}
	
	public AddressAdmin(AddressType addressType, String address1, 
			String address2, String city, 
			State state, String zip){
		super(addressType, address1, address2, city, state, zip);
	}
	
	public AddressAdmin(AddressType addressType, String address1, 
			String city, State state, String zip){
		super(addressType, address1, city, state, zip);
	}
	
	public Administrator getAdministrator() {
		return admin;
	}

	public void setAdministrator(Administrator admin) {
		this.admin = admin;
	}
	
	@Override
	public int hashCode(){
		String s = "ADMIN "
				+ toString();
		return s.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AddressAdmin){
			String s1 = "ADMIN " + toString();
			String s2 = "ADMIN " + obj.toString();
			return s1.equals(s2);
		}
		return false;
	}

}
