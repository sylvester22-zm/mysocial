package com.vanilla.chatconfig;

import com.vanilla.events.ParticipantRepository;
import com.vanilla.events.PresenceEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
@EnableConfigurationProperties(ChatProperties.class)
public class ChatConfiguration {


    @Autowired
    private ChatProperties chatProperties;

    @Bean
    @Description("Keeps connected users")
    public ParticipantRepository participantRepository() {
        return new ParticipantRepository();
    }


    @Bean
    @Description("Tracks user presence (join / leave) and broacasts it to all connected users")
    public PresenceEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate) {
        PresenceEventListener presence = new PresenceEventListener(messagingTemplate, participantRepository());
        presence.setLoginDestination(chatProperties.getDestinations().getLogin());
       // presence.setLogoutDestination(chatProperties.getDestinations().getLogout());

        return presence;
    }

}
