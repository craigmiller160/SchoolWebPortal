package io.craigmiller160.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity (name="user_student_admin")
public class PersonHolder {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="user_sa_id", length=20)
	private Long personHolderId;
	
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="student_id")
	private Student student;
	
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="admin_id")
	private Administrator admin;
	
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn (name="user_id")
	private SchoolUser user;
	
	@Transient
	private Long personId;
	
	@Transient
	private Class<?> personType;
	
	public void setStudent(Student student){
		this.personType = student.getClass();
		this.personId = student.getStudentId();
		this.student = student;
		this.admin = null;
	}
	
	public void setAdmin(Administrator admin){
		this.personType = admin.getClass();
		this.personId = admin.getAdminId();
		this.admin = admin;
		this.student = null;
	}
	
	public void setPersonHolderId(Long personHolderId){
		this.personHolderId = personHolderId;
	}
	
	public Long getPersonHolderId(){
		return personHolderId;
	}
	
	@SuppressWarnings("unused") //Private setter for Hibernate
	private Student getStudent(){
		return student;
	}
	
	@SuppressWarnings("unused") //Private setter for Hibernate
	private Administrator getAdmin(){
		return admin;
	}
	
	public Long getPersonId(){
		return personId;
	}
	
	public Class<?> getPersonType(){
		return personType;
	}
	
	public void setUser(SchoolUser user){
		this.user = user;
	}
	
	public SchoolUser getUser(){
		return user;
	}
	
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PersonHolder){
			return this.toString().equals(obj.toString());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return personType.toString() 
				+ "_" + personId.toString();
	}
	
}
