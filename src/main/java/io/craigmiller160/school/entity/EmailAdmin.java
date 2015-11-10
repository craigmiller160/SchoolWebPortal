package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="email_admin")
public class EmailAdmin 
extends Email implements Comparable<EmailAdmin>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9092873359031282853L;

	@ManyToOne
	@JoinColumn (name="admin_id")
	private Administrator admin;
	
	public EmailAdmin(){}
	
	public EmailAdmin(EmailType emailType, String emailAddress){
		super(emailType, emailAddress);
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
		if(obj instanceof EmailAdmin){
			String s1 = "ADMIN " + toString();
			String s2 = "ADMIN " + obj.toString();
			return s1.equals(s2);
		}
		return false;
	}
	
	@Override
	public int compareTo(EmailAdmin email){
		String s1 = "ADMIN " + toString();
		String s2 = "ADMIN " + email.toString();
		return s1.compareTo(s2);
	}
	
}
