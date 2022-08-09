package com.ez2db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 기록 엔티티 클래스
 */
@Entity
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

  /** 기록 생성 시각 */
  @Column(nullable = false)
  private LocalDateTime addTime;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public MusicInfo getMusic()
  {
    return music;
  }

  public void setMusic(MusicInfo music)
  {
    this.music = music;
  }

  public Member getMember()
  {
    return member;
  }

  public void setMember(Member member)
  {
    this.member = member;
  }

  public boolean isNoMiss()
  {
    return isNoMiss;
  }

  public void setNoMiss(boolean noMiss)
  {
    isNoMiss = noMiss;
  }

  public boolean isAllCool()
  {
    return isAllCool;
  }

  public void setAllCool(boolean allCool)
  {
    isAllCool = allCool;
  }

  public int getScore()
  {
    return score;
  }

  public void setScore(int score)
  {
    this.score = score;
  }

  public Grade getGrade()
  {
    return grade;
  }

  public void setGrade(Grade grade)
  {
    this.grade = grade;
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
