package com.ez2archive.common.auth;

import com.ez2archive.common.exception.auth.AuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest
{

  private static final String AUTHORIZATION_TYPE = "Bearer";

  // Don't modify this value
  private static final String TEST_SECRET_KEY = "ez2archive";

  private TokenProvider<String, JwtToken> tokenProvider;

  @BeforeEach
  void init()
  {
    tokenProvider = new JwtTokenProvider(TEST_SECRET_KEY);
  }

  @DisplayName("getToken null 케이스")
  @Test
  void getTokenNull()
  {
    // Given
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    // When
    Mockito.when(request.getHeader("Authorization"))
      .thenReturn(null);

    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.getToken(request));
  }

  @DisplayName("getToken only type  케이스")
  @Test
  void getTokenOnlyType()
  {
    // Given
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    // When
    Mockito.when(request.getHeader("Authorization"))
      .thenReturn("Bearer ");

    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.getToken(request));
  }

  @DisplayName("getToken only token  케이스")
  @Test
  void getTokenOnlyToken()
  {
    // Given
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    // When
    Mockito.when(request.getHeader("Authorization"))
      .thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM2NjcwODAsImV4cCI6MTY2NDI3MTg4MH0.iST4WK71oeY2hc5LRYus5q2xYspqWlhcrlbFeuukryEQpF14svQpMslIx_EOIU0GvKS_i73V0pOkyQzXltFcUw");

    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.getToken(request));
  }

  @DisplayName("getToken 성공 케이스")
  @Test
  void getToken()
  {
    // Given
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    // When
    final String expected = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM2NjcwODAsImV4cCI6MTY2NDI3MTg4MH0.iST4WK71oeY2hc5LRYus5q2xYspqWlhcrlbFeuukryEQpF14svQpMslIx_EOIU0GvKS_i73V0pOkyQzXltFcUw";
    Mockito.when(request.getHeader("Authorization"))
      .thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM2NjcwODAsImV4cCI6MTY2NDI3MTg4MH0.iST4WK71oeY2hc5LRYus5q2xYspqWlhcrlbFeuukryEQpF14svQpMslIx_EOIU0GvKS_i73V0pOkyQzXltFcUw");

    // Then
    Assertions.assertEquals( expected, tokenProvider.getToken(request).getAccessToken() );
  }

  @DisplayName("엑세스 토큰 발급 userId null 케이스")
  @Test
  void issueAccessTokenWithUserIdNull()
  {
    // Given
    final String userId = null;

    // When
    // Then
    Assertions.assertThrows(UnsupportedOperationException.class, () -> tokenProvider.issueAccessToken(userId));
  }

  @DisplayName("엑세스 토큰 발급 userId empty 케이스")
  @Test
  void issueAccessTokenWithUserIdEmpty()
  {
    // Given
    final String userId = "";

    // When
    // Then
    Assertions.assertThrows(UnsupportedOperationException.class, () -> tokenProvider.issueAccessToken(userId));
  }

  @DisplayName("엑세스 토큰 발급 성공 케이스")
  @Test
  void issueAccessToken()
  {
    // Given
    final String userId = "ez2archive";

    // When
    final String actual = tokenProvider.issueAccessToken(userId);

    // Then
    Assertions.assertTrue(
      actual != null
      && !actual.isEmpty()
    );
  }

  @DisplayName("리프레시 토큰 userId null 케이스")
  @Test
  void issueRefreshTokenWithUserIdNull()
  {
    // Given
    final String userId = null;

    // When
    // Then
    Assertions.assertThrows(UnsupportedOperationException.class, () -> tokenProvider.issueRefreshToken(userId));
  }

  @DisplayName("리프레시 토큰 userId null 케이스")
  @Test
  void issueRefreshTokenWithUserIdEmpty()
  {
    // Given
    final String userId = "";

    // When
    // Then
    Assertions.assertThrows(UnsupportedOperationException.class, () -> tokenProvider.issueRefreshToken(userId));
  }

  @DisplayName("리프레시 토큰 발급 성공 케이스")
  @Test
  void issueRefreshToken()
  {
    // Given
    final String userId = "ez2archive";

    // When
    final String actual = tokenProvider.issueRefreshToken(userId);

    // Then
    Assertions.assertTrue(
      actual != null
        && !actual.isEmpty()
    );
  }

  @DisplayName("토큰으로부터 아이디 조회 케이스")
  @Test
  void getIdFromToken()
  {
    // Given
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    // When
    Mockito.when(request.getHeader("Authorization"))
      .thenReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlejJhcmNoaXZlIiwiaWF0IjoxNjYzNjc3MTAzLCJleHAiOjE2NjQyODE5MDN9.OICBSfLWwHhNzfL7JtZH9iWc49Hll-jj2vF31jAHbC70JpFr9orVMoqJInyOpoUEk9dlWhHW-wEfyonygHZBfg");
    final JwtToken jwtToken = tokenProvider.getToken(request);

    final String expected = "ez2archive";
    final String actual = tokenProvider.getIdFromToken(jwtToken);

    // Then
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("토큰 유효성 만료 케이스")
  @Test
  void isValidWithExpiredToken()
  {
    // Given
    final JwtToken token = JwtToken.builder()
      .type(AUTHORIZATION_TYPE)
      .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM3MjEzMDMsImV4cCI6MTY2MzcyMTM2M30.F21zY3xk29R7n5uK7WuPDeOSGnJAHdo7xKgla2nl-8755g6NxSSuHO973gkw_PSVSsAU3o7PoasvIb2LD5CDxQ")
      .build();

    // When
    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.isValid(token));
  }

  @DisplayName("토큰 유효성 조작 케이스")
  @Test
  void isValidWithRiggedToken()
  {
    // Given
    final JwtToken token = JwtToken.builder()
      .type(AUTHORIZATION_TYPE)
      .accessToken("eyJhbGciOiJIUzUxMiJ.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM3MjEzMDMsImV4cCI6MTY2MzcyMTM2M30.F21zY3xk29R7n5uK7WuPDeOSGnJAHdo7xKgla2nl-8755g6NxSSuHO973gkw_PSVSsAU3o7PoasvIb2LD5CDxQ")
      .build();

    // When
    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.isValid(token));
  }

  @DisplayName("토큰 유효성 서명실패 케이스")
  @Test
  void isValidWithUnknownToken()
  {
    // Given
    final JwtToken token = JwtToken.builder()
      .type(AUTHORIZATION_TYPE)
      .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MDEiLCJpYXQiOjE2NjM3MjE1ODYsImV4cCI6MTY2NDMyNjM4Nn0.C8r1PstgkbaOh8_DZklDtrZA94WBGT2kZBE-tgxCJ2PHTipkLnlzW5S6jNIhg7qCtrfgcJXAe-tlDf3IksVwkQ")
      .build();

    // When
    // Then
    Assertions.assertThrows(AuthenticationException.class, () -> tokenProvider.isValid(token));
  }

  @DisplayName("토큰 유효성 성공 케이스")
  @Test
  void isValid()
  {
    // Given
    final String userId = "test01";
    final JwtToken token = JwtToken.builder()
      .type(AUTHORIZATION_TYPE)
      .accessToken(tokenProvider.issueAccessToken(userId))
      .build();

    // When
    boolean valid = tokenProvider.isValid(token);

    // Then
    Assertions.assertTrue(valid);
  }
}