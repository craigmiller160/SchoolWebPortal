package io.craigmiller160.school.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.craigmiller160.school.entity.SchoolUser;
import io.craigmiller160.school.entity.UserRole;

public interface SchoolUserService 
extends UserDetailsService{

	//TODO consider renaming this to avoid any confusing
	//with SchoolDataService (this being an interface, that being a class)
	
	void createUser(String userName, String password, UserRole...roles);
	
	void deleteByUserName(String userName);
	
	boolean userExists(String userName);
	
	boolean changePassword(String userName, String oldPassword, String newPassword);
	
	void updateUser(SchoolUser user);
	
	void insertUser(SchoolUser user);
	
	void deleteUser(SchoolUser user);
	
	void deleteUserById(Long userId);
	
	List<SchoolUser> getAllUsers();
	
	SchoolUser getUserById(Long userId);
	
	long getUserCount();
	
	List<SchoolUser> getUsersByPage(int pageNumber, int pageRowCount);
	
	boolean hasPagesRemaining(int pageNumber, int pageRowCount);
	
	SchoolUser getByUserName(String userName);
	
}
