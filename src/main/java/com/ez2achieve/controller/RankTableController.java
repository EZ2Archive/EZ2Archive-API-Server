package com.ez2achieve.controller;

import com.ez2achieve.common.response.CommonResponse;
import com.ez2achieve.entity.KeyType;
import com.ez2achieve.entity.MusicInfo;
import com.ez2achieve.service.MusicInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankTableController
{
  private final MusicInfoService musicInfoService;

  @Operation(summary = "서열표 목록 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/{keyType}")
  public ResponseEntity<CommonResponse<List<MusicInfo>>> rankGet(@PathVariable KeyType keyType)
  {
    List<MusicInfo> musicInfoList = musicInfoService.getRankTableListByKeyType(keyType);

    return ResponseEntity.ok().body(
      CommonResponse.success(musicInfoList)
    );
  }

  @Operation(summary = "서열표 목록 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/{keyType}/{level}")
  public ResponseEntity<CommonResponse<List<MusicInfo>>> rankGet(@PathVariable KeyType keyType, @PathVariable int level)
  {
    List<MusicInfo> musicInfoList = musicInfoService.getRankTableListByKeyTypeAndLevel(keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(musicInfoList)
    );
  }


}
