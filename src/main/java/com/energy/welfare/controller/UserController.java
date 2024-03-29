package com.energy.welfare.controller;

import com.energy.welfare.config.security.JwtConfig;
import com.energy.welfare.dto.Advice;
import com.energy.welfare.dto.Approval;
import com.energy.welfare.dto.EtcDto;
import com.energy.welfare.dto.User;
import com.energy.welfare.services.EtcService;
import com.energy.welfare.services.UserService;
import com.energy.welfare.utils.ARIAUtil;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserService userService;


    @Operation(summary = "로그인", description = "로그인 API")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelMap login(
            @RequestParam(value = "user", required = true) String user
            , @RequestParam(value = "password", required = true) String password
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            //String encPassword = ARIAUtil.ariaEncrypt(password);
            //String descPassword = ARIAUtil.ariaDecrypt(encPassword);
            int iUserChk = userService.userCheck(user);

            if(iUserChk == 0){
                modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.ID_PWD_FAIL_1);
                return modelMap;
            }

            int ret = userService.loginCheck(user, password);

            if(ret == 0){
                modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.ID_PWD_FAIL_2);
                return modelMap;
            }

            String token = jwtConfig.createToken(user, Arrays.asList("ADMIN"));

            modelMap.put(Define.CODE, Define.SUCCESS_CODE);
            modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            modelMap.put(Define.TOKEN, token);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return modelMap;
    }
}
