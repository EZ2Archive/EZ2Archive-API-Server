package com.ez2archive.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter @Setter
public class Email
{
  /** record no */
  @Id @GeneratedValue
  @Column(name = "email_id")
  private Long id;

  /** encrypted email */
  @Column(unique = true, nullable = false)
  private String address;

  /** use member */
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "email")
  private Member member;

  /** */
  private boolean verify;

  /** record added time */
  @JsonDeserialize
  private LocalDateTime addTime;

  @JsonDeserialize
  private LocalDateTime expireTime;

  /** transient */
  @JsonDeserialize
  private static final int EXPIRE_AMOUNT = 30;
  /** transient */
  @JsonDeserialize
  private static final ChronoUnit EXPIRE_UNIT = ChronoUnit.MINUTES;

  public Email()
  {
  }

  @Builder
  public Email(Long id, String address, Member member, boolean verify, LocalDateTime addTime, LocalDateTime expireTime)
  {
    this.id = id;
    this.address = address;
    this.member = member;
    this.verify = verify;
    this.addTime = addTime;
    this.expireTime = expireTime;
  }

  public LocalDateTime getExpireTime()
  {
    return this.addTime.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
  }
  public static LocalDateTime getExpireTime(LocalDateTime addTime)
  {
    return addTime.plus(EXPIRE_AMOUNT, EXPIRE_UNIT);
  }
}
