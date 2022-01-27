package com.vanilla.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

public class ProfileDammy {
         

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Lob
	String profilepic;
	String username;
	String firstname;
	String lastname;

	String bio;
	int followers;
	int following;
	
	public ProfileDammy() {
		
	}
	public ProfileDammy(UserProfile p) {
		this.id=p.getId();
		this.profilepic=p.getProfilepic();
		this.firstname=p.getFirstname();
		this.lastname=p.getLastname();
		this.bio=p.getBio();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getFollowing() {
		return following;
	}
	public void setFollowing(int following) {
		this.following = following;
	}
	
	
}
