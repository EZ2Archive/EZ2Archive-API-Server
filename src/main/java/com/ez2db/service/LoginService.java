package com.ez2db.service;

import com.ez2db.common.auth.JwtToken;
import com.ez2db.common.auth.TokenProvider;
import com.ez2db.common.exception.auth.AuthenticationException;
import com.ez2db.common.exception.business.IllegalValueException;
import com.ez2db.common.handler.crypt.PasswordCryptHandler;
import com.ez2db.common.validator.Validator;
import com.ez2db.entity.Member;
import com.ez2db.entity.MemberAuthority;
import com.ez2db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService
{
  private final MemberRepository repository;

  private final Validator<Member> validator;

  private final PasswordCryptHandler pwdHandler;

  private final TokenProvider<String, JwtToken> tokenProvider;

  private final SecureRandom sr;

  public Member sinUp(Member member)
  {
    final long salt =  sr.nextLong();

    Optional<Member> findMember = repository.findByUserId(member.getUserId());

    if(findMember.isPresent()) throw new AuthenticationException("이미 존재하는 사용자입니다.");

    member.setPassword( pwdHandler.encode(member.getPassword(), salt) );
    member.setSalt(salt);
    member.setAuthority(MemberAuthority.REGULAR);
    member.setAddTime(LocalDateTime.now());

    // 사용자 필드 유효성 검사
    if( !validator.isValidate(member) ) throw new IllegalValueException("잘못된 사용자 양식의 요청입니다.");

    return repository.save(member);
  }

  public JwtToken login(String userId, String password)
  {
    Optional<Member> findMember = repository.findByUserId(userId);

    if(!findMember.isPresent()) throw new AuthenticationException("사용자 아이디가 존재하지 않습니다.");

    final String realPwd  = findMember.get().getPassword();
    final Long   salt     = findMember.get().getSalt();

    if( !realPwd.equals(pwdHandler.encode(password, salt)) ) throw new AuthenticationException("사용자 패스워드가 일치하지 않습니다.");

    return tokenProvider.issue(userId);
  }

  public boolean isExistUserId(String userId)
  {
    Optional<Member> findMember = repository.findByUserId(userId);

    return findMember.isPresent();
  }
}
