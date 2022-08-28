package com.ez2archive.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
  @JoinColumn(name = "member_id", unique = true)
  private Member member;

  /** */
  private boolean verify;

  /** record added time */
  @JsonDeserialize
  private LocalDateTime addTime;
}
