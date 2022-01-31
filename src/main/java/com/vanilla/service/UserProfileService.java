package com.vanilla.service;

import java.util.List;

import com.vanilla.domain.ProfileDammy;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;

public interface UserProfileService {

	List<UserProfile> findByUserProfile(String username);

	UserProfile findUserProfile(Long userProfile);

	void saveProfile(UserProfile userprofile,User user);
     List<ProfileDammy>findAll();
	 UserProfile findByUserId(Long id) ;
	/* UserProfile userProfile(UserProfile userProfile); */
    
	UserProfile findByUserProfile(User user);

}
