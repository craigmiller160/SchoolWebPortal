package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="email_student")
public class StudentEmail extends Email {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1673236738064419856L;
	
	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public StudentEmail(){}
	
	public StudentEmail(EmailType emailType, String emailAddress){
		super(emailType, emailAddress);
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
