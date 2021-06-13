package com.example.toy.src.post.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "post_title")
    private String title;
//    @Column(name = "height", length = 20)

    @NonNull
    @Column(name = "post_content")
    private String content;

    @NonNull
    @Column(name = "board_idx")
    private Long board_idx;

    @NonNull
    @Column(name = "user_idx")
    private Long user_idx;

    @NonNull
    @Column(name = "is_blind", columnDefinition = "tinyint default 1")
    private Byte is_blind;

    @NonNull
    @Column(name = "status", columnDefinition = "tinyint default 1")
    private Byte status;

    @PrePersist
    public void prePersist() {
        this.is_blind =
                this.is_blind == null ? 1 : this.is_blind;
        this.status =
                this.status == null ? 1 : this.status;
    }


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
