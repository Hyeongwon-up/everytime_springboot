package com.example.toy.src.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostBoardReqDto {
    private String board_name;
    private Long user_idx;
}
