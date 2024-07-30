package com.energy.welfare.mapper;

import com.energy.welfare.dto.BusinessStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository
public interface BusinessStatusMapper {

    ArrayList<BusinessStatus> getBusinessStatusList();

    BusinessStatus getBusinessStatus(String id);
    
    int insertBusinessStatus(BusinessStatus BusinessStatus);

    int updateBusinessStatus(BusinessStatus BusinessStatus);

    int deleteBusinessStatus(String id);
}