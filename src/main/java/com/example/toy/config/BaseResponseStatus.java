package com.example.toy.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus { //enum!!! 알아오기
    // 1000: 요청 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    SUCCESS_SIGN_UP(true, 1000, "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(true, 1000, "로그인에 성공하였습니다."),
    SUCCESS_POST_COMMENT(true, 1000, "댓글 작성에 성공하였습니다."),

    // 2000 : Request 오류
    FAIL(false, 2000, "요청에 실패하였습니다."),
    OVERLAPPED_ID_FAIL(false, 2001, "중복된 아이디입니다."),
    OVERLAPPED_NICKNAME_FAIL(false, 2002, "중복된 닉네임입니다."),
    WRONG_ID(false, 2003, "아이디가 존재하지 않습니다."),
    OVERLAPPED_PHONENUM_FAIL(false, 2004, "중복된 전화번호입니다."),
    WRONG_LOGIN(false, 2005, "로그인 정보가 잘못되었습니다."),
    NOT_EXIST_COMMENT(false, 2006, "대상 댓글 인덱스가 잘못되었습니다."),
    NOT_ENTERED_COMMENT(false, 2006, "댓글 정보 중 미입력된 부분이 있습니다."),


    EMPTY_JWT(false, 2100, "JWT를 입력해주세요."),
    INVALID_TOKEN(false, 2101, "유효하지 않은 토큰입니다."),

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
