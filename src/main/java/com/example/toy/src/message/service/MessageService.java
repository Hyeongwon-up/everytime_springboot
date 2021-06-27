package com.example.toy.src.message.service;

import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.repository.MessageRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService {

  @Autowired
  private MessageRoomRepository messageRoomRepository;

  public MessageRoomCreateDto createMessageRoom(MessageRoomCreateDto messageRoomCreateDto){
    log.info("service start : {},{}", messageRoomCreateDto.getReceiverId(), messageRoomCreateDto.getSenderId());
    MessageRoom messageRoom =
      MessageRoom.builder()
        .receiver(messageRoomCreateDto.getReceiverId())
        .sender(messageRoomCreateDto.getSenderId())
        .build();
    log.debug("success create room : {}", messageRoom.getId());
    messageRoomRepository.save(messageRoom);
    return messageRoomCreateDto;
  }

  public List<MessageRoom> readMessageRoomList(Long userId){

    log.info("service start!!!1");
      return messageRoomRepository.readMessageRoomList(userId);
  }

}
