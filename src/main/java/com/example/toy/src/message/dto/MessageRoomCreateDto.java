package com.example.toy.src.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class MessageRoomCreateDto {
  private Long senderId;
  private Long receiverId;
}
