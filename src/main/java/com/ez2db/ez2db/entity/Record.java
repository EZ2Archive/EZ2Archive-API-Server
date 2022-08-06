package com.ez2db.ez2db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Record
{
  /** 레코드 아이디 */
  @Id
  @GeneratedValue
  private Long id;

  /** 음원 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "music_info_id")
  private MusicInfo music;

  /** 사용자 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  /** 스코어 */
  private int score;

  /** 랭크 */
  @Enumerated(EnumType.STRING)
  private Rank rank;

  /** 기록일시 */
  private LocalDateTime addTime;
}
