package com.ez2achieve.controller;

import com.ez2achieve.common.auth.TokenProvider;
import com.ez2achieve.common.auth.JwtToken;
import com.ez2achieve.common.exception.auth.AuthenticationException;
import com.ez2achieve.common.response.CommonResponse;
import com.ez2achieve.entity.Member;
import com.ez2achieve.service.LoginService;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController
{
  private final TokenProvider<String, JwtToken> tokenProvider;
  private final LoginService loginService;

  @Operation(summary = "로그인")
  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public ResponseEntity<CommonResponse<JwtToken>> loginPost(@ApiIgnore HttpServletRequest request, @RequestParam String userId, @RequestParam String password)
  {
    JwtToken token;

    try
    {
      token = tokenProvider.getToken(request);

      tokenProvider.isValid(token);

      if( !userId.equals(tokenProvider.getIdFromToken(token)) ) throw new AuthenticationException("토큰의 사용자 아이디가 일치하지 않습니다.");
    }
    catch( AuthenticationException | JwtException e )
    {
      // 헤더에 토큰이 존재하지 않거나 토큰이 유효하지 않은 경우 로그인 시도
      token = loginService.login(request, userId, password);
    }

    return ResponseEntity.ok().body(
      CommonResponse.success(token)
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
