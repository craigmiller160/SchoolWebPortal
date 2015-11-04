package io.craigmiller160.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name="phone")
public class Phone {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="phone_id", length=6)
	private int phoneId;
	
	@Enumerated (EnumType.STRING)
	@Column (name="phone_type")
	private PhoneType phoneType;
	
	@Column (name="area_code", length=3)
	private String areaCode;
	
	@Column (length=3)
	private String prefix;
	
	@Column (length=4)
	private String suffix;
	
	@Column (length=20)
	private String extension;
	
	//TODO many to one mapping goes here
	private Owner owner;

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
