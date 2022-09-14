package com.ez2archive.common.auth;

import com.ez2archive.common.exception.auth.AuthenticationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider<String, JwtToken>
{
  private final String secretKey;

  private final static int EXPIRE_ACCESS_TOKEN_AMOUNT = 10_080;

  private final static int EXPIRE_REFRESH_TOKEN_AMOUNT = 20_160; // 2 weeks

  private final static ChronoUnit EXPIRE_UNIT = ChronoUnit.MINUTES;

  @Override
  public JwtToken getToken(HttpServletRequest request)
  {
    // 요청 파라미터(-header 'Authorization: {type} {key}')
    final JwtToken result;

    final String [] auths;

    try
    {
      auths = request.getHeader("Authorization").split(" ");

      result = JwtToken.builder()
        .type(auths[0])
        .accessToken(auths[1])
        .build();
    }
    catch(NullPointerException | ArrayIndexOutOfBoundsException e)
    {
      throw new AuthenticationException("인증 타입 혹은 토큰 정보가 누락되었습니다.");
    }

    return result;
  }

  @Override
  public String issueAccessToken(String userId)
  {
    final LocalDateTime nowTime = LocalDateTime.now();
    final LocalDateTime accessExpireTime = nowTime.plus(EXPIRE_ACCESS_TOKEN_AMOUNT, EXPIRE_UNIT);

    return Jwts.builder()
      .signWith(SignatureAlgorithm.HS512, secretKey)
      .setSubject(userId)
      .setIssuedAt( Date.from(nowTime.atZone(ZoneId.systemDefault()).toInstant()) )
      .setExpiration( Date.from(accessExpireTime.atZone(ZoneId.systemDefault()).toInstant()) )
      .compact();
  }

  @Override
  public String issueRefreshToken(String userId)
  {
    final LocalDateTime nowTime = LocalDateTime.now();
    final LocalDateTime refreshExpireTime = nowTime.plus(EXPIRE_REFRESH_TOKEN_AMOUNT, EXPIRE_UNIT);

    return Jwts.builder()
      .signWith(SignatureAlgorithm.HS512, secretKey)
      .setSubject(userId)
      .setIssuedAt( Date.from(nowTime.atZone(ZoneId.systemDefault()).toInstant()) )
      .setExpiration( Date.from(refreshExpireTime.atZone(ZoneId.systemDefault()).toInstant()) )
      .compact();
  }

  @Override
  public String getIdFromToken(JwtToken token)
  {
    final Claims claims = Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token.getAccessToken())
      .getBody();

    return claims.getSubject();
  }

  @Override
  public boolean isValid(JwtToken token)
  {
    // from .parseClaimsJws() : ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;
    try
    {
      Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token.getAccessToken());
      return true;
    }
    catch( ExpiredJwtException e )
    {
      throw new AuthenticationException("만료된 토큰입니다.");
    }
    catch( JwtException e )
    {
      throw new AuthenticationException("유효하지 않은 토큰입니다.");
    }
  }
}
