package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="email_student")
public class EmailStudent 
extends Email implements Comparable<EmailStudent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1673236738064419856L;
	
	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public EmailStudent(){}
	
	public EmailStudent(EmailType emailType, String emailAddress){
		super(emailType, emailAddress);
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
		if(obj instanceof EmailStudent){
			String s1 = "STUDENT " + toString();
			String s2 = "STUDENT " + obj.toString();
			return s1.equals(s2);
		}
		return false;
	}
	
	@Override
	public int compareTo(EmailStudent email){
		String s1 = "STUDENT " + toString();
		String s2 = "STUDENT " + email.toString();
		return s1.compareTo(s2);
	}

}
