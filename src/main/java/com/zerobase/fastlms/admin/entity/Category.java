package com.zerobase.fastlms.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; //Id

    String categoryName; // 카테고리명
    int sortValue;   //정렬값
    boolean usingYn; //사용가능여부

}
