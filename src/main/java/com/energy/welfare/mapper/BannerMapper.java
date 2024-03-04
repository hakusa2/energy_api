package com.energy.welfare.mapper;

import com.energy.welfare.dto.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BannerMapper {

    ArrayList<Banner> getBannerList();

    Banner getBanner(String id);

    int insertBanner(Banner banner);

    int updateBanner(Banner banner);

    int deleteBanner(String id);
}