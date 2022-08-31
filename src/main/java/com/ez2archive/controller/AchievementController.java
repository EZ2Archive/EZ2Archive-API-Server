package com.ez2archive.controller;

import com.ez2archive.common.aspect.RequiredToken;
import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.RecordDetail;
import com.ez2archive.service.AchievementService;
import com.ez2archive.dto.achieve.AchieveDTO;
import com.ez2archive.dto.achieve.OverallDTO;
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
  public ResponseEntity<CommonResponse<List<AchieveDTO>>> achievementListGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType, @PathVariable int level)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    final List<AchieveDTO> achievementList = achievementService.findAchievementList(userId, keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(achievementList)
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 종합 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/overall/{keyType}/{level}")
  @RequiredToken
  public ResponseEntity<CommonResponse<OverallDTO>> achievementOverallGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType, @PathVariable int level)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    final OverallDTO overallDTO = achievementService.findAchievementOverall(userId, keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(overallDTO)
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

    return ResponseEntity.created(null).body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 성과표 점수 기록 삭제")
  @RequestMapping(method = RequestMethod.DELETE, value="/delete")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> achievementDeleteRecordDelete(@ApiIgnore JwtToken token, @RequestParam Long recordDetailId)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    achievementService.deleteRecord(userId, recordDetailId);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

}
