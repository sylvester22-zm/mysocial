package com.vanilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vanilla.domain.User;
import com.vanilla.security.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String name);

	User save(Role role);

	@Query("select u from User u where u.username=:username")
	List<User> findByUsernameContaining(@Param("username") String username);

	User findByEmail(String email);

	@Query("select u from User u where u.id=:id")
	User findByUserId(@Param("id") Long id);
            
	/*
	 * @Query("SELECT new com.vanilla.domain.Conversations(m.message)  from Message m   "
	 * ) List<Conversations> myCoversation();
	 
	
	 * 
	 * @Query("SELECT new com.vanilla.domain.Conversations(m.message,u.username)  from User u,  Message m "
	 * +
	 * "  where  m.toUser.id=:me or m.fromUser.id=m.toUser.id and u.id!=:me group by u.id"
	 * ) Slice<Conversations> myCoversation(Long me); // on user.id=:me // group by
	 * u.id*/
	} 
	
