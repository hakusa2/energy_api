package com.energy.welfare.services;

import com.energy.welfare.dto.ConstructionCase;
import com.energy.welfare.mapper.ConstructionCaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConstructionCaseService {

    @Autowired
    ConstructionCaseMapper ConstructionCaseMapper;

    public ArrayList<ConstructionCase> getConstructionCaseList() {
        return ConstructionCaseMapper.getConstructionCaseList();
    }

    public ConstructionCase getConstructionCase(String id) { return ConstructionCaseMapper.getConstructionCase(id); }

    public int insertConstructionCase(ConstructionCase constructionCase) { return ConstructionCaseMapper.insertConstructionCase(constructionCase); }

    public int updateConstructionCase(ConstructionCase constructionCase) { return ConstructionCaseMapper.updateConstructionCase(constructionCase); }

    public int deleteConstructionCase(String id) { return ConstructionCaseMapper.deleteConstructionCase(id); }
}