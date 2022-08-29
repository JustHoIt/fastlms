package com.zerobase.fastlms.admin.banner.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {
    Long id;

    String bannerName;
    String filename;
    String urlFilename;

    //삭제를 위한
    String idList;

    String target;
    int sortNumber;
    boolean publicYn;

    LocalDateTime createDt;
}
