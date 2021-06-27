package com.example.toy.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserReqDto {
    private String nickname;
    private String password;
    private String phone_num;
    private Integer univ_idx;
    private Integer univ_year;
    private String user_email;
    private String id;
    private String user_name;
}
