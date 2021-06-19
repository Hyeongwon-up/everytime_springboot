package com.example.toy.src.post.dto;

import com.example.toy.src.user.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class PostPostReqDto {
    private String title;
    private String content;
    private Long user_idx;
}
