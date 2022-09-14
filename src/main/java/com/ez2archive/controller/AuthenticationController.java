package com.ez2archive.controller;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.dto.auth.RequestLoginDTO;
import com.ez2archive.dto.auth.RequestSignUpDTO;
import com.ez2archive.service.LoginService;
import com.ez2archive.service.VerifyService;
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
  private final LoginService loginService;
  private final VerifyService verifyService;

  @Operation(summary = "로그인")
  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public ResponseEntity<CommonResponse<JwtToken>> loginPost(@ApiIgnore HttpServletRequest request, @RequestBody RequestLoginDTO requestLoginDTO)
  {
    // 헤더에 토큰이 존재하지 않거나 토큰이 유효하지 않은 경우 로그인 시도
    return ResponseEntity.ok().body(
      CommonResponse.success( loginService.login(request, requestLoginDTO) )
    );
  }

  @Operation(summary = "회원가입")
  @RequestMapping(method = RequestMethod.POST, value = "/signUp")
  public ResponseEntity<CommonResponse<?>> signUpPost(@RequestBody RequestSignUpDTO requestSignUpDTO)
  {
    loginService.sinUp(requestSignUpDTO);
    verifyService.sendVerifyMail(requestSignUpDTO.getEmail());

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

  @Operation(summary = "패스워드 재설정 이메일 발송")
  @RequestMapping(method = RequestMethod.POST, value = "/passwordre")
  public ResponseEntity<CommonResponse<?>> passwordRePost(@RequestParam String userId, @RequestParam String email)
  {
    loginService.passwordRe(userId, email);

    // TODO --> 패스워드 재설정 이메일 발송 WIP
    throw new UnsupportedOperationException();
  }

  @Operation(summary = "[Required = [Refresh Token] 엑세스 토큰 연장 요청")
  @RequestMapping(method = RequestMethod.GET, value = "/poll")
  public ResponseEntity<CommonResponse<String>> pollGet(@ApiIgnore JwtToken jwtToken)
  {
    // TODO --> 엑세스 토큰 연장 요청 WIP
    throw new UnsupportedOperationException();
  }
}
