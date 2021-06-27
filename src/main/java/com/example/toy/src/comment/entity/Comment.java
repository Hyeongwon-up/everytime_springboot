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
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmt_idx;

    @NonNull
    private String cmt_content;

    private Long user_idx;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="post_idx")
    @NotNull
    private Post post;

    @Column(columnDefinition = "tinyint default 0")
    private Byte is_blind;

    @Column(columnDefinition = "tinyint default 1")
    private Byte status;

}
