package com.example.toy.src.message.repository;

import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.MessageRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRoomRepositorySupport {

    List<MessageRoom> readMessageRoomList(Long userId);
}
