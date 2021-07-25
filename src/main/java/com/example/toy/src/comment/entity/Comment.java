package com.example.toy.src.comment.entity;

import com.example.toy.src.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
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

    @Column(name = "parent_cmt_idx", columnDefinition = "bigint default 0") // 대댓글의 대상(댓글 = cmt_idx)
    private Long parentCmtIdx;

    @Column(columnDefinition = "bigint default 1")
    private Long seq;

    @Column(columnDefinition = "tinyint default 0")
    private Byte isBlind;

    @Column(columnDefinition = "tinyint default 1")
    private Byte status;


    public Comment(String content, Long userIdx, Post post, Long parentCmtIdx, Long seq,
                   Byte isBlind, Byte status) {
        this.content = content;
        this.userIdx = userIdx;
        this.post = post;
        this.parentCmtIdx = parentCmtIdx;
        this.isBlind = isBlind;
        this.status = status;
        this.seq = seq;
    }

}
