package com.ez2archive.controller;

import com.ez2archive.common.aspect.RequiredAuthority;
import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.common.swagger.ApiPageable;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.MemberAuthority;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.service.MusicInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musicInfo")
public class MusicInfoController
{
  private final MusicInfoService musicInfoService;

  @Operation(summary = "Required = [Token, authority.ADMIN] 신규 음원 정보 등록")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  @RequestMapping(method = RequestMethod.POST, value = "/save")
  public ResponseEntity<CommonResponse<?>> saveMusicInfoPost(@RequestBody MusicInfo musicInfo)
  {
    Long musicInfoId = musicInfoService.save(musicInfo);

    URI createdLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/musicInfo/" + musicInfoId)
      .build().toUri();

    return ResponseEntity.created(createdLocation).body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "음원 정보 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<CommonResponse<MusicInfo>> musicInfoGet(@PathVariable Long id)
  {
    MusicInfo musicInfo = musicInfoService.findById(id);

    return ResponseEntity.ok().body(
      CommonResponse.success(musicInfo)
    );
  }

  @Operation(summary = "음원 정보 페이징 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/list/{keyType}")
  @ApiPageable
  public ResponseEntity<CommonResponse<List<MusicInfo>>> musicInfoListGet(@PathVariable KeyType keyType, @RequestParam(required = false, defaultValue = "") String name, @ApiIgnore Pageable pageable)
  {
    Page<MusicInfo> musicInfoList = musicInfoService.findByKeyTypeAndNameContaining(keyType, name, pageable);

    return ResponseEntity.ok().body(
      CommonResponse.success(musicInfoList.getContent())
    );
  }

  @Operation(summary = "Required = [Token, authority.ADMIN] 음원 정보 수정")
  @RequestMapping(method = RequestMethod.PUT, value = "/update")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  public ResponseEntity<CommonResponse<?>> musicInfoPut(@RequestBody MusicInfo musicInfo)
  {
    musicInfoService.update(musicInfo);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "Required = [Token, authority.ADMIN] 음원 정보 삭제")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{musicInfoId}")
  @RequiredAuthority(authority = MemberAuthority.ADMIN)
  public ResponseEntity<CommonResponse<?>> musicInfoDelete(@PathVariable Long musicInfoId)
  {
    // 음원을 삭제해도 기록은 삭제되지 않음
    musicInfoService.delete(musicInfoId);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }
}
