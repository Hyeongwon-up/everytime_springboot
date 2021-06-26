package com.example.toy.src.message.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessageRoom is a Querydsl query type for MessageRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageRoom extends EntityPathBase<MessageRoom> {

    private static final long serialVersionUID = -425014391L;

    public static final QMessageRoom messageRoom = new QMessageRoom("messageRoom");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Message, QMessage> messageList = this.<Message, QMessage>createList("messageList", Message.class, QMessage.class, PathInits.DIRECT2);

    public final NumberPath<Long> receiver = createNumber("receiver", Long.class);

    public final NumberPath<Long> sender = createNumber("sender", Long.class);

    public QMessageRoom(String variable) {
        super(MessageRoom.class, forVariable(variable));
    }

    public QMessageRoom(Path<? extends MessageRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageRoom(PathMetadata metadata) {
        super(MessageRoom.class, metadata);
    }

}

