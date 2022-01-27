package com.vanilla.serviceimpl;

import com.vanilla.repository.UserRepository;
import  com.vanilla.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
       User user=userRepository.findByUsername(email);

        if(user==null){
            throw new  UsernameNotFoundException("Username not found");
        }

        return user;
    }

    
}
