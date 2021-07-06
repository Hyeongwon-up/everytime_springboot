package com.example.toy.src.kakao.entity;

import lombok.Data;

@Data
public class Profile {
    public String nickname;
    public String thumbnail_image_url;
    public String profile_image_url;
    public Boolean is_default_image;
}
