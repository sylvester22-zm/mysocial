package com.vanilla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vanilla.serviceimpl.UserProfileServiceImpl;
import com.vanilla.serviceimpl.UserServiceImpl;

@SpringBootApplication
public class VanillaApplication 
{
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserProfileServiceImpl userProfileServiceImpl;
	
	
	public static void main(String[] args) {
	
		SpringApplication.run(VanillaApplication.class, args);
	}

	
		/*
		 * User user=new User();
		 * 
		 * String password=Securities.passwordEncoder().encode("kalu");
		 * user.setEmail("topkalu@gmail.com"); user.setFirstname("sylvester");
		 * user.setLastname("kalumbi"); user.setPassword(password);
		 * user.setUsername("top"); Role role=new Role(); role.setName("user");
		 * role.setRoleId(1); Set<UserRole>userRole=new HashSet<>(); userRole.add(new
		 * UserRole(user,role));
		 * if(userServiceImpl.findByUsername(user.getUsername())==null) {
		 * userServiceImpl.createUser(user,userRole); UserProfile profile=new
		 * UserProfile(); profile.setUsername(user.getUsername());
		 * profile.setUser(user); userProfileServiceImpl.saveProfile(profile); }
		 * 
		 * 
		 * // second user User user1=new User(); Role role1=new Role(); UserProfile
		 * profile1=new UserProfile(); Set<UserRole>userRole1=new HashSet<>(); String
		 * password1=Securities.passwordEncoder().encode("kalu");
		 * user1.setEmail("cornard@gmail.com"); user1.setFirstname("cornard");
		 * user1.setLastname("kalumbi"); user1.setPassword(password1);
		 * user1.setUsername("cor"); role1.setName("user"); role1.setRoleId(1);
		 * userRole1.add(new UserRole(user1,role1));
		 * if(userServiceImpl.findByUsername(user1.getUsername())==null) {
		 * userServiceImpl.createUser(user1,userRole1); profile1.setUser(user1);
		 * profile1.setUsername(user1.getUsername());
		 * userProfileServiceImpl.saveProfile(profile1); } ///third user User user2=new
		 * User(); Role role2=new Role(); UserProfile profile2=new UserProfile();
		 * Set<UserRole>userRole2=new HashSet<>(); String
		 * password2=Securities.passwordEncoder().encode("kalu");
		 * user2.setEmail("cornard@gmail.com"); user2.setFirstname("abraham");
		 * user2.setLastname("kalumbi"); user2.setPassword(password2);
		 * user2.setUsername("abcu"); role2.setName("user"); role2.setRoleId(2);
		 * 
		 * userRole2.add(new UserRole(user2,role2));
		 * if(userServiceImpl.findByUsername(user2.getUsername())==null) {
		 * userServiceImpl.createUser(user2,userRole2);
		 * profile2.setUsername(user2.getUsername());
		 * profile2.setBio("Son,Brother and Computer Programmer");
		 * profile2.setUser(user2); userProfileServiceImpl.saveProfile(profile2); }
		 * 
		 * //fourth user User user3=new User(); Role role3=new Role(); UserProfile
		 * profile3=new UserProfile(); Set<UserRole>userRole3=new HashSet<>(); String
		 * password3=Securities.passwordEncoder().encode("kalu");
		 * user3.setEmail("sim@gmail.com"); user3.setFirstname("simon");
		 * user3.setLastname("kalumbi"); user3.setPassword(password3);
		 * user3.setUsername("sim"); role3.setName("user"); role3.setRoleId(3);
		 * userRole3.add(new UserRole(user3,role3));
		 * if(userServiceImpl.findByUsername(user3.getUsername())==null) {
		 * userServiceImpl.createUser(user3,userRole3); profile3.setUser(user3);
		 * userProfileServiceImpl.saveProfile(profile3); }
		 */


}
