package com.vanilla.service;

import java.util.List;

import com.vanilla.domain.CreatePost;
import com.vanilla.domain.PostDammy;
import com.vanilla.domain.User;
import com.vanilla.domain.UserProfile;

public interface PostService {

	 void savePost(UserProfile profile,CreatePost newpost);

	List<PostDammy> findAllPosts();
}
