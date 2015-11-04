package io.craigmiller160.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="email")
public class Email {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int emailId;
	
	@Enumerated (EnumType.STRING)
	@Column (name="email_type")
	private EmailType emailType;
	
	@Column (name="email_address", length=255)
	private String emailAddress;
	
	@ManyToOne
	@JoinColumn (name="owner_id")
	private Owner owner;

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public EmailType getEmailType() {
		return emailType;
	}

	public void setEmailType(EmailType emailType) {
		this.emailType = emailType;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
