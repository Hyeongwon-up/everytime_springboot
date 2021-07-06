package com.example.toy.src.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1477766034L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Long> board_idx = createNumber("board_idx", Long.class);

    public final StringPath board_name = createString("board_name");

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final NumberPath<Integer> univ_idx = createNumber("univ_idx", Integer.class);

    public final NumberPath<Long> user_idx = createNumber("user_idx", Long.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

