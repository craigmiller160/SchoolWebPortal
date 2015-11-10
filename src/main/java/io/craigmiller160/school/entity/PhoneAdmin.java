package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="phone_admin")
public class PhoneAdmin extends Phone {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8463830402668053562L;
	
	@ManyToOne
	@JoinColumn (name="admin_id")
	private Administrator admin;
	
	public PhoneAdmin(){}
	
	public PhoneAdmin(PhoneType phoneType, String areaCode,
			String prefix, String suffix){
		super(phoneType, areaCode, prefix, suffix);
	}
	
	public PhoneAdmin(PhoneType phoneType, String areaCode,
			String prefix, String suffix, String extension){
		super(phoneType, areaCode, prefix, suffix, extension);
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
		if(obj instanceof PhoneAdmin){
			String s1 = "ADMIN " + toString();
			String s2 = "ADMIN " + obj.toString();
			return s1.equals(s2);
		}
		return false;
	}

}
