package com.vanilla.repository;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	List<UserProfile> findByfirstnameContaining(String user);

	UserProfile findByfirstname(String userProfile);

	UserProfile findByUser(User user);
    
	@Query(" SELECT u from UserProfile u where (u.id=:id )")
	UserProfile findOne(@Param("id") Long userProfile);
	
	@Query(" SELECT u from UserProfile u ")
	Slice<UserProfile>findAllUserProfiles();
}
