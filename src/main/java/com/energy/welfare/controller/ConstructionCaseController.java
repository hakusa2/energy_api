package com.energy.welfare.controller;

import com.energy.welfare.dto.ConstructionCase;
import com.energy.welfare.services.FileService;
import com.energy.welfare.services.ConstructionCaseService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "construction")
@RequiredArgsConstructor
public class ConstructionCaseController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    @Autowired
    ConstructionCaseService constructionCaseService;

    @Value("${file.path.construction}")
    private String filePath;

    @Operation(summary = "구축사례 조회", description = "타입별 구축사례 조회 API")
    @RequestMapping(value = "getListAll", method = RequestMethod.GET)
    public List<ConstructionCase> getListAll(
    ) {
        return constructionCaseService.getConstructionCaseListAll();
    }

    @Operation(summary = "구축사례 조회", description = "타입별 구축사례 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<ConstructionCase> getList(
            @RequestParam(value = "type", required = true) String type
            , @RequestParam(value = "page", required = true) int page
    ) {
        return constructionCaseService.getConstructionCaseList(type, page);
    }

    @Operation(summary = "구축사례 페이지 수 조회", description = "구축사례 페이지 수 조회 API")
    @RequestMapping(value = "getTotal", method = RequestMethod.GET)
    public int getTotal(
            @RequestParam(value = "type", required = true) String type
    ) {
        return constructionCaseService.getConstructionCaseTotal(type);
    }

    @Operation(summary = "구축사례 조회 Top3", description = "구축사례 전체 조회 Top3 API")
    @RequestMapping(value = "getListTop3", method = RequestMethod.GET)
    public List<ConstructionCase> getListTop3(
            @RequestParam(value = "type", required = true) String type
    ) {
        return constructionCaseService.getConstructionCaseListTop3(type);
    }

    @Operation(summary = "구축사례 상세(단건) 조회", description = "구축사례 상세(단건) 조회 API")
    @RequestMapping(value = "getSingle", method = RequestMethod.GET)
    public ConstructionCase getSingle(
            @RequestParam(value = "id", required = true) String id
    ) {
        return constructionCaseService.getConstructionCase(id);
    }

    @Operation(summary = "구축사례 등록", description = "구축사례 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "bType", required = true) String bType
            , @RequestParam(value = "groupName", required = false) String groupName
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "businessSummary", required = false) String businessSummary
            , @RequestParam(value = "packageCompose", required = false) String packageCompose
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "markYn", required = false) String markYn
            , @RequestParam(value = "tagYn1", required = false) String tagYn1
            , @RequestParam(value = "tagYn2", required = false) String tagYn2
            , @RequestParam(value = "tagYn3", required = false) String tagYn3
            , @RequestParam(value = "tagYn4", required = false) String tagYn4
            , @RequestParam(value = "tagYn5", required = false) String tagYn5
            , @RequestParam(value = "tagYn6", required = false) String tagYn6
            , @RequestParam(value = "tagYn7", required = false) String tagYn7
            , @RequestParam(value = "tagYn8", required = false) String tagYn8
            , @RequestParam(value = "tagYn9", required = false) String tagYn9

    ) {
        ModelMap modelMap = new ModelMap();

        try {
            String fileName = "";
            boolean fileInclude = false;

            if(image != null && !image.isEmpty()){
                fileInclude = true;
                fileName = fileService.serverUploadFile(image, filePath);
            }

            if(fileInclude && (fileName == null ||  fileName.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } else {
                ConstructionCase constructionCase = new ConstructionCase();
                constructionCase.setBType(bType);
                constructionCase.setGroupName(groupName);
                constructionCase.setAddr1(addr1);
                constructionCase.setAddr2(addr2);
                constructionCase.setBusinessSummary(businessSummary);
                constructionCase.setPackageCompose(packageCompose);
                constructionCase.setMarkYn(markYn);
                constructionCase.setTagYn1(tagYn1);
                constructionCase.setTagYn2(tagYn2);
                constructionCase.setTagYn3(tagYn3);
                constructionCase.setTagYn4(tagYn4);
                constructionCase.setTagYn5(tagYn5);
                constructionCase.setTagYn6(tagYn6);
                constructionCase.setTagYn7(tagYn7);
                constructionCase.setTagYn8(tagYn8);
                constructionCase.setTagYn9(tagYn9);

                if(fileInclude){
                    constructionCase.setImageFile(image.getOriginalFilename());
                    constructionCase.setImageUrl(Define.IMG_CONSTRUCTION_URL + fileName);
                } else {
                    constructionCase.setImageFile("");
                    constructionCase.setImageUrl("");
                }

                int rst = constructionCaseService.insertConstructionCase(constructionCase);

                if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                    modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                    modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
                } else {
                    modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                    modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
                }
            }
        } catch(Exception e){
            log.info("write" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "구축사례 수정", description = "구축사례 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "bType", required = true) String bType
            , @RequestParam(value = "groupName", required = false) String groupName
            , @RequestParam(value = "addr1", required = false) String addr1
            , @RequestParam(value = "addr2", required = false) String addr2
            , @RequestParam(value = "businessSummary", required = false) String businessSummary
            , @RequestParam(value = "packageCompose", required = false) String packageCompose
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "markYn", required = false) String markYn
            , @RequestParam(value = "tagYn1", required = false) String tagYn1
            , @RequestParam(value = "tagYn2", required = false) String tagYn2
            , @RequestParam(value = "tagYn3", required = false) String tagYn3
            , @RequestParam(value = "tagYn4", required = false) String tagYn4
            , @RequestParam(value = "tagYn5", required = false) String tagYn5
            , @RequestParam(value = "tagYn6", required = false) String tagYn6
            , @RequestParam(value = "tagYn7", required = false) String tagYn7
            , @RequestParam(value = "tagYn8", required = false) String tagYn8
            , @RequestParam(value = "tagYn9", required = false) String tagYn9
            , @RequestParam(value = "id", required = true) String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            String fileName = "";
            boolean fileInclude = false;

            if(image != null && !image.isEmpty()){
                fileInclude = true;
                fileName = fileService.serverUploadFile(image, filePath);
            }

            if(fileInclude && (fileName == null ||  fileName.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } else {
                ConstructionCase constructionCase = constructionCaseService.getConstructionCase(id);
                constructionCase.setBType(bType);
                constructionCase.setGroupName(groupName);
                constructionCase.setAddr1(addr1);
                constructionCase.setAddr2(addr2);
                constructionCase.setBusinessSummary(businessSummary);
                constructionCase.setPackageCompose(packageCompose);
                constructionCase.setMarkYn(markYn);
                constructionCase.setTagYn1(tagYn1);
                constructionCase.setTagYn2(tagYn2);
                constructionCase.setTagYn3(tagYn3);
                constructionCase.setTagYn4(tagYn4);
                constructionCase.setTagYn5(tagYn5);
                constructionCase.setTagYn6(tagYn6);
                constructionCase.setTagYn7(tagYn7);
                constructionCase.setTagYn8(tagYn8);
                constructionCase.setTagYn9(tagYn9);

                if(fileInclude){
                    constructionCase.setImageFile(image.getOriginalFilename());
                    constructionCase.setImageUrl(Define.IMG_CONSTRUCTION_URL + fileName);
                }

                int rst = constructionCaseService.updateConstructionCase(constructionCase);

                if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                    modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                    modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
                } else {
                    modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                    modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
                }
            }
        } catch(Exception e){
            log.info("modify" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }

    @Operation(summary = "구축사례 삭제", description = "구축사례 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String[] id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            for(String idx : id) {
                int rst = constructionCaseService.deleteConstructionCase(idx);

                if (rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE) {
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
