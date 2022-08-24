package com.ez2archive.common.aspect;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.exception.auth.AuthorizationException;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.MemberAuthority;
import com.ez2archive.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@SuppressWarnings("JavadocDeclaration")
@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect
{
  private final TokenProvider<String, JwtToken> tokenProvider;
  private final MemberService memberService;

  @Pointcut("@annotation(com.ez2archive.common.aspect.RequiredToken)")
  public void requiredToken(){}

  @Pointcut("@annotation(com.ez2archive.common.aspect.RequiredAuthority)")
  public void requiredAuthority(){}

  /**
   * 사용자 토큰 aop
   * @return 넘겨받은 파라미터에 JwtToken 클래스가 존재할 경우, 유효성을 확인하고 인스턴스를 심어 보내준다.
   * @throws Throwable
   */
  @Around("requiredToken()")
  public Object requiredTokenAround(ProceedingJoinPoint joinPoint) throws Throwable
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

    return joinPoint.proceed(joinPoint.getArgs());
  }

  /**
   * 사용자 권한 AOP
   * @return
   * @throws Throwable
   */
  @Around("requiredAuthority()")
  public Object requiredAuthorityAround(ProceedingJoinPoint joinPoint) throws Throwable
  {
    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

    final JwtToken token = tokenProvider.getToken(request);

    tokenProvider.isValid(token);

    final String userId = tokenProvider.getIdFromToken(token);

    final Member findMember = memberService.findByUserId(userId);

    MemberAuthority authority = ((MethodSignature)joinPoint
      .getSignature())
      .getMethod()
      .getAnnotation(RequiredAuthority.class)
      .authority();

    if( authority != findMember.getAuthority() ) throw new AuthorizationException("해당 요청을 수행하기 위한 권한이 충분하지 않습니다.");

    return joinPoint.proceed(joinPoint.getArgs());
  }
}
