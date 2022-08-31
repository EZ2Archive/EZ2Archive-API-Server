package com.ez2archive.controller;

import com.ez2archive.common.aspect.RequiredToken;
import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.TierPoint;
import com.ez2archive.service.TierTableService;
import com.ez2archive.dto.tier.TierInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tier")
public class TierTableController
{
  private final TierTableService tierTableService;
  private final TokenProvider<String, JwtToken> tokenProvider;

  @Operation(summary = "Required = [Token, authority.REGULAR] 티어 정보 조회")
  @RequestMapping(method = RequestMethod.GET, value="/info/{keyType}")
  @RequiredToken
  public ResponseEntity<CommonResponse<TierInfoDTO>> tierInfoGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    TierInfoDTO tierInfoDTO = tierTableService.findTierInfo(userId, keyType);

    return ResponseEntity.ok().body(
      CommonResponse.success(tierInfoDTO)
    );
  }

  @Operation(summary = "Required = [Token, authority.REGULAR] 최고 포인트 50개 기록 조회")
  @RequestMapping(method = RequestMethod.GET, value="/list/{keyType}")
  @RequiredToken
  public ResponseEntity<CommonResponse<List<TierPoint>>> tierListGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    List<TierPoint> tierPointList = tierTableService.getTierListByUserIdAndKeyType(userId, keyType);

    return ResponseEntity.ok().body(
      CommonResponse.success(tierPointList)
    );
  }
}
