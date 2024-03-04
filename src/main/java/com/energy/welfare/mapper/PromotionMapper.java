package com.energy.welfare.mapper;

import com.energy.welfare.dto.Promotion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface PromotionMapper {

    ArrayList<Promotion> getPromotionList();

    Promotion getPromotion(String id);

    int insertPromotion(Promotion promotion);

    int updatePromotion(Promotion promotion);

    int deletePromotion(String id);
}