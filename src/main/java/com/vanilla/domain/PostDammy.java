package com.vanilla.domain;

import javax.persistence.Lob;

public class PostDammy {
            
	private Long id;
	String username;
	private int postlikes;
	private int postloves;
	private int postshares;
	private String posttext;
	
	private String postimg;
	
	private String profilePic;
	private String localDatetime;
	private String firstname;
	private String lastname;
	private Long profileid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPostlikes() {
		return postlikes;
	}
	public void setPostlikes(int postlikes) {
		this.postlikes = postlikes;
	}
	public int getPostloves() {
		return postloves;
	}
	public void setPostloves(int postloves) {
		this.postloves = postloves;
	}
	public int getPostshares() {
		return postshares;
	}
	public void setPostshares(int postshares) {
		this.postshares = postshares;
	}
	public String getPosttext() {
		return posttext;
	}
	public void setPosttext(String posttext) {
		this.posttext = posttext;
	}
	public String getPostimg() {
		return postimg;
	}
	public void setPostimg(String postimg) {
		this.postimg = postimg;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getLocalDatetime() {
		return localDatetime;
	}
	public void setLocalDatetime(String localDatetime) {
		this.localDatetime = localDatetime;
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
	
	public Long getProfileid() {
		return profileid;
	}
	public PostDammy(Long id,UserProfile profileId, String username, int postlikes, int postloves, int postshares, String posttext,
			String postimg, String profilePic, String localDatetime,
			String firstname, String lastname) {
		super();
		this.id = id;
		this.profileid=profileId.getId();
		this.username = username;
		this.postlikes = postlikes;
		this.postloves = postloves;
		this.postshares = postshares;
		this.posttext = posttext;
		this.postimg = postimg;
		this.profilePic = profilePic;
		this.localDatetime = localDatetime;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	
}
