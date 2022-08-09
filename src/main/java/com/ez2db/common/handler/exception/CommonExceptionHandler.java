package com.ez2db.common.handler.exception;

import com.ez2db.common.exception.auth.AuthenticationException;
import com.ez2db.common.exception.auth.AuthorizationException;
import com.ez2db.common.exception.business.BadRequestException;
import com.ez2db.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler
{
  /**
   * 인가 예외 핸들러 unauthorized
   * @param e
   * @return
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<CommonResponse<?>> handlerAuthenticationException(AuthenticationException e)
  {
    log.debug(e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      CommonResponse.failed(e)
    );
  }

  /**
   * 권한 예외 핸들러 forbidden
   * @param e
   * @return
   */
  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<CommonResponse<?>> handlerAuthorizationException(AuthorizationException e)
  {
    log.debug(e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
      CommonResponse.failed(e)
    );
  }

  /**
   * 잘못된 요청 예외 핸들러 badRequest
   * @param e
   * @return
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<CommonResponse<?>> handlerBadRequestException(BadRequestException e)
  {
    log.debug(e.getMessage(), e);
    return ResponseEntity.badRequest().body(
      CommonResponse.failed(e)
    );
  }

  /**
   * 통상 서버오류 예외 핸들러 internalServerError
   * @param e
   * @return
   */
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<CommonResponse<?>> handlerCommonException(Throwable e)
  {
    log.error(e.getMessage(), e);
    return ResponseEntity.internalServerError().body(
      CommonResponse.failed(e)
    );
  }
}
