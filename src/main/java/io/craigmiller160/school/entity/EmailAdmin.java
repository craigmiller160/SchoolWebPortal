package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="email_admin")
public class EmailAdmin extends Email {

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
	
}
