package com.ez2achieve.controller;

import com.ez2achieve.common.aspect.RequiredAuthority;
import com.ez2achieve.common.response.CommonResponse;
import com.ez2achieve.common.swagger.ApiPageable;
import com.ez2achieve.entity.MemberAuthority;
import com.ez2achieve.entity.Notice;
import com.ez2achieve.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController
{
  private final NoticeService noticeService;

  @Operation(summary = "공지사항 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<CommonResponse<Notice>> listGet(@PathVariable Long id)
  {
    Notice notice = noticeService.findNoticeById(id);

    return ResponseEntity.ok().body(
      CommonResponse.success(notice)
    );
  }

  @Operation(summary = "공지사항 목록 페이징 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/list")
  @ApiPageable
  public ResponseEntity<CommonResponse<?>> listGet(@RequestParam(required = false, defaultValue = "") String title, @ApiIgnore Pageable pageable)
  {

    Page<Notice> noticeList = noticeService.findNoticesByTitle(title, pageable);

    return ResponseEntity.ok().body(
      CommonResponse.success(noticeList.getContent())
    );
  }

  @Operation(summary = "Required = [Token, authority.ADMIN] 공지사항 등록")
  @RequestMapping(method = RequestMethod.POST, value = "/save")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  public ResponseEntity<CommonResponse<?>> savePost(@RequestBody Notice notice)
  {
    Long createdNoticeId = noticeService.save(notice);

    URI createdLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/notice/" + createdNoticeId)
      .build().toUri();

    return ResponseEntity.created(createdLocation).body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "Required = [Token, authority.ADMIN] 공지사항 수정")
  @RequestMapping(method = RequestMethod.PATCH, value = "/update")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  public ResponseEntity<CommonResponse<?>> updatePut(@RequestBody Notice notice)
  {
    noticeService.update(notice);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "Required = [Token, authority.ADMIN] 공지사항 삭제")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  public ResponseEntity<CommonResponse<?>> updatePut(@PathVariable Long id)
  {
    noticeService.delete(id);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }
}
