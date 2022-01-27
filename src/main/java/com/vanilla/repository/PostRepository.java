package com.vanilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vanilla.domain.Post;
import com.vanilla.domain.PostDammy;



public interface PostRepository extends JpaRepository<Post, Long> {
	  
	/*
	 * @Query(
	 * value="SELECT p.id,p.username,p.posttext,p.firstname,p.lastname,p.postimg,p.profile_id,p.local_datetime,p.postlikes,p.postshares,p.profile_pic,p.postloves,p.postimg from POST p Order by p.local_datetime DESC"
	 * ,nativeQuery = true) Slice<Post> getAllPosts();
	 */
	@Query("select  new com.vanilla.domain.PostDammy(p.id,p.profile,p.username,p.postlikes,p.postloves,p.postshares,p.posttext,\r\n"
			+ "p.postimg,pro.profilepic,p.localDatetime,p.firstname,p.lastname) from Post p, UserProfile pro where p.profile=pro.id ")
	List<PostDammy> getAllPosts();
}
