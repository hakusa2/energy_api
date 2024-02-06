package com.energy.welfare.mapper.user;

import com.energy.welfare.dto.users.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper {

    ArrayList<User> findAll();

    User getUser(String email);
}