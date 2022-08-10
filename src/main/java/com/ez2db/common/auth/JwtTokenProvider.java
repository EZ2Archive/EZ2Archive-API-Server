package com.ez2db.common.auth;

import com.ez2db.common.exception.auth.AuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtTokenProvider implements TokenProvider<String, JwtToken>
{
  @Value("${ez2db.security.jwt.secret-key}")
  private String SECRET_KEY;

  public static final String AUTH_IDENTIFIER = "Authorization";
  public static final String TOKEN_TYPE = "Bearer";

  private static final int EXPIRED_TIME = 2;

  @Override
  public JwtToken getToken(HttpServletRequest request)
  {
    // 요청 파라미터(-header 'Authorization: {type} {key}')
    final JwtToken result;

    final String [] auths;

    try
    {
      auths = request.getHeader(AUTH_IDENTIFIER).split(" ");

      result = new JwtToken(auths[0], auths[1]);
    }
    catch(NullPointerException | ArrayIndexOutOfBoundsException e)
    {
      throw new AuthenticationException("인증 타입 혹은 토큰 정보가 누락되었습니다.");
    }

    return result;
  }

  @Override
  public JwtToken issue(String userId)
  {
    final LocalDateTime now = LocalDateTime.now();
    final LocalDateTime expired = now.plus(EXPIRED_TIME, ChronoUnit.HOURS);

    final String token = Jwts.builder()
      .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
      .setSubject(userId)
      .setIssuedAt( Date.from(now.atZone(ZoneId.systemDefault()).toInstant()) )
      .setExpiration( Date.from(expired.atZone(ZoneId.systemDefault()).toInstant()) )
      .compact();

    return new JwtToken(TOKEN_TYPE, token);
  }

  @Override
  public String getIdFromToken(JwtToken token)
  {
    final Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token.getToken())
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
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token.getToken());
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
