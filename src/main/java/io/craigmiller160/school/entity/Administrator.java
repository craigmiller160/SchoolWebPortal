package io.craigmiller160.school.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.craigmiller160.school.util.LocalDateConverter;

@Entity (name="admin")
public class Administrator 
implements Comparable<Administrator>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 5246015989704799314L;

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
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="admin", orphanRemoval=true)
	private List<AddressAdmin> addresses = new ArrayList<>();
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="admin", orphanRemoval=true)
	private List<PhoneAdmin> phones = new ArrayList<>();
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="admin", orphanRemoval=true)
	private List<EmailAdmin> emails = new ArrayList<>();
	
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
	
	public List<AddressAdmin> getAddresses(){
		return addresses;
	}
	
	public void setAddresses(List<AddressAdmin> addresses){
		this.addresses = addresses;
	}
	
	public boolean addAddress(AddressAdmin address){
		address.setAdministrator(this); //TODO review this line
		return this.addresses.add(address);
	}
	
	public boolean removeAddress(AddressAdmin address){
		return this.addresses.remove(address);
	}
	
	//TODO
	
	public List<PhoneAdmin> getPhones(){
		return phones;
	}
	
	public void setPhones(List<PhoneAdmin> phones){
		this.phones = phones;
	}
	
	public boolean addPhone(PhoneAdmin phone){
		phone.setAdministrator(this); //TODO review this line
		return this.phones.add(phone);
	}
	
	public boolean removePhone(PhoneAdmin phone){
		return this.phones.remove(phone);
	}
	
	//TODO
	
	public List<EmailAdmin> getEmails(){
		return emails;
	}
	
	public void setEmails(List<EmailAdmin> emails){
		this.emails = emails;
	}
	
	public boolean addEmail(EmailAdmin email){
		email.setAdministrator(this); //TODO review this line
		return this.emails.add(email);
	}
	
	public boolean removeEmail(EmailAdmin email){
		return this.emails.remove(email);
	}
	
	@Override
	public int hashCode(){
		return adminId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Administrator){
			return ((Administrator) obj).adminId == this.adminId;
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}

	@Override
	public int compareTo(Administrator admin) {
		return ((Integer) this.adminId)
				.compareTo((Integer) admin.adminId);
	}
	
}
