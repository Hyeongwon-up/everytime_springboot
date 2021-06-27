package com.example.toy.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReqDto {

  private String user_id;
  private String password;
  private Integer univ_idx;
  private Integer univ_year;
  private String nickname;
  private String user_name;
  private String user_email;
  private String phone_num;
  private Byte status;

}
