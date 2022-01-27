package com.vanilla.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanilla.domain.CreatePost;
import com.vanilla.domain.Post;
import com.vanilla.domain.PostDammy;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;
import com.vanilla.repository.PostRepository;
import com.vanilla.service.PostService;
import com.vanilla.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Async
	@Transactional
	public void savePost(UserProfile profile, CreatePost post) {

		int likes = 3;
		int loves = 2;
		int shares = 1;
		// String pic=profile.getProfilepic();
		// System.out.println(pic+ " in post service impl");
		// String image= Base64.getEncoder().encodeToString(post.getPhoto().getBytes());
		// newPost.setPostimg(Base64.getEncoder().encodeToString(post.getPhoto().getBytes()));

		// postdto.setProfile(profile);

		postRepository.save(new Post(post.getUsername(), post.getPayload(), post.getPhoto(), post.getFirstname(),
				post.getLastname(), post.getDateTime(), likes, loves, shares, profile));

	}

	@Override
	public List<PostDammy> findAllPosts() {

		//Slice<Post> posts = postRepository.getAllPosts();
		//return posts.map(p -> new CreatePost(p)).getContent();
		 List<PostDammy>obj=postRepository.getAllPosts();
		 return obj;
	}

}
