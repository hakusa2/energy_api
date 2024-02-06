package com.energy.welfare.services.user.impl;

import com.energy.welfare.dto.users.User;
import com.energy.welfare.enumeration.UserRole;
import com.energy.welfare.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public User loadUserByUsername(String userEmail) {
        User user = userService.getUser(userEmail);

        if(user == null){
            throw new UsernameNotFoundException(userEmail);
        }
        return User.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .build();
    }
}
