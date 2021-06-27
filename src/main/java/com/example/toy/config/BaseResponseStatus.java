package com.example.toy.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus { //enum!!! 알아오기
  // 1000: 요청 성공
  SUCCESS(true, 1000, "요청에 성공하였습니다."),

  // 2000 : Request 오류
  FAIL(false, 2000, "요청에 실패하였습니다."),
  EMPTY_JWT(false, 2010, "JWT를 입력해주세요."),
  INVALID_TOKEN(false, 2011, "유효하지 않은 토큰입니다."),

  // 3000 : Response 오류
  RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

  // 4000 : Database 오류
  SERVER_ERROR(false, 4000, "서버와의 통신에 실패하였습니다.");

  private final boolean isSuccess;
  private final int code;
  private final String message;

  private BaseResponseStatus(boolean isSuccess, int code, String message) {
    this.isSuccess = isSuccess;
    this.code = code;
    this.message = message;
  }
}
