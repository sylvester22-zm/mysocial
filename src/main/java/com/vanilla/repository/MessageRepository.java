 package com.vanilla.repository;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vanilla.domain.ChatMessage;
import com.vanilla.domain.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{
	
	  @Query("select m from  Message m where  (m.toUser.id=:fromUser and m.fromUser.id=:toUser) or (m.toUser.id=:toUser and m.fromUser.id=:fromUser) Order by m.id"	)
	  Slice<Message>fetchConversation(@Param("fromUser") Long me,@Param("toUser")  Long toUser);
	 
                    
	/*
	 * @Query("select new com.vanilla.domain.ChatMessage(m.id,m.toUsername,m.message,\r\n"
	 * +
	 * "	m.localDatetime,u.profilepic)  from  Message m,UserProfile u where  (m.toUser.id=:fromUser and m.fromUser.id=:toUser) or (m.toUser.id=:toUser and m.fromUser.id=:fromUser) Order by m.id"
	 * ) List<ChatMessage>fetchConversation(@Param("fromUser") Long
	 * me,@Param("toUser") Long toUser);
	 */
    @Query("select   m  from Message m  where m.toUser.id=:me or m.fromUser.id=:me  group by m.fromUser.id order by m.localDatetime DESC")
	  Slice<Message> findByToUserId(Long me);
    //order by m.id ASC ,
   
	 
    
    
}
