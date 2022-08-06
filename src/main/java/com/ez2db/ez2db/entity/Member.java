package com.ez2db.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member
{
  @Id @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String userId;

  private String password;

  private Long salt;

  private String name;

  @Enumerated(EnumType.STRING)
  private UserAuthority authority;

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

  public UserAuthority getAuthority()
  {
    return authority;
  }

  public void setAuthority(UserAuthority authority)
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
