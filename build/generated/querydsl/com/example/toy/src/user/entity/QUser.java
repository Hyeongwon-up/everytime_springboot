package com.example.toy.src.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1685204382L;

    public static final QUser user = new QUser("user");

    public final com.example.toy.config.QBaseEntity _super = new com.example.toy.config.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone_num = createString("phone_num");

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final NumberPath<Integer> univ_idx = createNumber("univ_idx", Integer.class);

    public final NumberPath<Integer> univ_year = createNumber("univ_year", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final StringPath user_email = createString("user_email");

    public final StringPath user_id = createString("user_id");

    public final NumberPath<Long> user_idx = createNumber("user_idx", Long.class);

    public final StringPath user_name = createString("user_name");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

