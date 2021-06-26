package com.example.toy.src.message.controller;


import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.service.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @PostMapping("/create")
  @ApiOperation(value = "새로운 쪽지함생성")
  public ResponseEntity<MessageRoomCreateDto> create(MessageRoomCreateDto messageRoomCreateDto) {
    log.info("controller start");
    log.info("{},{}", messageRoomCreateDto.getReceiverId(), messageRoomCreateDto.getSenderId());

    return ResponseEntity.ok(messageService.createMessageRoom(messageRoomCreateDto));
  }
//
//  @PostMapping("/create2")
//  @ApiOperation(value = "새로운 쪽지함생성")
//  public String create2(@RequestBody MessageRoomCreateDto messageRoomCreateDto) {
//    log.info("controller start");
//    log.info("{},{}", messageRoomCreateDto.getReceiverId(), messageRoomCreateDto.getSenderId());
//    messageService.createMessageRoom(messageRoomCreateDto);
//    return "success";
//  }

  @Getter(/)
}
