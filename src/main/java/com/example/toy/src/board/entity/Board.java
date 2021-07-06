package com.example.toy.src.board.entity;

import com.example.toy.src.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "board")
public class Board {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_idx;

    @Column
    @NonNull
    private String board_name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_idx")
    @Column
    @NonNull
    private Long user_idx;

    @Column
    @NonNull
    private Integer univ_idx;
    //user는 다수의 Board(게시판)을 만들 수 있기 때문에

    @Column(name = "status", columnDefinition = "tinyint default 1")
    private Byte status;
}
