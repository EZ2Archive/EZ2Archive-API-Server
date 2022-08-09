package com.ez2db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
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
  private LocalDateTime addTime;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getFileName()
  {
    return fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public String getFileOriginName()
  {
    return fileOriginName;
  }

  public void setFileOriginName(String fileOriginName)
  {
    this.fileOriginName = fileOriginName;
  }

  public String getFilePath()
  {
    return filePath;
  }

  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }

  public int getSize()
  {
    return size;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public LocalDateTime getAddTime()
  {
    return addTime;
  }

  public void setAddTime(LocalDateTime addTime)
  {
    this.addTime = addTime;
  }
}
