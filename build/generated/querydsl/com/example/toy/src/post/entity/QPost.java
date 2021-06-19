package com.example.toy.src.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -867176504L;

    public static final QPost post = new QPost("post");

    public final NumberPath<Long> board_idx = createNumber("board_idx", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> is_blind = createNumber("is_blind", Byte.class);

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> user_idx = createNumber("user_idx", Long.class);

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

