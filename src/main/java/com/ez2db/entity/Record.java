package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 기록 엔티티 클래스
 */
@Entity
@Getter @Setter
public class Record
{
  /** 기록 아이디 */
  @Id @GeneratedValue
  @Column(name = "record_id")
  private Long id;

  /** 음원 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "music_info_id", nullable = false)
  private MusicInfo music;

  /** 사용자 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  /** No Miss 여부 */
  private boolean isNoMiss;

  /** 올 Cool 여부 */
  private boolean isAllCool;

  /** 스코어 */
  private int score;

  /** 등급 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Grade grade;

  @OneToMany(mappedBy = "record")
  private List<RecordHistory> recordHistoryList = new ArrayList<>();

}
