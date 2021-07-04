package com.example.toy.src.kakao.entity;

import lombok.Data;

@Data
public class KakaoAccount {
    public Boolean profile_needs_agreement;
    public Profile profile;
    public Boolean email_needs_agreement;
    public String email;
}
