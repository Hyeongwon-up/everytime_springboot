package com.example.toy.src.message.repository;

import com.example.toy.src.message.entity.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long>, MessageRoomRepositorySupport {

}
