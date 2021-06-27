package com.example.toy.src.message.repository;

import com.example.toy.src.message.entity.Message;
import com.example.toy.src.message.entity.MessageRoom;
import com.example.toy.src.message.entity.QMessageRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class MessageRoomRepositorySupportImpl implements MessageRoomRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QMessageRoom messageRoom = QMessageRoom.messageRoom;

    public MessageRoomRepositorySupportImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<MessageRoom> readMessageRoomList(Long userId) {

        log.info("query start!!!!");

        List<MessageRoom> messageRoomList =
                jpaQueryFactory
                        .selectFrom(messageRoom)
                        .where(messageRoom.sender.eq(userId).or(messageRoom.receiver.eq(userId)))
                        .fetch();

        log.info("query result : {} ", messageRoomList.stream().toArray());

        return messageRoomList;
    }

    @Override
    public Optional<MessageRoom> findByBetweenUser(Long from, Long to) {

        log.info("***** findByFromAndTo Query START *****");

        MessageRoom findMessageRoom =
                jpaQueryFactory
                .selectFrom(messageRoom)
                .where((messageRoom.sender.eq(from)
                        .and(messageRoom.receiver.eq(to)))
                        .or((messageRoom.sender.eq(to)
                                .and(messageRoom.receiver.eq(from)))))
                .fetchOne();


        return Optional.of(findMessageRoom);
    }


}
