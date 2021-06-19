package com.example.toy.src.post.dto;

import com.example.toy.src.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostPostReqDto {
    private String title;
    private String content;
    private Long board_idx;
    private Long user_idx;
    private Byte status;
    private Byte is_blind;
}
