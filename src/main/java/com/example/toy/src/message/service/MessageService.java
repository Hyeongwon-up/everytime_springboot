package com.example.toy.src.message.service;

import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.Message;
import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.repository.MessageRoomRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageService {

  @Autowired
  private MessageRoomRepository messageRoomRepository;

  public MessageRoomCreateDto createMessageRoom(MessageRoomCreateDto messageRoomCreateDto) {
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

  public List<MessageRoom> readMessageRoomList(Long userId) {
    log.info("service start!!!1");
    return messageRoomRepository.readMessageRoomList(userId);
  }

  public Message sendMessage(Long from, Long to) {

    //둘이서 대화가능한 MessageRoom이 존재하는 지 확인하기!
    Optional<MessageRoom> messageRoom = messageRoomRepository.findByBetweenUser(from, to);

    if(!messageRoom.isPresent()) {

      log.info("해당 메세지방이 존재 안행!! 새로운방 생성!!");

      MessageRoomCreateDto messageRoomCreateDto = MessageRoomCreateDto
              .builder()
              .senderId(from)
              .receiverId(to)
              .build();

      createMessageRoom(messageRoomCreateDto);
    }


    // TODO : "여기하던중!!!!!!!!!







  }

  public boolean checkMessageRoomIsExist(Long from, Long to) {
    Optional<MessageRoom> messageRoom = messageRoomRepository.findByBetweenUser(from, to);
    return messageRoom.isPresent() == true ? true : false;
  }
}
