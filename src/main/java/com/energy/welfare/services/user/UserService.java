package com.energy.welfare.services.user;

import com.energy.welfare.dto.users.User;
import com.energy.welfare.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public ArrayList<User> findAll() {
        return userMapper.findAll();
    }

    public User getUser(String email) {
        return userMapper.getUser(email);
    }
}