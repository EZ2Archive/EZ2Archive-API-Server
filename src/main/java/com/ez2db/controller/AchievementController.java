package com.ez2db.controller;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.RequiredToken;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.entity.AchieveVO;
import com.ez2db.entity.KeyType;
import com.ez2db.entity.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 성과표 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/musicInfo")
public class AchievementController
{
  private final AchievementService recordService;

  private final TokenProvider<String, JwtToken> tokenProvider;

  @Operation(summary = "[T] 성과표 목록 조회")
  @RequestMapping(method = RequestMethod.GET, value = "/list/{keyType}/{level}")
  @RequiredToken
  public ResponseEntity<CommonResponse<List<AchieveVO>>> musicInfoListGet(JwtToken token, @PathVariable KeyType keyType, @PathVariable int level)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    final List<AchieveVO> achievementList = recordService.findAchievementList(userId, keyType, level);

    return ResponseEntity.ok().body(
      CommonResponse.success(achievementList)
    );
  }

}
