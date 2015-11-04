package io.craigmiller160.school.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.craigmiller160.school.util.LocalDateConverter;

@Entity (name="admin")
public class Administrator {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="admin_id", length=6)
	private int adminId;
	
	@Column (name="first_name", length=255)
	private String firstName;
	
	@Column (name="last_name", length=255)
	private String lastName;
	
	@Column (name="birth_date")
	@Convert (converter=LocalDateConverter.class)
	private LocalDate birthDate;
	
	@Enumerated (EnumType.STRING)
	private Gender gender;
	
	public Administrator() {}
	
	public Administrator(String firstName, String lastName,
			LocalDate birthDate, Gender gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
