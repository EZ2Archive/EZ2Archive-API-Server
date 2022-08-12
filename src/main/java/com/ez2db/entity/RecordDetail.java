package com.ez2db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

  /** 정확도 */
  private float percentage;

  /** 등급(S+++, S+, A+, B) */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Grade grade;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime addTime;
  
  @OneToMany(mappedBy = "recordDetail")
  @JsonIgnore
  private List<Record> recordList;
}
