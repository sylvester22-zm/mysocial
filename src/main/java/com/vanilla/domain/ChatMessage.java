package com.vanilla.domain;

public class ChatMessage {

	private Long id;
	private String toUsername;;
	private String message;
	private String username;
	private Long senderId;
	private Long receiverId;
	private String localDatetime;
	private Boolean isSender;
	private String profilepic;
	private String tofirstname;
	private String tolastname;
	

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public ChatMessage() {

	}
	public ChatMessage(String nomessages){}

	public Long getSenderId() {
		return senderId;
	}

	

	public Boolean getIsSender() {
		return isSender;
	}

	public void setIsSender(Boolean isSender) {
		this.isSender = isSender;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
     
	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLocalDatetime() {
		return localDatetime;
	}

	public void setLocalDatetime(String localDatetime) {
		this.localDatetime = localDatetime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToUsername() {
		return toUsername;
	}

	
	public String getTofirstname() {
		return tofirstname;
	}

	public void setTofirstname(String tofirstname) {
		this.tofirstname = tofirstname;
	}

	public String getTolastname() {
		return tolastname;
	}

	public void setTolastname(String tolastname) {
		this.tolastname = tolastname;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public ChatMessage(Message m, Boolean isSender) {
		this.toUsername = m.getToUser().getUsername();
		this.tofirstname=m.getToUser().getFirstname();
		this.tolastname=m.getToUser().getLastname();
	     this.message=m.getMessage();
		this.senderId = m.getFromUser().getId();
		this.receiverId = m.getToUser().getId();
		this.localDatetime=m.getLocalDatetime();
		this.username=m.getFromUser().getUsername();
		this.id = m.getId();
		this.isSender=isSender;
	}

	
    
	
	
}
