package com.ez2archive.controller;

import com.ez2archive.common.response.CommonResponse;
import com.ez2archive.dto.auth.RequestPasswordModifyDTO;
import com.ez2archive.service.VerifyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/verify")
public class VerifyController
{
  private final VerifyService verifyService;

  /**
   * <b>주의사항</b>
   * <p>
   *   - 이메일 인증 URL 경로와 동일하지 않을 경우 예외 발생<br>
   *   - '@see'에 기술된 메소드 URL 참조
   * </p>
   * @see com.ez2archive.controller.VerifyController#emailVerifyPatch(String)
   */
  @Operation(summary = "인증 이메일 발송 요청")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<CommonResponse<?>> sendMailPost(@RequestParam String email)
  {
    verifyService.sendVerifyMail(email);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "이메일 인증")
  @RequestMapping(method = RequestMethod.GET, value="/email/{key}")
  public ResponseEntity<CommonResponse<?>> emailVerifyPatch(@PathVariable String key)
  {
    verifyService.verifyEmail(key);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "패스워드 재설정 이메일 발송")
  @RequestMapping(method = RequestMethod.POST, value = "/password/request")
  public ResponseEntity<CommonResponse<?>> passwordRequest(@RequestParam String userId, @RequestParam String email)
  {
    verifyService.passwordRe(userId, email);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }

  @Operation(summary = "패스워드 재설정")
  @RequestMapping(method = RequestMethod.PATCH, value="/password/modify")
  public ResponseEntity<CommonResponse<?>> passwordModifyPatch(@RequestBody RequestPasswordModifyDTO dto)
  {
    verifyService.verifyPassword(dto);

    return ResponseEntity.ok().body(
      CommonResponse.success()
    );
  }
}
