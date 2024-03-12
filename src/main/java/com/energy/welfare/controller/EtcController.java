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
}
