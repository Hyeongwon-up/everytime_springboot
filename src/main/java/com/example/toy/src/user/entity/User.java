package com.example.toy.src.user.entity;

import com.example.toy.config.BaseEntity;
import com.example.toy.src.post.entity.Post;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name="user")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_idx;

    @NonNull
    @Column(name = "user_id") // 아이디
    private String user_id;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "univ_idx")
    private Integer univ_idx;

    @NonNull
    @Column(name = "univ_year")
    private Integer univ_year;

    @NonNull
    @Column(name = "nickname")
    private String nickname;

    @NonNull
    @Column(name = "user_name")
    private String user_name;

    @NonNull
    @Column(name = "user_email")
    private String user_email;

    @NonNull
    @Column(name = "phone_num")
    private String phone_num;

    @Column(name = "status", columnDefinition = "tinyint default 1")
    private Byte status;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();



//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JsonManagedReference("post_user_id")
//    private List<Post> posts??????;


}
