package com.example.toy.src.comment.entity;

import com.example.toy.src.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "cmt_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NonNull
    @Column(name = "cmt_content")
    private String content;

    @NonNull
    private Long userIdx;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="post_idx")
    private Post post;

    @Column(name = "reply_idx", columnDefinition = "bigint default 0") // 대댓글의 대상(댓글 = cmt_idx)
    private Long replyIdx;

    @Column(columnDefinition = "tinyint default 0")
    private Byte isBlind;

    @Column(columnDefinition = "tinyint default 1")
    private Byte status;

}
