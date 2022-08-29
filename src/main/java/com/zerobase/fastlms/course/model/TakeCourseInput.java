package com.zerobase.fastlms.course.model;


import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;

//멤버리스트에서 검색 파라미터 받아오기
@Data
public class TakeCourseInput extends CommonParam {
   long courseId;
   String userId;
   long takeCourseId; // take course Id


}
