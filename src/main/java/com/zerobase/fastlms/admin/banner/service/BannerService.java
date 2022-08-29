package com.zerobase.fastlms.admin.banner.service;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.model.BannerInput;
import com.zerobase.fastlms.admin.banner.model.BannerParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> list(BannerParam parameter);


    //카테고리 신규 추가

    boolean add(BannerInput parameter);


   //배너내용 삭제

    boolean del(String idList);

    /**
     * 배너정보 수정
     */
    boolean set(BannerInput parameter);

   // 배너 상세정보
    BannerDto getById(long id);

    List<BannerDto> bannerList();
}
