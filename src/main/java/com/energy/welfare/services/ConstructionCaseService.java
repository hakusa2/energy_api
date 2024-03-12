package com.energy.welfare.services;

import com.energy.welfare.dto.ConstructionCase;
import com.energy.welfare.mapper.ConstructionCaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConstructionCaseService {

    @Autowired
    ConstructionCaseMapper ConstructionCaseMapper;

    public ArrayList<ConstructionCase> getConstructionCaseList(String type, int page) {
        page = (page - 1) * 3;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("type", type);
        map.put("page", page);
        return ConstructionCaseMapper.getConstructionCaseList(map);
    }

    public int getConstructionCaseTotal(String type){ return ConstructionCaseMapper.getConstructionCaseTotal(type); }

    public ArrayList<ConstructionCase> getConstructionCaseListTop3() { return ConstructionCaseMapper.getConstructionCaseListTop3(); }

    public ConstructionCase getConstructionCase(String id) { return ConstructionCaseMapper.getConstructionCase(id); }

    public int insertConstructionCase(ConstructionCase constructionCase) { return ConstructionCaseMapper.insertConstructionCase(constructionCase); }

    public int updateConstructionCase(ConstructionCase constructionCase) { return ConstructionCaseMapper.updateConstructionCase(constructionCase); }

    public int deleteConstructionCase(String id) { return ConstructionCaseMapper.deleteConstructionCase(id); }
}