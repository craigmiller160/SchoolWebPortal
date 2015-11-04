package io.craigmiller160.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="address")
public class Address {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="address_id", length=6)
	private int addressId;
	
	@Enumerated (EnumType.STRING)
	@Column (name="address_type")
	private AddressType addressType;
	
	@Column (length=255)
	private String address1;
	
	@Column (length=255)
	private String address2;
	
	@Column (length=255)
	private String city;
	
	@Enumerated (EnumType.STRING)
	@Column (length=2)
	private State state;
	
	@Column (length=5)
	private String zip;
	
	@ManyToOne
	@JoinColumn (name="owner_id")
	private Owner owner;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
