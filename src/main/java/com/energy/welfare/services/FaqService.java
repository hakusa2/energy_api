package com.energy.welfare.services;

import com.energy.welfare.dto.Faq;
import com.energy.welfare.mapper.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FaqService {

    @Autowired
    FaqMapper faqMapper;

    public ArrayList<Faq> getFaqList() {
        ArrayList<Faq> faqList = faqMapper.getFaqList();

        for(Faq faq : faqList){
            faq.setDescriptionList(faq.getDescription().split("\n"));
        }
        return faqList;
    }

    public Faq getFaq(String id) {
        return faqMapper.getFaq(id);
    }

    public int insertFaq(Faq faq) { return faqMapper.insertFaq(faq); }

    public int updateFaq(Faq faq) { return faqMapper.updateFaq(faq); }

    public int deleteFaq(String id) { return faqMapper.deleteFaq(id); }
}