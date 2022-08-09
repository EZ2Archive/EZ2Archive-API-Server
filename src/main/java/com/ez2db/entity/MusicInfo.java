package com.ez2db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 음원 정보 엔티티 클래스
 */
@Entity
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

  /** 음원 추가 시각 */
  @Column(nullable = false)
  private LocalDateTime addTime;

  /** etc */
  @Lob
  private String description;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getArtist()
  {
    return artist;
  }

  public void setArtist(String artist)
  {
    this.artist = artist;
  }

  public File getImageFile()
  {
    return imageFile;
  }

  public void setImageFile(File imageFile)
  {
    this.imageFile = imageFile;
  }

  public KeyType getKeyType()
  {
    return keyType;
  }

  public void setKeyType(KeyType keyType)
  {
    this.keyType = keyType;
  }

  public MusicDifficulty getDifficulty()
  {
    return difficulty;
  }

  public void setDifficulty(MusicDifficulty difficulty)
  {
    this.difficulty = difficulty;
  }

  public MusicCategory getCategory()
  {
    return category;
  }

  public void setCategory(MusicCategory category)
  {
    this.category = category;
  }

  public int getLevel()
  {
    return level;
  }

  public void setLevel(int level)
  {
    this.level = level;
  }

  public int getRank()
  {
    return rank;
  }

  public void setRank(int rank)
  {
    this.rank = rank;
  }

  public int getBestScore()
  {
    return bestScore;
  }

  public void setBestScore(int bestScore)
  {
    this.bestScore = bestScore;
  }

  public LocalDateTime getAddTime()
  {
    return addTime;
  }

  public void setAddTime(LocalDateTime addTime)
  {
    this.addTime = addTime;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }
}
