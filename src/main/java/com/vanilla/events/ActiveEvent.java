package com.vanilla.events;

import java.util.Date;

import com.vanilla.domain.User;

public class ActiveEvent {


    String username;
    Date time;
    
    public ActiveEvent(String name){
    	//this.id=currentUser.getId();
        this.username=name;
        this.time=new Date();
        //System.out.print("the user is::"+username);
    }
    

    


	public ActiveEvent() {

    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
