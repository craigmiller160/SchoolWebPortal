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
public abstract class Phone 
implements Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 8478847944391493241L;

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
	
	protected Phone(){}
	
	protected Phone(PhoneType phoneType, String areaCode,
			String prefix, String suffix){
		this(phoneType, areaCode, prefix, suffix, null);
	}
	
	protected Phone(PhoneType phoneType, String areaCode,
			String prefix, String suffix, String extension){
		this.phoneType = phoneType;
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.suffix = suffix;
		this.extension = extension;
	}

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
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);
	
	@Override
	public String toString(){
		String output = phoneType + ": (" + areaCode + ") " 
				+ prefix + "-" + suffix;
		if(extension != null && !extension.equals("")){
			output = output + " x" + extension;
		}
		
		return output;
	}
	
}
