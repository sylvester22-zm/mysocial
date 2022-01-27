package com.vanilla.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Lob
	String profilepic;
	String username;
	String firstname;
	String lastname;
	String city;
	String joined;
	String status;
	String dob;

	String bio;
	int followers;
	int following;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany
	private List<Post>posts;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserProfile(String username,String firstname, String lastname, String bio, String pic, Long id) {
		this.username=username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.bio = bio;
		this.profilepic = pic;
		this.id = id;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public String getUsername() { return firstname; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 */

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserProfile() {

	}

	public UserProfile(User user) {
		this.firstname = user.getUsername();

	}

	@Override
	public String toString() {
		return "UserProfile [username="+ username  +"firstname=" + firstname + ", lastname=" + lastname + ", pic=" + profilepic + "]";
	}

}
