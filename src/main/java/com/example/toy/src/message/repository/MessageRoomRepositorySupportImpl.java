package com.example.toy.src.message.repository;

import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.entity.QMessageRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageRoomRepositorySupportImpl implements MessageRoomRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QMessageRoom messageRoom = QMessageRoom.messageRoom;

    public MessageRoomRepositorySupportImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MessageRoom> readMessageRoomList(Long userId) {

        return jpaQueryFactory
                .select(messageRoom)
                .where(messageRoom.sender.eq(userId).or(messageRoom.receiver.eq(userId)))
                .fetch();

    }
}
