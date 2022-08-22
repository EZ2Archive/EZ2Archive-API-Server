package com.ez2db.controller;

import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.entity.File;
import com.ez2db.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
@ApiIgnore
public class CommonFileController
{
  @Value("${spring.servlet.multipart.location}")
  private String FILE_PATH;
  private final FileService fileService;

  @Operation(summary = "파일 업로드")
  @RequestMapping(method = RequestMethod.POST, value = "/upload")
  public ResponseEntity<CommonResponse<?>> uploadPost(@RequestPart List<MultipartFile> multipartFiles) throws IOException
  {
    fileService.saveAll(multipartFiles);

    // TODO --> created URI 생성
    return ResponseEntity.created(null).body(
      CommonResponse.success()
    );
  }

  @ApiIgnore
  @Operation(summary = "Dev Only) 파일 전체목록 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/list")
  @ResponseBody
  public ResponseEntity<CommonResponse<List<String>>> listGet()
  {
    List<File> fileList = fileService.getAllFiles();

    List<String> resultList = new ArrayList<>();

    for ( File file : fileList )
    {
      resultList.add(
        ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("/files/download/")
          .path(file.getFileUUID())
          .toUriString()
      );
    }

    return ResponseEntity.ok().body(
      CommonResponse.success(resultList)
    );
  }

  @Operation(summary = "파일 다운로드")
  @RequestMapping(method = RequestMethod.GET, value = "/download/{fileUUID}")
  public ResponseEntity<Resource> downloadGet(@PathVariable String fileUUID) throws IOException
  {
    final Resource resource;
    final File file = fileService.findByFileUUID(fileUUID);

    try
    {
      resource = new InputStreamResource(Files.newInputStream(Path.of(FILE_PATH + "/" + file.getFileName())));
    }
    catch( NoSuchFileException e )
    {
      throw new ResourceNotFoundException("해당 파일을 찾을 수 없습니다: " + file.getFileName());
    }

    return ResponseEntity.ok()
      .headers(headers -> {
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", file.getFileOriginName()));
        headers.add(HttpHeaders.CONTENT_TYPE, file.getContentType());
      })
      .body(resource);
  }
}
