package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity (name="owner_ref")
public class Owner 
implements Comparable<Owner>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 236252504676442856L;

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
	
	@Override
	public int hashCode(){
		//This method would need to be updated
		//if additional entities are added to 
		//the join, or if the entity prefix changes.
		String tempId = ownerId.substring(2, ownerId.length());
		if(ownerId.startsWith("st")){
			tempId = "1" + tempId;
		}
		else if(ownerId.startsWith("ad")){
			tempId = "2" + tempId;
		}
		return Integer.parseInt(tempId);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Owner){
			return ((Owner) obj).ownerId.equals(this.ownerId);
		}
		else{
			return false;
		}
	}

	//TODO this needs to be tested to confirm that the 
	//number part of the id is compared properly.
	//ST vs AD should be consistently compared, and the
	//numbers should be compared by their value.
	@Override
	public int compareTo(Owner owner) {
		return this.ownerId.compareTo(owner.ownerId);
	}
	
}
