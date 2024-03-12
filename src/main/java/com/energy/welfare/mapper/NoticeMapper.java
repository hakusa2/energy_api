package com.energy.welfare.mapper;

import com.energy.welfare.dto.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface NoticeMapper {

    ArrayList<Notice> getNoticeList();

    ArrayList<Notice> getNoticeListTop5();

    Notice getNoticeListTop();

    Notice getNotice(String id);

    Notice getNoticePrev(String id);

    Notice getNoticeNext(String id);

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    int deleteNotice(String id);
}