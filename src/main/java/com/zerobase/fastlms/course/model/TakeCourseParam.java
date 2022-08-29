package com.zerobase.fastlms.course.model;


import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;

//멤버리스트에서 검색 파라미터 받아오기
@Data
public class TakeCourseParam extends CommonParam {
    long id;
    String status;
    String userId;


    long searchCourseId;
}
