package com.ez2db.controller;

import com.ez2db.common.aspect.RequiredToken;
import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.vo.AchieveVO;
import com.ez2db.entity.KeyType;
import com.ez2db.vo.OverallVO;
import com.ez2db.entity.RecordDetail;
import com.ez2db.service.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 성과표 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/achievement")
public class AchievementController
{
  private final AchievementService achievementService;

  private final TokenProvider<String, JwtToken> tokenProvider;

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 목록 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/list/{keyType}/{level}")
  @RequiredToken
  public ResponseEntity<CommonResponse<List<AchieveVO>>> achievementListGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType, @PathVariable int level)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    final List<AchieveVO> achievementList = achievementService.findAchievementList(userId, keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(achievementList)
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 종합 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/overall/{keyType}/{level}")
  @RequiredToken
  public ResponseEntity<CommonResponse<OverallVO>> achievementOverallGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType, @PathVariable int level)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    final OverallVO overallVO = achievementService.findAchievementOverall(userId, keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(overallVO)
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 점수 히스토리 조회")
  @RequestMapping(method = RequestMethod.GET, value="/history/{musicInfoId}")
  @RequiredToken
  public ResponseEntity<CommonResponse<List<RecordDetail>>> achievementHistoryGet(@ApiIgnore JwtToken token, @PathVariable Long musicInfoId)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    List<RecordDetail> recordDetailList = achievementService.findAchievementHistory(userId, musicInfoId);

    return ResponseEntity.ok().body(
      CommonResponse.success(recordDetailList)
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 점수 기록 등록")
  @RequestMapping(method = RequestMethod.POST, value="/save")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> achievementSavePost(@ApiIgnore JwtToken token, @RequestBody RecordDetail recordDetail)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    achievementService.saveAchievementRecord(userId, recordDetail);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

}
