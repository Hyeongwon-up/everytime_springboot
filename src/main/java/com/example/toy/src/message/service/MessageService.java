package com.example.toy.src.message.service;

import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.repository.MessageRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

  @Autowired
  private MessageRoomRepository messageRoomRepository;

  public void createMessageRoom(MessageRoomCreateDto messageRoomCreateDto){

    log.info("service start : {},{}", messageRoomCreateDto.getReceiverId(), messageRoomCreateDto.getSenderId());

    MessageRoom messageRoom =
      MessageRoom.builder()
        .receiver(messageRoomCreateDto.getReceiverId())
        .sender(messageRoomCreateDto.getSenderId())
        .build();

    messageRoomRepository.save(messageRoom);
    log.debug("success create room : {}", messageRoom.getId());
  }

}
