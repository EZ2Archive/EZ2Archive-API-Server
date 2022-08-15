package com.ez2db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 음원 정보 엔티티 클래스
 */
@Entity
@Getter @Setter
public class MusicInfo
{
  /** 음원 고유 아이디 */
  @Id @GeneratedValue
  @Column(name = "music_info_id")
  private Long id;

  /** 음원 명 */
  @Column(nullable = false)
  private String name;

  /** 아티스트 명 */
  @Column(nullable = false)
  private String artist;

  /** 디스크 이미지 파일 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "file_id")
  private File imageFile;

  /** 키(4K, 5K, 6K, 8K) 타입 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private KeyType keyType;

  /** 난이도(EZ, NM, HD, SHD) */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MusicDifficulty difficulty;

  /** 시리즈(1ST, 7TH, CV, TT...) 카테고리 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MusicCategory category;

  /** 음원 레벨 */
  private int level;

  /** 음원 서열 */
  private int rank;

  /** 음원 가용 최고 점수 */
  private int bestScore;

  /** 음원 노트 수 합계 */
  private int totalNote;

  @JsonIgnore
  @ApiModelProperty(hidden = true)
  @OneToMany(mappedBy = "music")
  private List<Record> recordList = new ArrayList<>();

  /** 음원 추가 시각 */
  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime addTime;

  /** etc */
  @Lob
  private String description;

}
