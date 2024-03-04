package com.energy.welfare.services;

import com.energy.welfare.dto.Promotion;
import com.energy.welfare.mapper.PromotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PromotionService {

    @Autowired
    PromotionMapper promotionMapper;

    public ArrayList<Promotion> getPromotionList() {
        return promotionMapper.getPromotionList();
    }

    public Promotion getPromotion(String id) {
        return promotionMapper.getPromotion(id);
    }

    public int insertPromotion(Promotion promotion) { return promotionMapper.insertPromotion(promotion); }

    public int updatePromotion(Promotion promotion) { return promotionMapper.updatePromotion(promotion); }

    public int deletePromotion(String id) { return promotionMapper.deletePromotion(id); }
}