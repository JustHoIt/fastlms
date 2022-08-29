package com.zerobase.fastlms.admin.banner.service.Impl;

import com.zerobase.fastlms.admin.banner.dto.BannerDto;
import com.zerobase.fastlms.admin.banner.entity.Banner;
import com.zerobase.fastlms.admin.banner.mapper.BannerMapper;
import com.zerobase.fastlms.admin.banner.model.BannerInput;
import com.zerobase.fastlms.admin.banner.model.BannerParam;
import com.zerobase.fastlms.admin.banner.repository.BannerRepository;
import com.zerobase.fastlms.admin.banner.service.BannerService;
import com.zerobase.fastlms.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;




    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectList(parameter);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;

//        List<BannerDto> result = new ArrayList<>();
//        List<Banner> lists =
//                bannerRepository.findAll();
//        if(lists.size() < 1) return result;
//        int i = 1;
//        for(Banner x: lists){
//            result.add(BannerDto.of(x));
//        }
//        for (BannerDto item: result) {
//            item.setSeq(i);
//            i++;
//        }
//        return result;
    }

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .target(parameter.getTarget())
                .sortNumber(parameter.getSortNumber())
                .publicYn(parameter.isPublicYn())
                .regDt(LocalDateTime.now())
                .build();
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if(!optionalBanner.isPresent()) { // 수정할 데이터 없음
            return false;
        }

        Banner banner = optionalBanner.get();

        banner.setBannerName(parameter.getBannerName());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());
        banner.setTarget(parameter.getTarget());
        banner.setSortNumber(parameter.getSortNumber());
        banner.setPublicYn(parameter.isPublicYn());
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);

    }

    @Override
    public List<BannerDto> bannerList() {
        List<BannerDto> list = null;

        Optional<List<Banner>> optionalBanner = bannerRepository.findByPublicYnOrderBySortNumber(true);

        if (optionalBanner.isPresent()) {
            List<Banner> banner = optionalBanner.get();
            list = BannerDto.of(banner);
        }

        return list;
    }

    @Override
    public boolean del(String idList) {

        if(idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if(id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }

//    @Override
//    public List<BannerDto> getIndexBanner() {
//
//        List<Banner> banners = bannerRepository.getIndexBanner();
//        List<BannerDto> list = new ArrayList<>();
//        for(Banner b : banners){
//            list.add(BannerDto.of(b));
//        }
//        return list;
//    }
}
