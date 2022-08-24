package com.ez2archive.service;

import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.entity.File;
import com.ez2archive.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService
{
  private final FileRepository fileRepository;

  public void save(MultipartFile multipartFile) throws IOException
  {
    final String fileOriginalName = multipartFile.getOriginalFilename();
    final String fileUUID         = UUID.randomUUID().toString();
    final String fileName         = fileUUID + getExt(Objects.requireNonNull(fileOriginalName));

    File file = File.builder()
      .fileUUID(fileUUID)
      .fileOriginName(fileOriginalName)
      .fileName(fileName)
      .contentType(multipartFile.getContentType())
      .size(multipartFile.getSize())
      .addTime(LocalDateTime.now())
      .build();

    fileRepository.save(file);

    multipartFile.transferTo(new java.io.File(fileName));
  }

  public void saveAll(List<MultipartFile> multipartFiles) throws IOException
  {
    for ( MultipartFile multipartFile : multipartFiles )
    {
      this.save(multipartFile);
    }
  }

  public File findByFileUUID(String fileUUID)
  {
    Optional<File> findFile = fileRepository.findFileByFileUUID(fileUUID);

    if(findFile.isEmpty()) throw new ResourceNotFoundException("해당 파일 정보를 찾을 수 없습니다.");

    return findFile.get();
  }

  public List<File> getAllFiles()
  {
    return fileRepository.findAll();
  }

  private static String getExt(String fileName)
  {
    return fileName.substring(fileName.lastIndexOf("."));
  }

}
