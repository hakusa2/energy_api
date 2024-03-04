package com.energy.welfare.controller;

import com.energy.welfare.dto.Notice;
import com.energy.welfare.services.FileService;
import com.energy.welfare.services.NoticeService;
import com.energy.welfare.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@Api(tags = {"회원가입 API"})
@RequestMapping(value = "notice")
@RequiredArgsConstructor
public class NoticeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FileService fileService;

    @Autowired
    NoticeService noticeService;

    @Value("${file.path.notice}")
    private String filePath;


    @Operation(summary = "공지사항 조회", description = "공지사항 전체 조회 API")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<Notice> getList(
    ) {
        return noticeService.getNoticeList();
    }

    @Operation(summary = "공지사항 Top5 조회", description = "공지사항 Top5 조회 API")
    @RequestMapping(value = "getListTop5", method = RequestMethod.GET)
    public List<Notice> getListTop5(
    ) {
        return noticeService.getNoticeListTop5();
    }

    @Operation(summary = "공지사항 상세(단건) 조회", description = "공지사항 상세(단건) 조회 API")
    @RequestMapping(value = "getSingle", method = RequestMethod.GET)
    public Notice getSingle(
            @RequestParam(value = "id", required = true) String id
    ) {
        return noticeService.getNotice(id);
    }

    @Operation(summary = "공지사항 상세(단건) 이전글 조회", description = "공지사항 상세(단건) 이전글 조회 API")
    @RequestMapping(value = "getPrev", method = RequestMethod.GET)
    public Notice getPrev(
            @RequestParam(value = "id", required = true) String id
    ) {
        return noticeService.getNoticePrev(id);
    }

    @Operation(summary = "공지사항 상세(단건) 다음글 조회", description = "공지사항 상세(단건) 다음글 조회 API")
    @RequestMapping(value = "getNext", method = RequestMethod.GET)
    public Notice getNext(
            @RequestParam(value = "id", required = true) String id
    ) {
        return noticeService.getNoticeNext(id);
    }

    @Operation(summary = "공지사항 등록", description = "공지사항 등록 API")
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelMap write(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "description", required = true) String description
            , @RequestParam(value = "image", required = false) MultipartFile image
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
                Notice notice = new Notice();
                notice.setTitle(title);
                notice.setDescription(description);

                if(fileInclude){
                    notice.setImageFile(image.getOriginalFilename());
                    notice.setImageUrl(Define.IMG_NOTICE_URL + fileName);
                } else {
                    notice.setImageFile("");
                    notice.setImageUrl("");
                }

                int rst = noticeService.insertNotice(notice);

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

    @Operation(summary = "공지사항 수정", description = "공지사항 수정 API")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelMap modify(
            @RequestParam(value = "title", required = true) String title
            , @RequestParam(value = "description", required = true) String description
            , @RequestParam(value = "image", required = false) MultipartFile image
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
                Notice notice = noticeService.getNotice(id);
                notice.setTitle(title);
                notice.setDescription(description);

                if(fileInclude){
                    notice.setImageFile(image.getOriginalFilename());
                    notice.setImageUrl(Define.IMG_NOTICE_URL + fileName);
                } 

                int rst = noticeService.updateNotice(notice);

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

    @Operation(summary = "공지사항 삭제", description = "공지사항 삭제 API")
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ModelMap remove(
            @RequestParam(value = "id", required = true) String id
    ) {
        ModelMap modelMap = new ModelMap();

        try {
            int rst = noticeService.deleteNotice(id);

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
