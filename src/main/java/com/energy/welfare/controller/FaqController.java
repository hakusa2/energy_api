package com.energy.welfare.controller;

import com.energy.welfare.dto.Faq;
import com.energy.welfare.services.FileService;
import com.energy.welfare.services.FaqService;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "faq")
@RequiredArgsConstructor
public class FaqController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    @Autowired
    FaqService faqService;

    @Value("${file.path.faq}")
    private String filePath;


    @Operation(summary = "FAQ 조회", description = "FAQ 전체 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<Faq> getList(
    ) {
        return faqService.getFaqList();
    }

    @Operation(summary = "FAQ 상세(단건) 조회", description = "FAQ 상세(단건) 조회 API")
    @RequestMapping(value = "getSingle", method = RequestMethod.GET)
    public Faq getSingle(
            @RequestParam(value = "id", required = true) String id
    ) {
        return faqService.getFaq(id);
    }

    @Operation(summary = "FAQ 등록", description = "FAQ 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "qTitle", required = true) String qTitle
            , @RequestParam(value = "aTitle", required = true) String aTitle
            , @RequestParam(value = "description", required = true) String description
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            Faq Faq = new Faq();
            Faq.setQTitle(qTitle);
            Faq.setATitle(aTitle);
            Faq.setDescription(description);

            int rst = faqService.insertFaq(Faq);

            if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }
        } catch(Exception e){
            log.info("write" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "FAQ 수정", description = "FAQ 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "qTitle", required = true) String qTitle
            , @RequestParam(value = "aTitle", required = true) String aTitle
            , @RequestParam(value = "description", required = true) String description
            , @RequestParam(value = "id", required = true) String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            Faq Faq = faqService.getFaq(id);
            Faq.setQTitle(qTitle);
            Faq.setATitle(aTitle);
            Faq.setDescription(description);

            int rst = faqService.updateFaq(Faq);

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

    @Operation(summary = "FAQ 삭제", description = "FAQ 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String[] id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            for(String idx : id){
                int rst = faqService.deleteFaq(idx);

                if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                    modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                    modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
                } else {
                    modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                    modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
                    return modelMap;
                }
            }
        } catch(Exception e){
            log.info("remove" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }
}
