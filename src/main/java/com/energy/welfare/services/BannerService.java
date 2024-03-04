package com.energy.welfare.services;

import com.energy.welfare.dto.Banner;
import com.energy.welfare.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BannerService {

    @Autowired
    BannerMapper bannerMapper;

    public ArrayList<Banner> getBannerList() {
        return bannerMapper.getBannerList();
    }

    public Banner getBanner(String id) {
        return bannerMapper.getBanner(id);
    }

    public int insertBanner(Banner banner) { return bannerMapper.insertBanner(banner); }

    public int updateBanner(Banner banner) { return bannerMapper.updateBanner(banner); }

    public int deleteBanner(String id) { return bannerMapper.deleteBanner(id); }
}