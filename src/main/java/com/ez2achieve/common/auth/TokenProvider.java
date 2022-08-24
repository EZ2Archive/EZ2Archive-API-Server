package com.ez2achieve.common.auth;

import javax.servlet.http.HttpServletRequest;

public interface TokenProvider<ID, TOKEN>
{
  /**
   * 클라이언트의 HTTP 요청으로부터 토큰 추출
   * @param request
   * @return
   */
  TOKEN getToken(HttpServletRequest request);

  /**
   * 식별자를 통한 토큰 발급(생성)
   * @param id
   * @return
   */
  TOKEN issue(ID id);

  /**
   * 토큰으로 부터 식별자 추출 (반드시 isValid()를 통해 유효성을 확인하고 호출할 것)
   * @param token
   * @return
   */
  ID getIdFromToken(TOKEN token);

  /**
   * 토큰의 유효성 여부 검증
   * @param token
   * @return
   */
  boolean isValid(TOKEN token);
}
