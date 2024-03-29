package com.energy.welfare.services;

import com.energy.welfare.dto.User;
import com.energy.welfare.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public ArrayList<User> findAll() {
        return userMapper.findAll();
    }

    public User getUser(String user) {
        return userMapper.getUser(user);
    }

    public int userCheck(String user){
        return userMapper.userCheck(user);
    }

    public int loginCheck(String user, String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user", user);
        map.put("password", password);

        return userMapper.loginCheck(map);
    }
}