package com.energy.welfare.mapper;

import com.energy.welfare.dto.Advice;
import com.energy.welfare.dto.Approval;
import com.energy.welfare.dto.EtcDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EtcMapper {

    Advice getAdvice();

    Approval getApproval();

    int updateEtc(EtcDto etcDto);
}