package com.vanilla.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	String username;
	private int postlikes;
	private int postloves;
	private int postshares;
	@Column(columnDefinition="LONGTEXT")
	private String posttext;
	@Lob
	private String postimg;
	private String localDatetime;
	private String firstname;
	private String lastname;
	
	
	
//	@OneToMany
//	private List<Comments>comments;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private UserProfile profile;
	
	public Post() {
		
	}

	public Post(String username,String posttext, String postimg, String firstname,String lastname, 
			String localDatetime,
			int likes, int loves, 
			int shares,UserProfile profile) {
		super();
		this.username=username;
		this.posttext = posttext;
		this.postimg = postimg;
		this.localDatetime = localDatetime;
		this.postlikes = likes;
		this.postloves = loves;
		this.postshares = shares;
		this.firstname=firstname;
		this.lastname=lastname;
		this.profile=profile;
	
	}
     
	////my second custome Constructor


	public Long getId() {
		return id;
	}


	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public void setId(Long id) {
		this.id = id;
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
    
	/*
	 * public List<Comments> getComments() { return comments; }
	 * 
	 * public void setComments(List<Comments> comments) { this.comments = comments;
	 * }
	 */
	
	public String getFirstname() {
		return firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	

	public String getLocalDatetime() {
		return localDatetime;
	}

	public void setLocalDatetime(String localDatetime) {
		this.localDatetime = localDatetime;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", posttext=" + posttext + ", localDatetime=" + localDatetime + ", profile=" + profile
				+ "]";
	}
	

}
