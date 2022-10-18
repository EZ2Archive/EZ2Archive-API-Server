package com.ez2archive.service;

import com.ez2archive.common.auth.JwtToken;
import com.ez2archive.common.auth.TokenProvider;
import com.ez2archive.common.exception.auth.AuthenticationException;
import com.ez2archive.common.exception.business.DuplicateUniqueValueException;
import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.common.handler.crypt.PasswordCryptHandler;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.dto.auth.RequestLoginDTO;
import com.ez2archive.dto.auth.RequestSignUpDTO;
import com.ez2archive.entity.*;
import com.ez2archive.repository.EmailRepository;
import com.ez2archive.repository.LoginHistoryRepository;
import com.ez2archive.repository.MemberRepository;
import com.ez2archive.repository.RefreshTokenRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService
{
  private final MemberRepository memberRepository;
  private final LoginHistoryRepository loginHistoryRepository;
  private final EmailRepository emailRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  private final Validator<RequestSignUpDTO> signUpValidator;
  private final Validator<RequestLoginDTO> loginValidator;

  private final PasswordCryptHandler passwordCryptHandler;
  private final EmailCryptHandler emailCryptHandler;

  private final TokenProvider<String, JwtToken> tokenProvider;

  private final SecureRandom sr;



  /**
   * 회원가입
   */
  @Transactional
  public void sinUp(RequestSignUpDTO dto)
  {
    // 사용자 양식 유효성 검사
    if( !signUpValidator.isValidWithTrim(dto) ) throw new IllegalValueException("잘못된 사용자 양식의 요청입니다.");

    final long salt =  sr.nextLong();
    final String encAddress = emailCryptHandler.encode(dto.getEmail());

    if ( memberRepository.findByUserId(dto.getUserId()).isPresent() )
      throw new DuplicateUniqueValueException("이미 존재하는 사용자입니다.");
    if( emailRepository.findEmailByAddress(encAddress).isPresent() )
      throw new DuplicateUniqueValueException("이미 사용중인 이메일입니다.");

    Email email = Email.builder()
      .address(encAddress)
      .verify(false)
      .addTime(LocalDateTime.now())
      .build();

    Member member = Member.builder()
      .userId(dto.getUserId())
      .name(dto.getName())
      .password( passwordCryptHandler.encode(dto.getPassword(), salt) )
      .salt(salt)
      .authority(MemberAuthority.REGULAR)
      .email(email)
      .addTime(LocalDateTime.now())
      .build();

    memberRepository.save(member);
  }

  /**
   * 로그인
   */
  @Transactional
  public JwtToken login(HttpServletRequest request, RequestLoginDTO requestLoginDTO) throws InterruptedException
  {
    JwtToken jwtToken;

    Thread.sleep(1500);

    if( !loginValidator.isValidWithTrim(requestLoginDTO) ) throw new IllegalValueException("잘못된 요청의 양식입니다.");

    try
    {
      // 헤더에 이미 사용가능한 토큰정보가 존재하는가?
      jwtToken = tokenProvider.getToken(request);

      tokenProvider.isValid(jwtToken);

      if( !requestLoginDTO.getUserId().equals(tokenProvider.getIdFromToken(jwtToken)) ) throw new AuthenticationException("토큰의 사용자 아이디가 일치하지 않습니다.");
    }
    catch( AuthenticationException | JwtException e )
    {
      // 헤더에 토큰이 없을 경우 로그인을 시도한다.

      final String userId = requestLoginDTO.getUserId();
      final String password = requestLoginDTO.getPassword();

      boolean isSucceed = false;

      try
      {
        Member findMember = memberRepository.findByUserId(userId)
          .orElseThrow( () -> new AuthenticationException("사용자 아이디 혹은 패스워드가 일치하지 않습니다.") );

        if( !findMember.getEmail().isVerify() ) throw new AuthenticationException("이메일 인증이 완료되지 않았습니다.");

        final String realPwd  = findMember.getPassword();
        final Long   salt     = findMember.getSalt();

        if( !realPwd.equals(passwordCryptHandler.encode(password, salt)) ) throw new AuthenticationException("사용자 아이디 혹은 패스워드가 일치하지 않습니다.");

        String accessToken = tokenProvider.issueAccessToken(userId);
        String refreshToken = tokenProvider.issueRefreshToken(userId);

        refreshTokenRepository.save(
          RefreshToken.builder()
            .userId(userId)
            .refreshToken(refreshToken)
            .build()
        );

        jwtToken = JwtToken.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .build();

        isSucceed = true;
      }
      finally
      {
        // 로그인 시도 성공여부를 따지지 않고, 모든 로그인 시도를 기록
        final LoginHistory loginHistory = new LoginHistory();
        loginHistory.setUserId(userId);
        loginHistory.setAgent( request.getHeader("User-Agent") );
        loginHistory.setIpAddress( request.getRemoteAddr() );
        loginHistory.setSucceed(isSucceed);
        loginHistory.setAddTime(LocalDateTime.now());

        loginHistoryRepository.save(loginHistory);
      }

    }

    return jwtToken;
  }

  public boolean isExistUserId(String userId)
  {
    return memberRepository.findByUserId(userId).isPresent();
  }

}
