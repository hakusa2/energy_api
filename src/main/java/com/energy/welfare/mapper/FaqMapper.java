package com.energy.welfare.mapper;

import com.energy.welfare.dto.Faq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface FaqMapper {

    ArrayList<Faq> getFaqList();

    Faq getFaq(String id);

    int insertFaq(Faq faq);

    int updateFaq(Faq faq);

    int deleteFaq(String id);
}