package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class LoginHistory
{
  @Id @GeneratedValue
  @Column(name = "login_history_id")
  private Long id;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private boolean isSucceed;

  @Column(nullable = false)
  private String ipAddress;

  @Column(nullable = false)
  private String agent;

  @Column(nullable = false)
  private LocalDateTime addTime;

}
