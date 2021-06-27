package com.example.toy.src.message.repository;

import com.example.toy.src.message.dto.MessageRoomCreateDto;
import com.example.toy.src.message.entity.MessageRoom;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRoomRepositorySupport {

    List<MessageRoom> readMessageRoomList(Long userId);

    Optional<MessageRoom> findByBetweenUser(Long from, Long to);
}
