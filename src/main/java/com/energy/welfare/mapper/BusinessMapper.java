package com.energy.welfare.mapper;

import com.energy.welfare.dto.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BusinessMapper {

    ArrayList<Business> getBusinessList();

    Business getBusiness(String id);

    int insertBusiness(Business business);

    int updateBusiness(Business business);

    int statusChangeBusiness(Business business);

    int deleteBusiness(String id);
}