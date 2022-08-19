package com.zerobase.fastlms.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String imagePath;
    String keyword;
    String Subject;

    @Column(length = 1000)
    String summary;

    @Lob
    String contents;
    long price;
    long salePrice;
     LocalDateTime saleEndDt;
}
