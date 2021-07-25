package com.example.toy.src.post.dto;

import com.example.toy.src.comment.dto.GetAllCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostCommentDto {
    private List<GetAllCommentDto> comments;
}
