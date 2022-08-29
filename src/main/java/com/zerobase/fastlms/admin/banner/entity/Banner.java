package com.zerobase.fastlms.admin.banner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bannerName; //배너명
    private String filename;  //파일이름
    private String urlFilename; //배너클릭시 이동주소

    private String target;
    private int sortNumber; //정렬번호
    private boolean publicYn; //공개여부

    private LocalDateTime regDt; //등록일자
}
