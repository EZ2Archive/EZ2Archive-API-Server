package com.ez2archive.service;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.exception.auth.AuthenticationException;
import com.ez2archive.common.exception.business.DuplicateUniqueValueException;
import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.handler.crypt.PasswordCryptHandler;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.LoginHistory;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.MemberAuthority;
import com.ez2archive.repository.LoginHistoryRepository;
import com.ez2archive.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService
{
  private final MemberRepository memberRepository;
  private final LoginHistoryRepository loginHistoryRepository;

  private final Validator<Member> memberValidator;

  private final PasswordCryptHandler passwordCryptHandler;

  private final TokenProvider<String, JwtToken> tokenProvider;

  private final SecureRandom sr;

  public void sinUp(Member member)
  {
    final long salt =  sr.nextLong();

    memberRepository.findByUserId(member.getUserId())
      .orElseThrow( () -> new DuplicateUniqueValueException("이미 존재하는 사용자입니다.") );

    member.setPassword( passwordCryptHandler.encode(member.getPassword(), salt) );
    member.setSalt(salt);
    member.setAuthority(MemberAuthority.REGULAR);
    member.setAddTime(LocalDateTime.now());

    // 사용자 필드 유효성 검사
    if( !memberValidator.isValidWithTrim(member) ) throw new IllegalValueException("잘못된 사용자 양식의 요청입니다.");

    memberRepository.save(member);

  }

  public JwtToken login(HttpServletRequest request, String userId, String password)
  {
    final JwtToken jwtToken;

    boolean isSucceed = false;

    try
    {
      Member findMember = memberRepository.findByUserId(userId)
        .orElseThrow( () -> new AuthenticationException("사용자 아이디 혹은 패스워드가 일치하지 않습니다.") );

      final String realPwd  = findMember.getPassword();
      final Long   salt     = findMember.getSalt();

      if( !realPwd.equals(passwordCryptHandler.encode(password, salt)) ) throw new AuthenticationException("사용자 아이디 혹은 패스워드가 일치하지 않습니다.");

      jwtToken = tokenProvider.issue(userId);

      isSucceed = true;
    }
    finally
    {
      final LoginHistory loginHistory = new LoginHistory();
      loginHistory.setUserId(userId);
      loginHistory.setAgent( request.getHeader("User-Agent") );
      loginHistory.setIpAddress( request.getRemoteAddr() );
      loginHistory.setSucceed(isSucceed);
      loginHistory.setAddTime(LocalDateTime.now());

      loginHistoryRepository.save(loginHistory);
    }

    return jwtToken;
  }

  public boolean isExistUserId(String userId)
  {
    return memberRepository.findByUserId(userId).isPresent();
  }

}
