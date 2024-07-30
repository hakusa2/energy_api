package com.energy.welfare.services;

import com.energy.welfare.dto.BusinessStatus;
import com.energy.welfare.mapper.BusinessStatusMapper;
import com.energy.welfare.utils.ARIAUtil;
import com.energy.welfare.utils.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class BusinessStatusService {

    @Autowired
    BusinessStatusMapper businessStatusMapper;

    public ArrayList<BusinessStatus> getBusinessStatusList() {
        //String encPassword = ARIAUtil.ariaEncrypt(password);
        //String descPassword = ARIAUtil.ariaDecrypt(encPassword);
        ArrayList<BusinessStatus> resultList = businessStatusMapper.getBusinessStatusList();

        try {
            for(BusinessStatus item : resultList){
                switch (item.getCategory()){
                    case "1":
                    	item.setCategoryName(Define.BUSINESS_STATUS_DATA2_1);
                        break;
                    case "2":
                    	item.setCategoryName(Define.BUSINESS_STATUS_DATA2_2);
                        break;
                    case "3":
                    	item.setCategoryName(Define.BUSINESS_STATUS_DATA2_3);
                        break;
                    case "4":
                    	item.setCategoryName(Define.BUSINESS_STATUS_DATA2_4);
                        break;
                }

            }
        } catch (Exception e){
        }

        return resultList;
    }

    public BusinessStatus getBusinessStatus(String id) {
        BusinessStatus businessStatus = businessStatusMapper.getBusinessStatus(id);


        switch (businessStatus.getCategory()){
            case "1":
                businessStatus.setCategoryName(Define.BUSINESS_STATUS_DATA2_1);
                break;
            case "2":
                businessStatus.setCategoryName(Define.BUSINESS_STATUS_DATA2_2);
                break;
            case "3":
                businessStatus.setCategoryName(Define.BUSINESS_STATUS_DATA2_3);
                break;
            case "4":
                businessStatus.setCategoryName(Define.BUSINESS_STATUS_DATA2_4);
                break;
        }


        return businessStatus;
    }
    

    public int insertBusinessStatus(BusinessStatus businessStatus) { return businessStatusMapper.insertBusinessStatus(businessStatus); }

    public int updateBusinessStatus(BusinessStatus businessStatus) { return businessStatusMapper.updateBusinessStatus(businessStatus); }

    public int deleteBusinessStatus(String id) { return businessStatusMapper.deleteBusinessStatus(id); }
}