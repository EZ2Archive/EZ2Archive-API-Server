package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
  @ApiModelProperty(hidden = true)
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

  /** 티어 포인트 */
  @ApiModelProperty(hidden = true)
  private double point;

  /** 정확도 */
  @ApiModelProperty(hidden = true)
  private float percentage;

  /** 등급(S+++, S+, A+, B) */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @ApiModelProperty(hidden = true)
  private Grade grade;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @ApiModelProperty(hidden = true)
  private LocalDateTime addTime;
  
  @OneToOne(mappedBy = "recordDetail")
  @JsonIgnore
  private Record record;

  public RecordDetail()
  {
  }

  @Builder
  public RecordDetail(Long id, Long musicInfoId, boolean isNoMiss, boolean isAllCool, int score, double point, float percentage, Grade grade, LocalDateTime addTime, Record record)
  {
    this.id = id;
    this.musicInfoId = musicInfoId;
    this.isNoMiss = isNoMiss;
    this.isAllCool = isAllCool;
    this.score = score;
    this.point = point;
    this.percentage = percentage;
    this.grade = grade;
    this.addTime = addTime;
    this.record = record;
  }
}
