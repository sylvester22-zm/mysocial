
package com.vanilla.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;
import com.vanilla.repository.RoleRepository;
import com.vanilla.repository.UserProfileRepository;
import com.vanilla.repository.UserRepository;
import com.vanilla.security.UserRole;
import com.vanilla.service.UserService;

/**
 * @author sylvester kalumbi UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserProfileRepository profileRepository;

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRole) {

		User currentUser = userRepository.findByUsername(user.getUsername());
		UserProfile profile = new UserProfile();

		currentUser = userRepository.save(user);
		profile.setUsername(user.getUsername());
		profile.setFirstname(user.getFirstname());
		profile.setLastname(user.getLastname());
		profile.setUser(user);
		profileRepository.save(profile);
		if (currentUser != null) {
			LOG.info("user {} already exists. Nothing will be done.");
		} else {
			for (UserRole ur : userRole) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRole().addAll(userRole);

		}
		return currentUser;
	}

	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	} // public User findByEmail(User uuser){
		// userRepository.findByEmail(user);
		// }

	@Override
	public User getUser(String fromUsername) {

		return userRepository.findByUsername(fromUsername);
	}

	public List<UserProfile> getUserProfile(String username) {
		List<User> seacrhedUser = userRepository.findByUsernameContaining(username);
		List<UserProfile> profile = new ArrayList<>();
		seacrhedUser.stream().forEach(pro -> {
			profile.add(new UserProfile(pro));
		});

		System.out.println("retrieved users::::" + userRepository.findByUsername(username).getUsername());

		return profile;
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	public User findByUserId(Long otherUser) {
		User user = userRepository.findByUserId(otherUser);
		return user;
	}
           
	public void updtaeUserProfile(UserProfile profile) {
		
	}
	

}