package com.vanilla.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(columnDefinition="LONGTEXT")
	String message;
	private String localDatetime;
	private String toUsername;
	@ManyToOne
	@JoinColumn(name = "fromUser", nullable = false)
	private User fromUser;

	@ManyToOne
	@JoinColumn(name = "toUser", nullable = false)
	private User toUser;
	

	/*
	 * @JoinColumn(name="user_id") private User user;
	 */

	public Message() {

	}

	public Message(User fromUser, User toUser, String message,String localDatetime) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.message = message;
		this.localDatetime=localDatetime;
        this.toUsername=toUser.getUsername();
       
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getLocalDatetime() {
		return localDatetime;
	}

	public void setLocalDatetime(String localDatetime) {
		this.localDatetime = localDatetime;
	}


	

	@Override
	public String toString() {
		return "Message [message=" + message + ", toUsername=" + toUsername + ", fromUser=" + fromUser.getUsername() + ", toUser="
				+ toUser.getUsername() + "]";
	}



}
