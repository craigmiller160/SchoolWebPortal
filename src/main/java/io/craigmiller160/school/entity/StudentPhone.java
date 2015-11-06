package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="phone_student")
public class StudentPhone extends Phone{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9128191029428611186L;

	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public StudentPhone(){}
	
	public StudentPhone(PhoneType phoneType, String areaCode,
			String prefix, String suffix){
		super(phoneType, areaCode, prefix, suffix);
	}
	
	public StudentPhone(PhoneType phoneType, String areaCode,
			String prefix, String suffix, String extension){
		super(phoneType, areaCode, prefix, suffix, extension);
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
