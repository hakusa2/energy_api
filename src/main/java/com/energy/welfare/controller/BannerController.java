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
@PreAuthorize("isAuthenticated()")
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
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "description", required = true) String description
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "imageDetail", required = false) MultipartFile imageDetail
            , @RequestParam(value = "link", required = false) String link
            , @RequestParam(value = "target", required = false) String target
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            String fileName = "";
            String fileNameDetail = "";
            boolean fileInclude = false;
            boolean fileIncludeDetail = false;

            if(image != null && !image.isEmpty()){
                fileInclude = true;
                fileName = fileService.serverUploadFile(image, filePath);
            }

            if(imageDetail != null && !imageDetail.isEmpty()){
                fileIncludeDetail = true;
                fileNameDetail = fileService.serverUploadFile(imageDetail, filePath);
            }

            if(fileInclude && (fileName == null ||  fileName.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } if(fileIncludeDetail && (fileNameDetail == null ||  fileNameDetail.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } else {
                Banner Banner = new Banner();
                Banner.setTitle(title);
                Banner.setDescription(description);
                Banner.setTarget(target);
                Banner.setLinkUrl(link);

                if(fileInclude){
                    Banner.setImageFile(image.getOriginalFilename());
                    Banner.setImageUrl(Define.IMG_BANNER_URL + fileName);
                } else {
                    Banner.setImageFile("");
                    Banner.setImageUrl("");
                }

                if(fileIncludeDetail){
                    Banner.setImageFileDetail(imageDetail.getOriginalFilename());
                    Banner.setImageUrlDetail(Define.IMG_BANNER_URL + fileNameDetail);
                } else {
                    Banner.setImageFileDetail("");
                    Banner.setImageUrlDetail("");
                }

                int rst = bannerService.insertBanner(Banner);

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
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "description", required = true) String description
            , @RequestParam(value = "image", required = false) MultipartFile image
            , @RequestParam(value = "imageDetail", required = false) MultipartFile imageDetail
            , @RequestParam(value = "link", required = false) String link
            , @RequestParam(value = "target", required = false) String target
            , @RequestParam(value = "id", required = true) String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            String fileName = "";
            String fileNameDetail = "";
            boolean fileInclude = false;
            boolean fileIncludeDetail = false;

            if(image != null && !image.isEmpty()){
                fileInclude = true;
                fileName = fileService.serverUploadFile(image, filePath);
            }

            if(imageDetail != null && !imageDetail.isEmpty()){
                fileIncludeDetail = true;
                fileNameDetail = fileService.serverUploadFile(imageDetail, filePath);
            }

            if(fileInclude && (fileName == null ||  fileName.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } if(fileIncludeDetail && (fileNameDetail == null ||  fileNameDetail.isEmpty())){
                modelMap.put(Define.CODE, Define.FILE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.FILE_FAIL_MESSAGE);
            } else {
                Banner Banner = bannerService.getBanner(id);
                Banner.setTitle(title);
                Banner.setDescription(description);
                Banner.setTarget(target);
                Banner.setLinkUrl(link);

                if(fileInclude){
                    Banner.setImageFile(image.getOriginalFilename());
                    Banner.setImageUrl(Define.IMG_BANNER_URL + fileName);
                }

                if(fileIncludeDetail){
                    Banner.setImageFileDetail(imageDetail.getOriginalFilename());
                    Banner.setImageUrlDetail(Define.IMG_BANNER_URL + fileNameDetail);
                }

                int rst = bannerService.updateBanner(Banner);

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
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            int rst = bannerService.deleteBanner(id);

            if(rst == Define.MYBATIS_EXECUTE_SUCCESS_CODE){
                modelMap.put(Define.CODE, Define.SUCCESS_CODE);
                modelMap.put(Define.MESSAGE, Define.SUCCESS_MESSAGE);
            } else {
                modelMap.put(Define.CODE, Define.DATABASE_FAIL_CODE);
                modelMap.put(Define.MESSAGE, Define.DATABASE_FAIL_MESSAGE);
            }
        } catch(Exception e){
            log.info("remove" + e.getMessage());
            modelMap.put(Define.CODE, Define.SYSTEM_FAIL_CODE);
            modelMap.put(Define.MESSAGE, Define.SYSTEM_FAIL_MESSAGE);
        }

        return modelMap;
    }
}
