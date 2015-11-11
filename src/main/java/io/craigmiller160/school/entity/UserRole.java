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

@Entity (name="user_role")
public class UserRole 
implements Comparable<UserRole>{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="user_role_id", length=20)
	private Long userRoleId;
	
	@Enumerated (EnumType.STRING)
	private Role role;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	private SchoolUser user;
	
	public UserRole(){}
	
	public UserRole(Role role){
		this.role = role;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		if(obj instanceof UserRole){
			return obj.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return role.toString();
	}

	@Override
	public int compareTo(UserRole role) {
		return this.toString().compareTo(role.toString());
	}
	
}
