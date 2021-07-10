package com.example.toy.src.message.controller;


import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.Message;
import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

  @Autowired
  private MessageService messageService;
//
//  @PostMapping("/create")
//  @ApiOperation(value = "새로운 쪽지함생성")
//  public ResponseEntity<MessageRoomCreateDto> create(MessageRoomCreateDto messageRoomCreateDto) {
//    log.info("controller start");
//    log.info("{},{}", messageRoomCreateDto.getReceiverId(), messageRoomCreateDto.getSenderId());
//    return ResponseEntity.ok(messageService.createMessageRoom(messageRoomCreateDto));
//  }
//
//  @GetMapping("/messageroom/{id}")
//  @ApiOperation(value = "userId로 메세지함 불러오기")
//  public List<MessageRoom> readMessageRoom(@PathVariable Long id) {
//    log.info("controller start : {} 로 찾기!!!", id);
//      return messageService.readMessageRoomList(id);
//  }
//
//
//  /**
//   * 메세지 보내깅~
//   */
//  @PostMapping("/{userId}")
//  @ApiOperation("메세지 보내기")
//  public ResponseEntity<Message> sendMessage(@PathVariable(value = "userId") Long fromId, Long toId){
//
//  }
}
