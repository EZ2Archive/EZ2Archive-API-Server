package com.ez2db.common.aspect;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Aspect
@Component
@RequiredArgsConstructor
public class RequiredTokenAspect
{
  private final TokenProvider<String, JwtToken> tokenProvider;

  private static final String TOKEN_AUTH_POINTCUT = "@annotation(com.ez2db.common.auth.RequiredToken)";

  @Pointcut(value = TOKEN_AUTH_POINTCUT)
  public void tokenAuth(){}

  @Around("tokenAuth()")
  public Object tokenAuthAround(ProceedingJoinPoint joinPoint) throws Throwable
  {
    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

    JwtToken token = tokenProvider.getToken(request);


    switch( token.getType().toLowerCase(Locale.ROOT) )
    {
      case "bearer":
        break;
      default:
        throw new UnsupportedOperationException("지원되지 않는 토큰 인증 타입입니다.");
    }

    tokenProvider.isValid(token);

    Object [] args = joinPoint.getArgs();

    for ( int i = 0; i < args.length; i++ )
    {
      if(args[i] instanceof JwtToken)
      {
        args[i] = token;
        i = args.length;
      }
    }

//    return joinPoint.proceed( new Object[]{ token } );

    return joinPoint.proceed(joinPoint.getArgs());
  }
}
