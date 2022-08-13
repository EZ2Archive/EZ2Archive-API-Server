package com.ez2db.controller;

import com.ez2db.common.handler.file.FileHandler;
import com.ez2db.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
@ApiIgnore
public class CommonFileController
{
  private final FileHandler<MultipartFile> fileHandler;

  @RequestMapping(method = RequestMethod.POST, value = "")
  @ResponseBody
  public ResponseEntity<CommonResponse<?>> uploadPost(@RequestPart List<MultipartFile> multipartFiles) throws IOException
  {
    fileHandler.saveAll(multipartFiles);

    return ResponseEntity.created(null).body(
      CommonResponse.builder()
        .message("success")
        .build()
    );
  }

  @RequestMapping(method = RequestMethod.GET, value = "/images/{fileUUID}")
  @ResponseBody
  public Resource uploadImgPost(@PathVariable String fileUUID) throws IOException
  {
    // TODO --> GET 이미지 리소스
    return new UrlResource("file:/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/images")
  @ResponseBody
  public ResponseEntity<CommonResponse<?>> uploadImgPost(@RequestPart List<MultipartFile> multipartFiles) throws IOException
  {
    fileHandler.saveAll(multipartFiles);

    return ResponseEntity.created(null).body(
      CommonResponse.builder()
        .message("success")
        .build()
    );
  }

  @RequestMapping(method = RequestMethod.GET, value = "/image/{fileUUID}")
  @ResponseBody
  public Resource imageGet(@PathVariable String fileUUID)
  {
//    return new UrlResource("file:" + file.get);
    throw new UnsupportedOperationException("미구현");
  }


  @RequestMapping(method = RequestMethod.GET, value = "/download/{fileUUID}")
  @ResponseBody
  public ResponseEntity<Resource> downloadGet(@PathVariable String fileUUID) throws MalformedURLException
  {
    final String fileName = "";
    final String contentDisposition = "attachment; filename=\"" + fileName + "\"";
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
      .body(new UrlResource(""));
  }
}
