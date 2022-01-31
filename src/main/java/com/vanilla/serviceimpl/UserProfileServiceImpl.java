package com.vanilla.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanilla.domain.ProfileDammy;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;
import com.vanilla.repository.UserProfileRepository;
import com.vanilla.repository.UserRepository;
import com.vanilla.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserRepository userRepository;

	
   /*this method returns a list of users from a seacrh
	success by looking up the name */
	@Override
	public List<UserProfile> findByUserProfile(String user) {
		List<UserProfile> profile= userProfileRepository.findByfirstnameContaining(user);
		
		  List<UserProfile>pro=new ArrayList<>();
		  profile.stream().forEach(p->{
		  pro.add(new UserProfile(p.getUsername(),p.getFirstname(),p.getLastname(),p.getBio(),p.getProfilepic(),p.getId(),p.getJoined(),p.getStatus()));
		  });
		 
		/*
		 * UserProfile pro=new UserProfile(); pro.getUsername(); pro.getProfilepic();
		 * profile.add(pro);
		 */
		
		
		return pro;
	}

	/* this method returns the profile of a particular single user */
	@Override
	public UserProfile findUserProfile(Long userProfile) {
		UserProfile profile=userProfileRepository.findOne(userProfile);
		return new UserProfile(profile.getUsername(),profile.getFirstname(),profile.getLastname(),profile.getBio(),profile.getProfilepic(),profile.getId(),profile.getJoined(),profile.getStatus()) ;
	}

	@Override
	@Transactional
	public void saveProfile(UserProfile userProfile,User user) {
		   userRepository.save(user);
		 
		userProfileRepository.save(userProfile);
		
		
	}

	@Override
	public UserProfile findByUserId(Long id) {
		// TODO Auto-generated method stub
		return userProfileRepository.getById(id);
	}

	@Override
	public UserProfile findByUserProfile(User user) {
		// TODO Auto-generated method stub
		return userProfileRepository.findByUser(user);
	}
	
	public List<ProfileDammy> findAll(){
		 
		Slice<UserProfile>youMayKnow=userProfileRepository.findAllUserProfiles();
		
	return	youMayKnow.map(p ->new ProfileDammy(p)).getContent();
		
	}
	
}
