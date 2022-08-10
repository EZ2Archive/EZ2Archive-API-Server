package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class RecordHistory
{
  @Id @GeneratedValue
  @Column(name = "record_history_id")
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id")
  private Record record;

  @Column(nullable = false)
  private LocalDateTime addTime;

}
