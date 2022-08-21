package com.zerobase.fastlms.course.service.impl;

import com.zerobase.fastlms.course.Repository.CourseRepository;
import com.zerobase.fastlms.course.Repository.TakeCourseRepository;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.mapper.TakeCourseMapper;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import com.zerobase.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {
    private final TakeCourseMapper TakecourseMapper;

    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {
        long totalCount = TakecourseMapper.selectListCount(parameter);
        List<TakeCourseDto> list = TakecourseMapper.selectList(parameter);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (TakeCourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    //문자열을 데이트 타임으로 바꿔주기

}
