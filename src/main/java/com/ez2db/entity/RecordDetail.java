package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class RecordDetail
{
  @Id @GeneratedValue
  @Column(name = "record_detail_id")
  private Long id;

  /** 그룹핑을 위한 음원 아이디 메타정보 */
  @Column(nullable = false)
  private Long musicInfoId;

  /** No Miss 여부 */
  @Column(nullable = false)
  private boolean isNoMiss;

  /** 올 Cool 여부 */
  @Column(nullable = false)
  private boolean isAllCool;

  /** 스코어 */
  private int score;

  /** 등급(S+++, S+, A+, B) */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Grade grade;

  @Column(nullable = false)
  private LocalDateTime addTime;
}
