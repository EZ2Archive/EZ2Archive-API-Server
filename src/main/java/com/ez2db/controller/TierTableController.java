package com.ez2db.controller;

import com.ez2db.common.aspect.RequiredToken;
import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.entity.KeyType;
import com.ez2db.entity.Tier;
import com.ez2db.service.TierTableService;
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

  @RequestMapping(method = RequestMethod.GET, value="/info/{keyType}")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> tierInfoGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  /**
   * Top 50개 조회
   */
  @RequestMapping(method = RequestMethod.GET, value="/list/{keyType}")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> tierListGet(@ApiIgnore JwtToken token, @PathVariable KeyType keyType)
  {
    final String userId = tokenProvider.getIdFromToken(token);

    List<Tier> tierList = tierTableService.getTierListByUserIdAndKeyType(userId, keyType);

    return ResponseEntity.ok().body(
      CommonResponse.success(tierList)
    );
  }
}
