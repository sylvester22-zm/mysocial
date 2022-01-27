package com.vanilla.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vanilla.domain.ChatMessage;
import com.vanilla.domain.Message;
import com.vanilla.domain.User;
import com.vanilla.repository.MessageRepository;
import com.vanilla.repository.UserRepository;
import com.vanilla.service.MessageService;
import com.vanilla.service.UserService;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;
   
    @Autowired
    UserService userService;
    
    
   
    @Async
    public void saveMessage(User fromUser, User toUser, ChatMessage message) {
     
    	message.setIsSender(true);
      messageRepository.save(new Message(fromUser,toUser,message.getMessage(),message.getLocalDatetime()));
    }


    @Override
    public List<ChatMessage> Conversation(Long me, Long toUser) {

      Slice<Message>messages=messageRepository.fetchConversation(me,toUser);
      
     return messages.map(m -> new ChatMessage(m, m.getFromUser().getId().compareTo(me)==0)).getContent();
    }
    @Override
    public List<ChatMessage> fetchMessages(Long me){
 
      Slice<Message>messagesList=messageRepository.findByToUserId(me);
  
		/*
		 * List<ChatMessage>chatMessages =new ArrayList<>();
		 * 
		 * messagesList.stream().forEach(message->{
		 * 
		 * chatMessages.add(new ChatMessage(message,true));
		 * 
		 * });
		 */
     return messagesList.map(m->new ChatMessage(m,m.getFromUser().getId().compareTo(me)==0)).getContent();
      
    }


   

     
}
