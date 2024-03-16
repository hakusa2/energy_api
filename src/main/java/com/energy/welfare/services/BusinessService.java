package com.energy.welfare.services;

import com.energy.welfare.dto.Business;
import com.energy.welfare.mapper.BusinessMapper;
import com.energy.welfare.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class BusinessService {

    @Autowired
    BusinessMapper businessMapper;

    public ArrayList<Business> getBusinessList() {
        return businessMapper.getBusinessList();
    }

    public Business getBusiness(String id) {
        Business business = businessMapper.getBusiness(id);

        switch (business.getBtype()){
            case "1":
                business.setBtypeName(Define.BUSINESS_BTYPE_DATA_1);
                break;
            case "2":
                business.setBtypeName(Define.BUSINESS_BTYPE_DATA_2);
                break;
            case "3":
                business.setBtypeName(Define.BUSINESS_BTYPE_DATA_3);
                break;
            case "4":
                business.setBtypeName(Define.BUSINESS_BTYPE_DATA_4);
                break;
        }

        switch (business.getStatus()){
            case "1":
                business.setStatusName(Define.BUSINESS_STATUS_DATA_1);
                break;
            case "2":
                business.setStatusName(Define.BUSINESS_STATUS_DATA_2);
                break;
            case "3":
                business.setStatusName(Define.BUSINESS_STATUS_DATA_3);
                break;
            case "4":
                business.setStatusName(Define.BUSINESS_STATUS_DATA_4);
                break;
        }

        if (business.getBtype().equals("3") && business.getAddr2() != null && business.getAddr2().length() > 0)
            business.setStype("1"); //세대
        else
            business.setStype("2"); //단지

        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
        business.setMobile(business.getMobile().replaceAll(regEx, "$1-$2-$3"));

        business.setEmail1(business.getEmail().split("@")[0]);
        business.setEmail2(business.getEmail().split("@")[1]);

        return business;
    }

    public Business getBusiness(String name, String mobile) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("mobile", mobile);

        return businessMapper.getBusinessConfirm(map);
    }

    public String getBusinessJoinCheck(String name, String mobile) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("mobile", mobile);

        return businessMapper.getBusinessJoinCheck(map);
    }

    public int insertBusiness(Business business) { return businessMapper.insertBusiness(business); }

    public int updateBusiness(Business business) { return businessMapper.updateBusiness(business); }

    public int statusChangeBusiness(Business business) { return businessMapper.statusChangeBusiness(business); }

    public int deleteBusiness(String id) { return businessMapper.deleteBusiness(id); }
}