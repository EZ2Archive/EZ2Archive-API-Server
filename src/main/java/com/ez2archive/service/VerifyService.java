package com.ez2archive.service;

import com.ez2archive.common.exception.auth.AuthenticationException;
import com.ez2archive.common.exception.business.BadRequestException;
import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.RequestTimeoutException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.common.handler.crypt.PasswordCryptHandler;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.dto.auth.RequestPasswordModifyDTO;
import com.ez2archive.entity.Email;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.PasswordModifyToken;
import com.ez2archive.repository.EmailRepository;
import com.ez2archive.repository.MemberRepository;
import com.ez2archive.repository.PasswordModifyTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VerifyService
{
  private final EmailRepository emailRepository;
  private final PasswordModifyTokenRepository passwordModifyTokenRepository;
  private final MemberRepository memberRepository;

  private final EmailCryptHandler emailCryptHandler;
  private final PasswordCryptHandler passwordCryptHandler;

  private final EmailService emailService;

  private final Validator<RequestPasswordModifyDTO> passwordModifyValidator;

  private final SecureRandom sr;

  private final String frontContextPath;

  @Transactional
  public void sendVerifyMail(String address)
  {
    String encAddress = emailCryptHandler.encode(address);

    Email findEmail = emailRepository.findEmailByAddress(encAddress)
      .orElseThrow( () -> new ResourceNotFoundException("저장된 이메일 정보가 존재하지 않습니다.") );

    if( findEmail.isVerify() ) throw new BadRequestException("이미 인증이 완료된 이메일입니다.");

    URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/verify/" + encAddress)
      .build().toUri();

    findEmail.setExpireTime(Email.getExpireTime(LocalDateTime.now()));

    emailService.send(address, "인증을 완료해주세요." , "아래의 링크를 클릭하여 인증을 완료하세요.\n\n" + uri);
  }

  @Transactional
  public void verifyEmail(String key)
  {
    Email findEmail = emailRepository.findEmailByAddress(key)
      .orElseThrow( () -> new BadRequestException("잘못된 이메일 인증 요청입니다.") );

    if( findEmail.isVerify() ) throw new BadRequestException("이미 인증된 이메일입니다.");

    if( LocalDateTime.now().isAfter( findEmail.getExpireTime() ) ) throw new RequestTimeoutException();

    findEmail.setVerify(true);
  }

  /**
   * 패스워드 재설정을 위한 이메일 발송
   * @param userId 사용자로부터 입력받은 아이디
   * @param email 사용자로부터 입력받은 이메일
   */
  @Transactional
  public void passwordRe(String userId, String email)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new AuthenticationException("해당 사용자의 이메일이 일치하지 않습니다.") );

    String expectedAddress = emailCryptHandler.encode(email);
    String actualAddress = findMember.getEmail().getAddress();

    if( !expectedAddress.equals(actualAddress) ) throw new AuthenticationException("해당 사용자의 이메일이 일치하지 않습니다.");

    String token = emailCryptHandler.encode(userId + email);

    // 임시 패스워드 재설정 토큰 생성
    PasswordModifyToken passwordModifyToken = passwordModifyTokenRepository.findById(token)
      .orElseGet( () -> PasswordModifyToken.builder()
        .userId(userId)
        .token(token)
        .addTime(LocalDateTime.now())
        .build()
      );

    // 이메일 발송
    URI uri = UriComponentsBuilder.fromPath(frontContextPath)
      .path("/password/" + token)
      .build().toUri();
    emailService.send(email, "패스워드 재설정", "아래의 링크를 클릭하면 비밀번호 재설정 페이지로 이동됩니다.\n\n" + uri);

    passwordModifyTokenRepository.save(passwordModifyToken);
//    throw new UnsupportedOperationException("아직 구현되지 않은 기능입니다.");
  }

  @Transactional
  public void verifyPassword(RequestPasswordModifyDTO dto)
  {
    if( !passwordModifyValidator.isValidWithTrim(dto) ) throw new IllegalValueException("잘못된 요청 양식입니다.");

    PasswordModifyToken findToken = passwordModifyTokenRepository.findById(dto.getToken())
      .orElseThrow( () -> new BadRequestException("잘못된 패스워드 재설정 요청입니다.") );

    Member findMember = memberRepository.findByUserId( findToken.getUserId() )
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    if( LocalDateTime.now().isAfter( findToken.getExpireTime() ) ) throw new RequestTimeoutException();

    final long newSalt = sr.nextLong();
    final String encPassword = passwordCryptHandler.encode(dto.getPassword(), newSalt);

    findMember.setPassword(encPassword);
    findMember.setSalt(newSalt);

    memberRepository.save(findMember);
    passwordModifyTokenRepository.delete(findToken);
  }
}
