package com.energy.welfare.controller.users;

import com.energy.welfare.config.security.JwtConfig;
import com.energy.welfare.dto.User;
import com.energy.welfare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
//@Api(tags = {"회원가입 API"})
@RequestMapping(value = "test")
@RequiredArgsConstructor
public class UserTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    //private final JwtConfig jwtConfig;

    @Autowired
    JwtConfig jwtConfig;
    @Autowired
    UserService userService;

//    @ApiOperation(value = "회원가입", response = User.class)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "join", method = RequestMethod.GET)
    public User get() {
//        log.trace("TRACE 로그!!");
//        log.debug("DEBUG 로그!!");
//        log.info("INFO 로그!!");
//        log.warn("WARN 로그!!");
//        log.error("ERROR 로그!!");
        return new User("admin1", "USER", "test");
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        User user = new User("admin1", "ADMIN", "test@naver.com");
        log.info("user.getUserRole() :: " + user.getUserRole());
        return jwtConfig.createToken("hakusa@naver.com", Arrays.asList(user.getUserRole()));
    }


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ArrayList<User> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResultCode("S0001");
        responseDTO.setRes(userService.findAll());
        ArrayList<User> obj = userService.findAll();

        log.info(responseDTO.getRes().toString());
        return obj;
    }

}
