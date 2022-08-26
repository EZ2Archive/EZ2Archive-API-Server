package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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
@SuppressWarnings("ALL")
@Entity
@Getter @Setter
public class MusicInfo
{
  /** 음원 고유 아이디 */
  @Id @GeneratedValue
  @Column(name = "music_info_id")
  @ApiModelProperty(hidden = true)
  private Long id;

  /** 음원 명 */
  @Column(nullable = false)
  private String name;

  /** 아티스트 명 */
  private String artist;

  /**
   * 2022.08.24 이미지 파일 업로드/다운로드 없이 프론트-엔드에서 이미지를 정적으로 처리하기로 결정.
   */
  /** 디스크 이미지 파일 정보 */
  /*
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "file_id")
  @JsonIgnoreProperties("hibernateLazyInitializer")
  private File imageFile;
  */

  /** 키(4K, 5K, 6K, 8K) 타입 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private KeyType keyType;

  /** 난이도(EZ, NM, HD, SHD) */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MusicDifficulty difficulty;

  /** 시리즈(1ST, 7TH, CV, TT...) 카테고리 */
  private String category;

  /** 음원 레벨 */
  private int level;

  /** 음원 서열 */
  @Column(name = "ranks")
  private int rank;

  /** 음원 가용 최고 점수 */
  @ApiModelProperty(hidden = true)
  private int bestScore;

  /** 음원 노트 수 합계 */
  private int totalNote;

  @OneToMany(mappedBy = "music")
  @JsonIgnore
  @ApiModelProperty(hidden = true)
  private List<Record> recordList = new ArrayList<>();

  /** 음원 추가 시각 */
  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @ApiModelProperty(hidden = true)
  private LocalDateTime addTime;

  /** etc */
  @Lob
  private String description;

}
