package com.example.toy.src.message.entity;


import com.example.toy.src.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRoom {

  @Id
  @Column(name = "messageRoom_idx")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long receiver;
  private Long sender;

  @OneToMany(mappedBy = "messageRoom")
  private List<Message> messageList = new ArrayList<>();



}
