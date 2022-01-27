package com.vanilla.service;


import java.util.List;

import com.vanilla.domain.ChatMessage;
import com.vanilla.domain.User;

public interface MessageService {

    void saveMessage(User fromUser, User toUser, ChatMessage message);
    
    List<ChatMessage>Conversation(Long me,Long otherUser);

    List<ChatMessage>fetchMessages(Long me);
	/* List<ChatMessage>userConversations(Long me); */
   
    
}
