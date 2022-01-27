package com.vanilla.service;

import java.util.Set;

import com.vanilla.domain.User;
import com.vanilla.security.UserRole;

public interface UserService {
    User createUser(User user,Set<UserRole> userRole);
    User findByUsername(String user);
     User getUser(String fromUsername);
     
}
