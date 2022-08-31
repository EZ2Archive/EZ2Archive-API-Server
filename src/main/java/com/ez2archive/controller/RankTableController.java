package com.ez2archive.controller;

import com.ez2archive.common.aspect.RequiredToken;
import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.service.MusicInfoService;
import com.ez2archive.service.RankSurveyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankTableController
{
  private final MusicInfoService musicInfoService;
  private final RankSurveyService rankSurveyService;

  private final TokenProvider<String, JwtToken> tokenProvider;

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

  @Operation(summary = "서열표 점수 입력")
  @RequestMapping(method = RequestMethod.POST, value = "/save")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> rankSavePost(@ApiIgnore JwtToken token, @RequestParam Long musicInfoId, @RequestParam int point)
  {
    String userId = tokenProvider.getIdFromToken(token);

    rankSurveyService.save(userId, musicInfoId, point);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }


}
