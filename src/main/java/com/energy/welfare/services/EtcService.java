package com.energy.welfare.services;

import com.energy.welfare.dto.Advice;
import com.energy.welfare.dto.Approval;
import com.energy.welfare.dto.EtcDto;
import com.energy.welfare.mapper.EtcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtcService {

    @Autowired
    EtcMapper etcMapper;

    public Advice getAdvice() {
        return etcMapper.getAdvice();
    }

    public Approval getApproval() { return etcMapper.getApproval(); }

    public int updateEtc(EtcDto etcDto) { return etcMapper.updateEtc(etcDto); }
}