package com.ez2db.common.handler.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CommonFileHandler implements FileHandler<MultipartFile>
{
  public void upload(MultipartFile multipartFile)
  {
    String fileName = multipartFile.getOriginalFilename();
  }

  @Override
  public void save(MultipartFile multipartFile) throws IOException
  {
    String originalFilename = multipartFile.getOriginalFilename();
    String fileUUIDName     = UUID.randomUUID().toString() + getExt(originalFilename);

    multipartFile.transferTo(new File(fileUUIDName));

//    multipartFile.transferTo(new File());
  }

  @Override
  public String getFullPath(String fileName)
  {
    return null;
  }

  private static final String getExt(String fileName)
  {
    return fileName.substring(fileName.lastIndexOf("."));
  }

}
