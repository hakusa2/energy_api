package com.energy.welfare.controller;

import com.energy.welfare.dto.Business;
import com.energy.welfare.services.FileService;
import com.energy.welfare.services.BusinessService;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "business")
@RequiredArgsConstructor
public class BusinessController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    @Autowired
    BusinessService businessService;

    @Operation(summary = "사업모델 조회", description = "사업모델 전체 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<Business> getList(
    ) {
        return businessService.getBusinessList();
    }

    @Operation(summary = "신청이력 확인", description = "신청이력 확인 API")
    @RequestMapping(value = "getCheck", method = RequestMethod.POST)
    public String getCheck(
            @RequestParam(value = "name") String name
            , @RequestParam(value = "mobile") String mobile
    ) {
        return businessService.getBusinessJoinCheck(name, mobile);
    }

    @Operation(summary = "신청이력 조회", description = "신청이력 조회 API")
    @RequestMapping(value = "getConfirm", method = RequestMethod.GET)
    public Business getConfirm(
            @RequestParam(value = "id", required = true) String id
    ) {
        return businessService.getBusiness(id);
    }

    @Operation(summary = "사업모델 등록", description = "사업모델 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "bType") String bType
            , @RequestParam(value = "status") String status
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "birth", required = false) String birth
            , @RequestParam(value = "mobile", required = false) String mobile
            , @RequestParam(value = "phone", required = false) String phone
            , @RequestParam(value = "email", required = false) String email
            , @RequestParam(value = "zipcode", required = false) String zipcode
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "sunLightYn", required = false) String sunLightYn
            , @RequestParam(value = "modelName", required = false) String modelName
            , @RequestParam(value = "remoteYn", required = false) String remoteYn
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            Business Business = new Business();
            Business.setBtype(bType);
            Business.setStatus(status);
            Business.setName(name);
            Business.setBirth(birth);
            Business.setMobile(mobile);
            Business.setPhone(phone);
            Business.setEmail(email);
            Business.setZipcode(zipcode);
            Business.setAddr1(addr1);
            Business.setAddr2(addr2);
            Business.setSunLightYn(sunLightYn);
            Business.setModelName(modelName);
            Business.setRemoteYn(remoteYn);

            int rst = businessService.insertBusiness(Business);

            if (rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE) {
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }

        } catch (Exception e) {
            log.info("write" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "사업모델 수정", description = "사업모델 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "bType") String bType
            , @RequestParam(value = "status") String status
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "birth", required = false) String birth
            , @RequestParam(value = "mobile", required = false) String mobile
            , @RequestParam(value = "phone", required = false) String phone
            , @RequestParam(value = "email", required = false) String email
            , @RequestParam(value = "zipcode", required = false) String zipcode
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "sunLightYn", required = false) String sunLightYn
            , @RequestParam(value = "modelName", required = false) String modelName
            , @RequestParam(value = "remoteYn", required = false) String remoteYn
            , @RequestParam(value = "id") String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            Business Business = businessService.getBusiness(id);
            Business.setBtype(bType);
            Business.setStatus(status);
            Business.setName(name);
            Business.setBirth(birth);
            Business.setMobile(mobile);
            Business.setPhone(phone);
            Business.setEmail(email);
            Business.setZipcode(zipcode);
            Business.setAddr1(addr1);
            Business.setAddr2(addr2);
            Business.setSunLightYn(sunLightYn);
            Business.setModelName(modelName);
            Business.setRemoteYn(remoteYn);

            int rst = businessService.updateBusiness(Business);

            if (rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE) {
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }

        } catch (Exception e) {
            log.info("modify" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "사업모델 상태변경", description = "사업모델 상태변경 API")
    @RequestMapping(value = "status", method = RequestMethod.GET)
    public ModelMap status(
            @RequestParam(value = "id") String id
            , @RequestParam(value = "status") String status
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            Business Business = businessService.getBusiness(id);
            Business.setStatus(status);

            int rst = businessService.statusChangeBusiness(Business);

            if (rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE) {
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }
        } catch (Exception e) {
            log.info("status" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "사업모델 삭제", description = "사업모델 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String[] id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            for(String idx : id) {
                int rst = businessService.deleteBusiness(idx);

                if (rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE) {
                    modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                    modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
                } else {
                    modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                    modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
                    return modelMap;
                }
            }
        } catch (Exception e) {
            log.info("remove" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }
}