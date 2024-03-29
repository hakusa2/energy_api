package com.energy.welfare.controller;

import com.energy.welfare.dto.Advice;
import com.energy.welfare.dto.Approval;
import com.energy.welfare.dto.EtcDto;
import com.energy.welfare.services.EtcService;
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

@RestController
@RequestMapping(value = "etc")
@RequiredArgsConstructor
public class EtcController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    EtcService etcService;

    @Operation(summary = "상담연락처 조회", description = "전화번호 Email 조회 API")
    @RequestMapping(value = "getAdvice", method = RequestMethod.GET)
    public Advice getAdvice(
    ) {
        return etcService.getAdvice();
    }

    @Operation(summary = "사업신청가능여부 조회", description = "사업신청 on/off 조회 API")
    @RequestMapping(value = "getApproval", method = RequestMethod.GET)
    public Approval getApproval(
    ) {
        return etcService.getApproval();
    }

    @Operation(summary = "etc 코드 수정", description = "etc 코드 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "code", required = true) String code
            , @RequestParam(value = "data", required = true) String data
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            EtcDto dto = new EtcDto();
            dto.setCode(code);
            dto.setData(data);

            int rst = etcService.updateEtc(dto);

            if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }
        } catch(Exception e){
            log.info("modify" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "사업신청가능여부 수정", description = "사업신청가능여부 수정 API")
    @RequestMapping(value = "modifyApproval", method = RequestMethod.POST)
    public ModelMap modifyApproval(
            @RequestParam(value = "code1", required = true) String code1
            , @RequestParam(value = "data1", required = true) String data1
            , @RequestParam(value = "code2", required = true) String code2
            , @RequestParam(value = "data2", required = true) String data2
            , @RequestParam(value = "code3", required = true) String code3
            , @RequestParam(value = "data3", required = true) String data3
            , @RequestParam(value = "code4", required = true) String code4
            , @RequestParam(value = "data4", required = true) String data4
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            EtcDto dto1 = new EtcDto();
            dto1.setCode(code1);
            dto1.setData(data1);

            int rst1 = etcService.updateEtc(dto1);

            EtcDto dto2 = new EtcDto();
            dto2.setCode(code2);
            dto2.setData(data2);

            int rst2 = etcService.updateEtc(dto2);

            EtcDto dto3 = new EtcDto();
            dto3.setCode(code3);
            dto3.setData(data3);

            int rst3 = etcService.updateEtc(dto3);

            EtcDto dto4 = new EtcDto();
            dto4.setCode(code4);
            dto4.setData(data4);

            int rst4 = etcService.updateEtc(dto4);

            if(rst1 == Define.MYBATIS_EXECUTE_SUCCESS_CODE
                    && rst2 == Define.MYBATIS_EXECUTE_SUCCESS_CODE
                    && rst3 == Define.MYBATIS_EXECUTE_SUCCESS_CODE
                    && rst4 == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }
        } catch(Exception e){
            log.info("modify" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }
}
