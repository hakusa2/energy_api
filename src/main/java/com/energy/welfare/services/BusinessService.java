package com.energy.welfare.services;

import com.energy.welfare.dto.Business;
import com.energy.welfare.mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BusinessService {

    @Autowired
    BusinessMapper businessMapper;

    public ArrayList<Business> getBusinessList() {
        return businessMapper.getBusinessList();
    }

    public Business getBusiness(String id) { return businessMapper.getBusiness(id); }

    public int insertBusiness(Business business) { return businessMapper.insertBusiness(business); }

    public int updateBusiness(Business business) { return businessMapper.updateBusiness(business); }

    public int statusChangeBusiness(Business business) { return businessMapper.statusChangeBusiness(business); }

    public int deleteBusiness(String id) { return businessMapper.deleteBusiness(id); }
}