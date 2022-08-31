package com.ez2archive.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Deprecated
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
@ApiIgnore
public class CommonFileController
{
  /**
   * 2022.08.24 이미지 파일 업로드/다운로드 없이 프론트-엔드에서 이미지를 정적으로 처리하기로 결정.
   */
  /*
  @Value("${spring.servlet.multipart.location}")
  private String FILE_PATH;
  private final FileService fileService;


  @Operation(summary = "파일 업로드")
  @RequestMapping(method = RequestMethod.POST, value = "/upload")
  public ResponseEntity<CommonResponse<?>> uploadPost(@RequestPart List<MultipartFile> multipartFiles) throws IOException
  {
    fileService.saveAll(multipartFiles);

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

    String fileName = new String(file.getFileOriginName().getBytes(), StandardCharsets.ISO_8859_1);

    return ResponseEntity.ok()
      .headers(headers -> {
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, file.getContentType());
      })
      .body(resource);
  }
  */
}
