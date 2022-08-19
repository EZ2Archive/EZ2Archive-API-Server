package com.ez2db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Getter @Setter
public class File
{
  @Id @GeneratedValue
  @Column(name = "file_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String fileUUID;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false)
  private String fileOriginName;

  @Column(nullable = false)
  private String contentType;

  @Column(nullable = false)
  private long size;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime addTime;

  public File()
  {
  }
  @Builder
  public File(Long id, String fileUUID, String fileName, String fileOriginName, String contentType, long size, LocalDateTime addTime)
  {
    this.id = id;
    this.fileUUID = fileUUID;
    this.fileName = fileName;
    this.fileOriginName = fileOriginName;
    this.contentType = contentType;
    this.size = size;
    this.addTime = addTime;
  }
}
