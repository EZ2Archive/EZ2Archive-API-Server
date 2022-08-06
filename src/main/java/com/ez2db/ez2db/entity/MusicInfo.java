package com.ez2db.ez2db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MusicInfo
{
  @Id
  @GeneratedValue
  @Column(name = "music_info_id")
  private Long id;

  private String name;

  private String imagePath;

  private int ez;

  private int nm;

  private int hd;

  private int shd;

  @Enumerated(EnumType.STRING)
  private MusicCategory category;

  private LocalDateTime addTime;
}
