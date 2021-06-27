package com.example.toy.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginReqDto {
    private String id;
    private String password;

}
