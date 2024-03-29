package com.energy.welfare.controller;

import com.energy.welfare.dto.Banner;
import com.energy.welfare.services.FileService;
import com.energy.welfare.services.BannerService;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "banner")
@RequiredArgsConstructor
//@PreAuthorize("isAuthenticated()")
public class BannerController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    @Autowired
    BannerService bannerService;

    @Value("${file.path.banner}")
    private String filePath;


    @Operation(summary = "배너 조회", description = "배너 전체 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<Banner> getList(
    ) {
        return bannerService.getBannerList();
    }

    @Operation(summary = "배너 조회 Top5", description = "배너 조회 Top5 API")
    @RequestMapping(value = "getListTop5", method = RequestMethod.GET)
    public List<Banner> getListTop5(
    ) {
        return bannerService.getBannerListTop5();
    }

    @Operation(summary = "배너 상세(단건) 조회", description = "배너 상세(단건) 조회 API")
    @RequestMapping(value = "getSingle", method = RequestMethod.GET)
    public Banner getSingle(
            @RequestParam(value = "id", required = true) String id
    ) {
        return bannerService.getBanner(id);
    }

    @Operation(summary = "배너 등록", description = "배너 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "type", required = true) String type
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "link", required = false) String link
            , @RequestParam(value = "sign", required = false) String sign
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
                Banner banner = new Banner();
                banner.setType(type);
                banner.setTitle(title);
                banner.setLinkUrl(link);
                banner.setSignYn(sign);

                if(fileInclude){
                    banner.setImageFile(image.getOriginalFilename());
                    banner.setImageUrl(Define.IMG_BANNER_URL + fileName);
                } else {
                    banner.setImageFile("");
                    banner.setImageUrl("");
                }

                int rst = bannerService.insertBanner(banner);

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

    @Operation(summary = "배너 수정", description = "배너 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "type", required = true) String type
            , @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "link", required = false) String link
            , @RequestParam(value = "sign", required = false) String sign
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
                Banner banner = bannerService.getBanner(id);
                banner.setType(type);
                banner.setTitle(title);
                banner.setLinkUrl(link);
                banner.setSignYn(sign);

                if(fileInclude){
                    banner.setImageFile(image.getOriginalFilename());
                    banner.setImageUrl(Define.IMG_BANNER_URL + fileName);
                }

                int rst = bannerService.updateBanner(banner);

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

    @Operation(summary = "배너 삭제", description = "배너 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String id[]
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            for(String idx : id){
                int rst = bannerService.deleteBanner(idx);

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
