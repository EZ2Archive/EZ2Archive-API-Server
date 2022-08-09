package com.ez2db.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 사용자 엔티티 클래스
 */
@Entity
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

  /** 사용자 생성 시각 */
  @Column(nullable = false)
  @ApiModelProperty(hidden = true)
  private LocalDateTime addTime;

  public Member()
  {
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Long getSalt()
  {
    return salt;
  }

  public void setSalt(Long salt)
  {
    this.salt = salt;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public MemberAuthority getAuthority()
  {
    return authority;
  }

  public void setAuthority(MemberAuthority authority)
  {
    this.authority = authority;
  }

  public LocalDateTime getAddTime()
  {
    return addTime;
  }

  public void setAddTime(LocalDateTime addTime)
  {
    this.addTime = addTime;
  }
}
