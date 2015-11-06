package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Address 
implements Comparable<Address>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 8381049048522883204L;

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
	
	//TODO join testing stuff
	
	/*@Column (name="unified_key", 
			length=20, 
			columnDefinition="bigint default 0",
			insertable=false,
			updatable=false)
	private int unifiedKey;*/
	
	protected Address(){}
	
	protected Address(AddressType addressType, String address1, 
			String address2, String city, 
			State state, String zip){
		this.addressType = addressType;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	protected Address(AddressType addressType, String address1, 
			String city, State state, String zip){
		this(addressType, address1, "", city, state, zip);
	}

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

	//TODO join testing stuff
	
	
	
	/*public int getUnifiedKey(){
		return unifiedKey;
	}
	
	public void setUnifiedKey(int unifiedKey){
		this.unifiedKey = unifiedKey;
	}*/
	
	@Override
	public int hashCode(){
		return addressId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Address){
			return ((Address) obj).addressId == this.addressId;
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(address1);
		builder.append(address2 != null ? address2 + ", " : ", ");
		builder.append(city + ", " + state + " " + zip);
		
		return builder.toString();
	}

	@Override
	public int compareTo(Address address) {
		return ((Integer) this.addressId)
				.compareTo((Integer)address.addressId);
	}
}
