package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Tier
{
  @Id
  @GeneratedValue
  @Column(name = "tier_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private KeyType keyType;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TierGrade tierGrade;

  private double totalPoint;

  private double changePoint;

  private double untilNextTier;

  @Column(nullable = false)
  @JsonIgnore
  private LocalDateTime addTime;

  @Column(nullable = false)
  @JsonIgnore
  private LocalDateTime lastModifyTime;

  public Tier()
  {
  }

  @Builder
  public Tier(Long id, Member member, KeyType keyType, TierGrade tierGrade, double totalPoint, double changePoint, double untilNextTier, LocalDateTime addTime, LocalDateTime lastModifyTime)
  {
    this.id = id;
    this.member = member;
    this.keyType = keyType;
    this.tierGrade = tierGrade;
    this.totalPoint = totalPoint;
    this.changePoint = changePoint;
    this.untilNextTier = untilNextTier;
    this.addTime = addTime;
    this.lastModifyTime = lastModifyTime;
  }
}
