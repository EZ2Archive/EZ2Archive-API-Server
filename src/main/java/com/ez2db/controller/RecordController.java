package com.ez2db.controller;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.aspect.RequiredToken;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.entity.Record;
import com.ez2db.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController
{
  private final RecordService recordService;

  private final TokenProvider<String, JwtToken> tokenProvider;

  @Operation(summary = "[T] 점수 저장")
  @RequestMapping(method = RequestMethod.POST, value = "/save/{musicInfoId}")
  @RequiredToken
  public ResponseEntity<CommonResponse<?>> savePost(@ApiIgnore JwtToken token, @RequestBody Record record, @PathVariable Long musicInfoId)
  {
    String userId = tokenProvider.getIdFromToken(token);

    Long recordId = recordService.save(userId, musicInfoId, record);

    return ResponseEntity.created(URI.create("/record/" + recordId)).body(
      CommonResponse.success()
    );
  }
}
