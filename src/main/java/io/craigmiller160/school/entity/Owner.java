package io.craigmiller160.school.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity (name="owner_ref")
public class Owner {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private String ownerId;
	
	@OneToOne
	@JoinColumn (name="student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn (name="admin_id")
	private Administrator administrator;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
}
