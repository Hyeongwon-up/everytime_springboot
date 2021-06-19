package com.example.toy.src.post.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "post_title")
    private String title;

    @NonNull
    @Column(name = "post_content")
    private String content;

    @NonNull
    @Column(name = "board_idx")
    private Long board_idx;

    @NonNull
    @Column(name = "user_idx")
    private Long user_idx;

    @Column(name = "is_blind", columnDefinition = "tinyint default 1")
    private Byte is_blind;

    @Column(name = "status", columnDefinition = "tinyint default 1")
    private Byte status;


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
