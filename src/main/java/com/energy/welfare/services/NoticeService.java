package com.energy.welfare.services;

import com.energy.welfare.dto.Notice;
import com.energy.welfare.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public ArrayList<Notice> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    public ArrayList<Notice> getNoticeListTop5() {
        return noticeMapper.getNoticeListTop5();
    }

    public Notice getNotice(String id) {
        return noticeMapper.getNotice(id);
    }

    public Notice getNoticePrev(String id) {
        return noticeMapper.getNoticePrev(id);
    }

    public Notice getNoticeNext(String id) {
        return noticeMapper.getNoticeNext(id);
    }

    public int insertNotice(Notice notice) { return noticeMapper.insertNotice(notice); }

    public int updateNotice(Notice notice) { return noticeMapper.updateNotice(notice); }

    public int deleteNotice(String id) { return noticeMapper.deleteNotice(id); }
}