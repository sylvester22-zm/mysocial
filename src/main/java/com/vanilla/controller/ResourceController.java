package com.vanilla.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanilla.domain.PostDammy;
import com.vanilla.domain.ProfileDammy;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;
import com.vanilla.repository.PostRepository;
import com.vanilla.security.Role;
import com.vanilla.security.UserRole;
import com.vanilla.service.PostService;
import com.vanilla.service.UserProfileService;
import com.vanilla.serviceimpl.UserServiceImpl;
import com.vanilla.utilities.Securities;

@Controller
public class ResourceController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private PostService postService;
	@Autowired
	PostRepository postRepo;
	
	
	 @RequestMapping("/youmayknow")
	 @ResponseBody
	 public List<ProfileDammy> youMayKnow() {
		 List<ProfileDammy> userProfile = userProfileService.findAll();
		 
		 return userProfile;
		 
	 }
	@RequestMapping("/myprofile")
	@ResponseBody
	public UserProfile getProfiles(Principal principal) {
		String me = principal.getName();
		User user=userServiceImpl.findByUsername(me);
		UserProfile profile = userProfileService.findByUserProfile(user);
		return new UserProfile(profile.getUsername(),profile.getFirstname(),profile.getLastname(),
				profile.getBio(),profile.getProfilepic(),profile.getId(),profile.getJoined(),profile.getStatus());
	}
	@RequestMapping("/posts")
	@ResponseBody
	public List<PostDammy> getProfileAndFeeds() {
         System.out.println("Posts method ");
		 List<PostDammy>posts=postService.findAllPosts();
		 return posts;
		
	}
	@RequestMapping(value="/editProfile", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String editProfile(Principal principal,@RequestBody UserProfile pro) {
		System.out.println(pro.getFirstname()+"the values"+pro.getBio());
		String me = principal.getName();
		User user=userServiceImpl.findByUsername(me);
		UserProfile profile = userProfileService.findByUserProfile(user);
		if(user!=null) {
			if(pro!=null)
			profile.setBio(pro.getBio());
			profile.setFirstname(pro.getFirstname());
			profile.setLastname(pro.getLastname());
			profile.setStatus(pro.getStatus());
			user.setFirstname(pro.getFirstname());
			user.setLastname(pro.getLastname());
	
			userProfileService.saveProfile(profile,user);
		}else {
			return "there was an error!!!";
		}
		return "Saved!!";
	}

	/*
	 * this was the method that was used to return the current users id to
	 * diffrenciate between message sender and receiver, instead i found a good
	 * approach
	 */
	/*
	 * @RequestMapping("/currentUser")
	 * 
	 * @ResponseBody public User currentUser(Principal principal) { String
	 * user=principal.getName(); User
	 * currentUser=userServiceImpl.findByUsername(user);
	 * 
	 * return new User(currentUser.getId()); }
	 */
	@RequestMapping("/search/{username}")
	@ResponseBody
	public List<UserProfile> userSearch(@PathVariable("username") String user) {

		System.out.println("success  " + user);

		List<UserProfile> userProfile = userProfileService.findByUserProfile(user);

		return userProfile;

	}

	@RequestMapping("/login")
	public String login() {

		return "index";
	}

	@RequestMapping("/register")
	public String register() {

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Post(@ModelAttribute("username") String username, @ModelAttribute("email") String email,
			@ModelAttribute("password") String password, @ModelAttribute("firstname") String firstname,
			@ModelAttribute("lastname") String lastname, Model model) {

		String encpassword = Securities.passwordEncoder().encode(password);

		if (userServiceImpl.findByEmail(email) != null) {
			model.addAttribute("emailExists", true);
			return "register";
		}
		if (userServiceImpl.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "register";
		}

		User user = new User();
		Role role = new Role();
		//role.setRoleId();
		role.setName("User");
		Set<UserRole> userRole = new HashSet<>();
		user.setEmail(email);
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(encpassword);
		userRole.add(new UserRole(user, role));
		userServiceImpl.createUser(user, userRole);
		model.addAttribute("RegistrationSuccessfull", "true");

		return "register";
	}

}
