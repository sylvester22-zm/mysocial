package com.vanilla.events;

import java.lang.reflect.Array;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.vanilla.domain.User;
import com.vanilla.service.UserService;

public class PresenceEventListener {
 private ParticipantRepository participantRepository;

 private SimpMessagingTemplate messagingTemplate;

    private String loginDestination;
    private UserService userService;

    //private String logoutDestination;



    public PresenceEventListener(SimpMessagingTemplate messagingTemplate, 
    ParticipantRepository participantRepository) {
        this.messagingTemplate = messagingTemplate;
        this.participantRepository = participantRepository;
    }
    @EventListener
     public void handleSessionConnection(SessionConnectedEvent event){
         SimpMessageHeaderAccessor headerAccessor=SimpMessageHeaderAccessor.wrap(event.getMessage());
         String username=event.getUser().getName();
         //User currentUser=userService.findByUsername(username);
         ActiveEvent activeEvent=new ActiveEvent(username);
        messagingTemplate.convertAndSend(loginDestination,activeEvent);
       
        participantRepository.add(headerAccessor.getSessionId(),activeEvent);

     }
    public String getLoginDestination() {
        return loginDestination;
    }

    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }

}
