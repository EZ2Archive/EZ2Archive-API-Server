package com.ez2db.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티 클래스
 */
@Entity
@Getter @Setter
public class Member
{
  /** 사용자 고유 번호 */
  @Id @GeneratedValue
  @Column(name = "member_id")
  @ApiModelProperty(hidden = true)
  private Long id;

  /** 사용자 아이디 */
  @Column(unique = true, nullable = false)
  private String userId;

  /** 사용자 패스워드 */
  @Column(nullable = false)
  private String password;

  /** salt */
  @Column(nullable = false)
  @ApiModelProperty(hidden = true)
  private Long salt;

  /** 사용자 닉네임 */
  @Column(nullable = false)
  private String name;

  /** 사용자 권한 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @ApiModelProperty(hidden = true)
  private MemberAuthority authority;

  @OneToMany(mappedBy = "member")
  @ApiModelProperty(hidden = true)
  private List<Record> recordList = new ArrayList<>();

  /** 사용자 생성 시각 */
  @Column(nullable = false)
  @ApiModelProperty(hidden = true)
  private LocalDateTime addTime;

}
