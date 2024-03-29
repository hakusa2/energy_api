package com.energy.welfare.mapper;

import com.energy.welfare.dto.ConstructionCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository
public interface ConstructionCaseMapper {

    ArrayList<ConstructionCase> getConstructionCaseListAll();

    ArrayList<ConstructionCase> getConstructionCaseList(Map<String,Object> map);

    int getConstructionCaseTotal(String type);

    ArrayList<ConstructionCase> getConstructionCaseListTop3(String type);

    ConstructionCase getConstructionCase(String id);

    int insertConstructionCase(ConstructionCase constructionCase);

    int updateConstructionCase(ConstructionCase constructionCase);

    int deleteConstructionCase(String id);
}