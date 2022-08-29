package com.zerobase.fastlms.course.model;


import lombok.Data;

@Data
public class ServiceResult {
//오류에 대한 결과 출력
    boolean result;
    String message;

    public ServiceResult() {
        result = true;
    }

    public ServiceResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }
    public ServiceResult(boolean result) {
        this.result = result;
    }
}
