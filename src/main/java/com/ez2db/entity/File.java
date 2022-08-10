package com.ez2db.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class File
{
  @Id @GeneratedValue
  @Column(name = "file_id")
  private Long id;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false)
  private String fileOriginName;

  @Column(nullable = false)
  private String filePath;

  @Column(nullable = false)
  private int size;

  @Column(nullable = false)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime addTime;

}
