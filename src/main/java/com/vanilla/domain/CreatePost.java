package com.vanilla.domain;

import java.time.LocalDateTime;

public class CreatePost {

	Long id;
	String payload;
	String username;
	String photo;
	String lastname;
	String firstname;
	String profilePic;
	String steamed;
	String dateTime;
	private Long profileId;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/*
	 * private UserProfile profile; public UserProfile getProfile() { return
	 * profile; }
	 * 
	 * public void setProfile(UserProfile profile) { this.profile = profile; }
	 */

	/*
	 * public CreatePost(Long id, String payload, String sender, String username,
	 * Long senderId, String photo) { super(); this.id = id; this.payload = payload;
	 * this.username = username; this.photo = photo;
	 * 
	 * }
	 */
	public CreatePost(Post p) {
		this.id = p.getId();
		this.username = p.getUsername();
		this.payload = p.getPosttext();
		this.dateTime = p.getLocalDatetime();
		this.photo = p.getPostimg();
		this.profileId = p.getProfile().getId();
		this.firstname = p.getFirstname();
		this.lastname = p.getLastname();

	}
	
	/*
	 * public CreatePost(Post p) { this.id = p.getId(); this.username =
	 * p.getUsername(); this.payload = p.getPosttext(); this.dateTime =
	 * p.getLocalDatetime(); this.photo = p.getPostimg(); this.profileId =
	 * p.getProfile().getId(); this.firstname = p.getFirstname(); this.lastname =
	 * p.getLastname(); this.profilePic=p.getProfilePic();
	 * 
	 * }
	 */

	public CreatePost() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getSteamed() {
		return steamed;
	}

	public void setSteamed(String steamed) {
		this.steamed = steamed;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "CreatePost [payload=" + payload + ", username=" + username + ", lastname=" + lastname + ", firstname="
				+ firstname + "]";
	}

}
