package io.craigmiller160.school.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity (name="user")
public class SchoolUser
implements Serializable, Comparable<SchoolUser>{

	//TODO experiment with restricting the access level
	//of the setters here
	
	//TODO need a test class for this
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3279088774494392377L;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="user_id", length=20)
	private Long userId;
	
	@Column (name="user_name", length=40)
	private String userName;
	
	@Column (length=60)
	private String password;
	
	private boolean enabled;
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user", orphanRemoval=true)
	private Set<UserRole> roles = new HashSet<>();
	
	public SchoolUser(){}
	
	public SchoolUser(String userName, String password,
			boolean enabled){
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<UserRole> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<UserRole> roles){
		this.roles = roles;
	}
	
	public boolean addRole(UserRole role){
		return this.roles.add(role);
	}
	
	public boolean removeRole(UserRole role){
		return this.roles.remove(role);
	}
	
	@Override
	public int hashCode(){
		return userId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof SchoolUser){
			return ((SchoolUser) obj).userId
					.equals(this.userId);
		}
		return false;
	}
	
	@Override
	public String toString(){
		return userName;
	}

	@Override
	public int compareTo(SchoolUser user) {
		return this.userId.compareTo(user.userId);
	}
	
}
