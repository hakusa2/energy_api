package com.energy.welfare.controller;

import com.energy.welfare.dto.BusinessStatus;
import com.energy.welfare.services.BusinessStatusService;
import com.energy.welfare.utils.ARIAUtil;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "businessStatus")
@RequiredArgsConstructor
public class BusinessStatusController {
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    BusinessStatusService businessStatusService;

    //@PreAuthorize("isAuthenticated()")
    @Operation(summary = "사업현황 조회", description = "사업현황 전체 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<BusinessStatus> getList(
    ) {
        return businessStatusService.getBusinessStatusList();
    }

    @Operation(summary = "사업현황 등록", description = "사업현황 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "category") String category
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "builtDate", required = false) String builtDate
            , @RequestParam(value = "units", required = false) String units
            , @RequestParam(value = "zipcode", required = false) String zipcode
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "latitude", required = false) Double latitude
            , @RequestParam(value = "longitude", required = false) Double longitude
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            //String encPassword = ARIAUtil.ariaEncrypt(password);
            //String descPassword = ARIAUtil.ariaDecrypt(encPassword);
            BusinessStatus BusinessStatus = new BusinessStatus();
            BusinessStatus.setCategory(category);
            BusinessStatus.setName(name);
            BusinessStatus.setBuiltDate(builtDate);
            BusinessStatus.setUnits(units);
            BusinessStatus.setZipcode(zipcode);
            BusinessStatus.setAddr1(addr1);
            BusinessStatus.setAddr2(addr2);
            BusinessStatus.setLatitude(latitude);
            BusinessStatus.setLongitude(longitude);

            int rst = businessStatusService.insertBusinessStatus(BusinessStatus);

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

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @Operation(summary = "사업현황 수정", description = "사업현황 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "category") String category
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "builtDate", required = false) String builtDate
            , @RequestParam(value = "units", required = false) String units
            , @RequestParam(value = "zipcode", required = false) String zipcode
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "latitude", required = false) Double latitude
            , @RequestParam(value = "longitude", required = false) Double longitude
            , @RequestParam(value = "id") String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            BusinessStatus BusinessStatus = businessStatusService.getBusinessStatus(id);
            BusinessStatus.setCategory(category);
            BusinessStatus.setName(name);
            BusinessStatus.setBuiltDate(builtDate);
            BusinessStatus.setUnits(units);
            BusinessStatus.setZipcode(zipcode);
            BusinessStatus.setAddr1(addr1);
            BusinessStatus.setAddr2(addr2);
            BusinessStatus.setLatitude(latitude);
            BusinessStatus.setLongitude(longitude);

            int rst = businessStatusService.updateBusinessStatus(BusinessStatus);

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

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @Operation(summary = "사업현황 삭제", description = "사업현황 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String[] id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            for(String idx : id) {
                int rst = businessStatusService.deleteBusinessStatus(idx);

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