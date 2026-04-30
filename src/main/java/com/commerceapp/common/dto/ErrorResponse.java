package com.commerceapp.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 공통 에러 응답 dto
public class ErrorResponse {

    private final LocalDateTime timestamp; // 에러 발생 시간
    private final int status; // HTTP 상태 코드
    private final String error; // 에러 이름
    private final String message; // 상세 에러 메세지
    private final String path; // 에러 발생 URI


    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
