package com.ez2db.controller;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.response.CommonResponse;
import com.ez2db.entity.Member;
import com.ez2db.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController
{
  private final LoginService loginService;

  @Operation(summary = "로그인")
  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public ResponseEntity<CommonResponse<JwtToken>> loginPost(@RequestParam String userId, @RequestParam String password)
  {
    final JwtToken issueToken = loginService.login(userId, password);

    return ResponseEntity.ok().body(
      CommonResponse.success(issueToken)
    );
  }

  @Operation(summary = "회원가입")
  @RequestMapping(method = RequestMethod.POST, value = "/signUp")
  public ResponseEntity<CommonResponse<?>> signUpPost(@RequestBody Member member)
  {
    loginService.sinUp(member);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "아이디 중복 체크")
  @RequestMapping(method = RequestMethod.GET, value = "/idCheck")
  public ResponseEntity<CommonResponse<Boolean>> idCheckGet(@RequestParam String userId)
  {
    final boolean isExist = loginService.isExistUserId(userId);

    return ResponseEntity.ok().body(
      CommonResponse.<Boolean>builder()
        .message((isExist) ? "exist" : "notExist")
        .data(isExist)
        .build()
    );
  }
}
