package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="phone_student")
public class PhoneStudent extends Phone{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9128191029428611186L;

	@ManyToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	public PhoneStudent(){}
	
	public PhoneStudent(PhoneType phoneType, String areaCode,
			String prefix, String suffix){
		super(phoneType, areaCode, prefix, suffix);
	}
	
	public PhoneStudent(PhoneType phoneType, String areaCode,
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
