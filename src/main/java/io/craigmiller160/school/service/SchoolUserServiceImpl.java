package io.craigmiller160.school.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.craigmiller160.school.entity.SchoolUser;
import io.craigmiller160.school.entity.UserRole;
import io.craigmiller160.school.repo.GenericEntityDaoBean;
import io.craigmiller160.school.repo.HibernateUserDao;

@Service ("schoolUserService")
public class SchoolUserServiceImpl implements SchoolUserService {

	private final GenericEntityDaoBean<SchoolUser> userDao;
	
	@Autowired (required=true)
	public SchoolUserServiceImpl(GenericEntityDaoBean<SchoolUser> userDao){
		this.userDao = userDao;
	}
	
	public GenericEntityDaoBean<SchoolUser> getUserDao(){
		return userDao;
	}
	
	@Transactional
	@Override
	public void updateUser(SchoolUser user){
		userDao.updateEntity(user);
	}

	@Transactional
	@Override
	public void deleteByUserName(String userName) {
		if(userDao instanceof HibernateUserDao){
			//Retrieve user for username and delete it
			SchoolUser user = ((HibernateUserDao) userDao)
					.getUserByUsername(userName);
			deleteUser(user);
		}
		else{
			throw new IllegalArgumentException(
					"Provided DAO does not have authentication capability");
		}
	}
	
	@Transactional
	@Override
	public void deleteUserById(Long userId){
		SchoolUser user = userDao.getEntityById(userId);
		deleteUser(user);
	}
	
	@Transactional
	@Override
	public void deleteUser(SchoolUser user){
		userDao.deleteEntity(user);
	}

	@Transactional
	@Override
	public boolean changePassword(String userName, String oldPassword, 
			String newPassword) throws UsernameNotFoundException{
		//Return false if this operation fails
		boolean result = false;
		if(userDao instanceof HibernateUserDao){
			//Retrieve user and test for existence
			SchoolUser user = ((HibernateUserDao) userDao)
					.getUserByUsername(userName);
			if(user == null){
				//Throw exception if user doesn't exist
				throw new UsernameNotFoundException(userName + " not found");
			}
			
			//If old password is user's password, change it
			if(user.getPassword().equals(oldPassword)){
				user.setPassword(newPassword);
				result = true;
			}
		}
		else{
			throw new IllegalArgumentException(
					"Provided DAO does not have authentication capability");
		}
		return result;
	}

	@Transactional
	@Override
	public boolean userExists(String userName) {
		if(userDao instanceof HibernateUserDao){
			//Retrieve user for username and return true if it's not null
			SchoolUser user = ((HibernateUserDao) userDao)
					.getUserByUsername(userName);
			return user != null;
		}
		else{
			throw new IllegalArgumentException(
					"Provided DAO does not have authentication capability");
		}
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(userDao instanceof HibernateUserDao){
			//Retrieve SchoolUser based on username, or throw exception
			SchoolUser user = ((HibernateUserDao) userDao).getUserByUsername(username);
			if(user == null){
				throw new UsernameNotFoundException(username + " not found");
			}
			
			/*Set<UserRole> roles = user.getAuthorities();

			//Convert UserRole objects to GrantedAuthority objects.
			List<GrantedAuthority> authorities = new ArrayList<>();
			for(UserRole r : roles){
				GrantedAuthority auth = new SimpleGrantedAuthority(r.getRole().toString());
				authorities.add(auth);
			}
			
			//Create Spring Security User to return
			return new User(
					user.getUserName(), 
					user.getPassword(), 
					user.isEnabled(),
					user.isEnabled(),
					user.isEnabled(),
					user.isEnabled(),
					authorities); */
			return user;
		}
		else{
			throw new IllegalArgumentException(
					"Provided DAO does not have authentication capability");
		}
	}

	@Transactional
	@Override
	public void createUser(String userName, String password, UserRole...roles) {
		SchoolUser user = new SchoolUser(userName, password, true);
		for(UserRole r : roles){
			user.addRole(r);
		}
		userDao.insertEntity(user);
	}
	
	@Transactional
	@Override
	public void insertUser(SchoolUser user){
		userDao.insertEntity(user);
	}

	@Transactional
	@Override
	public List<SchoolUser> getAllUsers() {
		return userDao.getAllEntities();
	}

	@Transactional
	@Override
	public SchoolUser getUserById(Long userId) {
		return userDao.getEntityById(userId);
	}

	@Transactional
	@Override
	public long getUserCount() {
		return userDao.getEntityCount();
	}

	@Transactional
	@Override
	public List<SchoolUser> getUsersByPage(int pageNumber, int pageRowCount) {
		int startPageAfterRow = (pageNumber - 1) * pageRowCount;
		return userDao.getEntitiesByPage(startPageAfterRow, pageRowCount);
	}

	@Transactional
	@Override
	public boolean hasPagesRemaining(int pageNumber, int pageRowCount) {
		long expectedCount = pageNumber * pageRowCount;
		long actualCount = userDao.getEntityCount();
		
		return actualCount > expectedCount;
	}
	
	@Transactional
	@Override
	public SchoolUser getByUserName(String userName) throws UsernameNotFoundException{
		if(userDao instanceof HibernateUserDao){
			SchoolUser user = ((HibernateUserDao) userDao)
					.getUserByUsername(userName);
			if(user == null){
				throw new UsernameNotFoundException(userName + " not found"); 
			}
			
			return user;
		}
		else{
			throw new IllegalArgumentException(
					"Provided DAO does not have authentication capability");
		}
	}

}
