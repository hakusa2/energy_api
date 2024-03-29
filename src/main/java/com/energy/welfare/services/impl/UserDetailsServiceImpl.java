package com.energy.welfare.services.impl;

import com.energy.welfare.dto.User;
import com.energy.welfare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public User loadUserByUsername(String user) {
        User users = userService.getUser(user);

        if(users == null){
            throw new UsernameNotFoundException(user);
        }

        return users;
    }
}
