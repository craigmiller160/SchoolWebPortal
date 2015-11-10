package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Email 
implements Comparable<Email>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = -29777207447136173L;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="email_id")
	private int emailId;
	
	@Enumerated (EnumType.STRING)
	@Column (name="email_type")
	private EmailType emailType;
	
	@Column (name="email_address", length=255)
	private String emailAddress;
	
	protected Email(){}
	
	protected Email(EmailType emailType, String emailAddress){
		this.emailType = emailType;
		this.emailAddress = emailAddress;
	}

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
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);
	
	@Override
	public String toString(){
		return emailType + ": " + emailAddress;
	}

	@Override
	public int compareTo(Email email) {
		return ((Integer) this.emailId)
				.compareTo((Integer) email.emailId);
	}
	
}
