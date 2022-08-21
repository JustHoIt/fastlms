package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourseDto {

    Long id;
    long categoryId;
    String imagePath;
    String keyword;
    String subject;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;//등록일
    LocalDateTime udtDt;


    long totalCount;
    long seq;

    public static CourseDto of(Course course) {

        return CourseDto.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDt(course.getSaleEndDt())
                .regDt(course.getRegDt())
                .udtDt(course.getUdtDt())
                .build();
    }

    public static List<CourseDto> of(List<Course> courses) {

        if(courses == null){
            return  null;
        }
        List<CourseDto> courseDtos = new ArrayList<>();
        for(Course x : courses) {
            courseDtos.add(CourseDto.of(x));
        }
        return courseDtos;

//
//        if (courses != null) {
//            List<CourseDto> courseDtos = new ArrayList<>();
//            for (Course x : courses) {
//                courseDtos.add(CourseDto.of(x));
//            }
//            return courseDtos;
//        }
//        return null;
    }
}
