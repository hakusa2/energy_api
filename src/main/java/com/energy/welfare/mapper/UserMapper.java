package com.energy.welfare.mapper;

import com.energy.welfare.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    ArrayList<User> findAll();

    User getUser(String user);

    int userCheck(String user);

    int loginCheck(Map<String,Object> map);
}